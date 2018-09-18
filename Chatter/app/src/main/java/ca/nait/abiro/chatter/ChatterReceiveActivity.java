package ca.nait.abiro.chatter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

public class ChatterReceiveActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatter_receive);

        getFromChatter();
    }

    private void getFromChatter()
    {
        BufferedReader in = null;
        TextView textview = (TextView)findViewById(R.id.textview_receive_chatter);
        try
        {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI("http://www.youcode.ca/JSONServlet"));
            HttpResponse response= client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
           // StringBuffer sb = new StringBuffer("");
            String line = "";
           // String NL = System.getProperty("line.separator");

            while((line = in.readLine()) != null)
            {
                //sb.append(line + NL);
               // System.out.println(line);

                textview.append(line + "\n");
            }
            in.close();

        }
        catch(Exception e)
        {
            Toast.makeText(this, "Error: " + e, Toast.LENGTH_LONG).show();
        }

    }
}













