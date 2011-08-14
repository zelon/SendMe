package com.wimy.android.sendme.filter;

import com.wimy.android.sendme.SendMeData;
import com.wimy.android.sendme.SendMeFilter;

public class TweetDeckFacebook implements SendMeFilter
{
	@Override
	public SendMeData filter(SendMeData original)
	{
		final String checkSubject = "Facebook Status";
		final String checkBody = "Original Facebook Status";
		
		if ( original.getSubject().indexOf(checkSubject) != -1
				&& original.getBody().indexOf(checkBody) != -1
				) {
			SendMeData newData = new SendMeData();
			newData.copyFrom(original);
			
			String newSubject = original.getBody().substring(0, original.getBody().indexOf(checkBody) - 2);
			newData.setSubject(newSubject);

			return newData;
		}
		
		return null;
	}

}
