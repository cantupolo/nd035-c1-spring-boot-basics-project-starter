package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private NoteService noteService;

    private CredentialService credentialService;

    private FileService fileService;

    private UserService userService;

    public HomeController(UserService userService,
                          NoteService noteService, CredentialService credentialService, FileService fileService) {
        this.userService = userService;
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.fileService = fileService;
    }

    @GetMapping()
    public String view(Model model,
                       @RequestParam(name = "activeTab", required = false, defaultValue = "files")
                                   String activeTab) {
        User user = getLoggedUser();
        loadViewData(model, user, activeTab);
        return "home";
    }

    @PostMapping("/note/save")
    public String noteSave(Note note, Model model) {
        User user = getLoggedUser();
        note.setUserId(user.getId());
        noteService.saveNote(note);
        loadViewData(model, user, "notes");
        model.addAttribute("successMsg", "true");
        model.addAttribute("activeTab", "notes");
        return "result";
    }

    @GetMapping("/note/delete/{id}")
    public String noteDelete(@PathVariable Integer id, Model model) {
        noteService.deleteNote(id);
        User user = getLoggedUser();
        loadViewData(model, user, "notes");
        model.addAttribute("successMsg", "true");
        model.addAttribute("activeTab", "notes");
        return "result";
    }

    @PostMapping("/credential/save")
    public String credentialSave(Credential credential, Model model) {
        User user = getLoggedUser();
        credential.setUserId(user.getId());
        credentialService.saveCredential(credential);
        loadViewData(model, user, "credentials");
        model.addAttribute("successMsg", "true");
        model.addAttribute("activeTab", "credentials");
        return "result";
    }

    @GetMapping("/credential/delete/{id}")
    public String credentialDelete(@PathVariable Integer id, Model model) {
        credentialService.deleteCredential(id);
        User user = getLoggedUser();
        loadViewData(model, user, "credentials");
        model.addAttribute("successMsg", "true");
        model.addAttribute("activeTab", "credentials");
        return "result";
    }

    @GetMapping("/file/delete/{id}")
    public String fileDelete(@PathVariable Integer id, Model model) {
        fileService.deleteFile(id);
        User user = getLoggedUser();
        loadViewData(model, user, "files");
        model.addAttribute("successMsg", "true");
        model.addAttribute("activeTab", "files");
        return "result";
    }

    @PostMapping("/file/insert")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile file, RedirectAttributes attributes) {
        if (file.isEmpty()) {
            attributes.addAttribute("errorMsg", "Please select a file to upload.");
            attributes.addAttribute("activeTab", "files");
            return "redirect:/result";
        }

        // normalize the file path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        // check if the selected file is already stored.
        if (fileService.isFileAlreadyStored(fileName)) {
            attributes.addAttribute("errorMsg", "This file is already stored. Please select a different file to upload.");
            attributes.addAttribute("activeTab", "files");
            return "redirect:/result";
        }

        // save the file on the database
        try {
            File dbFile = new File();
            dbFile.setFileName(fileName);
            dbFile.setFileSize(String.valueOf(file.getSize()));
            dbFile.setFileData(file.getBytes());
            dbFile.setContentType(file.getContentType());

            User user = getLoggedUser();
            dbFile.setUserId(getLoggedUser().getId());

            fileService.insertFile(dbFile);

            attributes.addAttribute("successMsg", "true");
            attributes.addAttribute("activeTab", "files");
            return "redirect:/result";

        } catch (IOException e) {
            attributes.addAttribute("errorMsg", "Error on file upload: " + e.getMessage());
            attributes.addAttribute("activeTab", "files");
            return "redirect:/result";
        }
    }

    @GetMapping("/file/download/{id}")
    public void downloadFile(@PathVariable Integer id, Model model, HttpServletResponse response) throws IOException {
        File file = fileService.getFile(id);
        if (file != null) {
            response.setContentType("application/octet-stream");
            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename = " + file.getFileName();
            response.setHeader(headerKey, headerValue);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(file.getFileData());
            outputStream.close();
        }
    }

    private void loadViewData(Model model, User user, String activeTab) {
        List<Note> notes = noteService.getNotes(user.getId());
        model.addAttribute("notes", notes);
        List<File> fileList = fileService.getFilesWithoutContent(user.getId());
        model.addAttribute("fileList", fileList);
        List<Credential> credentials = credentialService.getCredentials(user.getId());
        model.addAttribute("credentials", credentials);

        model.addAttribute("note", new Note());
        model.addAttribute("credential", new Credential());

        model.addAttribute(activeTab + "TabActive", true);
    }

    private User getLoggedUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUser(userName);
        return user;
    }

}
