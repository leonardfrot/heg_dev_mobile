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
import com.example.keepnote.adapters.NotesAdapter;
import com.example.keepnote.database.NotesDatabase;
import com.example.keepnote.entities.Note;
import com.example.keepnote.listeners.NotesListener;

import java.util.ArrayList;
import java.util.List;

public class TrashActivity extends AppCompatActivity implements NotesListener {

    private RecyclerView notesTrashRecyclerView;
    private List<Note> noteList;
    private List<Note> noteTrashList;
    private NotesAdapter notesAdapter;

    private AlertDialog dialogDeleteNote;

    private Note alreadyAvailableNote;
    private CreateNoteActivity CreateNoteActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trash);

        ImageView imageBack = findViewById(R.id.imageBack);
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
        notesAdapter = new NotesAdapter(noteList, this);
        notesTrashRecyclerView.setAdapter(notesAdapter);

        //@SuppressLint("StaticFieldLeak")
        class GetNotesTask extends AsyncTask<Void, Void, List<Note>> {

            @Override
            protected List<Note> doInBackground(Void... voids) {
                return NotesDatabase.getDatabase(getApplicationContext()).noteDao().getAllNotes();
            }

            @Override
            protected void onPostExecute(List<Note> notes) {
                noteList.addAll(notes);
                for (Note note : noteList) {
                    if (note.getDeleteDate().equals(true)) {
                        noteTrashList.add(note);
                    }
                }
                super.onPostExecute(noteTrashList);
                notesAdapter.notifyDataSetChanged();
            }
        }
        new GetNotesTask().execute();
    }

    @Override
    public void onNoteClicked(Note note, int position) {

        @SuppressLint("StaticFieldLeak")
        class DeleteNote extends AsyncTask<Void, Void, Void>{

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

        new DeleteNote().execute();
    }
}