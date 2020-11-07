package com.vladf.labs.lab6.data;

public class Person {
    private String name;
    private String craft;

    public Person(String name, String craft) {
        this.name = name;
        this.craft = craft;
    }

    public String getName() {
        return name;
    }

    public String getCraft() {
        return craft;
    }
}