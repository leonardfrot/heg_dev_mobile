package com.example.keepnote.entities;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

@Entity(tableName = "tag")
public class Tag implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="title")
    @NonNull
    private String title;

    // reference à l'entité parent
    @ColumnInfo(name="noteTitle")
    public String noteTitle;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return title.equals(tag.title) && Objects.equals(noteTitle, tag.noteTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, noteTitle);
    }
}
