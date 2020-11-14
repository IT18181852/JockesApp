package com.aries.jokesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.aries.jokesapp.models.Common;
import com.aries.jokesapp.models.User;

public class Register extends AppCompatActivity {

    EditText name,email,password,retypePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name=findViewById(R.id.register_fname);
        email=findViewById(R.id.register_email);
        password=findViewById(R.id.register_password);
        retypePassword=findViewById(R.id.register_retypepassword);
    }

    public void goToLogin(View view){
        super.onBackPressed();
    }

    public void doRegister(View view){
        if(!name.getText().toString().isEmpty() && !email.getText().toString().isEmpty() && !password.getText().toString().isEmpty() && !retypePassword.getText().toString().isEmpty()){
            if(password.getText().toString().equals(retypePassword.getText().toString())){
                boolean check=true;

                for(User user:Common.usersList){
                    if(user.getEmail().equals(email.getText().toString())){
                        check=false;
                        Toast.makeText(this, "Email exists", Toast.LENGTH_SHORT).show();break;
                    }
                }

                if(check==true){
                    Common.usersList.add(new User(name.getText().toString(),email.getText().toString(),password.getText().toString()));
                    clearAll();
                    Toast.makeText(this, "Registration Success", Toast.LENGTH_SHORT).show();
                }

            }else{
                Toast.makeText(this, "Passwords doesn't match", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Please fill all data", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearAll() {
        name.setText("");
        email.setText("");
        password.setText("");
        retypePassword.setText("");
    }
}