package com.paul_nikki.cse5236.appointmentpal;

import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.paul_nikki.cse5236.appointmentpal.Activities.BaseAccountActivity;

/**
 * Created by Paul Williams on 12/7/2015.
 */
public class BaseAccountActivityTest extends ActivityInstrumentationTestCase2<BaseAccountActivity> {

    private BaseAccountActivity mBaseAccountActivity;
    private Button back;
    private Button newAppt;
    private TextView title;

    public BaseAccountActivityTest() {
        super(BaseAccountActivity.class);
    }
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        setActivityInitialTouchMode(true);

        mBaseAccountActivity = getActivity();
        newAppt = (Button) mBaseAccountActivity.findViewById(R.id.btnNewAppt);
        back = (Button) mBaseAccountActivity.findViewById(R.id.btnBack);
        title = (TextView) mBaseAccountActivity.findViewById(R.id.titleAppointments);
    }
    public void testNewButton(){
        final View decorView = mBaseAccountActivity.getWindow().getDecorView();
        ViewAsserts.assertOnScreen(decorView, newAppt);
        final ViewGroup.LayoutParams layoutParams =
                newAppt.getLayoutParams();
        assertNotNull(layoutParams);
        String newApptText = "NEW APPOINTMENT";
        assertEquals(newAppt.getText(), newApptText);


    }
    public void testBackButton(){

        final View decorView = mBaseAccountActivity.getWindow().getDecorView();
        ViewAsserts.assertOnScreen(decorView, back);
        final ViewGroup.LayoutParams layoutParams =
                back.getLayoutParams();
        assertNotNull(layoutParams);
        String backText = "BACK";
        assertEquals(back.getText(), backText);
    }

    public void testTitle(){
        final View decorView = mBaseAccountActivity.getWindow().getDecorView();
        ViewAsserts.assertOnScreen(decorView,title);
        final ViewGroup.LayoutParams layoutParams =
                title.getLayoutParams();
        assertNotNull(layoutParams);
        String apptText = "Appointments";
        assertEquals(title.getText(), apptText);

    }
}

