package com.example.keepnote.adapters;

import android.annotation.SuppressLint;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.keepnote.R;
import com.example.keepnote.entities.NoteTrash;
import com.example.keepnote.listeners.NotesTrashListener;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class NotesTrashAdapter extends RecyclerView.Adapter<NotesTrashAdapter.NoteTrashViewHolder>{

    private List<NoteTrash> notesTrash;
    private NotesTrashListener notesTrashListener;
    private List<NoteTrash> notesTrashSource;


    public NotesTrashAdapter(List<NoteTrash> notes, NotesTrashListener notesTrashListener) {
        this.notesTrash = notes;
        this.notesTrashListener = notesTrashListener;
        notesTrashSource = notes;
    }

    // il permet de récupérer item container note
    @NonNull
    @Override
    public NotesTrashAdapter.NoteTrashViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesTrashAdapter.NoteTrashViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_note, parent, false)
        );
    }


    // il permet de cliquer sur les images
    @Override
    public void onBindViewHolder(@NonNull NoteTrashViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.setNoteTrash(notesTrash.get(position));
        holder.layoutNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notesTrashListener.onNoteTrashClicked(notesTrash.get(position), position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notesTrash.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    static class NoteTrashViewHolder extends RecyclerView.ViewHolder{

        TextView textTitle, textSubtitle, textDateTime, alertDateTime, deleteDateTime;
        LinearLayout layoutNote;
        RoundedImageView imageNote;

        NoteTrashViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textSubtitle = itemView.findViewById(R.id.textSubtitle);
            textDateTime = itemView.findViewById(R.id.textDateTime);
            layoutNote = itemView.findViewById(R.id.layoutNote); //depuis item_container_note
            imageNote = itemView.findViewById(R.id.imageNote);
            alertDateTime = itemView.findViewById(R.id.alertDateTime);
            deleteDateTime = itemView.findViewById(R.id.deleteDateTime);

        }

        // cette méthdoe permet d'afficher les informations sauvegardés dans les note, notamment l'image
        void setNoteTrash(NoteTrash note){
            textTitle.setText(note.getTitle());
            if(note.getSubtitle().trim().isEmpty()){
                textSubtitle.setVisibility(View.GONE);
            } else {
                textSubtitle.setText(note.getSubtitle());
            }
            textDateTime.setText("Created in: " + note.getDateTime());

            alertDateTime.setText("Do before: " + note.getAlertDate());

            GradientDrawable gradientDrawable = (GradientDrawable) layoutNote.getBackground();
            if (note.getColor() != null){
                gradientDrawable.setColor(Color.parseColor(note.getColor()));
            } else {
                gradientDrawable.setColor(Color.parseColor("#333333"));
            }
            if(note.getImagePath() != null){
                imageNote.setImageBitmap(BitmapFactory.decodeFile(note.getImagePath()));
                imageNote.setVisibility(View.VISIBLE);
            }else{
                imageNote.setVisibility(View.GONE);
            }
        }
    }
}