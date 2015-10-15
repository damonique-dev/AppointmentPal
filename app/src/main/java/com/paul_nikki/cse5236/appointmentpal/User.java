package com.paul_nikki.cse5236.appointmentpal;
import java.util.Date;
import java.util.Vector;

/**
 * Created by nikkbandy on 10/13/15.
 */
public class User {
    String firstName;
    String lastName;
    String email;
    String address;
    String phoneNumber;
    Date birthday;
    String password;
    Vector<Appointment> appts;

    public User(){
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
