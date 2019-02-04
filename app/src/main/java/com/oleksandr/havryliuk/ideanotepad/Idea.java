package com.oleksandr.havryliuk.ideanotepad;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "ideas")
public class Idea {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "text")
    private String text;

    @ColumnInfo(name = "id")
    private String category;

    @NonNull
    @ColumnInfo(name = "date")
    private int date;
}
