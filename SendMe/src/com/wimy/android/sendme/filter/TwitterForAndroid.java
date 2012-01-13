package com.wimy.android.sendme.filter;

import com.wimy.android.sendme.SendMeData;
import com.wimy.android.sendme.SendMeFilter;

public class TwitterForAndroid implements SendMeFilter {

	@Override
	public SendMeData filter(SendMeData original) {
		final String checkSubject = "has shared a tweet with you";
		
		if ( original.getSubject().indexOf(checkSubject) != -1 ) {
			SendMeData newData = new SendMeData();
			newData.copyFrom(original);
			
			String newSubject = original.getBody();
			newData.setSubject(newSubject);

			return newData;
		}
		
		return null;
	}

}
