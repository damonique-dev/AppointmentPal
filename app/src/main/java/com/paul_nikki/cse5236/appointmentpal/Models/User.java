package com.paul_nikki.cse5236.appointmentpal.Models;
import com.paul_nikki.cse5236.appointmentpal.Models.Appointment;

import java.util.Date;
import java.util.Vector;

/**
 * Created by nikkbandy on 10/13/15.
 */
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phoneNumber;
    private String password;
    private Vector<Appointment> appts;

    public User(String FN, String LN, String mail, String addr, String phNo, String pwd){
        //TODO: validation & encryption (?)
        firstName = FN;
        lastName = LN;
        email = mail;
        address = addr;
        password = pwd;
    }

    public Appointment[] getAppointments(){
        int size = appts.size();
        Appointment[] apptArray = new Appointment[size];
        return apptArray;
    }

    public void addNewAppointment(Appointment a){
        appts.add(a);
    }

}
