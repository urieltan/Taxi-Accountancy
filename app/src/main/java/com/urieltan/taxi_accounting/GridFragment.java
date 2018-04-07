package com.urieltan.taxi_accounting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.urieltan.taxi_accounting.R;

/**
 * Created by urieltan on 22/03/18.
 */

public class GridFragment extends Fragment {

    public static GridFragment newInstance(String info) {
        // Take note this one is arguments, might be used in the future
        //Bundle args = new Bundle();
        GridFragment fragment = new GridFragment();
        //args.putString("info", info);
        //fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.button_grid, null);
        return view;
    }
}
