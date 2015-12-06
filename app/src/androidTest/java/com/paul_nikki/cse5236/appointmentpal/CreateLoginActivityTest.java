package com.paul_nikki.cse5236.appointmentpal;

import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import com.paul_nikki.cse5236.appointmentpal.Activities.CreateLoginActivity;

/**
 * Created by nikkbandy on 12/1/15.
 */
public class CreateLoginActivityTest extends ActivityInstrumentationTestCase2<CreateLoginActivity> {

    private CreateLoginActivity activity;

    public CreateLoginActivityTest() {super(CreateLoginActivity.class);}

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        activity = this.getActivity();
    }

    private void testIntent(){
        startActivity(mLaunchIntent, null, null);
        final Button launchNextButton =
                (Button) getActivity()
                        .findViewById(R.id.launch_next_activity_button);
        launchNextButton.performClick();

        final Intent launchIntent = getStartedActivityIntent();
        assertNotNull("Intent was null", launchIntent);
        assertTrue(isFinishCalled());

        final String payload =
                launchIntent.getStringExtra(NextActivity.EXTRAS_PAYLOAD_KEY);
        assertEquals("Payload is empty", LaunchActivity.STRING_PAYLOAD, payload);
    }
    protected void tearDown() throws Exception {
        activity.finish();
    }
}
