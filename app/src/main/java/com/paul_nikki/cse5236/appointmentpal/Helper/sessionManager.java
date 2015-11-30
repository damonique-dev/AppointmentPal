package com.paul_nikki.cse5236.appointmentpal.Helper;
 
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.util.Log;

import com.paul_nikki.cse5236.appointmentpal.Models.User;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class SessionManager {
    // LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;
    Editor editor;
    Context _context;
 
    // Shared pref mode
    int PRIVATE_MODE = 0;
 
    // Shared preferences file name
    private static final String PREF_NAME = "AppointmentPalLogin";
     
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    StrictMode.ThreadPolicy policy;

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
        policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
 
    public void setLogin(boolean isLoggedIn) {
 
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        // commit changes
        editor.commit();
 
        Log.d(TAG, "User login session modified!");
    }
     
    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }


    public boolean isNetworkAvailable(Context context){
        ConnectivityManager cm = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if(info != null){
            return hasActiveConnection();
        }
        return false;
    }

    public boolean hasActiveConnection(){
        try{
            HttpURLConnection url = (HttpURLConnection)(new URL("http://www.google.com").openConnection());
            url.setRequestProperty("User-Agent", "Test");
            url.setRequestProperty("Connection", "close");
            url.setConnectTimeout(1500);
            url.connect();
            return(url.getResponseCode() == 200);
        }
        catch(IOException ex){
            Log.e(TAG, "Error checking internet connection", ex);
        }
        return false;
    }
}