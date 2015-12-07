package com.paul_nikki.cse5236.appointmentpal.Models;

import java.util.Date;
import java.util.UUID;

/**
 * Created by nikkbandy on 10/13/15.
 * edit pw 10/19/15
 */
public class Appointment {
	private String mId;
    public String mDate;
    public String mDoctor;
    private String mDoctorEmail;
    public String mLocation;
    public String mLocationName;

    public Appointment(String uuid, String d, String doc, String docEmail, String loc, String locName){
    	//create unique ID

    	mId = uuid;
        mDate = d;
        mDoctor = doc;
        mDoctorEmail = docEmail;
        mLocation = loc;
        mLocationName = locName;
    }

    public String getId(){
    	return mId;
    }

    public String getDate(){
    	return mDate;
    }

    public String getDoctorEmail() {return mDoctorEmail; }
    public String getLocationName() { return mLocationName; }
    public String getDoctor(){
    	return mDoctor;
    }

    public String getLocation(){
    	return mLocation;
    }




}
