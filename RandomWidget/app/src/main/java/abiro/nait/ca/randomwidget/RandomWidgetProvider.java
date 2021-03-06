package abiro.nait.ca.randomwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.Random;

/**
 * Created by abiro1 on 10/30/2018.
 */

public class RandomWidgetProvider extends AppWidgetProvider
{
    RemoteViews view;
    public static int currentColor = 1;
    private static final String TAG = "RandomWidgetProvider";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
    {
        ComponentName thisWidget = new ComponentName(context, RandomWidgetProvider.class);
        int[] widgetIds = appWidgetManager.getAppWidgetIds(thisWidget);

        for(int widgetId : widgetIds)
        {
            int number= new Random().nextInt(100);
            Log.d(TAG, "color = " + currentColor);

            switch (currentColor)
            {
                case 1:
                {
                    view = new RemoteViews(context.getPackageName(), R.layout.widget_layout_blue);
                    currentColor = 2;
                    break;
                }
                case 2:
                {
                    view = new RemoteViews(context.getPackageName(), R.layout.widget_layout_red);
                    currentColor = 3;
                    break;
                }
                case 3:
                {
                    view = new RemoteViews(context.getPackageName(), R.layout.widget_layout_green);
                    currentColor = 1;
                    break;
                }
            }//end of switch

            Intent intent = new Intent(context, RandomWidgetProvider.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent,
                                                            PendingIntent.FLAG_UPDATE_CURRENT);

            view.setOnClickPendingIntent(R.id.textview_random_number, pendingIntent);
            view.setTextViewText(R.id.textview_random_number, String.valueOf(number));

            appWidgetManager.updateAppWidget(widgetId, view);
        }
    }
}
