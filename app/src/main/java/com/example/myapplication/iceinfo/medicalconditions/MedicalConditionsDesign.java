package com.example.myapplication.iceinfo.medicalconditions;

public class MedicalConditionsDesign {
    private final int id;
    private final String medicalCondition;

    //Constructor
    public MedicalConditionsDesign(int id, String medicalCondition) {
        this.id = id;
        this.medicalCondition = medicalCondition;
    }

    //Getters Below
    public String getMedicalCondition() { return medicalCondition; }

    public int getId() {
        return id;
    }

}