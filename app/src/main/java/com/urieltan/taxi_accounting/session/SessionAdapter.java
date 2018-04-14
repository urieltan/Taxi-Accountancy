package com.urieltan.taxi_accounting.session;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.urieltan.taxi_accounting.R;

import java.util.List;

/**
 * Created by urieltan on 09/04/18.
 */

public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.ViewHolder> {
    List<SessionEntity> items;

    public SessionAdapter(List<SessionEntity> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(items.get(position).getName());
        holder.time.setText(items.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        //return 0;
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView time;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.session_name);
            time= itemView.findViewById(R.id.session_time);
        }
    }
}