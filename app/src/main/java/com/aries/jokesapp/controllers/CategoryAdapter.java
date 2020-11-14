package com.aries.jokesapp.controllers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.aries.jokesapp.JokeList;
import com.aries.jokesapp.R;
import com.aries.jokesapp.models.Joke;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{
    private ArrayList<String> listdata;
    private JokeList jokeList;

    public CategoryAdapter(ArrayList <String> listdata,JokeList jokeList) {
        this.listdata = listdata;
        this.jokeList=jokeList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ViewHolder(View itemView) {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.jokecategory);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.activity_joke_category, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String record = listdata.get(position);
        holder.name.setText(record);

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jokeList.jokeFilter=record;
                jokeList.loadCategories();
            }
        });
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }


}
