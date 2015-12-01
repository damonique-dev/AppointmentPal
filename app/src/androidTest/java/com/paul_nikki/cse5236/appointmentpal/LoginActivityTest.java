package com.paul_nikki.cse5236.appointmentpal;


import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;


import com.paul_nikki.cse5236.appointmentpal.Activities.LoginActivity;


/**
 * Created by nikkbandy on 11/28/15.
 *
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest = "app/src/main/AndroidManifest.xml", emulateSdk = 18)
public class LoginActivityTest extends ActivityInstrumentationTestCase2<LoginActivity> {

    Intent intent;
    LoginActivity activity;
    EditText username;
    EditText password;
    Button btnLogin;
    Button btnRegister;

    public LoginActivityTest() {
        super(LoginActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = this.getActivity();
        intent = new Intent(getInstrumentation().getTargetContext(), LoginActivity.class);
        username = (EditText) activity.findViewById(R.id.email);
        password = (EditText) activity.findViewById(R.id.password);
        btnLogin = (Button) activity.findViewById(R.id.btnLogin);
        btnRegister = (Button) activity.findViewById(R.id.btnLinkToRegisterScreen);
    }

    public void testLoginIntent() {
        btnLogin.performClick();
        //ShadowActivity shadowActivity = shadowof_(activity);
    }

    public void testRegisterIntent(){

    }

    protected void tearDown() throws Exception {
        activity.finish();
    }
}
