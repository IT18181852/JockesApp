package com.aries.jokesapp.controllers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.aries.jokesapp.R;
import com.aries.jokesapp.models.Joke;

import java.util.ArrayList;

public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.ViewHolder>{
    private ArrayList<Joke> listdata;

    public JokeAdapter(ArrayList <Joke> listdata) {
        this.listdata = listdata;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title,answer;
        public ViewHolder(View itemView) {
            super(itemView);
            this.title = (TextView) itemView.findViewById(R.id.joketitle);
            this.answer = (TextView) itemView.findViewById(R.id.jokeanswer);
        }
    }

    @Override
    public JokeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.activity_joke_record, parent, false);
        JokeAdapter.ViewHolder viewHolder = new JokeAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(JokeAdapter.ViewHolder holder, int position) {
        final Joke record = listdata.get(position);
        holder.title.setText(record.getSetup());
        holder.answer.setText(record.getPunchline());
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

}
