package com.paul_nikki.cse5236.appointmentpal.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.paul_nikki.cse5236.appointmentpal.R;

public class AppointmentActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnEditAppt;
    TextView dateText;
    TextView timeText;
    TextView doctorText;
    TextView locationText;
    int apptIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);


        btnEditAppt = (Button)findViewById(R.id.btn_editAppt);
        btnEditAppt.setOnClickListener(this);
        dateText = (TextView)findViewById(R.id.lbl_apptDate);
        timeText = (TextView)findViewById(R.id.lbl_apptTime);
        doctorText = (TextView)findViewById(R.id.lbl_apptDoctor);
        locationText = (TextView)findViewById(R.id.lbl_apptLocation);
        apptIndex = getIntent().getIntExtra("ApptIndex", -1);
        GenerateApptInfo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_appointment_page, menu);
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

    public void GenerateApptInfo(){
        //Example until dataBase is set up
        String ex = String.format("Index %d", apptIndex);
        timeText.setText(ex);
    }

    public void onClick(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.btn_editAppt:
                intent = new Intent(this, EditAppointmentActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
