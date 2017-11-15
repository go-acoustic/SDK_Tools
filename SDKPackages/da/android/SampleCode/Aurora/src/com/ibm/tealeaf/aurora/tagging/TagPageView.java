/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2015
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
package com.ibm.tealeaf.aurora.tagging;

import android.app.Activity;
import android.util.Log;

import com.digitalanalytics.api.DigitalAnalytics;

/**
 * 
 * @author Sohil Shah (sohishah@us.ibm.com)
 * 
 */
public class TagPageView {
    private Activity activity;
    private boolean sessionStarted = false;

    public TagPageView(final Activity activity, final boolean sessionStarted) {
        this.activity = activity;
        this.sessionStarted = sessionStarted;
    }

    public void executeTag() {
        try {
            Boolean success = false;

            // Do some session management
            if (this.sessionStarted) {
                success = DigitalAnalytics.startNewSession(this.activity);
                if (!success) {
                    Log.e(TagConstants.LOG_TAG, "Failure: Starting a New Tag Session");
                    return;
                }
                
                TagSession.getInstance().startNewSession();
            }

            // Perform Tagging
            String pageId = this.activity.getLocalClassName();
            success = DigitalAnalytics.firePageview(pageId, TagConstants.CATEGORY, null, null, null, null);
            if (!success) {
                Log.e(TagConstants.LOG_TAG, "Failure: PageView Tag");
            }

        } catch (final Exception e) {
            Log.e(TagConstants.LOG_TAG, e.getMessage(), e);
        }
    }
}
