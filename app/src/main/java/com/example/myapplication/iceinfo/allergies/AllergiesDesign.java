package com.example.myapplication.iceinfo.allergies;

public class AllergiesDesign {
    private final int id;
    private final String allergy;

    //Constructor
    public AllergiesDesign(int id, String allergy) {
        this.id = id;
        this.allergy = allergy;
    }

    //Getters Below
    public String getAllergy() { return allergy; }

    public int getId() {
        return id;
    }

}