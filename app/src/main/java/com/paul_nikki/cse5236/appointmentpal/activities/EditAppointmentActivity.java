package com.paul_nikki.cse5236.appointmentpal.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.paul_nikki.cse5236.appointmentpal.R;


public class EditAppointmentActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnMap;
    Button btnReschedule;
    Button btnCancel;
    Button btnContact;
    TextView whenText;
    TextView whereText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_appointment);

        btnMap = (Button)findViewById(R.id.btn_map);
        btnMap.setOnClickListener(this);
        btnReschedule = (Button)findViewById(R.id.btn_reschedule);
        btnReschedule.setOnClickListener(this);
        btnCancel = (Button)findViewById(R.id.btn_cancelAppt);
        btnCancel.setOnClickListener(this);
        btnContact = (Button)findViewById(R.id.btn_contactOffice);
        btnContact.setOnClickListener(this);
        whenText = (TextView)findViewById(R.id.lbl_apptWhen);
        whereText = (TextView)findViewById(R.id.lbl_apptWhere);
        GenerateApptInfo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_appointment, menu);
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

    }

    public void CancelDialog(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Are you sure you want to cancel this appointment?");
        builder1.setCancelable(true);
        builder1.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        //Delete Appointment from database
                        DeleteDialog();
                    }
                });
        builder1.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public void DeleteDialog(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Appointment Canceled");
        builder1.setCancelable(true);
        builder1.setPositiveButton("Okay",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(EditAppointmentActivity.this, AppointmentsMainActivity.class);
                        startActivity(intent);
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public void onClick(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.btn_reschedule:
                intent = new Intent(this, CalendarActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_map:
                intent = new Intent(this, MapActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_cancelAppt:
                CancelDialog();
                break;
            case R.id.btn_contactOffice:
//                intent = new Intent(this, AppointmentsMainActivity.class);
//                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
