package com.paul_nikki.cse5236.appointmentpal.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.paul_nikki.cse5236.appointmentpal.AppConfig;
import com.paul_nikki.cse5236.appointmentpal.Controllers.AppController;
import com.paul_nikki.cse5236.appointmentpal.Helper.AppointmentsAdapter;
import com.paul_nikki.cse5236.appointmentpal.Models.Appointment;
import com.paul_nikki.cse5236.appointmentpal.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BaseAccountActivity extends AppCompatActivity  {
    Button back;
    Button newAppt;
    String uuid;
    TextView title;
    String TAG = "BaseAccountActivity";
    ArrayList<String> apptListArray;
    ListView apptListView;
    Date apptDate;
    private ArrayList<Appointment> apptArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_account);
        title = (TextView) findViewById(R.id.titleAppointments);
        apptListView = (ListView) findViewById(R.id.listView);
        apptArray = new ArrayList<>();
        apptListArray = new ArrayList<>();
        generateApptListView();

        back = (Button) findViewById(R.id.btnBack);
        newAppt = (Button)findViewById(R.id.btnNewAppt);

        back.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseAccountActivity.this,
                        MainScreenActivity.class);
                intent.putExtra("name", getIntent().getStringExtra("name"));

                intent.putExtra("uuid", getIntent().getStringExtra("uuid"));
                startActivity(intent);
                finish();

            }
        });

        newAppt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaseAccountActivity.this,
                        NewAppointmentActivity.class);
                intent.putExtra("uuid", getIntent().getStringExtra("uuid"));
                intent.putExtra("name", getIntent().getStringExtra("name"));
                startActivity(intent);
                finish();

            }
        });


    }

    public void generateApptListView(){
        Intent i = getIntent();
        uuid = i.getStringExtra("uuid");



        String tag_string_req = "request appointments";

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_APPOINTMENTS, new Response.Listener<String>() {

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
                            Date realdate = dateformat.parse(date);
                            String doctor = appt.getString("doctorname");
                            String doctoremail =appt.getString("doctoremail");
                            String location = appt.getString("location");
                            Appointment next = new Appointment(uuid, date, doctor, doctoremail, location);
                            apptListArray.add(realdate + " at " + location);
                            apptArray.add(next);
                        }
                        // Create the adapter to convert the array to views
                        ArrayAdapter<String> adapter;
                        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, apptListArray);
                        apptListView.setAdapter(adapter);
                        apptListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                String appt = ((TextView) view).getText().toString();
                                Intent intent = new Intent(getApplicationContext(), AppointmentActivity.class);
                                int index = appt.indexOf(" at ");
                                appt = appt.substring(0, index);
                                String locationName = appt.substring(index+4);
                                intent.putExtra("LocationName", locationName);
                                intent.putExtra("Date", apptDate);
                                intent.putExtra("uuid", getIntent().getStringExtra("uuid"));
                                startActivity(intent);
                            }
                        });

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
                } catch (ParseException e) {
                    e.printStackTrace();
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
                params.put("uuid", uuid);

                return params;
            }
        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);


    }
}
