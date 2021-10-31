package com.example.keepnote.listeners;


import com.example.keepnote.entities.Note;

public interface NotesListener {
    void onNoteClicked(Note note, int position);
}
