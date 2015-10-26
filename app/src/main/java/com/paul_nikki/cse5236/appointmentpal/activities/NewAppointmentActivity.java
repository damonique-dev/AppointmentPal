package com.paul_nikki.cse5236.appointmentpal.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.paul_nikki.cse5236.appointmentpal.R;

public class NewAppointmentActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnFirstAvailable;
    Button btnFromSchedule;
    Spinner ddlDoctors;
    Spinner ddlLocations;

    String[] doctors;
    String[] locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_appointment);

        btnFirstAvailable = (Button)findViewById(R.id.btn_firstAvailable);
        btnFirstAvailable.setOnClickListener(this);
        btnFromSchedule = (Button)findViewById(R.id.btn_fromSchedule);
        btnFromSchedule.setOnClickListener(this);
        ddlDoctors = (Spinner)findViewById(R.id.ddl_doctors);
        ddlLocations = (Spinner)findViewById(R.id.ddl_locations);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_appointment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.btn_firstAvailable:
//                intent = new Intent(this, AppointmentsMainActivity.class);
//                startActivity(intent);
                break;
            case R.id.btn_fromSchedule:
                intent = new Intent(this, CalendarActivity.class);
                //Get doctor name from chosen doctor ddl!
                intent.putExtra("DoctorName", "Dr. Doctor");
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    public void GenerateDoctorsList(){
        //Example Array until database is setup
        doctors = new String[]{"Dr. 1", "Dr. 2", "Dr. 3", "Dr. 4"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, doctors);
        ddlDoctors.setAdapter(adapter);
    }

    public void GenerateLocationsList(){
        //Example Array until database is setup
        locations = new String[]{"Location 1", "Location 2", "Location 3", "Location 4"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, locations);
        ddlLocations.setAdapter(adapter);
    }
}
