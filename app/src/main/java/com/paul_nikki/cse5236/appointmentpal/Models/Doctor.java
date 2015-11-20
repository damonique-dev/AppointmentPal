package com.paul_nikki.cse5236.appointmentpal.Models;

/**
 * Created by Paul Williams on 11/19/2015.
 */
public class Doctor {
    String doctorName;
    String address;
    String practiceName;
    String doctorEmail;

    public Doctor(String dName, String location, String practicenm, String email){
        doctorName = dName;
        address = location;
        practiceName = practicenm;
        doctorEmail = email;
    }

    public String getName(){ return doctorName;}
    public String getEmail(){ return doctorEmail;}
    public String getPracticeName() { return practiceName;}
    public String getAddress(){return address;}
}
