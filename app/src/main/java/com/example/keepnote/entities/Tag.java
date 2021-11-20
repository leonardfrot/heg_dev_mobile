package com.example.keepnote.entities;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tag")
public class Tag implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="title")
    private String title;

    // reference à l'entité parent
    @ColumnInfo(name="noteCreatorId")
    public int noteCreatorId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNoteCreatorId() {
        return noteCreatorId;
    }

    public void setNoteCreatorId(int noteCreatorId) {
        this.noteCreatorId = noteCreatorId;
    }

    @NonNull
    @Override
    public String toString() {
        return this.title;}
}
