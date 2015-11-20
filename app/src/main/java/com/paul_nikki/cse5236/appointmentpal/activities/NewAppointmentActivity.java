package com.paul_nikki.cse5236.appointmentpal.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.paul_nikki.cse5236.appointmentpal.AppConfig;
import com.paul_nikki.cse5236.appointmentpal.Controllers.AppController;
import com.paul_nikki.cse5236.appointmentpal.Helper.AppointmentsAdapter;
import com.paul_nikki.cse5236.appointmentpal.Helper.DoctorsAdapter;
import com.paul_nikki.cse5236.appointmentpal.Models.Doctor;
import com.paul_nikki.cse5236.appointmentpal.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewAppointmentActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnFirstAvailable;
    Button btnFromSchedule;
    Spinner ddlDoctors;
    String TAG = "NewAppointmentActivity";
    ArrayList<Doctor> doctorArray;
    TextView mLocationDisplay;
    String doctorName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_appointment);
        mLocationDisplay = (TextView) findViewById(R.id.lbl_locationDisplay);
        doctorArray = new ArrayList<Doctor>();
        btnFirstAvailable = (Button) findViewById(R.id.btn_firstAvailable);
        btnFirstAvailable.setOnClickListener(this);
        btnFromSchedule = (Button) findViewById(R.id.btn_fromSchedule);
        btnFromSchedule.setOnClickListener(this);

        String tag_string_req = "request appointments";

        StringRequest strReq = new StringRequest(Request.Method.GET,
                AppConfig.URL_LOCATIONS, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    String error = jObj.getString("error");

                    // Check for error node in json
                    if (error.equals("0")) {
                        // we got a response successfully
                        JSONArray doctors = jObj.getJSONArray("Doctors");
                        for (int i = 0; i < doctors.length(); i++) {
                            JSONObject doctor = doctors.getJSONObject(i);
                            String doctorName = doctor.getString("doctorname");
                            String practicename = doctor.getString("practicename");
                            String doctorEmail = doctor.getString("email");
                            String address = doctor.getString("address");
                            Doctor newDoctor = new Doctor(doctorName, address, practicename, doctorEmail);
                            doctorArray.add(i, newDoctor);
                        }
                    } else {
                        // Error in login. Get the error message
                        String errorMsg = "error getting appointments";//jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Volley Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);


        DoctorsAdapter adapter = new DoctorsAdapter(this, doctorArray, 0);
        // Attach the adapter to a ListView
        ListView listView = (ListView) findViewById(R.id.listView2);

        listView.setAdapter(adapter);
    }

    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_firstAvailable:
                intent = new Intent(this, BaseAccountActivity.class);
                intent.putExtra("uuid", getIntent().getStringExtra("uuid"));
                startActivity(intent);
                finish();
                break;
            case R.id.btn_fromSchedule:
                intent = new Intent(this, CalendarActivity.class);
                //Get doctor name from chosen doctor ddl!
                ListView orOther = (ListView) findViewById(R.id.listView2);
                DoctorsAdapter something = (DoctorsAdapter) orOther.getAdapter();
                Doctor selectedDoctor = something.getSelectedDoctor();
                intent.putExtra("doctorEmail", selectedDoctor.getEmail());
                intent.putExtra("uuid", getIntent().getStringExtra("uuid"));
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }
}