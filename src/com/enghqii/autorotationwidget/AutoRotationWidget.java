package com.enghqii.autorotationwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

public class AutoRotationWidget extends AppWidgetProvider {

	public static final String ACTION_EVENT = "com.enghqii.widget.ACTION_EVENT";

	public AutoRotationWidget() {

	}

	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);

		String action = intent.getAction();

		if (ACTION_EVENT.equals(action)) {
			this.onButtonClick(context);
		}
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {

		super.onUpdate(context, appWidgetManager, appWidgetIds);

		RemoteViews remoteView = new RemoteViews(context.getPackageName(),
				R.layout.widget_design);

		// let 'button1' send pendingIntent
		PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
				new Intent(ACTION_EVENT), 0);
		remoteView.setOnClickPendingIntent(R.id.button1, pendingIntent);

		appWidgetManager.updateAppWidget(appWidgetIds, remoteView);
	}

	private void onButtonClick(Context context) {

		Toast.makeText(context, "Do what i want", Toast.LENGTH_SHORT).show();
	}
}