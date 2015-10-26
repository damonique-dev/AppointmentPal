package com.paul_nikki.cse5236.appointmentpal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CreateLoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText firstNameTextField;
    EditText lastNameTextField;
    EditText emailTextField;
    EditText passwordTextField;
    EditText addressTextField;
    EditText ageTextField;
    EditText confirmPasswordTextField;
    Button btnCreate;
    User newUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_login);

        firstNameTextField = (EditText)findViewById(R.id.txt_firstName);
        lastNameTextField = (EditText)findViewById(R.id.txt_lastName);
        emailTextField = (EditText)findViewById(R.id.txt_email);
        passwordTextField = (EditText)findViewById(R.id.txt_Password);
        addressTextField = (EditText)findViewById(R.id.txt_address);
        phoneNumberTextField = (EditText)findViewById(R.id.)
        confirmPasswordTextField = (EditText)findViewById(R.id.txt_comfirmPassword);
        btnCreate = (Button)findViewById(R.id.btn_create);
        btnCreate.setOnClickListener(this);

        if(passwordTextField != confirmPasswordTextField){
            newUser = new User(firstNameTextField, lastNameTextField, emailTextField, addressTextField, passwordTextField, );
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_login, menu);
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

    public void createAccount(){
        newUser.firstName = firstNameTextField.getText().toString();
        newUser.lastName = lastNameTextField.getText().toString();
        newUser.email = emailTextField.getText().toString();
        newUser.address = addressTextField.getText().toString();
        String birthday = birthdayTextField.getText().toString();
        DateFormat day = new SimpleDateFormat("MM/dd/yyyy");
        Date birth = new Date();
        try {
            birth = day.parse(birthday);
        } catch (ParseException e){
            //Error for wrong birthday input!!
        }
        newUser.birthday = birth;
        if(passwordTextField.getText().toString() == confirmPasswordTextField.getText().toString()) {
            newUser.password = passwordTextField.getText().toString();
        }
        else {
            //Error for not matching password!!
        }

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_create:
                createAccount();
                Intent intent = new Intent(this, MainScreenActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
