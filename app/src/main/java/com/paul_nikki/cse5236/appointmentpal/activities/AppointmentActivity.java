package com.paul_nikki.cse5236.appointmentpal.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.paul_nikki.cse5236.appointmentpal.AppConfig;
import com.paul_nikki.cse5236.appointmentpal.Controllers.AppController;
import com.paul_nikki.cse5236.appointmentpal.Models.Appointment;
import com.paul_nikki.cse5236.appointmentpal.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AppointmentActivity extends AppCompatActivity implements View.OnClickListener {

    String TAG = "AppointmentActivity";
    Button btnDelAppt;
    Button btnBack;
    TextView dateText;
    TextView doctorText;
    TextView locationText;
    String apptDate;
    String doctorname;
    String locationName;
    String uuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        btnBack = (Button) findViewById(R.id.btn_bacc);
        btnBack.setOnClickListener(this);
        btnDelAppt = (Button)findViewById(R.id.btn_editAppt);
        btnDelAppt.setOnClickListener(this);
        dateText = (TextView)findViewById(R.id.lbl_date);
        doctorText = (TextView)findViewById(R.id.lbl_doctor);
        locationText = (TextView)findViewById(R.id.lbl_location);
        apptDate = getIntent().getStringExtra("date");
        locationName = getIntent().getStringExtra("address");
        uuid = getIntent().getStringExtra("uuid");
        doctorname = getIntent().getStringExtra("doctorname") + " - " + getIntent().getStringExtra("practicename");
        dateText.append(" "+apptDate);
        locationText.append(" " + locationName);
        doctorText.append(" "+doctorname);
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

    public void deleteAppointment(){
        String tag_string_req = "request appointments";

        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_DELETEAPPOINTMENT, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Delete response " + response.toString());

                try {
                    JSONObject jObj = new JSONObject(response);
                    String error = jObj.getString("error");

                    // Check for error node in json
                    if (error.equals("0")) {
                        //toast and go to new intent
                        Toast.makeText(getApplicationContext(), "Successfully deleted", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(AppointmentActivity.this, BaseAccountActivity.class);
                        i.putExtra("uuid", uuid);
                        i.putExtra("name", getIntent().getStringExtra("name"));
                        startActivity(i);
                        finish();

                    } else {
                        //toast the error
                        Toast.makeText(getApplicationContext(),
                                "could not delete", Toast.LENGTH_LONG).show();
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
                params.put("uuid", getIntent().getStringExtra("uuid"));
                params.put("date", getIntent().getStringExtra("date"));
                params.put("doctoremail", getIntent().getStringExtra("doctoremail"));

                return params;
            }
        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }
    public void alertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Add the buttons
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                deleteAppointment();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();

            }
        });
        builder.setMessage("Are you sure you want to delete this appointment?");

        // Create the AlertDialog
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    public void onClick(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.btn_editAppt:
                alertDialog();
                break;
            case R.id.btn_bacc:
                finish();
                break;
            default:
                break;
        }
    }
}
