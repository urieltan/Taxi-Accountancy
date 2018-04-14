package com.urieltan.taxi_accounting.session;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.urieltan.taxi_accounting.R;

import java.util.List;

/**
 * Created by urieltan on 03/04/18.
 */

public class AnotherActivity extends AppCompatActivity {


    FloatingActionButton fab;
    public static RecyclerView recyclerView;
    public static RecyclerView.Adapter adapter;
    private static int sessionID;
    List<TodoListEntity> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.another_activity);

        sessionID = 0;

        //whenever the activity is started, it reads data from database and stores it into
        // local array list 'items'
        final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,"production")
                .fallbackToDestructiveMigration()
                .build();

        //it is very bad practice to pull data from Room on main UI thread,
        // that's why we create another thread which we use for getting the data and displaying it
        Runnable r = new Runnable(){
            @Override
            public void run() {
                items = db.todoInterface().getAllItems();
                recyclerView= (RecyclerView)findViewById(R.id.recyclerview);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplication()));
                adapter= new TodoAdapter(items);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }
        };

        Thread newThread= new Thread(r);
        newThread.start();

        fab=(FloatingActionButton)findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AnotherActivity.this,AddItemActivity.class);
                i.putExtra("SessionID",sessionID);
                startActivity(i);
                finish();
            }
        });

    }
}
