package com.example.myapplication.iceinfo.personalinfo;

public class PersonalInfoDesign {
    private final int id;
    private final String firstName, lastName, dateOfBirth, Address, City, Zipcode, phoneNumber,
            insuranceProvider, policyNumber, bloodType;

    //Constructor
    public PersonalInfoDesign(int id, String firstName, String lastName, String dateOfBirth,
                              String Address, String City, String Zipcode, String phoneNumber,
                              String insuranceProvider, String policyNumber, String bloodType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.Address = Address;
        this.City = City;
        this.Zipcode = Zipcode;
        this.phoneNumber = phoneNumber;
        this.insuranceProvider = insuranceProvider;
        this.policyNumber = policyNumber;
        this.bloodType = bloodType;
    }

    //Getters Below
    public String getFirstName() {
        return firstName;
    }

    public int getId() { return id; }

    public String getLastName() { return lastName; }

    public String getDateOfBirth() { return dateOfBirth; }

    public String getAddress() { return Address; }

    public String getCity() { return City; }

    public String getZipcode() { return Zipcode; }

    public String getPhoneNumber() { return phoneNumber; }

    public String getInsuranceProvider() { return insuranceProvider; }

    public String getPolicyNumber() { return policyNumber; }

    public String getBloodType() { return bloodType; }

}


