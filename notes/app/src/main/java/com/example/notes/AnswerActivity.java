package com.example.notes;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AnswerActivity extends AppCompatActivity {
    String ID;int ans;
    single_answer single_answer;
    ListView listView;
    TextView newanswer;
    private ArrayList<Pair<String,single_answer>> answerarraylist = new ArrayList<>();
    AnswerAdapter adapter=new AnswerAdapter(answerarraylist,this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        ID=getIntent().getExtras().getString("ID");
        ans=getIntent().getExtras().getInt("ans");
        newanswer=findViewById(R.id.editanswer);
        listView=findViewById(R.id.answerlist);
        listView.setAdapter(adapter);

    }
    @Override
    protected void onResume() {
        super.onResume();
        fetchQuestions(adapter);
    }

    private void fetchQuestions(final AnswerAdapter adapter) {
        FirebaseActivity.db.collection("questions").document(ID).
                collection("answers").orderBy("likes", Query.Direction.DESCENDING ).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                answerarraylist.clear();
                for (DocumentSnapshot snapshot : queryDocumentSnapshots) {
                    single_answer = snapshot.toObject(single_answer.class);
                    answerarraylist.add(new Pair<String,single_answer>(snapshot.getId(),single_answer));
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
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MM yyyy hh:mm a");
        Date date = Calendar.getInstance().getTime();
        String new_answer=newanswer.getText().toString();
        single_answer = new single_answer(new_answer,simpleDateFormat.format(date),0);
        ans=ans+1;
        FirebaseActivity.db.collection("questions").document(ID).update("answers",ans);
        FirebaseActivity.db.collection("questions").document(ID).collection("answers").add(single_answer).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(AnswerActivity.this,"Answer added",Toast.LENGTH_SHORT).show();
                fetchQuestions(adapter);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });




    }


}
