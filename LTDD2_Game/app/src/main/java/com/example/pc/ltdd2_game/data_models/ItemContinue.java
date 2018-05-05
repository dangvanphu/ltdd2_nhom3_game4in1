package com.example.pc.ltdd2_game.data_models;

public class ItemContinue {
    private String userName;
    private String score;

    public ItemContinue(String userName, String score) {
        this.userName = userName;
        this.score = score;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
