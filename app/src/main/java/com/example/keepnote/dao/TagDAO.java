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

    @Query("SELECT * FROM tag WHERE noteCreatorId == :id")
    List<Tag> getAllTagById(int id);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTag(Tag tag);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Tag> tags);

    @Delete
    void deleteNote(Tag tag);

}
