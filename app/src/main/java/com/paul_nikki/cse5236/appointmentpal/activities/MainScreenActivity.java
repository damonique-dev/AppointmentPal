package com.paul_nikki.cse5236.appointmentpal.Activities;

import com.paul_nikki.cse5236.appointmentpal.Helper.SessionManager;
import com.paul_nikki.cse5236.appointmentpal.Models.User;
import com.paul_nikki.cse5236.appointmentpal.R;

import java.util.HashMap;
 
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
 
public class MainScreenActivity extends Activity {
 
    private TextView txtGreeting;
    private TextView txtEmail;
    private Button btnAppointments;
    private Button btnLocations;
    private Button btnLogout;

    private SessionManager session;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        txtGreeting = (TextView) findViewById(R.id.name);
        txtEmail = (TextView) findViewById(R.id.email);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnAppointments = (Button) findViewById(R.id.btnAppointments);
        btnLocations = (Button) findViewById(R.id.btnLocations);

 
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
                        AppointmentsMainActivity.class);
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