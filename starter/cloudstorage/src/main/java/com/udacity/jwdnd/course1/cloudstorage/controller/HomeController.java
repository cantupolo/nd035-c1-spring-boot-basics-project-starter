package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {

    private NoteService noteService;

    private UserService userService;

    public HomeController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @GetMapping()
    public String view(Note note, Model model) {
        User user = getLoggedUser();
        List<Note> notes = noteService.getNotes(user.getId());
        model.addAttribute("notes", notes);
        return "home";
    }

    @PostMapping("/note/save")
    public String noteSave(Note note, Model model) {
        User user = getLoggedUser();
        note.setUserId(user.getId());
        noteService.saveNote(note);
        List<Note> notes = noteService.getNotes(user.getId());
        model.addAttribute("notes", notes);
        return "home";
    }

    @GetMapping("/note/delete/{id}")
    public String noteDelete(@PathVariable Integer id, Model model) {
        noteService.deleteNote(id);
        User user = getLoggedUser();
        List<Note> notes = noteService.getNotes(user.getId());
        model.addAttribute("notes", notes);
        model.addAttribute("note", new Note());
        return "home";
    }

    private User getLoggedUser() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUser(userName);
        return user;
    }

}
