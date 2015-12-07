package com.paul_nikki.cse5236.appointmentpal;

import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.paul_nikki.cse5236.appointmentpal.Activities.BaseAccountActivity;
import com.paul_nikki.cse5236.appointmentpal.Activities.LocationActivity;
import com.paul_nikki.cse5236.appointmentpal.Activities.LoginActivity;

/**
 * Created by Paul Williams on 12/7/2015.
 */
public class LoginActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {

    private LoginActivity  mLoginActivity;
    private Button Register;
    private Button Login;
    private TextView title;
    private TextView description;
    private EditText email;
    private EditText password;

    public LoginActivityTest() {
        super(LoginActivity.class);
    }
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        setActivityInitialTouchMode(true);

        mLoginActivity = getActivity();
        Register = (Button) mLoginActivity.findViewById(R.id.btnLinkToRegisterScreen);
        Login = (Button) mLoginActivity.findViewById(R.id.btnLogin);
        title = (TextView) mLoginActivity.findViewById(R.id.titleText);
        description =(TextView) mLoginActivity.findViewById(R.id.lbl_mainText);
        email =(EditText) mLoginActivity.findViewById(R.id.email);
        password = (EditText) mLoginActivity.findViewById(R.id.password);
    }
    public void testLoginButton(){
        final View decorView = mLoginActivity.getWindow().getDecorView();
        ViewAsserts.assertOnScreen(decorView, Login);
        final ViewGroup.LayoutParams layoutParams = Login.getLayoutParams();
        assertNotNull(layoutParams);
        String loginText = "LOGIN";
        assertEquals(Login.getText(), loginText);


    }
    public void testRegisterButton(){

        final View decorView = mLoginActivity.getWindow().getDecorView();
        ViewAsserts.assertOnScreen(decorView, Register);
        final ViewGroup.LayoutParams layoutParams =
                Register.getLayoutParams();
        assertNotNull(layoutParams);
        String regText = "Not signed up? Register now";
        assertEquals(Register.getText(), regText);
    }

    public void testTitle(){
        final View decorView = mLoginActivity.getWindow().getDecorView();
        ViewAsserts.assertOnScreen(decorView,title);
        final ViewGroup.LayoutParams layoutParams =
                title.getLayoutParams();
        assertNotNull(layoutParams);
        String apptText = "APPOINTMENT PAL";
        assertEquals(title.getText(), apptText);

    }


    public void testDescription(){
        final View decorView = mLoginActivity.getWindow().getDecorView();
        ViewAsserts.assertOnScreen(decorView,description);
        final ViewGroup.LayoutParams layoutParams =
                description.getLayoutParams();
        assertNotNull(layoutParams);
        String descText = "Find, schedule, and manage your doctor appointments securely from your smartphone!";
        assertEquals(description.getText(), descText);

    }

    public void testEmailInput(){
        final View decorView = mLoginActivity.getWindow().getDecorView();
        ViewAsserts.assertOnScreen(decorView,email);
        final ViewGroup.LayoutParams layoutParams =
               email.getLayoutParams();
        assertNotNull(layoutParams);
        String emailHint = "email";
        assertEquals(email.getHint(), emailHint);

    }

    public void testPasswordInput(){
        final View decorView = mLoginActivity.getWindow().getDecorView();
        ViewAsserts.assertOnScreen(decorView,password);
        final ViewGroup.LayoutParams layoutParams =
                password.getLayoutParams();
        assertNotNull(layoutParams);
        String passwordHint = "password";
        assertEquals(email.getHint(), passwordHint);

    }
}


