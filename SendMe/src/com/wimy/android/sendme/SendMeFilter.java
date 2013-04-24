package com.wimy.android.sendme;

import android.content.Context;

public interface SendMeFilter
{
	SendMeData filter(SendMeData original, Context context);
}
