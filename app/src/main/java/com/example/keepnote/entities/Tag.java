package com.example.keepnote.entities;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tag")
public class Tag implements Serializable {

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name="title")
    @NonNull
    private String title;

    // reference à l'entité parent
    @ColumnInfo(name="noteTitle")
    public String noteTitle;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    @NonNull
    @Override
    public String toString() {
        return this.noteTitle + this.title;}
}
