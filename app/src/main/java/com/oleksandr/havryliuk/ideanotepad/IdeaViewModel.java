package com.oleksandr.havryliuk.ideanotepad;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.support.annotation.NonNull;

import java.util.List;

public class IdeaViewModel extends AndroidViewModel {

    public static final String IDEA = "idea";
    public static final String CATEGORY = "category";
    public static final String TIME = "time";

    private IdeasRepository repository;
    private final MutableLiveData<String> ideasOrder = new MutableLiveData<>();
    private LiveData<List<Idea>> ideas =
        Transformations.switchMap(ideasOrder, (order) -> repository.orderBy(order));

    public IdeaViewModel(Application application){
        super(application);
        repository = IdeasRepository.getRepository(application);
    }


    public LiveData<List<Idea>> getAllIdeas() {
        return ideas;
    }

    public void insert(Idea idea){
        repository.insert(idea);
    }


    public void setOrder(String order) {
        ideasOrder.setValue(order);
    }
}
