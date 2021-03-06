package com.oleksandr.havryliuk.ideanotepad.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.oleksandr.havryliuk.ideanotepad.UI.IdeaViewModel;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class IdeasRepository {

    private IdeaDao mIdeaDao;
    private Executor executor;

    private static IdeasRepository INSTANCE;

    public static IdeasRepository getRepository(Application application) {
        if (INSTANCE == null) {
            INSTANCE = new IdeasRepository();
            IdeaRoomDatabase db = IdeaRoomDatabase.getDatabase(application);
            INSTANCE.mIdeaDao = db.ideaDao();
            INSTANCE.executor = Executors.newSingleThreadExecutor();
        }
        return INSTANCE;
    }

    public LiveData<List<Idea>> getAllIdeas(){
        return mIdeaDao.getAllIdeas();
    }

    public LiveData<List<Idea>> orderBy(String order){
        switch (order){
            case IdeaViewModel.IDEA:
                return mIdeaDao.getIdeasOrderByIdea();
            case IdeaViewModel.CATEGORY:
                return mIdeaDao.getIdeasOrderByCategory();
            case IdeaViewModel.TIME:
                return mIdeaDao.getAllIdeas();
                default:
                    return mIdeaDao.getAllIdeas();
        }
    }

    public void insert(Idea idea){
        executor.execute(() -> {
            mIdeaDao.insert(idea);
        });
    }

    public void clearAllIdeas() {
        executor.execute(() -> {
            mIdeaDao.deleteAll();
        });
    }

    public void updateIdea(Idea idea) {
        executor.execute(() -> {
            mIdeaDao.update(idea);
        });
    }

    public void delete(Idea idea) {
        executor.execute(() -> {
            mIdeaDao.delete(idea);
        });
    }
}
