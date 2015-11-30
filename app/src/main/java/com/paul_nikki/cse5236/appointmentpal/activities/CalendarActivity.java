package com.paul_nikki.cse5236.appointmentpal.Activities;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.paul_nikki.cse5236.appointmentpal.AppConfig;
import com.paul_nikki.cse5236.appointmentpal.Controllers.AppController;
import com.paul_nikki.cse5236.appointmentpal.R;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class CalendarActivity extends AppCompatActivity implements View.OnClickListener{
    CalendarView calendar;

    ArrayList<String> arrayOfStrings;
    Button eight;
    Button nine;
    Button ten;
    Button eleven;
    Button twelve;
    Button one;
    Button two;
    Button three;
    Button four;
    String selectedDate;
    String TAG = "CalendarActivity";
    String doctorEmail;
    String doctorName;
    String locationName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //set layout of activity
        setContentView(R.layout.activity_calendar);


        retrieveUnavailableAppts();
        //get buttons
        eight = (Button) findViewById(R.id.btn_8);
        eight.setOnClickListener(this);
        nine = (Button) findViewById(R.id.btn_9);
        nine.setOnClickListener(this);
        ten = (Button) findViewById(R.id.btn_10);
        ten.setOnClickListener(this);
        eleven = (Button) findViewById(R.id.btn_11);
        eleven.setOnClickListener(this);
        twelve = (Button) findViewById(R.id.btn_12);
        twelve.setOnClickListener(this);
        one = (Button) findViewById(R.id.btn_1);
        one.setOnClickListener(this);
        two = (Button) findViewById(R.id.btn_2);
        two.setOnClickListener(this);
        three = (Button) findViewById(R.id.btn_3);
        three.setOnClickListener(this);
        four = (Button) findViewById(R.id.btn_4);
        four.setOnClickListener(this);

        arrayOfStrings = new ArrayList<String>();

        Intent i = getIntent();
        doctorName =  i.getStringExtra("DoctorName");
        locationName = i.getStringExtra("LocationName");

        initializeCalendar();
		
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void initializeCalendar(){
		
        //set labels
        calendar = (CalendarView) findViewById(R.id.calendar);
        //headerText = (TextView) findViewById(R.id.lbl_calendarHeader);

        //calendar settings
        calendar.setShowWeekNumber(false);

        //colors
        calendar.setSelectedWeekBackgroundColor(getResources().getColor(R.color.green));
        calendar.setUnfocusedMonthDateColor(getResources().getColor(R.color.transparent));
        calendar.setWeekSeparatorLineColor(getResources().getColor(R.color.transparent));
        calendar.setSelectedDateVerticalBar(R.color.darkgreen);

        calendar.setOnDateChangeListener(new OnDateChangeListener(){

            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {


                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                selectedDate = year+"-"+month+"-"+day;
                String conflictDate;
                String conflictTime;

                Iterator<String> itr = arrayOfStrings.iterator();
                while (itr.hasNext()) {
                    String date = itr.next();
                    String shortDate = date.substring(0,10);
                    if(date == selectedDate){
                        conflictTime = date.substring(11,12);
                        Log.d(TAG, "conflict time"+conflictTime);
                        setBtn(conflictTime, false);
                    }
                }
            }

        });

    }

    public void setBtn(String btnName, boolean set) {
        switch (btnName) {
            case "08":
                if (!set) {
                    eight.setVisibility(View.GONE);
                }
                break;
            case "09":
                if (!set) {
                    nine.setVisibility(View.GONE);
                }
                break;
            case "10":
                if (!set) {
                    ten.setVisibility(View.GONE);
                }
                break;
            case "11":

                if (!set) {
                    eight.setVisibility(View.GONE);
                }
                break;
            case "12":
                btnName = "12";
                if (!set) {
                    nine.setVisibility(View.GONE);
                }
                break;
            case "13":

                if (!set) {
                    ten.setVisibility(View.GONE);
                }
                break;

            case "14":

                if (!set) {
                    nine.setVisibility(View.GONE);
                }
                break;
            case "15":


                if (!set) {
                    ten.setVisibility(View.GONE);
                }
                break;
            case "16":

                if (!set) {
                    nine.setVisibility(View.GONE);
                }
                break;

        }
    }

//    public void createAppts(){
//
//        String tag_string_req = "create appointments";
//
//        StringRequest strReq = new StringRequest(Request.Method.POST,
//                AppConfig.URL_NEW_APPOINTMENT, new Response.Listener<String>() {
//
//            @Override
//            public void onResponse(String response) {
//                Log.d(TAG, "Appointment Response: " + response.toString());
//
//                try {
//                    JSONObject jObj = new JSONObject(response);
//                    String error = jObj.getString("error");
//
//                    // Check for error node in json
//                    if (error.equals("0")) {
//                        //appointment
//                        Intent intent = new Intent(CalendarActivity.this, BaseAccountActivity.class);
//                        intent.putExtra("uuid", getIntent().getStringExtra("uuid"));
//                        startActivity(intent);
//                        finish();
//
//
//                    } else {
//                        // Error in creation. Get the error message
//                        String errorMsg = "Couldn't create appointment";//jObj.getString("error_msg");
//                        Toast.makeText(getApplicationContext(),
//                                errorMsg, Toast.LENGTH_LONG).show();
//                    }
//                } catch (JSONException e) {
//                    // JSON error
//                    e.printStackTrace();
//                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e(TAG, "Volley Error: " + error.getMessage());
//                Toast.makeText(getApplicationContext(),
//                        error.getMessage(), Toast.LENGTH_LONG).show();
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() {
//                // Posting parameters to login url
//                Map<String, String> params = new HashMap<String, String>();
//                Intent i = getIntent();
//                String doctorEmail = i.getStringExtra("doctorEmail");
//                params.put("uuid", i.getStringExtra("uuid"));
//                params.put("doctoremail", doctorEmail);
//                params.put("appointment", selectedDate);
//
//                return params;
//            }
//        };
//        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
//
//    }

    public void retrieveUnavailableAppts(){

        String tag_string_req = "request appointments";

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_FREE_APPOINTMENTS, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Appointment Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    String error = jObj.getString("error");

                    // Check for error node in json
                    if (error.equals("0")) {
                        // we got a response successfully
                        JSONArray appointments = jObj.getJSONArray("Appointments");
                        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

                        for (int i = 0; i < appointments.length(); i++) {
                            JSONObject appt = appointments.getJSONObject(i);
                            String date = appt.getString("date").substring(0, 10)+" "+appt.getString("date").substring(11, 19);
                            Log.d(TAG, date);
                            arrayOfStrings.add(i, date);
                        }
                        // Create the adapter to convert the array to views



                    } else {
                        // Error in login. Get the error message
                        String errorMsg = "error getting appointments";//jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    // JSON error
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Json error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Volley Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                Intent i = getIntent();
                String doctorEmail = i.getStringExtra("doctorEmail");
                params.put("doctoremail", doctorEmail);
                params.put("uuid", i.getStringExtra("uuid"));
                params.put("date", selectedDate);

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }
	
    public void onClick(View v){
        Intent intent = new Intent(this, ConfirmAppointmentActivity.class);

        intent.putExtra("uuid", getIntent().getStringExtra("uuid"));
        intent.putExtra("doctorEmail", getIntent().getStringExtra("doctorEmail"));
        intent.putExtra("DoctorName", doctorName);
        intent.putExtra("LocationName", locationName);
        switch (v.getId()){

            case R.id.btn_8:
                selectedDate.concat(" 08:00:00");
                intent.putExtra("appointmentDate", selectedDate);
                intent.putExtra("doctoremail", "drsmith@gmail.com");
                startActivity(intent);
                finish();
                break;
            case R.id.btn_9:
                selectedDate.concat(" 09:00:00");
                intent.putExtra("appointmentDate", selectedDate);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_10:
                selectedDate.concat(" 10:00:00");
                intent.putExtra("appointmentDate", selectedDate);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_11:
                selectedDate.concat(" 11:00:00");
                intent.putExtra("appointmentDate", selectedDate);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_12:
                selectedDate.concat(" 12:00:00");
                intent.putExtra("appointmentDate", selectedDate);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_1:
                selectedDate.concat(" 13:00:00");
                intent.putExtra("appointmentDate", selectedDate);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_2:
                selectedDate.concat(" 14:00:00");
                intent.putExtra("appointmentDate", selectedDate);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_3:
                selectedDate.concat(" 15:00:00");
                intent.putExtra("appointmentDate", selectedDate);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_4:
                selectedDate.concat(" 16:00:00");
                intent.putExtra("appointmentDate", selectedDate);
                startActivity(intent);
                finish();
                break;

        }
    }
//    public void alertDialog(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        // Add the buttons
//        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                createAppts();
//            }
//        });
//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                //donothing
//            }
//        });
//        // Set other dialog properties
//
//        // Create the AlertDialog
//        AlertDialog dialog = builder.create();
//
//    }
}
