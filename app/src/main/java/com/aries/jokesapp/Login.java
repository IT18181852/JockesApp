package com.aries.jokesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.aries.jokesapp.models.Common;
import com.aries.jokesapp.models.User;

public class Login extends AppCompatActivity {

    EditText email,password;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        
        email=findViewById(R.id.login_email);
        password=findViewById(R.id.login_password);
    }

    public void goToRegister(View view){
        startActivity(new Intent(Login.this,Register.class));
    }
    
    public void doLogin(View view){
        if(!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()){
            boolean check=true;
            for(User user: Common.usersList){
                if(user.getEmail().equals(email.getText().toString()) && user.getPassword().equals(password.getText().toString())){
                    check=false;
                    startActivity(new Intent(Login.this,Home.class));
                    break;
                }
            }

            if(check==true){
                Toast.makeText(this, "Authentication failure", Toast.LENGTH_SHORT).show();
            }
            
        }else{
            Toast.makeText(this, "Please fill all data", Toast.LENGTH_SHORT).show();
        }
    }
}