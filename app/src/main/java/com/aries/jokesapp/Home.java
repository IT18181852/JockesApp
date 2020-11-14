package com.aries.jokesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Calendar;

public class Home extends AppCompatActivity {

    TextView homeJoke,homeAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        homeJoke=findViewById(R.id.homejoketxt);
        homeAnswer=findViewById(R.id.homeanswer);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent notificationIntent = new Intent(this, AlarmReceiver.class);
        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 5);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);

        initialize();
    }

    public void getAnother(View view){
        initialize();
    }

    public void goToAllJokes(View view){
        startActivity(new Intent(Home.this,JokeList.class));
    }

    private void initialize() {
        RequestQueue queue = Volley.newRequestQueue(Home.this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://official-joke-api.appspot.com/random_joke",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jObject = new JSONObject(response);
                            homeJoke.setText(jObject.getString("setup"));
                            homeAnswer.setText(jObject.getString("punchline"));

                        }catch(Exception e){
                            Toast.makeText(Home.this, "Parsing Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
                Toast.makeText(Home.this, "Something Wrong", Toast.LENGTH_SHORT).show();
            }
        });

        queue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {

    }


}