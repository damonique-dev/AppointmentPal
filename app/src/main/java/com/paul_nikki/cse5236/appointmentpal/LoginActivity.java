package com.paul_nikki.cse5236.appointmentpal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText emailTextField;
    EditText passwordTextField;
    Button btnLogin;
    String emailAddress;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailTextField = (EditText)findViewById(R.id.txt_emailLogin);
        passwordTextField = (EditText)findViewById(R.id.txt_passwordLogin);
        btnLogin = (Button)findViewById(R.id.btn_loginLogin);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    public boolean LoginSuccessful(){
        emailAddress = emailTextField.getText().toString();
        password = passwordTextField.getText().toString();
        //If valid login return true
        //else return false

        //For compiling purposes
        return true;
    }

    public void onClick(View v){
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btn_loginLogin:
                intent = new Intent(this, BaseAccountActivity.class);
                startActivity(intent);
//                if(LoginSuccessful()) {
//                    intent = new Intent(this, CreateLoginActivity.class);
//                    startActivity(intent);
//                } else {
//                    //Error with Login!!
//                }
                break;
            default:
                break;
        }
    }
}
