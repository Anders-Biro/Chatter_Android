package abiro.nait.ca.mybrowser;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import static abiro.nait.ca.mybrowser.R.id.buttonTargetA;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonTargetA = (Button)findViewById(R.id.buttonTargetA);
        buttonTargetA.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
                startActivity(intent);
            }
        });

        Button buttonTargetB = (Button)findViewById(R.id.buttonTargetB);
        buttonTargetB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                WebView webview = (WebView)findViewById(R.id.WebViewA);
                webview.getSettings().setJavaScriptEnabled(true);
                webview.loadUrl("http://www.youcode.ca");
            }
        });
    }
}
