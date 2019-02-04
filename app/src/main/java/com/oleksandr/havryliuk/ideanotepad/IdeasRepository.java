package com.oleksandr.havryliuk.ideanotepad;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IdeasRepository {

    private MutableLiveData<List<Idea>> ideas;

    public IdeasRepository(){
        ideas = new MutableLiveData<>();
        List<Idea> list = new ArrayList<>();
        Date date = new Date();
        list.add(new Idea(1, "first idea", "test Ideas", date.getTime()));
        list.add(new Idea(2, "second idea", "test Ideas", 12192435));
        ideas.setValue(list);
    }

    public LiveData<List<Idea>> getIdeas(){
        return ideas;
    }
}
