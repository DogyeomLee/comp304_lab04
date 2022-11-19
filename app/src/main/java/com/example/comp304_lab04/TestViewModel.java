package com.example.comp304_lab04;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TestViewModel extends AndroidViewModel {
    private TestRepository testRepository;
    private LiveData<List<Test>> allTests;

    public TestViewModel(@NonNull Application application) {
        super(application);
        testRepository = new TestRepository(application);
        allTests = testRepository.getAllTests();
    }

    public  LiveData<Test> findByTestID(int testID) {return testRepository.findbyTestID(testID); }

    public void insert(Test test) { testRepository.insert(test); }

    public void update(Test test) { testRepository.update(test); }

    public void delete(Test test){ testRepository.delete(test);}

    public LiveData<List<Test>> getAllTests() { return allTests; }
}
