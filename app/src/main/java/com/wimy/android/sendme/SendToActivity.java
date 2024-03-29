package com.wimy.android.sendme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Make another Intent to email application and finish itself
 * @author zelon
 *
 */
public class SendToActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();

        if (null != bundle)
        {
            SendMeData sendMeData = new SendMeData();

            sendMeData.setSubject(bundle.getString(Intent.EXTRA_SUBJECT));
            sendMeData.setBody(bundle.getString(Intent.EXTRA_TEXT));

            filterData(sendMeData);

            callEmailActivity(sendMeData);
        }

        finish();
    }

    private void filterData(SendMeData sendMeData)
    {
        SendMeFilter [] filters = new SendMeFilter[] {
                new com.wimy.android.sendme.filter.TwitterForAndroid(),
        };

        for ( SendMeFilter filter : filters )
        {
            SendMeData filtered = filter.filter(sendMeData, this);

            if ( filtered != null )
            {
                sendMeData.copyFrom(filtered);
                return;
            }
        }
    }

    private void callEmailActivity(SendMeData sendMeData)
    {
        final Intent emailIntent = new Intent(
                android.content.Intent.ACTION_SEND
        );

        String email = SettingsActivity.getCurrentEmail(this);

        if ( email == null || email.trim().length() <= 2 ) {
            Toast.makeText(this, "Please set your email address before sending", Toast.LENGTH_LONG).show();
            return;
        }

        emailIntent.setType("plain/text");

        emailIntent.putExtra(Intent.EXTRA_EMAIL,
                new String[] { email });

        emailIntent.putExtra(Intent.EXTRA_SUBJECT, SettingsActivity.getSubjectResult(this, sendMeData.getSubject()) );

        emailIntent.putExtra(Intent.EXTRA_TEXT, sendMeData.getBody());

        startActivity(emailIntent);
    }
}
