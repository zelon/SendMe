package com.wimy.android.sendme.filter;

import android.content.Context;

import com.wimy.android.sendme.SendMeData;
import com.wimy.android.sendme.SendMeFilter;

public class TweetDeckTwitter implements SendMeFilter
{

	@Override
	public SendMeData filter(SendMeData original, Context context)
	{
		final String checkSubject = "Tweet forwarded";
		final String checkBody = "Original Tweet";
		
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
