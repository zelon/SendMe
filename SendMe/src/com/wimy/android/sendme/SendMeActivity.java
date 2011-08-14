package com.wimy.android.sendme;

import com.wimy.android.sendme.filter.TweetDeckFacebook;
import com.wimy.android.sendme.filter.TweetDeckTwitter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SendMeActivity extends Activity
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
			new TweetDeckTwitter(),
			new TweetDeckFacebook(),
		};
		
		for ( SendMeFilter filter : filters ) {
			SendMeData filtered = filter.filter(sendMeData);
			
			if ( filtered != null ) {
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

		emailIntent.setType("plain/text");

		emailIntent.putExtra(Intent.EXTRA_EMAIL,
				new String[] { "zelonion@gmail.com" });

		emailIntent.putExtra(Intent.EXTRA_SUBJECT,
				"[SendMe]   " + sendMeData.getSubject());

		emailIntent.putExtra(Intent.EXTRA_TEXT, sendMeData.getBody());

		startActivity(emailIntent);
	}

}

