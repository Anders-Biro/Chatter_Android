package ca.nait.abiro.oscarreviewlab;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;

public class ViewReviewListActivity extends ListActivity
{
    ArrayList<HashMap<String, String>> oscarReview = new ArrayList<HashMap<String, String>>();
    String rgSelection = Global.stringToPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oscar_custom_list);

        displayReviews();
    }


    private void displayReviews( ) {
        String[] keys = new String[]{"DATE", "REVIEWER", "CATEGORY", "NOMINEE", "REVIEW"};
        int[] ids = new int[]{R.id.custom_row_date, R.id.custom_row_reviewer,
                R.id.custom_row_category, R.id.custom_row_nominee, R.id.custom_row_review};

        SimpleAdapter adapter = new SimpleAdapter(this, oscarReview, R.layout.activity_view_review_list, keys, ids);

        populateList();

        this.setListAdapter(adapter);
    }

    private void populateList( )
    {
        BufferedReader in = null;

        String categoryURL = String.format("http://www.youcode.ca/Lab01Servlet?CATEGORY=%s", rgSelection);

        try
        {
            HttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet();
            request.setURI(new URI(categoryURL));
            HttpResponse response = client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            String line = "";

            while ((line = in.readLine()) != null)
            {
                HashMap<String, String> temp = new HashMap<String, String>();
                temp.put("DATE", line);

                line = in.readLine();
                temp.put("REVIEWER", line);

                line = in.readLine();
                temp.put("CATEGORY", line);

                line = in.readLine();
                temp.put("NOMINEE", line);

                line = in.readLine();
                temp.put("REVIEW", line);

                oscarReview.add(temp);
            }
            in.close();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Error: " + e, Toast.LENGTH_LONG).show();
        }

    }
}

