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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.paul_nikki.cse5236.appointmentpal.AppConfig;
import com.paul_nikki.cse5236.appointmentpal.Controllers.AppController;
import com.paul_nikki.cse5236.appointmentpal.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class LocationsMainActivity extends AppCompatActivity implements View.OnClickListener {
    String TAG = "LocationsMainActivity";
    Button btnFilter;
    EditText filterText;
    ListView locationsList;
    ArrayList<String> locations;
    JSONArray ja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations_main);

        btnFilter = (Button)findViewById(R.id.btn_filterGo);
        btnFilter.setOnClickListener(this);
        filterText = (EditText)findViewById(R.id.txt_filter);
        locationsList = (ListView)findViewById(R.id.list_locations);
        GenerateLocations();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_locations, menu);
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

    public void GenerateLocations(){
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, AppConfig.URL_LOCATIONS, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        try {
                            ja = response.getJSONArray("Doctors");
                            int s = ja.length();
                            locations = new ArrayList<>();
                            for (int i = 0; i < s; i++) {
                                JSONObject jsonobject = ja.getJSONObject(i);
                                String practicename = jsonobject.getString("practicename");
                                if(!locations.contains(practicename)) {
                                    locations.add(practicename);
                                }
                            }
                            GenerateList(locations);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, error.toString() );

                    }
                });

        // Access the RequestQueue through your singleton class.
        AppController.getInstance().addToRequestQueue(jsObjRequest);

    }

    public void filterLocations(){
        ArrayList<String> filteredLocations = new ArrayList<>();
        String filter = filterText.getText().toString();
        for (String word : locations) {
            if (word.matches(filter + "(.*)")) {
                filteredLocations.add(word);
            }
        }
        GenerateList(filteredLocations);
    }

    public void GenerateList(ArrayList<String> listArray){
        if(listArray.size() > 0) {
            Collections.sort(listArray, String.CASE_INSENSITIVE_ORDER);
            ArrayAdapter<String> adapter;
            adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, listArray);
            locationsList.setAdapter(adapter);
            locationsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String locationName = ((TextView) view).getText().toString();
                    Intent intent = new Intent(getApplicationContext(), LocationActivity.class);
                    intent.putExtra("LocationName", locationName);
                    startActivity(intent);
                }
            });
        }
        else{
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("No Results Found");
            builder1.setPositiveButton("Okay",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            filterText.setText("");
                            GenerateList(locations);
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.show();
        }
    }

    public void onClick(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.btn_filterGo:
                filterLocations();
                break;
            default:
                break;
        }
    }
}
