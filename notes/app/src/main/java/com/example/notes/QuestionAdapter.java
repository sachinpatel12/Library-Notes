package com.example.notes;

import android.app.Activity;
import android.content.Context;
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

import java.util.ArrayList;

public class QuestionAdapter extends BaseAdapter {
    static String QuestionIdentity;
    Intent intent;
    private ArrayList<Pair<String,Question>> questionArrayList;
    private Activity activity;
    public QuestionAdapter( ArrayList<Pair<String,Question>>questionArrayList,Activity activity  ) {
        this.questionArrayList = questionArrayList;
        this.activity = activity;
    }
    @Override
    public int getCount() {
        return questionArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return questionArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        LayoutInflater layoutInflater=activity.getLayoutInflater();
        final View inflate=layoutInflater.inflate(R.layout.singlequestion,parent,false);
        final TextView question = inflate.findViewById(R.id.question);
        final TextView fcm=inflate.findViewById(R.id.fcm);
        final TextView nameho=inflate.findViewById(R.id.nameho);
        final TextView upvotes=inflate.findViewById(R.id.numberofupvotes);
        TextView date = inflate.findViewById(R.id.date);
        TextView number=inflate.findViewById(R.id.numberofanswers);
        final ImageView likeimg=inflate.findViewById(R.id.likeimg);
        final Animation rotate= AnimationUtils.loadAnimation(activity,R.anim.bounce);
        final Animation blink=AnimationUtils.loadAnimation(activity,R.anim.blink_anim);

        final Question second = questionArrayList.get(position).second;
        upvotes.setText(second.getUpvotes()+"");
        fcm.setText(second.getFcm());
        nameho.setText(second.getName());
        question.setText(second.getQuestion());
        date.setText(second.getDate());
        number.setText(second.getAnswers()+"");

        inflate.findViewById(R.id.upvoteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                       int upvoteCount= second.getUpvotes()+1;
                       second.setUpvotes(upvoteCount);
                       upvotes.setText(upvoteCount+"");
                       likeimg.startAnimation(rotate);
                       upvotes.startAnimation(blink);

                FirebaseActivity.db.collection("questions")
                        .document(questionArrayList.get(position).first).update("upvotes",upvoteCount);
            }
        });
        inflate.findViewById(R.id.shareButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingintent = new Intent(Intent.ACTION_SEND);
                sharingintent.setType("text/plain");
                sharingintent.putExtra(Intent.EXTRA_SUBJECT,questionArrayList.get(position).second.getQuestion() );
                sharingintent.putExtra(Intent.EXTRA_TEXT,questionArrayList.get(position).second.getQuestion());
                activity.startActivity(sharingintent);

            }
        });
        inflate.findViewById(R.id.answerButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(activity,AnswerActivity.class);
                QuestionIdentity=questionArrayList.get(position).first;
                intent.putExtra("ans",questionArrayList.get(position).second.getAnswers());
                intent.putExtra("ID",questionArrayList.get(position).first);
                activity.startActivity(intent);
            }
        });







        return inflate;
    }

}

