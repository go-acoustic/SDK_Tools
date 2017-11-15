/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
package com.tl.digitalanalytics.tagging;

import android.app.Activity;
import android.util.Log;

import com.da.kitchensink.R;
import com.digitalanalytics.api.DigitalAnalytics;

/**
 * 
 * @author Sohil Shah (sohishah@us.ibm.com)
 * 
 */
public class TagPageView {
	private Activity activity;
	private boolean sessionStarted = false;

	public TagPageView(final Activity activity, boolean sessionStarted) {
		this.activity = activity;
		this.sessionStarted = sessionStarted;
	}

	public void executeTag() {
		try {
			Boolean success = true;
			
			// Do some session management
			if (this.sessionStarted) {
				success = DigitalAnalytics.startNewSession(this.activity);
			}

			// Perform Tagging
			String pageId = this.activity.getLocalClassName();
			String categoryId = "mobilesdk";
			success = DigitalAnalytics.firePageview(pageId, categoryId, null, null, null, null);

			this.finish(success);

		} catch (Exception e) {
			Log.e(Constants.LOG_TAG, e.getMessage(), e);
			this.finish(false);
		}
	}

	private void finish(boolean success) {
		// This is for functional testing/user feedback only. In practice,
		// tagging should just be silent
	    Helper.myAlertDialog(activity, success, null, R.string.tag_page_view_success, R.string.tag_page_view_failed);
	}
}
