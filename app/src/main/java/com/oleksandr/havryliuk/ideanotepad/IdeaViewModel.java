package com.oleksandr.havryliuk.ideanotepad;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class IdeaViewModel extends AndroidViewModel {

    private LiveData<List<Idea>> ideas;
    private IdeasRepository repository;

    public IdeaViewModel(Application application){
        super(application);
        repository = IdeasRepository.getRepository(application);
        ideas = repository.getAllIdeas();
    }


    public LiveData<List<Idea>> getAllIdeas() {
        return ideas;
    }

    public void insert(Idea idea){
        repository.insert(idea);
    }
}
