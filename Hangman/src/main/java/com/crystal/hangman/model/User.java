package com.crystal.hangman.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class User {
    private String nickName;
    private int highScore;
    private String password;

    public User(String nickName, String password) {
        setNickName(nickName);
        this.highScore = 0;
        setPassword(password);
    }


    /**
     * @return highScore, password
     */
    @Override
    public String toString() {
        return highScore + "," + password;
    }
}

