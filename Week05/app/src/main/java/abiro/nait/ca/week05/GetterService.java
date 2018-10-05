package abiro.nait.ca.week05;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

public class GetterService extends Service
{
    static final String TAG = "GetterService";
    static final int DELAY = 5000; //Milliseconds --- 1000 MS in 1 S
    private boolean bRun = false;
    private MyReaderThread reader = null;

    public GetterService()
    {

    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        reader = new MyReaderThread();
        Log.d(TAG, "onCreate() instantiates a new thread");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        bRun = true;
        reader.start();
        Log.d(TAG, "onStartCommand ... thread started");

        return START_STICKY;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        bRun = false;
        reader.interrupt();
        reader = null;
        Log.d(TAG, "onDestroy() ... thread stopped");
    }
    private class MyReaderThread extends Thread
    {
        public MyReaderThread()
        {
            super("Week05Activity-Reader");
        }

        @Override
        public void run()
        {
            while(bRun == true)
            {
                try
                {
                    Log.d(TAG, "my thread reader executed one cycle");
                    Thread.sleep(DELAY);
                }
                catch(InterruptedException e)
                {
                    bRun = false;
                }
            }
        }
    }
}
