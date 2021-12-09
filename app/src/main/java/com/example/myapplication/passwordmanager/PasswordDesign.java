package com.example.myapplication.passwordmanager;

public class PasswordDesign {
    private final int id;
    private final String AppName, Username, Password;

    //Constructor
    public PasswordDesign(int id, String AppName, String Username, String Password) {
        this.id = id;
        this.AppName = AppName;
        this.Username = Username;
        this.Password = Password;
    }

    //Getters Below
    public String getAppName() {
        return AppName;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

}

