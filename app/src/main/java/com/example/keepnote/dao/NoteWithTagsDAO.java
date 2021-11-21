package com.example.keepnote.dao;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.keepnote.entities.Note;
import com.example.keepnote.entities.NoteWithTags;
import com.example.keepnote.entities.Tag;

import java.util.List;
import java.util.Map;

@Dao
public interface NoteWithTagsDAO {

    // la méthode de sélection pour toutes les notes
    @Transaction
    @Query("SELECT * FROM notes "+
            "JOIN tag ON idNotes = noteCreatorId"
    )
    public List<NoteWithTags> getNoteWithTags();






}
