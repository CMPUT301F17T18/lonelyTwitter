package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;

import com.robotium.solo.Solo;

import junit.framework.TestCase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LonelyTwitterActivityTest extends ActivityInstrumentationTestCase2<LonelyTwitterActivity> {
    private Solo solo;

    public LonelyTwitterActivityTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    /**
     * Runs before the starting tests.
     * @throws Exception
     */
    public void setUp() throws Exception{
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void testStart() throws Exception {
        Activity activity = getActivity();

    }

    public void testTweet() {
        // make sure correct activity
        solo.assertCurrentActivity("Wrong Activity", LonelyTwitterActivity.class);
        solo.enterText((EditText) solo.getView(R.id.body), "Test tweet!");
        solo.clickOnButton("Save");

        solo.enterText((EditText) solo.getView(R.id.body), "");
        assertTrue(solo.waitForText("Test Tweet!"));
        solo.clickOnButton("Clear");
    }

    public void testClickTweetList(){

        solo.assertCurrentActivity("Wrong Activity", LonelyTwitterActivity.class);
        solo.clickOnButton("Clear");

        solo.enterText((EditText) solo.getView(R.id.body), "Test Tweet!");
        solo.clickOnButton("Save");
        solo.enterText((EditText) solo.getView(R.id.body), "");
        solo.waitForText("Test Tweet!");

        solo.clickInList(0);
        solo.assertCurrentActivity("wrong activity", EditTweetActivity.class);

    }

    public void testDisplayTweet(){
        solo.assertCurrentActivity("Wrong Activity", LonelyTwitterActivity.class);
        solo.clickOnButton("Clear");

        solo.enterText((EditText) solo.getView(R.id.body), "Test Tweet!");
        solo.clickOnButton("Save");
        solo.enterText((EditText) solo.getView(R.id.body), "");
        solo.waitForText("Test Tweet!");

        solo.clickInList(0);
        solo.assertCurrentActivity("wrong activity", EditTweetActivity.class);
        assertTrue((solo.getView(R.id.MessageTweet).getVisibility() ==  View.VISIBLE));
        assertTrue(solo.waitForText("Test Tweet"));

        assertTrue((solo.getView(R.id.dateTweet).getVisibility() ==  View.VISIBLE));
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
        assertTrue(solo.waitForText(sdf.format( new Date())));

    }


    /**
     * runs after tests.
     * @throws Exception
     */
    public void tearDown() throws Exception{
        solo.finishOpenedActivities();
    }
}