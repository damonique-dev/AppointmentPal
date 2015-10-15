package com.paul_nikki.cse5236.appointmentpal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalenderActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnNext;
    TextView headerText;
    String doctorName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doctorName = getIntent().getStringExtra("DoctorName");
        setContentView(R.layout.activity_calender);
        btnNext = (Button)findViewById(R.id.btn_next);
        btnNext.setOnClickListener(this);
        headerText = (TextView)findViewById(R.id.lbl_calenderHeader);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calender, menu);
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

    public void GenerateHeader(){
        String newheader = doctorName +"'s Calender";
        headerText.setText(newheader);
    }

    public void onClick(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.btn_next:
                intent = new Intent(this, ConfirmAppointmentActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
