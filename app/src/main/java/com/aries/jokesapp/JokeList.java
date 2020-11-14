package com.aries.jokesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aries.jokesapp.controllers.CategoryAdapter;
import com.aries.jokesapp.controllers.JokeAdapter;
import com.aries.jokesapp.models.Joke;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class JokeList extends AppCompatActivity {

    ArrayList <String> categories;
    ArrayList <Joke> jokes;
    ArrayList<Joke> jokesAll;

    RecyclerView recyclerViewCategory;
    CategoryAdapter categoryAdapter;

    RecyclerView recyclerViewJokes;
    public JokeAdapter jokesAdapter;

    public String jokeFilter="all";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_list);

        categories=new ArrayList<>();
        jokes=new ArrayList<>();
        jokesAll=new ArrayList<>();

        recyclerViewCategory = (RecyclerView) findViewById(R.id.categoriesview);
        categoryAdapter=new CategoryAdapter(categories,this);
        recyclerViewCategory.setHasFixedSize(true);
        recyclerViewCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewCategory.setAdapter(categoryAdapter);


        recyclerViewJokes = (RecyclerView) findViewById(R.id.jokesview);
        jokesAdapter=new JokeAdapter(jokes);
        recyclerViewJokes.setHasFixedSize(true);
        recyclerViewJokes.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewJokes.setAdapter(jokesAdapter);

        loadData();
    }

    public void refreshList(View view){
        jokeFilter="all";
        loadData();
    }

    private void loadData() {
        jokesAll.clear();

        RequestQueue queue = Volley.newRequestQueue(JokeList.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://official-joke-api.appspot.com/random_ten",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray arrayRecords = new JSONArray(response);

                            for(int i=0;i<arrayRecords.length();i++){
                                JSONObject jsonObject= (JSONObject) arrayRecords.get(i);
                                jokesAll.add(new Joke(jsonObject.getInt("id"),jsonObject.getString("type"),jsonObject.getString("setup"),jsonObject.getString("punchline")));
                            }

                            loadCategories();

                        }catch(Exception e){
                            Toast.makeText(JokeList.this, "Parsing Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
                Toast.makeText(JokeList.this, "Something Wrong", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(stringRequest);
    }

    public void loadCategories() {

        categories.clear();
        jokes.clear();

        for(Joke joke:jokesAll){
            boolean check=true;
            for(String cat:categories){
                if(cat.equals(joke.getType())){
                    check=false;
                    break;
                }
            }

            if(check==true){
                categories.add(joke.getType());
                System.out.println(joke.getType());
            }

            if(jokeFilter.equals("all")){
                jokes.add(joke);
            }else{
                if(joke.getType().equals(jokeFilter)) jokes.add(joke);
            }
        }

        categoryAdapter.notifyDataSetChanged();
        jokesAdapter.notifyDataSetChanged();

    }
}