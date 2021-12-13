package com.example.livewithme;

import java.util.Date;

public class Expense {
    private String name;
    private String date;
    private int cost;

    // empty constructor needed by firebase
    public Expense() {

    }

    public Expense(String name, String date, int cost){
        this.name = name;
        this.date = date;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public int getCost() {
        return cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
