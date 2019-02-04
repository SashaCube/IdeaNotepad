package com.oleksandr.havryliuk.ideanotepad;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Idea.class}, version = 1)
public abstract class IdeaRoomDatabase extends RoomDatabase {

    public abstract IdeaDao ideaDao();
    private static volatile IdeaRoomDatabase INSTANCE;

    static IdeaRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (IdeaRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            IdeaRoomDatabase.class, "idea_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    public static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAnsync(INSTANCE).execute();
                }
            };

    public static class PopulateDbAnsync extends AsyncTask<Void, Void, Void> {
        private final IdeaDao mDao;

        PopulateDbAnsync(IdeaRoomDatabase db){
            mDao = db.ideaDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
