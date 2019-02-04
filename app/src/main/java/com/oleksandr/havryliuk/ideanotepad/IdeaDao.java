package com.oleksandr.havryliuk.ideanotepad;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface IdeaDao {

    @Insert
    void insert(Idea idea);

    @Query("DELETE FROM ideas")
    void deleteAll();

    @Query("SELECT * FROM ideas ORDER BY date DESC")
    LiveData<List<Idea>> getAllIdeas();
}
