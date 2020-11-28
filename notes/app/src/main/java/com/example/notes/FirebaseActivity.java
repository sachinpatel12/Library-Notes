package com.example.notes;

import android.content.Intent;
import android.media.audiofx.Equalizer;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseActivity extends AppCompatActivity {
    ListView listView;
    TextView jut;
    public static FirebaseFirestore db;
    static ArrayList<Pair<String,Question>> questionArrayList = new ArrayList<>();
    private QuestionAdapter adapter;
    ProgressBar pgsBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);
        listView = findViewById(R.id.list);
        pgsBar =findViewById(R.id.pBar);
        jut=findViewById(R.id.jut);
        adapter = new QuestionAdapter(questionArrayList, this);
        listView.setAdapter(adapter);
        FirebaseApp.initializeApp(this);
        db = FirebaseFirestore.getInstance();


        //fetchQuestions(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchQuestions(adapter);
    }

    private void fetchQuestions(final QuestionAdapter adapter) {
        db.collection("questions").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                questionArrayList.clear();
                jut.setVisibility(View.GONE);
                pgsBar.setVisibility(View.GONE);
                for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                    Question question = snapshot.toObject(Question.class);
                    questionArrayList.add(new Pair<String, Question>(snapshot.getId(),question));
                }
                adapter.notifyDataSetChanged();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    public void onclick(View view) {
        startActivity(new Intent(this, NewQuestion.class));
    }
}
