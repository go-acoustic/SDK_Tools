/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
package tl.hybridhtmlembeddedauto;

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