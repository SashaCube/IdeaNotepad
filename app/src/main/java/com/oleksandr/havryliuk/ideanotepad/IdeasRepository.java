package com.oleksandr.havryliuk.ideanotepad;

import android.app.Application;
import android.arch.lifecycle.LiveData;
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
}
