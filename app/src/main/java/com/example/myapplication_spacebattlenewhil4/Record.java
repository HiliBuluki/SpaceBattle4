package com.example.myapplication_spacebattlenewhil4;

public class Record {

    private String name;
    private int score;

    public Record() {
    }

    public Record(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
