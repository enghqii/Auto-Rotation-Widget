package com.enghqii.autorotationwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.widget.RemoteViews;
import android.widget.Toast;

public class AutoRotationWidget extends AppWidgetProvider {

	public AutoRotationWidget() {
		
	}

	@Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
 
        RemoteViews remoteView = new RemoteViews(context.getPackageName(), R.layout.widget_design);
        remoteView.setTextViewText(R.id.title, "Widget test");
        appWidgetManager.updateAppWidget(appWidgetIds, remoteView);
        
        Toast.makeText(context, "widget clicked", Toast.LENGTH_SHORT).show();
    }
}