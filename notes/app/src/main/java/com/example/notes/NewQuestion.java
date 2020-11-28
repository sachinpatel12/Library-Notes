package com.example.notes;

import android.app.AlertDialog;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NewQuestion extends AppCompatActivity {
    TextView question,name,devicenotfound;
    String questi,tname="";
    private Question quest;
    private String fcm,loadedfcm,loadedname;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_question);
        getSupportActionBar().setTitle("Post a question");
        question = findViewById(R.id.quest);
        name=findViewById(R.id.name);
        progressBar=findViewById(R.id.pbar);
        devicenotfound=findViewById(R.id.devicenotfound);
        FirebaseActivity.db.collection("questions").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                    loadedfcm=snapshot.getString("fcm");
                    loadedname=snapshot.getString("name");
                    if(loadedfcm.equals(Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID))){
                        tname=loadedname;
                        findViewById(R.id.name).setVisibility(View.GONE);
                        devicenotfound.setVisibility(View.GONE);
                        break;
                    }
                }    }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public void onpress(View view) {
        switch (view.getId()) {
            case R.id.post:
                progressBar.setVisibility(View.VISIBLE);
                questi = question.getText().toString();
                postQuestion();
                break;
            case R.id.cancel:
                finish();
                break;
        }
    }

    private void postQuestion() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM yyyy hh:mm a");
        Date date = Calendar.getInstance().getTime();
        if(tname.equals(""))
            tname=name.getText().toString();
        quest = new Question(0, simpleDateFormat.format(date), questi,0,Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID),tname);
        FirebaseActivity.db.collection("questions").add(quest).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                progressBar.setVisibility(View.GONE);
                finish();
                Toast.makeText(NewQuestion.this, "Question Posted Successfully", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(NewQuestion.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
