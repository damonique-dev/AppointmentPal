package com.paul_nikki.cse5236.appointmentpal.Activities;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.paul_nikki.cse5236.appointmentpal.AppConfig;
import com.paul_nikki.cse5236.appointmentpal.Controllers.AppController;
import com.paul_nikki.cse5236.appointmentpal.Helper.AppointmentsAdapter;
import com.paul_nikki.cse5236.appointmentpal.Helper.SessionManager;
import com.paul_nikki.cse5236.appointmentpal.Models.Appointment;
import com.paul_nikki.cse5236.appointmentpal.Models.User;
import com.paul_nikki.cse5236.appointmentpal.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainScreenActivity extends Activity {
 
    private TextView txtGreeting;
    private Button btnAppointments;
    private Button btnLocations;
    private Button btnLogout;
    private SessionManager session;
    private String uuid;
    private String TAG = "MainScreenActivity";
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        txtGreeting = (TextView) findViewById(R.id.name);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnAppointments = (Button) findViewById(R.id.btnAppointments);
        btnLocations = (Button) findViewById(R.id.btnLocations);
        // Construct the data source
        
 
        session = new SessionManager(getApplicationContext());
 
        if (!session.isLoggedIn()) {
            logoutUser();
        }
        Intent i = getIntent();
        String greeting = "Hello, "+i.getStringExtra("name");

        // Displaying the user details on the screen
        txtGreeting.setText(greeting);

 
        // Logout button click event
        btnLogout.setOnClickListener(new View.OnClickListener() {
 
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
        btnLocations.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreenActivity.this,
                        LocationsMainActivity.class);
                startActivity(intent);
                finish();

            }
        });
        btnAppointments.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainScreenActivity.this,
                        BaseAccountActivity.class);
                intent.putExtra("name", getIntent().getStringExtra("name"));
                intent.putExtra("email", getIntent().getStringExtra("email"));
                intent.putExtra("uuid", getIntent().getStringExtra("uuid"));
                startActivity(intent);
                finish();

            }
        });
    }


    /**
     * Logging out the user. Will set isLoggedIn flag to false in shared
     * preferences Clears the user data from sqlite users table
     * */
    private void logoutUser() {
        session.setLogin(false);

        // Launching the login activity
        Intent intent = new Intent(MainScreenActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}