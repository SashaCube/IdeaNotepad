package com.oleksandr.havryliuk.ideanotepad;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

public class IdeaViewModel extends ViewModel {

    private LiveData<List<Idea>> ideas;
    private IdeasRepository repository;

    public IdeaViewModel(){
        repository = new IdeasRepository();
        ideas = repository.getAllIdeas();
    }


    public LiveData<List<Idea>> getAllIdeas() {
        return ideas;
    }
}
