package com.paul_nikki.cse5236.appointmentpal.Models;
import com.paul_nikki.cse5236.appointmentpal.Models.Appointment;

import java.util.Date;
import java.util.Vector;

/**
 * Created by nikkbandy on 10/13/15.
 */
public class User {
    private String name;
    private String uuid;
    private String email;
    private Vector<Appointment> appts;

    public User(String nm, String uuid, String mail){
        //TODO: validation & encryption (?)
        name = nm;
        uuid = uuid;
        email = mail;

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
