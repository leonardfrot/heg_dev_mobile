package com.example.keepnote.listeners;


// cette classe est pour cliquer sur les images.
import com.example.keepnote.entities.Note;

public interface NotesListener {
    void onNoteClicked(Note note, int position);
}
