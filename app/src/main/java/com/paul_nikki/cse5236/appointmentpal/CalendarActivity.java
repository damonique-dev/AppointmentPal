package com.paul_nikki.cse5236.appointmentpal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;


public class CalendarActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnNext;
    TextView headerText;
    String doctorName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set layout of activity
        setContentView(R.layout.activity_calendar);

        //initialize calendar
        initializeCalendar();

        doctorName = getIntent().getStringExtra("DoctorName");
        btnNext = (Button)findViewById(R.id.btn_next);
        btnNext.setOnClickListener(this);
        headerText = (TextView)findViewById(R.id.lbl_calendarHeader);

    }

    public void initializeCalendar(){

        calendar = (CalendarView) findViewById(r.id.calendar);

        calendar.setShowWeekNumber(false);

        //colors
        calendar.setSelectedWeekBackgroundColor(getResources().getColor(R.color.green));
        calendar.setUnfocusedMonthDateColor(getResources().getColor(R.color.transparent));    
        calendar.setWeekSeparatorLineColor(getResources().getColor(R.color.transparent));
        calendar.setSelectedDateVerticalBar(getResources().getColor(R.color.darkgreen));

        calendar.setOnDateChangeListener(new OnDateChangeListener(){

            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {

                //display the available appointment times for this day
            }

        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calendar, menu);
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
        String newheader = doctorName +"'s Calendar";
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
