package com.example.keepnote.entities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "notes")
public class Note implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="title")
    private String title;

    @ColumnInfo(name="date_time")
    private String dateTime;

    @ColumnInfo(name="subtitle")
    private String subtitle;

    @ColumnInfo(name="note_text")
    private String noteText;

    @ColumnInfo(name="image_path")
    private String imagePath;

    @ColumnInfo(name="color")
    private String color;

    @ColumnInfo(name="web_link")
    private String webLink;

    @ColumnInfo(name="alert_date")
    private String alertDate;

    @ColumnInfo(name="delete_date")
    private boolean deleteDate;

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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getWebLink() {
        return webLink;
    }

    public void setWebLink(String webLink) {
        this.webLink = webLink;
    }

    @Nullable
    public String getAlertDate() {
        return alertDate;
    }

    public void setAlertDate(@Nullable String alertDate) {
        this.alertDate = alertDate;
    }

    public boolean getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(boolean deleteDate) {
        this.deleteDate = deleteDate;
    }

    @NonNull
    @Override
    public String toString() {
        return title + " : " + dateTime;
    }
}
