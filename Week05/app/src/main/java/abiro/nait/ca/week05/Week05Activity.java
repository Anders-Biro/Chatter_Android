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

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

public class Week05Activity extends BaseActivity implements View.OnClickListener
{
    private static final String TAG = "Week05Activity";
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week05);

        Button sendButton = (Button)findViewById(R.id.button_post_message);
        sendButton.setOnClickListener(this);

        Log.d(TAG,"onCreate() called");
    }

    @Override
    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.button_post_message:
            {
                EditText text = (EditText) findViewById(R.id.edit_text_message);
                String message = text.getText().toString();
                pd = ProgressDialog.show(this,"", "Posting message");
                new ChatWriter().execute(message);

                text.setText("");
                break;
            }
        }
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
                HttpPost request = new HttpPost("http://www.youcode.ca/JitterServlet");
                List<NameValuePair> postParameters = new ArrayList<NameValuePair>();
                postParameters.add(new BasicNameValuePair("DATA", message));
                postParameters.add(new BasicNameValuePair("LOGIN_NAME", "Gerry"));
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters);
                request.setEntity(formEntity);
                HttpResponse response = client.execute(request);

            }
            catch(Exception e)
            {
                Toast.makeText(Week05Activity.this, "Error: " + e, Toast.LENGTH_LONG).show();
                Log.d(TAG, "Error posting message:" + e);
            }

            return null;
        }
    }
}