package com.example.keepnote.listeners;

import com.example.keepnote.entities.NoteTrash;

public interface NotesTrashListener {
    void onNoteTrashClicked(NoteTrash noteTrash, int position);
}
