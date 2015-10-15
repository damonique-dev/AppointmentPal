package com.paul_nikki.cse5236.appointmentpal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BaseAccountActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnAppts;
    Button btnLocations;
    TextView lblGreeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_account);

        btnAppts = (Button)findViewById(R.id.btn_viewAppts);
        btnAppts.setOnClickListener(this);
        btnLocations = (Button)findViewById(R.id.btn_locations);
        btnLocations.setOnClickListener(this);
        lblGreeting = (TextView)findViewById(R.id.lbl_userGreeting);
        //Set greeting to say Hello, (Users Name) from database!!
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_base_account_page, menu);
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
            case R.id.btn_viewAppts:
                intent = new Intent(this, AppointmentsMainActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_locations:
                intent = new Intent(this, LocationsMainActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
