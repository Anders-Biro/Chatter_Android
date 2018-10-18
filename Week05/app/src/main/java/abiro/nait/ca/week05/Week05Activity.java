package abiro.nait.ca.week05;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class Week05Activity extends AppCompatActivity implements View.OnClickListener
{
    private static final String TAG = "Week05Activity";
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week05);


        Button postButton = (Button)findViewById(R.id.button_post_message);
        postButton.setOnClickListener(this);

        Log.d(TAG, "onCreate() Called");
    }

    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.button_post_message:
            {
                EditText editText = (EditText)findViewById(R.id.edit_text_message);
                String message = editText.getText().toString();

                pd = ProgressDialog.show(this, "", "Posting message");
                new ChatWriter().execute(message);

                editText.setText("");
                break;
            }
        }
    }

    private void postToChatter(String message)
    {
        try
        {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost("http://www.youcode.ca/JitterServlet");
            List<NameValuePair> formParameters = new ArrayList<NameValuePair>();
            formParameters.add(new BasicNameValuePair("DATA", message));
            formParameters.add(new BasicNameValuePair("LOGIN_NAME", "Ders"));
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(formParameters);
            post.setEntity(formEntity);
            client.execute(post);
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Error: " + e, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId())
        {
            case R.id.menu_item_start_service:
            {
                startService(new Intent(this, GetterService.class));
                break;
            }
            case R.id.menu_item_stop_service:
            {
                stopService(new Intent(this, GetterService.class));
                break;
            }
            case R.id.menu_item_show_chatter:
            {
                startService(new Intent(this, ShowChatter.class));
                break;
            }
            case R.id.menu_item_cursor_list:
            {
                startService(new Intent(this, ChatCursorAdapterActivity.class));
                break;
            }
        }
        return true;
    }
    private class ChatWriter extends AsyncTask<String, Void, String>
    {
        @Override
        protected void onPostExecute(String s)
        {
            pd.dismiss();
        }

        @Override
        protected String doInBackground(String... strings)
        {
            String message = strings[0];

            try
            {
                HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost("http://www.youcode.ca/JitterServlet");
                List<NameValuePair> formParameters = new ArrayList<NameValuePair>();
                formParameters.add(new BasicNameValuePair("DATA", message));
                formParameters.add(new BasicNameValuePair("LOGIN_NAME", "Ders"));
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(formParameters);
                post.setEntity(formEntity);
                client.execute(post);
            }
            catch(Exception e)
            {
                Toast.makeText(Week05Activity.this, "Error: " + e, Toast.LENGTH_LONG).show();
            }

            return null;
        }
    }
}
