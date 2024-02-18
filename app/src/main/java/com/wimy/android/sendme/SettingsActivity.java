package com.wimy.android.sendme;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

public class SettingsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }

    public static String getSubjectResult(Activity activity, String subject) {
        StringBuilder str = new StringBuilder();
        str.append(getSubjectPrefix(activity));
        str.append(subject);

        return str.toString();
    }

    public static String getSubjectPrefix(Activity activity) {
        android.content.SharedPreferences defaultPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        return  defaultPreferences.getString("subject_prefix", "[SendMe] ");
    }

    public static String getCurrentEmail(Activity activity) {
        android.content.SharedPreferences defaultPreferences = PreferenceManager.getDefaultSharedPreferences(activity);
        return defaultPreferences.getString("email_address", "");
    }
}