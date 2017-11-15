/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2017
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
package tl.hybridhtmlembeddedauto;

import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

import tl.hybridhtmlembeddedauto.MyWebChromeClient;

/**
 * @author ohernandezltmac
 *
 */
public class MyWebView extends WebView {
    /**
     * @param context
     */
    public MyWebView(Context context) {
        super(context);
        init();
    }

    /**
     * @param context
     * @param attrs
     */
    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public MyWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressLint("NewApi")
    @Override
    public void loadUrl(final String url) {
        loadUrl(url, null);
    }

    /**
     * {@inheritDoc}
     */
    public final void loadUrl(final String url, final Map<String, String> extraHeaders) {
        super.loadUrl(url, extraHeaders);
    }

    /**
     * Initializes WebView.
     */
    private void init() {
    	this.setWebChromeClient(new MyWebChromeClient());
    }

}
