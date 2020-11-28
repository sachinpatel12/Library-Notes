package com.example.notes;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class login_activity extends AppCompatActivity {
    TextView email,password;
    Button login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        getSupportActionBar().hide();
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        //mAuth = FirebaseAuth.getInstance();
        login=findViewById(R.id.loginbutton);
        //if (mAuth.getCurrentUser()!=null)
            //startActivity(new Intent(this,FirebaseActivity.class));
    }


    public void onlogin(View view) {
    startActivity(new Intent(this,FirebaseActivity.class));}

        /*mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(login_activity.this,"passed",Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(login_activity.this,FirebaseActivity.class));



                        } else {
                            Toast.makeText(login_activity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });*/
    }

