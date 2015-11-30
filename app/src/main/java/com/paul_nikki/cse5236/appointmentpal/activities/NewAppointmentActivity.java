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
import com.paul_nikki.cse5236.appointmentpal.Helper.DoctorsAdapter;
import com.paul_nikki.cse5236.appointmentpal.Models.Doctor;
import com.paul_nikki.cse5236.appointmentpal.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

public class NewAppointmentActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnFirstAvailable;
    Button btnFromSchedule;
    Spinner ddlLocations;
    Spinner ddlDoctors;
    String TAG = "NewAppointmentActivity";
    String chosenLocation;
    String chosenDoctor;
    ArrayList<String> locations;
    ArrayList<String> doctorsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_appointment);


        ddlLocations = (Spinner) findViewById(R.id.ddlLocations);
        ddlDoctors = (Spinner) findViewById(R.id.ddlDoctors);
        btnFirstAvailable = (Button) findViewById(R.id.btn_firstAvailable);
        btnFirstAvailable.setOnClickListener(this);
        btnFromSchedule = (Button) findViewById(R.id.btn_fromSchedule);
        btnFromSchedule.setOnClickListener(this);

        locations = new ArrayList<>();
        doctorsList = new ArrayList<>();

        GenerateLocationsList();
    }

    public void GenerateLocationsList(){
        String tag_string_req = "request appointments";

        StringRequest strReq = new StringRequest(Request.Method.GET,
                AppConfig.URL_LOCATIONS, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response);

                try {
                    JSONObject jObj = new JSONObject(response);
                    String error = jObj.getString("error");

                    // Check for error node in json
                    if (error.equals("0")) {
                        // we got a response successfully
                        JSONArray doctors = jObj.getJSONArray("Doctors");
                        for (int i = 0; i < doctors.length(); i++) {
                            JSONObject doctor = doctors.getJSONObject(i);
                            String practicename = doctor.getString("practicename");
                            if(!locations.contains(practicename)){
                                locations.add(practicename);
                            }
                        }
                        Collections.sort(locations, String.CASE_INSENSITIVE_ORDER);
                        ArrayAdapter<String> adapter;
                        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, locations);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        ddlLocations.setAdapter(adapter);
                        ddlLocations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view,
                                                       int position, long id) {
                                chosenLocation = parent.getItemAtPosition(position).toString();
                                doctorsList.clear();
                                ArrayAdapter<String> adapter2 = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, doctorsList);
                                ddlDoctors.setAdapter(adapter2);
                                GenerateDoctorsList();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> arg0) {

                            }
                        });
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

    }

    public void GenerateDoctorsList(){
        String tag_string_req = "request appointments";

        StringRequest strReq = new StringRequest(Request.Method.GET,
                AppConfig.URL_LOCATIONS, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response);

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
                            if(practicename.equals(chosenLocation)){
                                doctorsList.add(doctorName);
                            }
                        }
                        Collections.sort(doctorsList, String.CASE_INSENSITIVE_ORDER);
                        ArrayAdapter<String> adapter;
                        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, doctorsList);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        ddlDoctors.setAdapter(adapter);
                        ddlDoctors.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view,
                                                       int position, long id) {
                                chosenDoctor = parent.getItemAtPosition(position).toString();
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> arg0) {

                            }
                        });
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

    }
    public void onClick(View v) {
        Intent intent;
        /*ListView orOther = (ListView) findViewById(R.id.listView);
        DoctorsAdapter something = (DoctorsAdapter) orOther.getAdapter();
        Doctor selectedDoctor = something.getSelectedDoctor();
        Log.d(TAG, selectedDoctor.getEmail());
        String mail = "drsmith@gmail.com"; // selectedDoctor.getEmail();

        String mail = selectedDoctor.getEmail();*/

        switch (v.getId()) {
            case R.id.btn_firstAvailable:
                intent = new Intent(this, BaseAccountActivity.class);
                intent.putExtra("uuid", getIntent().getStringExtra("uuid"));
                intent.putExtra("name", getIntent().getStringExtra("name"));
                startActivity(intent);
                finish();
                break;
            case R.id.btn_fromSchedule:
                intent = new Intent(this, CalendarActivity.class);

                intent.putExtra("doctorEmail", "drsmith@gmail.com");
                intent.putExtra("uuid", getIntent().getStringExtra("uuid"));
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }
    }
}