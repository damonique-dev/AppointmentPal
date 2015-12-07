package com.paul_nikki.cse5236.appointmentpal.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.paul_nikki.cse5236.appointmentpal.AppConfig;
import com.paul_nikki.cse5236.appointmentpal.Controllers.AppController;
import com.paul_nikki.cse5236.appointmentpal.Helper.SessionManager;
import com.paul_nikki.cse5236.appointmentpal.Models.User;
import com.paul_nikki.cse5236.appointmentpal.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Paul Williams on 12/1/2015.
 */
public class SMScodeActivity extends Activity {

    TextView title;
    private EditText inputCode;
    private Button btnSubmitCode;
    String TAG = "SMSCodeActivity";
    private SessionManager session;
    String smscode;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(bundle) called");
        setContentView(R.layout.activity_getsmscode);
        session = new SessionManager(getApplicationContext());
        title = (TextView) findViewById(R.id.titleText);
        inputCode = (EditText) findViewById(R.id.code);
        btnSubmitCode = (Button) findViewById(R.id.btnSubmitCode);
        title.setText("Code sent to phone, submit to login as doctor!");
        btnSubmitCode.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                String tag_string_req = "smscode";

                smscode = inputCode.getText().toString().trim();
                StringRequest strReq = new StringRequest(Request.Method.POST,
                        AppConfig.URL_POSTSMSCODE, new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Login Response: " + response.toString());

                        try {
                            JSONObject jObj = new JSONObject(response);
                            String error = jObj.getString("error");

                            // Check for error node in json
                            if (error.equals("0")) {
                                // user successfully logged in
                                String uid = jObj.getString("uuid");
                                String username = jObj.getString("name");
                                String email = jObj.getString("email");
                                // Create login session
                                session.setLogin(true);
                                // Launch main activity
                                Intent intent = new Intent(SMScodeActivity.this,
                                        MainScreenActivity.class);
                                intent.putExtra("name", username);
                                intent.putExtra("email", email);
                                intent.putExtra("uuid", uid);
                                startActivity(intent);
                                finish();
                            } else {
                                // Error in login. Get the error message
                                String errorMsg = "error in login";//jObj.getString("error_msg");
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
                        Log.e(TAG, "Login Error: " + error.getMessage());
                        Toast.makeText(getApplicationContext(),
                                "Incorrect Username/password", Toast.LENGTH_LONG).show();
                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() {
                        // Posting parameters to login url
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("email", getIntent().getStringExtra("email"));
                        params.put("smscode", smscode);

                        return params;
                    }

                };

                // Adding request to request queue
                AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
            }
        });
    }
}
