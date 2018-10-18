package abiro.nait.ca.week05;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * Created by abiro1 on 10/18/2018.
 */



public class ChatCursorAdapter extends SimpleCursorAdapter
{
    static final String TAG = "ChatCursorAdapter";
    static final String[] columns = {DBManager.C_SENDER, DBManager.C_DATE, DBManager.C_DATA};
    static final int[] textviewIds = {R.id.cursor_sender, R.id.cursor_date, R.id.cursor_message};


    //this method is called once for each record in the associated query
    @Override
    public void bindView(View row, Context context, Cursor cursor)
    {
        super.bindView(row, context, cursor);

        //the following code is an example of how to override the default behavior and/or massage the data
        String strDate = cursor.getString(cursor.getColumnIndex(DBManager.C_DATE));
        Log.d(TAG, "strDate = " + strDate);
        String strShort = strDate.substring(7, 17);
        TextView textView = (TextView)row.findViewById(R.id.cursor_date);
    }

    public ChatCursorAdapter(Context context, Cursor cursor)
    {
        super(context, R.layout.cursor_list_row, cursor, columns, textviewIds);
    }


}
