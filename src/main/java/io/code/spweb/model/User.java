package io.code.spweb.model; 

public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return "my name is:" + name;
    }
}