/********************************************************************************************
* Copyright (C) 2015 Acoustic, L.P. All rights reserved.
*
* NOTICE: This file contains material that is confidential and proprietary to
* Acoustic, L.P. and/or other developers. No license is granted under any intellectual or
* industrial property rights of Acoustic, L.P. except as may be provided in an agreement with
* Acoustic, L.P. Any unauthorized copying or distribution of content from this file is
* prohibited.
********************************************************************************************/
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
