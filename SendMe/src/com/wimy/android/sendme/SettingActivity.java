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
	private static final String mSubjectPrefixKey = "subject_prefix";
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    setContentView(R.layout.setting);

    	final EditText et = (EditText) findViewById(R.id.email);
    	final EditText etSubjectPrefix = (EditText) findViewById(R.id.subject_prefix);
    	
    	final SharedPreferences pref = getSharedPreferences(mPref, MODE_PRIVATE); 

    	assert(et != null);
    	assert(etSubjectPrefix != null);
    	
	    String currentEmail = pref.getString(mEmailKey, null);
	    
	    if ( currentEmail != null ) {
	    	Log.i("sendme", "current email is " + currentEmail);
	    	et.setText(currentEmail);
	    }
	    else {
	    	Log.i("sendme", "current email is null");
	    }
	    
	    String currentSubjectPrefix = SettingActivity.getSubjectPrefix(this);
	    etSubjectPrefix.setText(currentSubjectPrefix);
	    
	    Button bt = (Button) findViewById(R.id.save_button);
	    bt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				String newEmailAddress = et.getText().toString();
				String newEmailSubjectPrefix = etSubjectPrefix.getText().toString();
				
				SharedPreferences.Editor editor = pref.edit();
				
				editor.putString(mEmailKey, newEmailAddress);
				editor.putString(mSubjectPrefixKey, newEmailSubjectPrefix);
				
				if ( false == editor.commit() ) {
					Toast.makeText(SettingActivity.this, "Cannot save", Toast.LENGTH_LONG).show();
				}
				else {
					Toast.makeText(SettingActivity.this, "Saved", Toast.LENGTH_LONG).show();
				}
			}
		});
	}
	
	public static String getSubjectResult(Activity activity, String subject) {
		StringBuilder str = new StringBuilder();
		str.append(getSubjectPrefix(activity));
		str.append(subject);
		
		return str.toString();
	}
	
	public static String getSubjectPrefix(Activity activity) {
		return activity.getSharedPreferences(mPref, MODE_PRIVATE).getString(mSubjectPrefixKey, "[SendMe] ");
	}
	
	public static String getCurrentEmail(Activity activity) {
		final SharedPreferences pref = activity.getSharedPreferences(mPref, MODE_PRIVATE);
		return pref.getString(mEmailKey, null);
	}

}
