package com.paul_nikki.cse5236.appointmentpal.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.paul_nikki.cse5236.appointmentpal.R;

public class LocationsMainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnFilter;
    EditText filterText;
    ListView locationsList;
    String[] locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations_main);

        btnFilter = (Button)findViewById(R.id.btn_filterGo);
        btnFilter.setOnClickListener(this);
        filterText = (EditText)findViewById(R.id.txt_filter);
        locationsList = (ListView)findViewById(R.id.listView_locations);
        GenerateLocationsList();
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

    public void GenerateLocationsList(){
        //Example Array until database is setup
        locations = new String[]{"Location 1", "Location 2", "Location 3", "Location 4"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, locations);
        locationsList.setAdapter(adapter);
    }

    public void onClick(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.btn_filterGo:
//                intent = new Intent(this, AppointmentsMainActivity.class);
//                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
