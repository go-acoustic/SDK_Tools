package tl.hybridhtmlembedded;

import com.ibm.eo.util.LogInternal;

import android.webkit.WebChromeClient;

public class MyWebChromeClient extends WebChromeClient {
	
	/**
     * {@inheritDoc}
     */
    public final void onConsoleMessage(final String message, final int lineNumber, final String sourceID) {
        LogInternal.log("WebView: " + message + " -- From line " + lineNumber + " of " + sourceID);
    }
}