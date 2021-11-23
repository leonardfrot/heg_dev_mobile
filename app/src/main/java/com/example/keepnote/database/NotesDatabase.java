package com.example.keepnote.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.keepnote.dao.NoteDAO;
import com.example.keepnote.dao.NoteWithTagsDAO;
import com.example.keepnote.dao.TagDAO;
import com.example.keepnote.entities.Note;
import com.example.keepnote.entities.NoteWithTags;
import com.example.keepnote.entities.Tag;

@Database(entities = {Note.class, Tag.class}, version = 1, exportSchema = false)
public abstract class NotesDatabase extends RoomDatabase {

    private static NotesDatabase notesDatabase;

    public static synchronized NotesDatabase getDatabase(Context context){
        if(notesDatabase == null){
            notesDatabase = Room.databaseBuilder(
                    context,
                    NotesDatabase.class,
                    "notes_db"
            ).build();
        }
        return notesDatabase;
    }

    // il faut ajouter tous les DAO
    public abstract NoteDAO noteDao();

    public abstract NoteWithTagsDAO noteWithTagsDAO();

    public abstract TagDAO tagDAO();


}
