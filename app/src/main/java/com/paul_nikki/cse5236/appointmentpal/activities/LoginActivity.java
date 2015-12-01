package com.paul_nikki.cse5236.appointmentpal.Activities;
 
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
 
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
 
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.paul_nikki.cse5236.appointmentpal.Models.User;
import com.paul_nikki.cse5236.appointmentpal.R;
import com.paul_nikki.cse5236.appointmentpal.AppConfig;
import com.paul_nikki.cse5236.appointmentpal.Controllers.AppController;
import com.paul_nikki.cse5236.appointmentpal.Helper.SessionManager;
 
public class LoginActivity extends Activity {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private Button btnLogin;
    private Button bypassLogin;

    private Button btnLinkToRegister;
    private EditText inputEmail;
    private EditText inputPassword;
    private ProgressDialog pDialog;
    private SessionManager session;
 
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(bundle) called");
        setContentView(R.layout.activity_login);
 
        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLinkToRegister = (Button) findViewById(R.id.btnLinkToRegisterScreen);
        bypassLogin = (Button) findViewById(R.id.bypassLogin);
        // Progress dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
 
        // Session manager
        session = new SessionManager(getApplicationContext());
 
        // Check if user is already logged in or not
       /* if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(LoginActivity.this, MainScreenActivity.class);
            startActivity(intent);
            finish();
        }*/

        if(!session.isNetworkAvailable(this)){
            btnLogin.setEnabled(false);
            btnLinkToRegister.setEnabled(false);
            NoConnectionDialog();
        }
        else {
            btnLogin.setEnabled(true);
            btnLinkToRegister.setEnabled(true);
        }
 
        // Login button Click Event
        btnLogin.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View view) {
                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
 
                // Check for empty data in the form
                if (!email.isEmpty() && !password.isEmpty()) {
                    // login user
                    checkLogin(email, password);
                } else {
                    // Prompt user to enter credentials
                    Toast.makeText(getApplicationContext(),
                            "Please enter the credentials!", Toast.LENGTH_LONG)
                            .show();
                }
            }
 
        });
 
        // Link to Register Screen
        btnLinkToRegister.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        CreateLoginActivity.class);
                startActivity(i);
                finish();
            }
        });
        bypassLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),
                        MainScreenActivity.class);
                session.setLogin(true);
                i.putExtra("name", "Dan");
                i.putExtra("uuid", "0e2641c0-85f8-11e5-b56d-a5a3292ab26b");
                startActivity(i);
                finish();
            }
        });
    }

    /**
     * function to verify login details in mysql db
     * */
    private void checkLogin(final String email, final String password) {
        // Tag used to cancel the request
        String tag_string_req = "req_login";
 
        pDialog.setMessage("Logging in ...");
        showDialog();
 
        StringRequest strReq = new StringRequest(Method.POST,
                AppConfig.URL_LOGIN, new Response.Listener<String>() {
 
            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Login Response: " + response.toString());
                hideDialog();
 
                try {
                    JSONObject jObj = new JSONObject(response);
                    String error = jObj.getString("error");
 
                    // Check for error node in json
                    if (error.equals("0")) {
                        // user successfully logged in
                        String uid = jObj.getString("uuid");
                        String username = jObj.getString("User");
                        // Create login session
                        User you = new User (username, uid, email);

                        session.setLogin(true);

                        // Launch main activity
                        Intent intent = new Intent(LoginActivity.this,
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
                hideDialog();
            }
        }) {
 
            @Override
            protected Map<String, String> getParams() {
                // Posting parameters to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("password", password);
 
                return params;
            }
 
        };
 
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }
 
    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    public void NoConnectionDialog(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setTitle("No Internet Connection");
        builder1.setMessage("Please connect to the Internet to use this app!");
        builder1.setPositiveButton("Okay",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                        startActivity(intent);
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}