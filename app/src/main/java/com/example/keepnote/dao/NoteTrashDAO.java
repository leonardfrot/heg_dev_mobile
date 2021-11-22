package com.example.keepnote.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.keepnote.entities.NoteTrash;

import java.util.List;

@Dao
public interface NoteTrashDAO {

    @Query("SELECT * FROM notesTrash ORDER BY id DESC")
    List<NoteTrash> getAllNotes();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(NoteTrash noteTrash);


    @Delete
    void deleteNote(NoteTrash noteTrash);
}
