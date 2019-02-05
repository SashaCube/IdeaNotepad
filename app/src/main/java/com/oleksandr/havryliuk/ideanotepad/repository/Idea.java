package com.oleksandr.havryliuk.ideanotepad.repository;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.oleksandr.havryliuk.ideanotepad.utils.PreferenceManager;
import com.oleksandr.havryliuk.ideanotepad.utils.Utils;

import java.util.Date;

@Entity(tableName = "ideas")
public class Idea {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "text")
    private String text;

    @ColumnInfo(name = "category")
    private String category;


    @ColumnInfo(name = "color")
    private String color;

    @NonNull
    @ColumnInfo(name = "date")
    private long date;

    public Idea(@NonNull String text, String category, long date) {
        this.text = text;
        this.category = category;
        this.date = date;

        color = PreferenceManager.getString(category);
        if(color == null) {
            this.color = Utils.getRandomColor();
            PreferenceManager.setString(category, color);
        }
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    @NonNull
    public String getText() {
        return text;
    }

    public void setText(@NonNull String text) {
        this.text = text;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @NonNull
    public long getDate() {
        return date;
    }

    public void setDate(@NonNull long date) {
        this.date = date;
    }
}
