package com.paul_nikki.cse5236.appointmentpal;

import android.test.ActivityInstrumentationTestCase2;

import com.paul_nikki.cse5236.appointmentpal.Activities.BaseAccountActivity;

/**
 * Created by Paul Williams on 12/7/2015.
 */
public class BaseAccountActivityTest extends ActivityInstrumentationTestCase2<BaseAccountActivity> {

    private BaseAccountActivity mBaseAccountActivity;

    public BaseAccountActivityTest() {
        super(BaseAccountActivity.class);
    }
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        setActivityInitialTouchMode(true);


        mBaseAccountActivity = getActivity();
    }

    public void
}

