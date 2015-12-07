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
import android.widget.TextView;
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

    ArrayList<String> arrayOfStrings= new ArrayList<String>();
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
    TextView headerText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        String doctorEmail = i.getStringExtra("doctorEmail");
        retrieveUnavailableAppts();
        //set layout of activity
        setContentView(R.layout.activity_calendar);

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
        initializeCalendar();
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void initializeCalendar(){

        //set labels
        calendar = (CalendarView) findViewById(R.id.calendar);
        headerText = (TextView) findViewById(R.id.header);
        headerText.setText("Toggle Times to set Availability");
        //calendar settings
        calendar.setShowWeekNumber(false);

        //colors
        calendar.setSelectedWeekBackgroundColor(getResources().getColor(R.color.green));
        calendar.setUnfocusedMonthDateColor(getResources().getColor(R.color.transparent));
        calendar.setWeekSeparatorLineColor(getResources().getColor(R.color.transparent));
        calendar.setSelectedDateVerticalBar(R.color.darkgreen);
        calendar.setOnDateChangeListener(new OnDateChangeListener() {

            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                setVisible();
                month = month+1; //for some reason the numbered months start at 0...
                String dayNumber;
                if(day < 10){
                   dayNumber = "0"+String.valueOf(day);
                }
                else{
                    dayNumber = String.valueOf(day);
                }
                SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                selectedDate = year + "-" + month + "-" + dayNumber;
                String conflictDate;
                String conflictTime;

                for (String x: arrayOfStrings) {
                    String date = x;
                    Log.d(TAG, "fulldate"+date);
                    String shortDate = date.substring(0, 10);
                    Log.d(TAG, "shortdate"+shortDate);
                    Log.d(TAG, "Selected date: "+selectedDate);
                    if (shortDate.equals(selectedDate)) {
                        conflictTime = date.substring(11, 13);
                        Log.d(TAG, "conflict time: "+conflictTime);
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
                    eight.setVisibility(View.INVISIBLE);
                }
                else
                {
                    eight.setVisibility(View.VISIBLE);
                }
                break;
            case "09":
                if (!set) {
                    nine.setVisibility(View.INVISIBLE);
                }
                else
                {
                    nine.setVisibility(View.VISIBLE);
                }
                break;
            case "10":
                if (!set) {
                    ten.setVisibility(View.INVISIBLE);
                }
                else
                {
                    ten.setVisibility(View.VISIBLE);
                }
                break;
            case "11":

                if (!set) {
                    eleven.setVisibility(View.INVISIBLE);
                }
                else
                {
                    eleven.setVisibility(View.VISIBLE);
                }
                break;
            case "12":

                if (!set) {
                    twelve.setVisibility(View.INVISIBLE);
                }
                else
                {
                    twelve.setVisibility(View.VISIBLE);
                }
                break;

            case "13":

                if (!set) {
                    one.setVisibility(View.INVISIBLE);
                }
                else
                {
                    one.setVisibility(View.VISIBLE);
                }
                break;

            case "14":

                if (!set) {
                    two.setVisibility(View.INVISIBLE);
                }
                else
                {
                    two.setVisibility(View.VISIBLE);
                }
                break;
            case "15":


                if (!set) {
                    three.setVisibility(View.INVISIBLE);
                }
                else
                {
                    three.setVisibility(View.VISIBLE);
                }
                break;
            case "16":

                if (!set) {
                    four.setVisibility(View.INVISIBLE);
                }
                else
                {
                    four.setVisibility(View.VISIBLE);
                }
                break;

        }
    }


    public void createAppts(String dt){
        selectedDate = dt;
        String tag_string_req = "create appointments";

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_NEW_APPOINTMENT, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Appointment Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    String error = jObj.getString("error");

                    // Check for error node in json
                    if (error.equals("0")) {
                        //appointment
                        Intent intent = new Intent(CalendarActivity.this, BaseAccountActivity.class);
                        intent.putExtra("uuid", getIntent().getStringExtra("uuid"));
                        intent.putExtra("name", getIntent().getStringExtra("name"));
                        startActivity(intent);
                        finish();


                    } else {
                        // Error in creation. Get the error message
                        String errorMsg = "Couldn't create appointment";//jObj.getString("error_msg");
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
                        "Typical volley error", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("uuid", getIntent().getStringExtra("uuid"));
                params.put("doctoremail", getIntent().getStringExtra("doctorEmail"));
                params.put("date", selectedDate);

                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }

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

                        for (int i = 0; i < appointments.length(); i++) {
                            JSONObject appt = appointments.getJSONObject(i);
                            String date = appt.getString("date").substring(0, 10)+" "+appt.getString("date").substring(11, 19);
                            Log.d(TAG, date);
                            arrayOfStrings.add(i, date);
                        }



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

                params.put("doctoremail", getIntent().getStringExtra("doctorEmail"));


                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }
	public void setVisible(){

        eight.setVisibility(View.VISIBLE);
        nine.setVisibility(View.VISIBLE);
        ten.setVisibility(View.VISIBLE);
        eleven.setVisibility(View.VISIBLE);
        twelve.setVisibility(View.VISIBLE);
        one.setVisibility(View.VISIBLE);
        two.setVisibility(View.VISIBLE);
        three.setVisibility(View.VISIBLE);
        four.setVisibility(View.VISIBLE);
    }
    public void onClick(View v){
        Intent intent = new Intent(this, ConfirmAppointmentActivity.class);

/*        intent.putExtra("uuid", getIntent().getStringExtra("uuid"));
        intent.putExtra("doctorEmail", getIntent().getStringExtra("doctorEmail"));
        intent.putExtra("DoctorName", doctorName);
        intent.putExtra("LocationName", locationName);*/
        switch (v.getId()){

            case R.id.btn_8:
                selectedDate = selectedDate + " 08:00:00";
                alertDialog(selectedDate);
                break;
            case R.id.btn_9:
                selectedDate = selectedDate + " 09:00:00";
                alertDialog(selectedDate);
                break;
            case R.id.btn_10:
                selectedDate = selectedDate + " 10:00:00";
                alertDialog(selectedDate);
                break;
            case R.id.btn_11:
                selectedDate = selectedDate + " 11:00:00";
                alertDialog(selectedDate);
                break;
            case R.id.btn_12:
                selectedDate = selectedDate + " 12:00:00";
                alertDialog(selectedDate);
                break;
            case R.id.btn_1:
                selectedDate = selectedDate + " 13:00:00";
                alertDialog(selectedDate);
                break;
            case R.id.btn_2:
                selectedDate = selectedDate + " 14:00:00";
                alertDialog(selectedDate);
                break;
            case R.id.btn_3:
                selectedDate = selectedDate + " 15:00:00";
                alertDialog(selectedDate);
                break;
            case R.id.btn_4:
                selectedDate = selectedDate + " 16:00:00";
                alertDialog(selectedDate);
                break;

        }
    }
    public void alertDialog(final String dt){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Add the buttons
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                createAppts(dt);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();

            }
        });
        builder.setMessage("Appointment time "+dt.substring(0,10)+" at "+dt.substring(11, 16)+" okay?");

        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
