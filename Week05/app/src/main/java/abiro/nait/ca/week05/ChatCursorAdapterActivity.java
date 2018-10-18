package abiro.nait.ca.week05;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class ChatCursorAdapterActivity extends AppCompatActivity
{
    static final String TAG = "ChatCursorListActivity";
    DBManager dbManager;
    SQLiteDatabase database;
    Cursor cursor;
    ListView listView;

    @Override
    protected void onResume()
    {
        cursor = database.query(DBManager.TABLE_NAME, null, null, null, null, null,
                DBManager.C_ID + " DESC");
        startManagingCursor(cursor);
        ChatCursorAdapter adapter = new ChatCursorAdapter(this, cursor);
        listView.setAdapter(adapter);

        super.onResume();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_cursor_adapter);

        dbManager = new DBManager(this);
        database = dbManager.getReadableDatabase();
        listView = (ListView)findViewById(R.id.cursor_list);
    }
}
