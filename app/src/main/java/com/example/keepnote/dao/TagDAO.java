package com.example.keepnote.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.keepnote.entities.Note;
import com.example.keepnote.entities.Tag;

import java.util.List;
@Dao
public interface TagDAO {

    @Query("SELECT * FROM tag ORDER BY id DESC")
    List<Tag> getAllTags();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTag(Tag tag);

    @Delete
    void deleteNote(Tag tag);

}
