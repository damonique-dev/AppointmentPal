package com.paul_nikki.cse5236.appointmentpal.Activities;
 
import com.paul_nikki.cse5236.appointmentpal.Helper.SQLiteHandler;
import com.paul_nikki.cse5236.appointmentpal.Helper.sessionManager;
import com.paul_nikki.cse5236.appointmentpal.R;

import java.util.HashMap;
 
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
 
public class MainScreenActivity extends Activity {
 
    private TextView txtName;
    private TextView txtEmail;
    private Button btnAppointments;
    private Button btnLocations;
    private Button btnLogout;
 
    private SQLiteHandler db;
    private sessionManager session;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        txtName = (TextView) findViewById(R.id.name);
        txtEmail = (TextView) findViewById(R.id.email);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnAppointments = (Button) findViewById(R.id.btnAppointments);
        btnLocations = (Button) findViewById(R.id.btnLocations);
 
        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());
 
        // session manager
        session = new sessionManager(getApplicationContext());
 
        if (!session.isLoggedIn()) {
            logoutUser();
        }
 
        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();
 
        String name = user.get("name");
        String email = user.get("email");
 
        // Displaying the user details on the screen
        txtName.setText(name);
        txtEmail.setText(email);
 
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
 
        db.deleteUsers();
 
        // Launching the login activity
        Intent intent = new Intent(MainScreenActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}