package com.example.comp304_lab04;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TestDao
{

    @Insert
    void insert(Test test);

    @Update
    void update(Test test);

    @Delete
    void delete(Test test);

    @Query("DELETE FROM test_table")
    void deleteAll();

    @Query("Select * FROM test_table where testID = :testID")
    LiveData<Test> getByTestID(int testID);

    @Query("Select * FROM test_table")
    LiveData<List<Test>> getAllTests();
}
