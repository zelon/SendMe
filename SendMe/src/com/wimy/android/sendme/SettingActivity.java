package com.wimy.android.sendme;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Activity for setting. Get email address
 * @author zelon
 *
 */
public class SettingActivity extends Activity {

	private static final String mPref = "default_setting";
	private static final String mEmailKey = "email";
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    setContentView(R.layout.setting);

    	final EditText et = (EditText) findViewById(R.id.email);
    	final SharedPreferences pref = getSharedPreferences(mPref, MODE_PRIVATE); 

    	assert(et != null);
    	
	    String currentEmail = pref.getString(mEmailKey, null);
	    
	    if ( currentEmail != null ) {
	    	Log.i("sendme", "current email is " + currentEmail);
	    	et.setText(currentEmail);
	    }
	    else {
	    	Log.i("sendme", "current email is null");
	    }
	    
	    Button bt = (Button) findViewById(R.id.save_button);
	    bt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String newEmailAddress = et.getText().toString();
				
				SharedPreferences.Editor editor = pref.edit();
				
				editor.putString(mEmailKey, newEmailAddress);
				if ( false == editor.commit() ) {
					Toast.makeText(SettingActivity.this, "Cannot save", Toast.LENGTH_LONG).show();
				}
				else {
					Toast.makeText(SettingActivity.this, "Saved", Toast.LENGTH_LONG).show();
				}
			}
		});
	}
	
	public static String getCurrentEmail(Activity activity) {
		final SharedPreferences pref = activity.getSharedPreferences(mPref, MODE_PRIVATE);
		return pref.getString(mEmailKey, null);
	}

}
