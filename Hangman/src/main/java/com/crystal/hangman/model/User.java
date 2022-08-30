package com.crystal.hangman.model;

public class User {
    private String nickName;
    public static User currentUser;
    private int score = 0;

    public User(String nickName) {
        this.nickName = nickName;
        this.score =0;
        currentUser = this;
    }


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public void incrementScore(int score) {
        this.score += score;
    }
}
