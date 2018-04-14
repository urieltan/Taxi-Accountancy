package com.urieltan.taxi_accounting.listfragment;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.urieltan.taxi_accounting.session.AnotherActivity;
import com.urieltan.taxi_accounting.session.AppDatabase;
import com.urieltan.taxi_accounting.session.SessionAdapter;
import com.urieltan.taxi_accounting.session.SessionEntity;
import com.urieltan.taxi_accounting.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * Created by urieltan on 22/03/18.
 */

public class ListFragment extends Fragment {

    DbHelper dbHelper;
    ArrayAdapter<String> mAdapter;
    ListView lstTask;

    public ListFragment(){
        super();

//
//        dbHelper = new DbHelper(this.getContext());
//
//        lstTask = (ListView)this.getActivity().findViewById(R.id.lstTask);
    }
    public static ListFragment newInstance(String info) {
        ListFragment fragment = new ListFragment();
        return fragment;
    }


    public static RecyclerView recyclerView;
    public static RecyclerView.Adapter adapter;
    List<SessionEntity> items;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View v = inflater.inflate(R.layout.new_history_list,null);
        final AppDatabase db = Room.databaseBuilder(v.getContext(), AppDatabase.class,"production")
                .fallbackToDestructiveMigration()
                .build();


        recyclerView= (RecyclerView)v.findViewById(R.id.history_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        //it is very bad practice to pull data from Room on main UI thread,
        // that's why we create another thread which we use for getting the data and displaying it
        Runnable r = new Runnable(){
            @Override
            public void run() { //shift this to onResume
                items = db.sessionInterface().getAllItems();
                adapter= new SessionAdapter(items);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }
        };      //PRAY EVERYTHING WORKS


        Thread newThread= new Thread(r);
        newThread.start();



        v.findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText taskEditText = new EditText(ListFragment.this.getContext());
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = df.format(c.getTime());
                taskEditText.setText(formattedDate,null);


//                DialogFragment newFragment = new AddData();
//                newFragment.show(ListFragment.this.getFragmentManager(), "AddData");

                //TODO: edit the adddata dialog to add data into the database

                //TODO: after that then delete the below chunk of code

                AlertDialog dialog = new AlertDialog.Builder(ListFragment.this.getContext())
                        .setTitle("Add New Task")
                        .setMessage("What do you want to do next?")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(taskEditText.getText());
                                Log.d("ListFragment","Add button clicked");

                                //TODO: ADD DATA INTO DATABASE NOW

                                SessionEntity se = new SessionEntity();
                                se.setTime(task);
                                se.setName("name");
                                final SessionEntity see = se;
                                AsyncTask.execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        db.sessionInterface().insertAll(see);
                                    }
                                });

                                Intent intent = new Intent(ListFragment.this.getContext(), AnotherActivity.class);
                                intent.putExtra("SessionID", "test");

                                ListFragment.this.getContext().startActivity(intent);

                            }
                        })
                        .setNegativeButton("Cancel",null)
                        .create();
                dialog.show();
            }
        });
        return v;
    }





//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        view = inflater.inflate(R.layout.history_list, null);
//        dbHelper = new DbHelper(this.getContext());
//
//        // TODO: change all the codes to accomodate for the room database
//        lstTask = (ListView)view.findViewById(R.id.lstTask);
//        loadTaskList();
//
//        view.findViewById(R.id.floatingActionButton).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                final EditText taskEditText = new EditText(ListFragment.this.getContext());
//                Calendar c = Calendar.getInstance();
//                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                String formattedDate = df.format(c.getTime());
//                taskEditText.setText(formattedDate,null);
//                AlertDialog dialog = new AlertDialog.Builder(ListFragment.this.getContext())
//                        .setTitle("Add New Task")
//                        .setMessage("What do you want to do next?")
//                        .setView(taskEditText)
//                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                String task = String.valueOf(taskEditText.getText());
//                                dbHelper.insertNewTask(task);
//                                loadTaskList();
//
//                                Log.d("ListFragment","Add button clicked");
//
//                                //Bundle args = new Bundle();
//                                //args.putInt("doctor_id",1);
//                                Intent intent = new Intent(ListFragment.this.getContext(), AnotherActivity.class);
//                                intent.putExtra("SessionID", 1);
//
//                                ListFragment.this.getContext().startActivity(intent);
//
//                            }
//                        })
//                        .setNegativeButton("Cancel",null)
//                        .create();
//                dialog.show();
////                Intent intent = new Intent(view.getContext(), AnotherActivity.class);
////                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////               view.getContext().startActivity(intent);
//            }
//        });
//        return view;
//    }

    //todo: remove this method soon
    private void loadTaskList() {
        ArrayList<String> taskList = dbHelper.getTaskList();
        if(mAdapter==null){
            mAdapter = new ArrayAdapter<String>(this.getActivity(),R.layout.layout,R.id.task_title,taskList);
            lstTask.setAdapter(mAdapter);
        }
        else{
            mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();
        }
    }


}
