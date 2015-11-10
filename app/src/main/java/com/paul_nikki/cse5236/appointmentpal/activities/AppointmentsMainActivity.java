package com.paul_nikki.cse5236.appointmentpal.Activities;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
import com.paul_nikki.cse5236.appointmentpal.Helper.AppointmentAdapter;
import com.paul_nikki.cse5236.appointmentpal.Models.Appointment;
import com.paul_nikki.cse5236.appointmentpal.Models.User;
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

public class AppointmentsMainActivity extends ListActivity implements View.OnClickListener, AdapterView.OnItemClickListener{
    String TAG = "AppointmentsMainActivity";
    int numOfAppt = 0;
    String uuid;
    Button btnNewAppt;
    TextView lblApptNumGreeting;
    AppointmentAdapter adapter;
    ArrayList<Appointment> arrayOfAppts = new ArrayList<Appointment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments_main);
        btnNewAppt = (Button)findViewById(R.id.btn_newAppointment);
        btnNewAppt.setOnClickListener(this);

        GenerateApptListView();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_appointments_page, menu);
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
    public void GenerateApptListView(){
        Intent i = getIntent();
        uuid = i.getStringExtra("uuid");

        //Example Array until database is setup

        String tag_string_req = "request appointments";

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_APPOINTMENTS, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    String error = jObj.getString("error");

                    // Check for error node in json
                    if (error.equals("0")) {
                        // we got a response successfully
                        JSONArray appointments = jObj.getJSONArray("Appointments");
                        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

                        for (int i = 0; i < appointments.length(); i++) {
                            numOfAppt++;
                            JSONObject appt = appointments.getJSONObject(i);
                            String date = appt.getString("date").substring(0, 10)+" "+appt.getString("date").substring(11, 19);
                            Log.d(TAG, date);
                            Date realdate = dateformat.parse(date);
                            String doctor = appt.getString("doctorname");
                            String doctoremail =appt.getString("doctoremail");
                            String location = appt.getString("location");
                            Appointment next = new Appointment(realdate, doctor, doctoremail, location);
                            arrayOfAppts.add(i, next);
                        }
                        // Create the adapter to convert the array to views

                        // Attach the adapter to a ListView

                        String greeting = String.format("You have %d Appointments!", numOfAppt);
                        if( numOfAppt == 1) {
                            greeting = String.format("You have %d Appointment!", numOfAppt);
                        }
                        lblApptNumGreeting = (TextView)findViewById(R.id.lbl_numberAppt);
                        lblApptNumGreeting.setText(greeting);
                        adapter = new AppointmentAdapter(getApplicationContext(), R.layout.item_appointment, arrayOfAppts);
                        setListAdapter(adapter);

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


    public void onClick(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.btn_newAppointment:
                intent = new Intent(this, NewAppointmentActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, AppointmentActivity.class);
        intent.putExtra("ApptIndex", position);
        startActivity(intent);
    }

}
