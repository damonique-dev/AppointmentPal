package com.paul_nikki.cse5236.appointmentpal;

import java.util.Date;
import java.util.UUID;

/**
 * Created by nikkbandy on 10/13/15.
 * edit pw 10/19/15
 */
public class Appointment {
	private UUID mId;
    private Date mDate;
    private String mDoctor;
    private String mLocation;

    public Appointment(Date d, String doc, String loc){
    	//create unique ID
    	mId = UUID.randomUUID();
    	mDate = d;
    	mDoctor = doc;
    	mLocation = loc;

    }

    public UUID getId(){
    	return mId;
    }

    public Date getDate(){
    	return mDate;
    }

    public String getDoctor(){
    	return mDoctor;
    }

    public String getLocation(){
    	return mLocation;
    }




}
