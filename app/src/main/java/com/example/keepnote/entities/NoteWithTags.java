package com.example.keepnote.entities;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class NoteWithTags {
    @Embedded
    public Note note;

    @Relation(
            parentColumn = "id",
            entityColumn = "noteCreatorId"
    )

    public List<Tag> tags;


}

