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
import android.widget.TextView;

import com.paul_nikki.cse5236.appointmentpal.R;

public class LocationActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnMap;
    TextView headerText;
    TextView apptWhen;
    TextView apptWhere;
    TextView bio;
    Spinner ddlDoctors;
    String[] doctors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        btnMap = (Button)findViewById(R.id.btn_mapLoc);
        btnMap.setOnClickListener(this);
        headerText = (TextView)findViewById(R.id.lbl_locationHeader);
        apptWhen = (TextView)findViewById(R.id.lbl_apptWhen);
        apptWhere = (TextView)findViewById(R.id.lbl_apptWhere);
        bio = (TextView)findViewById(R.id.lbl_doctorBio);
        ddlDoctors = (Spinner)findViewById(R.id.ddl_DoctorsLoc);
        GenerateOfficeInfo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_location, menu);
        return true;
    }

    public void GenerateOfficeInfo(){
        //Example until database is setup

    }

    public void GenerateDoctorsList(){
        //Example Array until database is setup
        doctors = new String[]{"Dr. 1", "Dr. 2", "Dr. 3", "Dr. 4"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, doctors);
        ddlDoctors.setAdapter(adapter);
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
            case R.id.btn_mapLoc:
                intent = new Intent(this, MapActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
