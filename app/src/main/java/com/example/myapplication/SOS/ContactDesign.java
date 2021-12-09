package com.example.myapplication.SOS;

public class ContactDesign {
    private final int id;
    private final String phoneNumber, ContactName;

    //Constructor
    public ContactDesign(int id,String ContactName, String phoneNumber) {
        this.id=id;
        this.phoneNumber = validate(phoneNumber);
        this.ContactName = ContactName;
    }

    //Check Phone Number Format
    private String validate(String phone){
        //case1 checks if contact already includes country code +1 for US calls and adds it if not
        StringBuilder case1 = new StringBuilder("+1");

        //case2 checks if contact contains any spaces or dashes("-") and removes them
        StringBuilder case2 = new StringBuilder();

        //Run case1
        if(phone.charAt(0)!='+'){
            for(int i=0; i<phone.length(); i++){
                //remove any spaces or "-"
                if(phone.charAt(i)!='-' && phone.charAt(i)!=' '){
                    case1.append(phone.charAt(i));
                }
            }
            return case1.toString();

        //Run case2
        }else{
            for(int i=0; i<phone.length(); i++){
                //remove any spaces or "-"
                if(phone.charAt(i)!='-' || phone.charAt(i)!=' '){
                    case2.append(phone.charAt(i));
                }
            }
            return case2.toString();
        }

    }

    //Getters Below
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getId() {
        return id;
    }

    public String getContactName() {
        return ContactName;
    }

}
