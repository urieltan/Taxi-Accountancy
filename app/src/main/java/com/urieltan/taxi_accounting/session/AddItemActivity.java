package com.urieltan.taxi_accounting.session;

/**
 * Created by urieltan on 03/04/18.
 */

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.urieltan.taxi_accounting.R;

//todo Change this into a dialog

public class AddItemActivity extends AppCompatActivity {

    EditText todo;
    EditText time;
    Button button;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,"production")
                .build();

        todo = (EditText) findViewById(R.id.name);
        time = (EditText) findViewById(R.id.time);
        button = (Button) findViewById(R.id.addButton);


        final AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,"production")
                .build();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!todo.getText().toString().equals("") && !time.getText().toString().equals("")){

                    final TodoListEntity todoListEntity = new TodoListEntity(todo.getText().toString(),time.getText().toString());
                    //save the item before leaving the activity


                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            db.todoInterface().insertAll(todoListEntity);
                        }
                    });


                    Intent i = new Intent(AddItemActivity.this,AnotherActivity.class);
                    startActivity(i);

                    finish();
                }
            }
        });
    }
}