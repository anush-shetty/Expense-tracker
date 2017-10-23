package com.example.add;

import java.text.DateFormat;
import java.util.Date;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class AlarmNotification extends BroadcastReceiver {
	//Notification ID to allow future updates
	private static final int NOTIFICATION_ID=1;
	private static final String TAG="AlarmNotification";
	private final CharSequence tickerText="Please Update your expense";
	private final CharSequence contentTitle="PBM";
	private final CharSequence contentText="Your budget has not been updated ";
	
	
	//Notification Action Elements
    private Intent mNotificationIntent;
	private PendingIntent mContentIntent;
	
	
	//Notification vibration
	private long[] mVibratePattern={0,200,200,300};
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		mNotificationIntent=new Intent(context,DisplayResult.class);
		mContentIntent=PendingIntent.getActivity(context, 0,mNotificationIntent, Intent.FLAG_ACTIVITY_NEW_TASK);
	
		NotificationCompat.Builder notification =new NotificationCompat.Builder(context).setTicker(tickerText).setSmallIcon(R.drawable.stats_warning).setAutoCancel(true).setContentTitle(contentTitle).setContentText(contentText).setContentIntent(mContentIntent).setVibrate(mVibratePattern);
	
	//Pass the Notification to NotificationManager
		NotificationManager mNotificationManager =(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		mNotificationManager.notify(NOTIFICATION_ID,notification.build());
		
		Log.i(TAG,"Sending Notification at:"+DateFormat.getDateTimeInstance().format(new Date()));
	}

}
