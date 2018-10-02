package ca.nait.abiro.chatter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.audiofx.BassBoost;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class ChatterSendActivity extends AppCompatActivity
        implements View.OnClickListener, SharedPreferences.OnSharedPreferenceChangeListener
{

    SharedPreferences settings;
    View mainView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatter_send);

        settings = PreferenceManager.getDefaultSharedPreferences(this);

        settings.registerOnSharedPreferenceChangeListener(this);

        mainView = this.findViewById(R.id.send_activity_linear_layout);
        String bgColor = settings.getString("main_bg_color_list", "#990000");
        mainView.setBackgroundColor(Color.parseColor(bgColor));

        //this is a hack to allow us to use the main UI thread
        //will be removed when we learn how to multithread.
        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        Button buttonSend = (Button)findViewById(R.id.button_send_data);
        buttonSend.setOnClickListener(this);

        Button buttonView = (Button)findViewById(R.id.button_view_data);
        buttonView.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_item_view_chats:
            {
                Intent intent = new Intent(this, ChatterReceiveActivity.class);
                this.startActivity(intent);
                break;
            }
            case R.id.menu_item_view_list:
            {
                Intent intent = new Intent(this, ChatterListActivity.class);
                this.startActivity(intent);
                break;
            }
            case R.id.menu_item_custom_list:
            {
                Intent intent = new Intent(this, ChatterCustomListActivity.class);
                this.startActivity(intent);
                break;
            }
            case R.id.menu_item_settings:
            {
                Intent intent = new Intent(this, SettingsActivity.class);
                this.startActivity(intent);
                break;
            }
            case R.id.menu_item_grade_spinner:
            {
                Intent intent = new Intent(this, SimpleSpinnerActivity.class);
                this.startActivity(intent);
                break;
            }
            case R.id.menu_item_object_spinner:
            {
                Intent intent = new Intent(this, ChatterObjectActivity.class);
                this.startActivity(intent);
                break;
            }
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.button_send_data:
            {
                EditText editText = (EditText)findViewById(R.id.edittext_send_chat);
                String chatter = editText.getText().toString();
                postToChatter(chatter);
                editText.setText("");
                break;
            }

        }
        Intent intent = new Intent(this, ChatterReceiveActivity.class);
        this.startActivity(intent);

    }

    private void postToChatter(String chatter)
    {
        String userName = settings.getString("user_name", "Unknown");
        try
        {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost("http://www.youcode.ca/JitterServlet");
            List<NameValuePair> formParameters = new ArrayList<NameValuePair>();
            formParameters.add(new BasicNameValuePair("DATA", chatter));
            formParameters.add(new BasicNameValuePair("LOGIN_NAME", userName));
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
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s)
    {
        mainView = findViewById(R.id.send_activity_linear_layout);
        String bgColor = settings.getString("main_bg_color_list", "#009999");
        mainView.setBackgroundColor(Color.parseColor(bgColor));
    }
}







