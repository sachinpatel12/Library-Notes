package com.example.notes;

public class single_answer {
    String answer,time;
    int likes;
    public single_answer()
    {}

    public single_answer(String answer, String time, int likes) {
        this.answer = answer;
        this.time = time;
        this.likes = likes;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
