package abiro.nait.ca.week05;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

public class GetterService extends Service
{
    static final String TAG = "GetterService";
    static final int DELAY = 10000; //Milliseconds --- 1000 MS in 1 S
    private boolean bRun = false;
    private MyReaderThread reader = null;

    DBManager dbManager;
    SQLiteDatabase database;

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
        dbManager = new DBManager(this);
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
                    //read from chatter
                    //store new messages in onboard database
                    getFromChatter();
                    Log.d(TAG, "my thread reader executed one cycle");
                    Thread.sleep(DELAY);
                }
                catch(InterruptedException e)
                {
                    bRun = false;
                }
            }
        }

        private void getFromChatter()
        {
            BufferedReader in = null;
            try
            {
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI("http://www.youcode.ca/Week05Servlet"));
                HttpResponse response= client.execute(request);
                in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                String line = "";

                database = dbManager.getWritableDatabase();
                ContentValues values = new ContentValues();

                while((line = in.readLine()) != null)
                {
                    values.clear();
                    values.put(DBManager.C_ID, Integer.parseInt(line));

                    line = in.readLine();
                    values.put(DBManager.C_SENDER, line);

                    line = in.readLine();
                    values.put(DBManager.C_DATA, line);

                    line = in.readLine();
                    values.put(DBManager.C_DATE, line);

                    try
                    {
                        database.insertOrThrow(DBManager.TABLE_NAME, null, values);
                        Log.d(TAG, "Record inserted");
                    }
                    catch (SQLException e)
                    {
                        //ignore duplicate records
                        Log.d(TAG, "duplicate record");
                    }
                }//closes while
                database.close();
            }
            catch (Exception e)
            {
                //Toast.makeText(this, "Error: " + e, Toast.LENGTH_LONG).show();
            }
        }
    }
}
