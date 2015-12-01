package com.paul_nikki.cse5236.appointmentpal.Helper;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.paul_nikki.cse5236.appointmentpal.Activities.MainScreenActivity;
import com.paul_nikki.cse5236.appointmentpal.Models.Appointment;
import com.paul_nikki.cse5236.appointmentpal.R;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AppointmentsAdapter extends ArrayAdapter<Appointment> {
    int position_id;

    public AppointmentsAdapter(Context context, ArrayList<Appointment> appointments) {
        super(context, 0, appointments);
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Appointment appointment = getItem(position);

       // Check if an existing view is being reused, otherwise inflate the view
       if (convertView == null) {
          convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_appointment, parent, false);
       }

       // Lookup view for data population
       TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);
       TextView tvName = (TextView) convertView.findViewById(R.id.tvDoctor);
       TextView tvHome = (TextView) convertView.findViewById(R.id.tvLocation);

       // Populate the data into the template view using the data object
       String date = appointment.getDate();

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        try {
            Date appointmentDate = dateformat.parse(date);
            date = appointmentDate.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        tvDate.setText(date);
       tvHome.setText(appointment.getDoctor());

       // Return the completed view to render on screen
       return convertView;
   }

}