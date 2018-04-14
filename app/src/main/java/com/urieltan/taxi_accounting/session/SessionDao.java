package com.urieltan.taxi_accounting.session;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by urieltan on 09/04/18.
 */
@Dao
public interface SessionDao {

    //Need to update all the queries for better querying pratices


    @Query("SELECT * FROM SessionEntity")
    List<SessionEntity> getAllItems();

    @Insert
    void insertAll(SessionEntity... sessionEntities);
}
