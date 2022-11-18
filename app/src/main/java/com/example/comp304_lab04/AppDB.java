package com.example.comp304_lab04;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Patient.class}, version = 1, exportSchema = false)
public abstract class AppDB extends RoomDatabase {

    public abstract PatientDao patientDao();
    private static AppDB instance;

    public static synchronized AppDB getDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDB.class, "app_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void> {
        PopulateDbAsyncTask(AppDB instance){
            PatientDao patientDao = instance.patientDao();
        }
        @Override
        protected Void doInBackground(Void... voids){
            return null;
        }
    }
}

