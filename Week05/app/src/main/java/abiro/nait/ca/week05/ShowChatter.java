package abiro.nait.ca.week05;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ShowChatter extends AppCompatActivity
{
    static final String TAG = "ShowChatter";
    DBManager dbManager;
    SQLiteDatabase database;
    Cursor cursor;
    TextView textview;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_chatter);
        textview = (TextView)findViewById(R.id.text_view_show_chatter);

        dbManager = new DBManager(this);
        database = dbManager.getReadableDatabase();

        Log.d(TAG, "onCreate()");
    }

    @Override
    protected void onResume()
    {
        String sender, date, data, output;
        cursor = database.query(DBManager.TABLE_NAME,
                null,null,null,null,null,DBManager.C_ID + " DESC");

        startManagingCursor(cursor);
        while(cursor.moveToNext())
        {
            sender = cursor.getString(cursor.getColumnIndex(DBManager.C_SENDER));
            date = cursor.getString(cursor.getColumnIndex(DBManager.C_DATE));
            data = cursor.getString(cursor.getColumnIndex(DBManager.C_DATA));

            output = String.format("%s: from %s - %s\n", date, sender, data);

            textview.append(output);
        }
        Log.d(TAG, "onResume()");
        super.onResume();
    }

    @Override
    protected void onDestroy()
    {
        database.close();
        super.onDestroy();
    }

}
