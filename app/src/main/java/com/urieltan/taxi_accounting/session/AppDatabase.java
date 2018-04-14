package com.urieltan.taxi_accounting.session;

/**
 * Created by urieltan on 03/04/18.
 */
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {TodoListEntity.class, SessionEntity.class },version = 5)//change version number??
public abstract class AppDatabase extends RoomDatabase {

    public abstract TodoListDao todoInterface();
    public abstract SessionDao sessionInterface();

    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }
}