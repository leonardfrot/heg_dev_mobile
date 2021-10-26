package com.example.keepnote;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;

public class NoteEditorActivity extends AppCompatActivity {

    int noteID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        EditText editText = (EditText) findViewById(R.id.editText);
        Intent intent = getIntent();
        noteID = intent.getIntExtra("noteID", -1); //-1 pour éviter qu'un nombre aléatoire ne soit transmis à noteID

        if (noteID != -1) {
            editText.setText(MainActivity.notes.get(noteID));  //récupératoin de notre ID
        } else {
            MainActivity.notes.add("");                // initialisation de la note vide
            noteID = MainActivity.notes.size() - 1;
            MainActivity.mListAdapter.notifyDataSetChanged(); //Mise à jour de la liste avec les dernières modif
        }

        editText.addTextChangedListener(new TextWatcher() { //Pour mettre à jour le texte dans la note lors d'une modif
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //TextWatcher() oblige la définition de cette méthode mais on ne fait rien
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.notes.set(noteID, String.valueOf(s));  //Sélectionner la note qui est en cours de modif
                MainActivity.mListAdapter.notifyDataSetChanged();   //Mise à jour de la liste avec les dernières modif

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.tanay.thunderbird.deathnote", Context.MODE_PRIVATE); //Enregistrer les nouvelle données dans notre BD
                HashSet<String> set = new HashSet<>(MainActivity.notes);
                sharedPreferences.edit().putStringSet("notes", set).apply();
            }

            @Override
            public void afterTextChanged(Editable s) {
                //TextWatcher() oblige la définition de cette méthode mais on ne fait rien
            }

        });
    }

}