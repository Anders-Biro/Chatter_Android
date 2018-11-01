package abiro.nait.ca.simplewidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.widget.RemoteViews;

/**
 * Created by abiro1 on 10/25/2018.
 */

public class SimpleWidgetProvider extends AppWidgetProvider
{
    RemoteViews view;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds)
    {
        /**
         *
         *  Widget for wifi quick switch
         *
         */
//        ComponentName thisWidget = new ComponentName(context, SimpleWidgetProvider.class);
//        view = new RemoteViews(context.getPackageName(), R.layout.main);
//
//        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
//        for(int widgetId : allWidgetIds)
//        {
//            Intent intent = new Intent(context, SimpleWidgetProvider.class);
//            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
//            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//            view.setOnClickPendingIntent(R.id.textview, pendingIntent);
//            WifiManager wifi = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
//
//            if (wifi.isWifiEnabled())
//            {
//                wifi.setWifiEnabled(false);
//                view.setTextViewText(R.id.textview, "Turn On");
//            }
//            else
//            {
//                wifi.setWifiEnabled(true);
//                view.setTextViewText(R.id.textview, "Turn Off");
//            }
//        }
//
//        appWidgetManager.updateAppWidget(thisWidget, view);

        /**
         *  Widget for battery level displays
         */

        context.getApplicationContext().registerReceiver(batteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        ComponentName thisWidget = new ComponentName(context, SimpleWidgetProvider.class);
        view = new RemoteViews(context.getPackageName(), R.layout.main);



        appWidgetManager.updateAppWidget(thisWidget, view);
    }

    private BroadcastReceiver batteryReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            Integer batteryLevel = intent.getIntExtra("level", -1);
            view.setTextViewText(R.id.textview, batteryLevel.toString() + "%");

            ComponentName cn = new ComponentName(context, SimpleWidgetProvider.class);
            AppWidgetManager.getInstance(context).updateAppWidget(cn, view);
        }
    };
}
