/********************************************************************************************
* Copyright (C) 2015 Acoustic, L.P. All rights reserved.
*
* NOTICE: This file contains material that is confidential and proprietary to
* Acoustic, L.P. and/or other developers. No license is granted under any intellectual or
* industrial property rights of Acoustic, L.P. except as may be provided in an agreement with
* Acoustic, L.P. Any unauthorized copying or distribution of content from this file is
* prohibited.
********************************************************************************************/
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
