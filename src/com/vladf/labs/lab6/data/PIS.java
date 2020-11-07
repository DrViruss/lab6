package com.vladf.labs.lab6.data;

import java.util.ArrayList;

public class PIS {
    public PIS(String message, ArrayList<Person> people, float number) {
        this.message = message;
        this.people = people;
        this.number = (int)number;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    private String message;
    private ArrayList < Person > people = new ArrayList < Person > ();
    private int number;

}