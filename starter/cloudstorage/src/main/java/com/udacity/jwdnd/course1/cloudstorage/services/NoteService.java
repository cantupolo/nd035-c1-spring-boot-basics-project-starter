package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }

    public List<Note> getNotes(Integer userId) {
        return noteMapper.getNotes(userId);
    }

    public int saveNote(Note note) {
        if (note.getId() != null && note.getId() > 0) {
            noteMapper.update(note);
            return note.getId();
        } else {
            return noteMapper.insert(note);
        }
    }

    public void deleteNote(Integer noteId) {
        noteMapper.delete(noteId);
    }

}
