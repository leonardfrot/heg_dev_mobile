package com.example.keepnote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//test1
public class MainActivity extends AppCompatActivity {

    static List<String> notes = new ArrayList<String>();
    //static List<String> arrayAdapter;
    static ArrayAdapter<String> mListAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater(); //Création du menu en haut à droite
        menuInflater.inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) //Permet de nous placer dans la création d'une note lors de la sélection ajouter note dans le menu
    {
        super.onOptionsItemSelected(item);

        if(item.getItemId() == R.id.add_note)
        {
            Intent intent = new Intent(getApplicationContext(), NoteEditorActivity.class);
            startActivity(intent);
            return true;
        }

        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView)findViewById(R.id.listView);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.tanay.thunderbird.deathnote", Context.MODE_PRIVATE);
        HashSet<String> hashSet = (HashSet<String>)sharedPreferences.getStringSet("notes", null);

        if(hashSet == null)
        {
            notes.add("Exemple de note");
        }

        else
        {
            notes = new ArrayList<>(hashSet);         // to bring all the already stored data in the set to the notes ArrayList
        }

        mListAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, notes);
        listView.setAdapter(mListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(getApplicationContext(), NoteEditorActivity.class); //Création de l'intent
                intent.putExtra("noteID", position);  //sert à nous dire quelle ligne est sélectionnée
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {  //lors d'un appuie long, pour lancer la méthode de confirmation effacement de la note
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) //grâce aux paramètre, on sait sur quelle note on a appuyer
            {
                new AlertDialog.Builder(MainActivity.this)                   // we can't use getApplicationContext() here as we want the activity to be the context, not the application
                        .setTitle("Effacer?")
                        .setMessage("Voulez-vous confirmer l'effacement de la note ?")
                        .setIcon(android.R.drawable.ic_delete)
                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which)                        //Efface la note sur laquelle on avait cliquée
                            {
                                notes.remove(position); //efface note selon sa position
                                mListAdapter.notifyDataSetChanged(); // Mise à jours de notre liste sans la note effacée

                                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.tanay.thunderbird.deathnote", Context.MODE_PRIVATE); //enregistrement dans notre BD de notre nouvelle liste
                                HashSet<String> set = new HashSet<>(MainActivity.notes);
                                sharedPreferences.edit().putStringSet("notes", set).apply();
                            }
                        })

                        .setNegativeButton("Non", null) // Si on appuie sur "non", on ne fait rien
                        .show();

                return true;               // this was initially false but we change it to true as if false, the method assumes that we want to do a short click after the long click as well
            }
        });
    }
}