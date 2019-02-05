package com.oleksandr.havryliuk.ideanotepad.repository;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface IdeaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Idea idea);

    @Update
    void update(Idea idea);

    @Query("SELECT * FROM ideas ORDER BY date DESC")
    LiveData<List<Idea>> getAllIdeas();

    @Query("SELECT * FROM ideas ORDER BY text")
    LiveData<List<Idea>>  getIdeasOrderByIdea();

    @Query("SELECT * FROM ideas ORDER BY category")
    LiveData<List<Idea>>  getIdeasOrderByCategory();

    @Query("DELETE FROM ideas")
    void  deleteAll();

    @Delete
    void delete(Idea idea);
}
