package abiro.nait.ca.mybrowser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view_layout);

        WebView webView = (WebView)findViewById(R.id.webViewB);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://www.nait.ca");
    }
}
