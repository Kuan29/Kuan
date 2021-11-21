package com.example.stepcounter.database;

/**
 * Created by Admin on 09-04-2015.
 */
public class DataProvider {   private String name;
    private String password;

    public DataProvider(String n, String p){
        name = n;
        password = p;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
