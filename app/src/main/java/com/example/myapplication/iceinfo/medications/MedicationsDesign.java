package com.example.myapplication.iceinfo.medications;

public class MedicationsDesign {
    private final int id;
    private final String medication;

    //Constructor
    public MedicationsDesign(int id, String medication) {
        this.id = id;
        this.medication = medication;
    }

    //Getters Below
    public String getMedication() { return medication; }

    public int getId() {
        return id;
    }

}
