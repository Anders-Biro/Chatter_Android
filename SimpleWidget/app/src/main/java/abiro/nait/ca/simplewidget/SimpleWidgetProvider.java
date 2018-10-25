package abiro.nait.ca.simplewidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.widget.RemoteViews;

/**
 * Created by abiro1 on 10/25/2018.
 */

public class SimpleWidgetProvider extends AppWidgetProvider
{
    RemoteViews views;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
    {
        ComponentName component = new ComponentName(context, SimpleWidgetProvider.class);
        views = new RemoteViews(context.getPackageName(), R.layout.main);
        appWidgetManager.updateAppWidget(component, views);
    }
}
