package com.urieltan.taxi_accounting.gridfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.urieltan.taxi_accounting.R;

/**
 * Created by urieltan on 22/03/18.
 */

public class GridFragment extends Fragment {

    public static GridFragment newInstance(String info) {
        GridFragment fragment = new GridFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.button_grid, null);

        //TODO: ADD ONCLICK LISTENERS TO EACH OF THE BUTTONS

        //vvv delete in the future, then implement switching the view or something

//        Button start = (Button)view.findViewById(R.id.start);
//        start.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), SessionActivity.class);
//                view.getContext().startActivity(intent);
//                // vv This also works but just use the simpler version on top
////                Intent intent = new Intent(GridFragment.this.getActivity(), SessionActivity.class);
////                GridFragment.this.getActivity().startActivity(intent);
//            }
//        });
        return view;
    }
}
