package com.art.code.psycare;

public class Consultant {

    private String name;
    private String rank;
    private String price;
    private int score;
    private int avatar;

    public Consultant(String name, String rank, String price, int score) {
        this.name = name;
        this.rank = rank;
        this.price = price;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }


}
