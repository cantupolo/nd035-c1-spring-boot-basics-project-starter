package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private NoteService noteService;

    private FileService fileService;

    private UserService userService;

    public HomeController(NoteService noteService, UserService userService, FileService fileService) {
        this.noteService = noteService;
        this.userService = userService;
        this.fileService = fileService;
    }

    @GetMapping()
    public String view(Note note, Model model, HttpServletRequest request) {
        User user = getLoggedUser();
        loadViewData(model, user);
        String errorMsg = request.getParameter("fileErrorMsg");
        if (errorMsg != null && !errorMsg.isEmpty()) {
            model.addAttribute("fileErrorMsg", errorMsg);
        }
        return "home";
    }

    @PostMapping("/note/save")
    public String noteSave(Note note, Model model) {
        User user = getLoggedUser();
        note.setUserId(user.getId());
        noteService.saveNote(note);
        loadViewData(model, user);
        return "home";
    }

    @GetMapping("/note/delete/{id}")
    public String noteDelete(@PathVariable Integer id, Model model) {
        noteService.deleteNote(id);
        User user = getLoggedUser();
        loadViewData(model, user);
        model.addAttribute("note", new Note());
        return "home";
    }

    @GetMapping("/file/delete/{id}")
    public String fileDelete(@PathVariable Integer id, Model model) {
        fileService.deleteFile(id);
        User user = getLoggedUser();
        loadViewData(model, user);
        model.addAttribute("note", new Note());
        return "home";
    }

    private void loadViewData(Model model, User user) {
        List<Note> notes = noteService.getNotes(user.getId());
        model.addAttribute("notes", notes);
        List<File> fileList = fileService.getFilesWithoutContent(user.getId());
        model.addAttribute("fileList", fileList);
        model.addAttribute("fileErrorMsg", "");
    }

    private User getLoggedUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUser(userName);
        return user;
    }

    @PostMapping("/file/insert")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile file, RedirectAttributes attributes) {
        if (file.isEmpty()) {
            attributes.addAttribute("fileErrorMsg", "Please select a file to upload.");
            return "redirect:/home";
        }

        // normalize the file path
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

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

        } catch (IOException e) {
            attributes.addAttribute("fileErrorMsg", "Error on file upload: " + e.getMessage());
            e.printStackTrace();
        }

        return "redirect:/home";
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
}
