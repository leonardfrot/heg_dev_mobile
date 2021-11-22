package com.example.keepnote.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.keepnote.R;
import com.example.keepnote.adapters.NotesTrashAdapter;
import com.example.keepnote.database.NotesDatabase;
import com.example.keepnote.database.NotesTrashDatabase;
import com.example.keepnote.entities.Note;
import com.example.keepnote.entities.NoteTrash;
import com.example.keepnote.listeners.NotesTrashListener;

import java.util.ArrayList;
import java.util.List;

public class TrashActivity extends AppCompatActivity implements NotesTrashListener {

    private RecyclerView notesTrashRecyclerView;
    private List<Note> noteList;
    private List<NoteTrash> noteTrashList;
    private NotesTrashAdapter notesTrashAdapter;
    private Note note;
    private AlertDialog dialogDeleteNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trash);

        ImageView imageBack = findViewById(R.id.imageTrashBack);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        notesTrashRecyclerView = findViewById(R.id.notesTrashRecyclerView);
        notesTrashRecyclerView.setLayoutManager(
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        );

        noteList = new ArrayList<>();
        noteTrashList = new ArrayList<>();
        notesTrashAdapter = new NotesTrashAdapter(noteTrashList, this);
        notesTrashRecyclerView.setAdapter(notesTrashAdapter);


        @SuppressLint("StaticFieldLeak")
        class GetNotesTask extends AsyncTask<Void, Void, List<NoteTrash>> {

            @Override
            protected List<NoteTrash> doInBackground(Void... voids) {
                return NotesTrashDatabase.getDatabase(getApplicationContext()).noteTrashDao().getAllNotes();
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void onPostExecute(List<NoteTrash> notes) {
                super.onPostExecute(noteTrashList);
                noteTrashList.addAll(notes);
                notesTrashAdapter.notifyDataSetChanged();
            }
        }
        new GetNotesTask().execute();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onNoteTrashClicked(NoteTrash noteTrash, int position) {
        if (dialogDeleteNote == null){
            AlertDialog.Builder builder = new AlertDialog.Builder(TrashActivity.this);
            View view = LayoutInflater.from(this).inflate(
                    R.layout.layout_note_trash,
                    (ViewGroup)  findViewById(R.id.layoutNoteTrashContainer)
            );
            builder.setView(view);
            dialogDeleteNote = builder.create();
            if(dialogDeleteNote.getWindow() != null){
                dialogDeleteNote.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            }

            note = new Note();
            note.setId(noteTrash.getId());
            note.setTitle(noteTrash.getTitle());
            note.setDateTime(noteTrash.getDateTime());
            note.setSubtitle(noteTrash.getSubtitle());
            note.setNoteText(noteTrash.getNoteText());
            note.setImagePath(noteTrash.getImagePath());
            note.setColor(noteTrash.getColor());
            note.setWebLink(noteTrash.getWebLink());
            note.setAlertDate(noteTrash.getAlertDate());

            view.findViewById(R.id.textDeleteNote).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    @SuppressLint("StaticFieldLeak")
                    class DeleteNoteTask extends AsyncTask<Void, Void, Void>{

                        @Override
                        protected Void doInBackground(Void... voids) {
                            NotesTrashDatabase.getDatabase(getApplicationContext()).noteTrashDao()
                                    .deleteNote(noteTrash);
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void unused) {
                            super.onPostExecute(unused);
                            Intent intent = new Intent();
                            intent.putExtra("isNoteDeleted", true);
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    }

                    new DeleteNoteTask().execute();
                }
            });

            view.findViewById(R.id.textCancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogDeleteNote.dismiss();
                }
            });

            view.findViewById(R.id.textRecover).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                    @SuppressLint("StaticFieldLeak")
                    class SaveNoteTask extends AsyncTask<Void, Void, Void>{

                        @Override
                        protected Void doInBackground(Void... voids){
                            NotesDatabase.getDatabase(getApplicationContext()).noteDao().insertNote(note);
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid){
                            super.onPostExecute(aVoid);
                            Intent intent = new Intent();
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    }

                    new SaveNoteTask().execute();

                    @SuppressLint("StaticFieldLeak")
                    class DeleteNoteTask extends AsyncTask<Void, Void, Void>{

                        @Override
                        protected Void doInBackground(Void... voids) {
                            NotesDatabase.getDatabase(getApplicationContext()).noteDao()
                                    .deleteNote(note);
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void unused) {
                            super.onPostExecute(unused);
                            Intent intent = new Intent();
                            intent.putExtra("isNoteDeleted", true);
                            setResult(RESULT_OK, intent);
                            finish();
                        }
                    }

                    new DeleteNoteTask().execute();
                }
            });

            dialogDeleteNote.show();
        }
    }
}