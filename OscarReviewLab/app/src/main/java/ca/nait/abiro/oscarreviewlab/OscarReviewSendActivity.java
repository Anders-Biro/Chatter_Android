package ca.nait.abiro.oscarreviewlab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class OscarReviewSendActivity extends AppCompatActivity implements View.OnClickListener, SharedPreferences.OnSharedPreferenceChangeListener
{
    View mainView;
    RadioGroup radioGroup;
    RadioButton film, actor, actress, editing, effects;
    SharedPreferences settings;
    String rgSelection = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oscar_review_send);

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

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        film = (RadioButton)findViewById(R.id.radio_best_picture);
        actor = (RadioButton)findViewById(R.id.radio_best_actor);
        actress = (RadioButton)findViewById(R.id.radio_best_actress);
        editing = (RadioButton)findViewById(R.id.radio_film_editing);
        effects = (RadioButton)findViewById(R.id.radio_visual_effects);

        GetSelectedRadioButton();

        Button buttonSend = (Button)findViewById(R.id.button_send_data);
        buttonSend.setOnClickListener(this);

    }


    private void GetSelectedRadioButton()
    {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId)
            {

                switch(group.getCheckedRadioButtonId())
                {
                    case R.id.radio_best_picture:
                        rgSelection = "film";
                        break;
                    case R.id.radio_best_actor:
                        rgSelection = "actor";
                        break;
                    case R.id.radio_best_actress:
                        rgSelection = "actress";
                        break;
                    case R.id.radio_film_editing:
                        rgSelection = "editing";
                        break;
                    case R.id.radio_visual_effects:
                        rgSelection = "effects";
                        break;
                }
                Global.stringToPass = rgSelection;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_item_view_reviews:
            {
                Intent intent = new Intent(this, ViewReviewListActivity.class);
                this.startActivity(intent);
                break;
            }
            case R.id.menu_item_settings:
            {
                Intent intent = new Intent(this, SettingsActivity.class);
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
        EditText editTextReview = (EditText)findViewById(R.id.edittext_send_review);
        EditText editTextNominee = (EditText)findViewById(R.id.edittext_send_nominee);
        String oscarReview = editTextReview.getText().toString();
        String oscarNominee = editTextNominee.getText().toString();
        postToChatter(oscarReview, oscarNominee);
    }



    private void postToChatter(String oscarReview, String oscarNominee)
    {
        String userName = settings.getString("user_name", "Unknown");
        String userPassword = settings.getString("user_password", "");

        try
        {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost("http://www.youcode.ca/Lab01Servlet");
            List<NameValuePair> formParameters = new ArrayList<NameValuePair>();
            formParameters.add(new BasicNameValuePair("REVIEW", oscarReview));
            formParameters.add(new BasicNameValuePair("REVIEWER", userName));
            formParameters.add(new BasicNameValuePair("NOMINEE", oscarNominee));
            formParameters.add(new BasicNameValuePair("CATEGORY", rgSelection));
            formParameters.add(new BasicNameValuePair("PASSWORD", userPassword));
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
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
    {
        mainView = findViewById(R.id.send_activity_linear_layout);
        String bgColor = settings.getString("main_bg_color_list", "#990000");
        mainView.setBackgroundColor(Color.parseColor(bgColor));
    }
}
