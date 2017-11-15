package tl.hybridhtmlembedded;

import android.webkit.WebView;
import android.webkit.WebViewClient;


public class MyWebViewClient extends WebViewClient {

    public MyWebViewClient() {
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
        view.loadUrl(url);
        return true;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void onPageFinished(final WebView view, final String url) {
        view.loadUrl("javascript:TLT.registerBridgeCallbacks([ "
            + "{enabled: true, cbType: 'screenCapture', cbFunction: function (){tlBridge.screenCapture();}},"
            + "{enabled: true, cbType: 'messageRedirect', cbFunction: function (data){tlBridge.addMessage(data);}}]);");
    }
}
