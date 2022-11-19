package com.example.comp304_lab04;

import android.app.Application;
import android.app.AsyncNotedAppOp;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TestRepository {

    public TestDao testDao;
    private LiveData<List<Test>> allTests;

    public TestRepository(Application application)
    {
        AppDB db = AppDB.getDatabase(application);
        testDao = db.testDao();
        allTests = testDao.getAllTests();
    }

    public void insert(Test test) {
        new InsertTestAsyncTask(testDao).execute(test);
    }

    public void update(Test test) {
        new UpdateTestAsyncTask(testDao).execute(test);
    }

    public void delete(Test test){
        new DeleteTestAsyncTask(testDao).execute(test);
    }

    public LiveData<List<Test>> getAllTests() {return allTests; }

    public LiveData<Test> findbyTestID(int testID) {return testDao.getByTestID(testID); }

    private static class InsertTestAsyncTask extends AsyncTask<Test, Void, Void> {
        private TestDao testDao;
        private InsertTestAsyncTask(TestDao testDao){
            this.testDao = testDao;
        }

        @Override
        protected Void doInBackground(Test... model) {
            testDao.insert(model[0]);
            return null;
        }
    }

    private static class UpdateTestAsyncTask extends AsyncTask<Test, Void, Void>{
        private TestDao testDao;
        private UpdateTestAsyncTask(TestDao testDao){
            this.testDao = testDao;
        }

        @Override
        protected Void doInBackground(Test... model) {
            testDao.update(model[0]);
            return null;
        }
    }

    private static class DeleteTestAsyncTask extends AsyncTask<Test, Void, Void>{
        private TestDao testDao;
        private DeleteTestAsyncTask(TestDao testDao){
            this.testDao = testDao;
        }

        @Override
        protected Void doInBackground(Test... model) {
            testDao.delete(model[0]);
            return null;
        }
    }
}
