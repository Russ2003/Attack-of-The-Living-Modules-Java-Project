package com.example.attackofthelivingmodules;

public class Scores {
    private final String name;
    private final int score;

    public Scores(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {return name;}

    public int getScore() {return score;}
}
