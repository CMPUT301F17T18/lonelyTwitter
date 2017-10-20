package ca.ualberta.cs.lonelytwitter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.text.SimpleDateFormat;

public class EditTweetActivity extends Activity {

    private EditText messageText;
    private EditText dateText;
    private Tweet parameters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_tweet);

        Intent intent = getIntent();
        parameters = (Tweet) intent.getSerializableExtra("counter");



        messageText = (EditText) findViewById(R.id.MessageTweet);
        messageText.setText(parameters.getMessage());

        dateText = (EditText) findViewById(R.id.dateTweet);
        SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
        dateText.setText( sdf.format( parameters.getDate()));

    }
}
