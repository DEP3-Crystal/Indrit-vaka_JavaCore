package com.crystal.hangman.model;
public class User {
    private String nickName;
    private static User currentUser;
    private int totalScore;
    private int score = 0;
    private String password;

    public User(String nickName, String password) {
        setNickName(nickName);
        this.totalScore = 0;
        currentUser = this;
        setPassword(password);
    }
    public static User getCurrentUser(){
        return currentUser;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public void incrementScore(int score) {
        this.totalScore += score;
        this.score = this.totalScore;
    }

    /**
     * @return nickname,totalScore,score,password
     */
    @Override
    public String toString() {
        return nickName + "," + totalScore + "," + score +"," + password;
    }
}
