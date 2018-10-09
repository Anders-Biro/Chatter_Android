package abiro.nait.ca.week05;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by abiro1 on 10/9/2018.
 */

public class DBManager extends SQLiteOpenHelper
{
    static final String TAG = "DBManager";
    static final String DB_NAME = "chatter.db";
    static final int DB_VERSION = 1;
    static final String TABLE_NAME = "chatter";
    static final String C_ID = BaseColumns._ID;
    static final String C_DATE = "postDate";
    static final String C_SENDER = "sender";
    static final String C_DATA = "data";

    public DBManager(Context context)
    {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion)
    {
        database.execSQL("drop table if exists " + TABLE_NAME);
        Log.d(TAG, "onUpgrade");
        onCreate(database);
    }

    @Override
    public void onCreate(SQLiteDatabase database)
    {
        String sql = "create table " + TABLE_NAME + " (" + C_ID + " int primary key, "
                    + C_DATE + " text, " + C_SENDER + " text, " + C_DATA + " text)";
        Log.d(TAG, sql);
        database.execSQL(sql);
    }
}
