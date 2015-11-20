package com.paul_nikki.cse5236.appointmentpal.Helper;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.paul_nikki.cse5236.appointmentpal.Models.Doctor;
import com.paul_nikki.cse5236.appointmentpal.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Paul Williams on 11/19/2015.
 */
public class DoctorsAdapter extends BaseAdapter
{
    private Context mContext;
    private ArrayList<Doctor> mDoctorList;
    private int mSelectedDoctor;



    public DoctorsAdapter(Context context, ArrayList<Doctor> doctors, int selectedVariation)
    {
        mContext = context;
        mDoctorList = doctors;
        mSelectedDoctor = selectedVariation;

    }


    @Override
    public int getCount() {
        return mDoctorList.size();
    }

    @Override
    public Doctor getItem(int position) {
        return mDoctorList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public Doctor getSelectedDoctor(){
        return mDoctorList.get(mSelectedDoctor);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        View view = convertView;
        if(view==null)
        {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_doctor, null);
        }

        final Doctor doctor = mDoctorList.get(position);

        TextView name = (TextView) view.findViewById(R.id.name);
        RadioButton radio = (RadioButton) view.findViewById(R.id.radio);

        name.setText(doctor.getName());
        if(position==mSelectedDoctor) radio.setChecked(true);
        else radio.setChecked(false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectedDoctor = position;

                DoctorsAdapter.this.notifyDataSetChanged();
            }
        });

        return view;
    }

}