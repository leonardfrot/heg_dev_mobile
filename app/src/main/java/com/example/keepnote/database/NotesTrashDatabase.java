package com.example.keepnote.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.keepnote.dao.NoteDAO;
import com.example.keepnote.dao.NoteTrashDAO;
import com.example.keepnote.entities.Note;
import com.example.keepnote.entities.NoteTrash;

@Database(entities = NoteTrash.class, version = 2, exportSchema = false)
public abstract class NotesTrashDatabase extends RoomDatabase {

    private static com.example.keepnote.database.NotesTrashDatabase notesTrashDatabase;

    public static synchronized com.example.keepnote.database.NotesTrashDatabase getDatabase(Context context){
        if(notesTrashDatabase == null){
            notesTrashDatabase = Room.databaseBuilder(
                    context,
                    com.example.keepnote.database.NotesTrashDatabase.class,
                    "notesTrash_db"
            ).build();
        }
        return notesTrashDatabase;
    }

    public abstract NoteTrashDAO noteTrashDao();
}
