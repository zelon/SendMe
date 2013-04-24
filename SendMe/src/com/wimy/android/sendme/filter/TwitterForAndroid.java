package com.wimy.android.sendme.filter;

import android.content.Context;

import com.wimy.android.sendme.R;
import com.wimy.android.sendme.SendMeData;
import com.wimy.android.sendme.SendMeFilter;

public class TwitterForAndroid implements SendMeFilter {

	@Override
	public SendMeData filter(SendMeData original, Context context) {
		final String checkBody = context.getResources().getString(R.string.filter_TwitterForAndroid_check_body);
		
		if ( original.getBody().indexOf(checkBody) != -1 )
		{
			SendMeData newData = new SendMeData();
			newData.copyFrom(original);
			
			String newSubject = "Twitter : " + original.getBody().substring(original.getBody().indexOf(checkBody) + 4);
			newData.setSubject(newSubject);

			return newData;
		}
		
		return null;
	}

}
