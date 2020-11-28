package com.example.notes;

import android.app.Activity;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AnswerAdapter extends BaseAdapter {


    private ArrayList<Pair<String,single_answer>> answerarraylist;
    private Activity activity;
    public AnswerAdapter( ArrayList<Pair<String,single_answer>>answerarraylist,Activity activity  ) {
        this.answerarraylist=answerarraylist;
        this.activity = activity;
    }
    @Override
    public int getCount() {
        return answerarraylist.size();
    }

    @Override
    public Object getItem(int position) {
        return answerarraylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        LayoutInflater layoutInflater=activity.getLayoutInflater();
        final View inflate=layoutInflater.inflate(R.layout.single_answerxml,parent,false);
        TextView answer=inflate.findViewById(R.id.answer);
        final TextView likes=inflate.findViewById(R.id.likes);
        TextView dateanswer=inflate.findViewById(R.id.dateanswer);
        answer.setText(answerarraylist.get(position).second.getAnswer());
        likes.setText(answerarraylist.get(position).second.getLikes()+"");
        dateanswer.setText(answerarraylist.get(position).second.getTime());
        inflate.findViewById(R.id.upvoteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int upvoteCount= answerarraylist.get(position).second.getLikes()+1;
                answerarraylist.get(position).second.setLikes(upvoteCount);
                likes.setText(upvoteCount+"");
                FirebaseActivity.db.collection("questions")
                        .document(QuestionAdapter.QuestionIdentity).collection("answers").document(answerarraylist.get(position).first).update("likes",upvoteCount);

            }
        });








        return inflate;
    }
}