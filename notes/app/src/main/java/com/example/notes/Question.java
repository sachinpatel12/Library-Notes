package com.example.notes;

public class Question {
    private String question;
    private String date;
    private int answers;
    private int upvotes;
    private String fcm;
    private String name;



    public Question(){
    }

    public Question(int answers,String date,String question,int upvotes, String fcm,String name){
        this.question=question;
        this.date=date;
        this.answers=answers;
        this.upvotes=upvotes;
        this.fcm = fcm;
        this.name=name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getAnswers() {
        return answers;
    }

    public void setAnswers(int answers) {
        this.answers = answers;
    }

    public String getFcm() {
        return fcm;
    }

    public void setFcm(String fcm) {
        this.fcm = fcm;
    }
}
