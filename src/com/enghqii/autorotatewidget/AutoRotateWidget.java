package com.enghqii.autorotatewidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.widget.RemoteViews;

public class AutoRotateWidget extends AppWidgetProvider {

	public static final String ACTION_EVENT = "com.enghqii.widget.ACTION_EVENT";

	public AutoRotateWidget() {

	}

	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);

		String action = intent.getAction();

		if (ACTION_EVENT.equals(action)) {
			
			this.onButtonClick(context);

			// to call 'onUpdate'
			AppWidgetManager manager = AppWidgetManager.getInstance(context);
			this.onUpdate(context, AppWidgetManager.getInstance(context),
					manager.getAppWidgetIds(new ComponentName(context,
							AutoRotateWidget.class)));
		}
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {

		super.onUpdate(context, appWidgetManager, appWidgetIds);

		RemoteViews remoteView = new RemoteViews(context.getPackageName(),
				R.layout.widget_design);

		// let 'linearBtn' send pendingIntent
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
				new Intent(ACTION_EVENT), 0);
		
		remoteView.setOnClickPendingIntent(R.id.imageView, pendingIntent);

		// set Background Image
		if (getAutoOrientationEnabled(context.getContentResolver())) {
			remoteView.setImageViewResource(R.id.imageView,
					R.drawable.auto_rotation_on);
		} else {
			remoteView.setImageViewResource(R.id.imageView,
					R.drawable.auto_rotation_off);
		}

		appWidgetManager.updateAppWidget(appWidgetIds, remoteView);
	}

	private void onButtonClick(Context context) {

		ContentResolver resolver = context.getContentResolver();

		if (getAutoOrientationEnabled(resolver)) {
			// auto-rotation is Enabled
			
			setAutoOrientationEnabled(resolver, false);
			//Toast.makeText(context, "DISABLED", Toast.LENGTH_SHORT).show();

		} else {
			
			setAutoOrientationEnabled(resolver, true);
			//Toast.makeText(context, "ENABLED", Toast.LENGTH_SHORT).show();

		}
	}
	
	public boolean getAutoOrientationEnabled(ContentResolver resolver){
		return android.provider.Settings.System.getInt(resolver,
				Settings.System.ACCELEROMETER_ROTATION, 0) == 1;
	}

	public void setAutoOrientationEnabled(ContentResolver resolver,
			boolean enabled) {
		Settings.System.putInt(resolver,
				Settings.System.ACCELEROMETER_ROTATION, enabled ? 1 : 0);
	}
}