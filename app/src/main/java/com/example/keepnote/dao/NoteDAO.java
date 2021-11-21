package com.example.keepnote.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.keepnote.entities.Note;
import com.example.keepnote.entities.NoteWithTags;
import com.example.keepnote.entities.Tag;

import java.util.List;
import java.util.Map;

@Dao
public interface NoteDAO {

    @Query("SELECT * FROM notes ORDER BY id DESC")
    List<Note> getAllNotes();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(Note note);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllTags(List<Tag> tags);

    @Transaction
    @Query("SELECT * FROM notes WHERE title = :title")
    List<NoteWithTags> getNotesWithTags(String title);

    @Delete
    void deleteNote(Note note);
}
