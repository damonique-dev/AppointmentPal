package com.paul_nikki.cse5236.appointmentpal.Helper;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.paul_nikki.cse5236.appointmentpal.Models.Appointment;
import com.paul_nikki.cse5236.appointmentpal.R;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AppointmentAdapter extends ArrayAdapter<Appointment> {

    public ArrayList<Appointment> appointments;
    TextView tvDate;
    TextView tvLocation;
    TextView tvHome;

    public AppointmentAdapter(Context context, int textViewResourceId, ArrayList<Appointment> appointments) {
        super(context, textViewResourceId, appointments);
        this.appointments = appointments;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Appointment appointment = getItem(position);
        View v = convertView;
       // Check if an existing view is being reused, otherwise inflate the view
       if (v == null) {
          v = LayoutInflater.from(getContext()).inflate(R.layout.item_appointment, null);
       }

       // Lookup view for data population
       TextView tvDate = (TextView) v.findViewById(R.id.tvDate);
       TextView tvName = (TextView) v.findViewById(R.id.tvDoctor);
       TextView tvHome = (TextView) v.findViewById(R.id.tvLocation);

       // Populate the data into the template view using the data object

       tvDate.setText(appointment.getDate().toString());
       tvName.setText(appointment.getDoctor());
       tvHome.setText(appointment.getLocation());

       // Return the completed view to render on screen
       return convertView;
   }
}