package com.urieltan.taxi_accounting.session;

/**
 * Created by urieltan on 03/04/18.
 */
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface TodoListDao {

    @Query("SELECT * FROM TodoListEntity")
    List<TodoListEntity> getAllItems();

    @Insert
    void insertAll(TodoListEntity... todoListEntities);
}