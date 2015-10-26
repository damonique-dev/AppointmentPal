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

public class ConfirmAppointmentActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnConfirm;
    Button btnBackToCal;
    Button btnMap;
    TextView whereText;
    TextView whenText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_appointment);

        btnConfirm = (Button)findViewById(R.id.btn_confirmAppt);
        btnConfirm.setOnClickListener(this);
        btnBackToCal = (Button)findViewById(R.id.btn_backToCalender);
        btnBackToCal.setOnClickListener(this);
        btnMap = (Button)findViewById(R.id.btn_mapConfirm);
        btnMap.setOnClickListener(this);
        whenText = (TextView)findViewById(R.id.lbl_confirmWhen);
        whereText = (TextView)findViewById(R.id.lbl_confirmWhere);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_confirm_appointment, menu);
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
            case R.id.btn_confirmAppt:
                intent = new Intent(this, BaseAccountActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_backToCalender:
                intent = new Intent(this, CalendarActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_mapConfirm:
                intent = new Intent(this, MapActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
