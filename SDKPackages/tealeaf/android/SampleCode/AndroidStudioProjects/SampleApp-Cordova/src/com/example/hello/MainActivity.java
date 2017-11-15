/*
       Licensed to the Apache Software Foundation (ASF) under one
       or more contributor license agreements.  See the NOTICE file
       distributed with this work for additional information
       regarding copyright ownership.  The ASF licenses this file
       to you under the Apache License, Version 2.0 (the
       "License"); you may not use this file except in compliance
       with the License.  You may obtain a copy of the License at

         http://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing,
       software distributed under the License is distributed on an
       "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
       KIND, either express or implied.  See the License for the
       specific language governing permissions and limitations
       under the License.
 */

package com.example.hello;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.tl.uic.Tealeaf;
import com.tl.uic.javascript.JavaScriptInterface;

import org.apache.cordova.CordovaActivity;
import org.apache.cordova.LOG;
import org.apache.cordova.engine.SystemWebViewClient;
import org.apache.cordova.engine.SystemWebViewEngine;

public class MainActivity extends CordovaActivity
{
    public WebView webView;
    public TestViewClient testViewClient;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        Tealeaf tealeaf = new Tealeaf(this.getApplication());
        Tealeaf.enable();

        super.onCreate(savedInstanceState);
        super.init();

        // enable Cordova apps to be started in the background
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.getBoolean("cdvStartInBackground", false)) {
            moveTaskToBack(true);
        }

        // Set by <content src="index.html" /> in config.xml
        loadUrl(launchUrl);

        SystemWebViewEngine engine = (SystemWebViewEngine) this.appView.getEngine();
        testViewClient = new TestViewClient(engine);

        webView = (WebView)engine.getView();
        webView.clearCache(true);
        webView.setWebViewClient(testViewClient);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.addJavascriptInterface(new JavaScriptInterface(this.getApplicationContext(), Tealeaf.getPropertyName((View)webView).getId()), "tlBridge");
    }

    public class TestViewClient extends SystemWebViewClient {
        public TestViewClient(SystemWebViewEngine parentEngine) {
            super(parentEngine);
            LOG.d("userwebview", "TestViewClient()");
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
        public void onPageStarted(final WebView view, final String url) {
            view.loadUrl("javascript:TLT.registerBridgeCallbacks([ "
                    + "{enabled: true, cbType: 'screenCapture', cbFunction: function (){tlBridge.screenCapture();}},"
                    + "{enabled: true, cbType: 'messageRedirect', cbFunction: function (data){tlBridge.addMessage(data);}}]);");
        }
    }

    /**
     * {@inheritDoc}
     */
    public void onPause() {
        Tealeaf.onPause(this, null);
        super.onPause();
    }

    /**
     * {@inheritDoc}
     */
    public void onResume() {
        Tealeaf.onResume(this, null);
        super.onResume();
    }

    /**
     * {@inheritDoc}
     */
    public void onDestroy() {
        Tealeaf.onDestroy(this, null);
        super.onDestroy();
    }

    /* Add touch event to collect gestures for Tealeaf.
 *
 * (non-Javadoc)
 * @see android.app.Activity#dispatchTouchEvent(android.view.MotionEvent)
 */
    public boolean dispatchTouchEvent(MotionEvent e) {
        //detector.onTouchEvent(e);
        Tealeaf.dispatchTouchEvent(this, e);
        return super.dispatchTouchEvent(e);
    }
}
