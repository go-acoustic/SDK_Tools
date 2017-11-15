/*******************************************************************************
 * Licensed Materials - Property of IBM
 * 5725-K23
 * (c) Copyright IBM Corp. 2013, 2014
 * US Government Users Restricted Rights - Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
package com.tl.uic.appDarkHoloAuto;

import android.app.Activity;

public class BaseActivity extends Activity {
	private String logicalPageName;
	
	/**
     * Logical page name of the Activity.
     * 
     * @return Logical page name of the Activity.
     */
    public final String getLogicalPageName() {
        if ((this.logicalPageName == null) || (this.logicalPageName.equals(""))) {
            this.logicalPageName = this.getClass().getName().substring(this.getClass().getName().lastIndexOf('.') + 1);
        }
        return this.logicalPageName;
    }

    /**
     * Logical page name of the Activity.
     * 
     * @param logicalPageName
     *            Logical page name of the Activity.
     */
    public final void setLogicalPageName(final String logicalPageName) {
        this.logicalPageName = logicalPageName;
    }

	/**
     * {@inheritDoc}
     */
    public void onPause() {
        super.onPause();
        // TeaCuts (AspectJ) needs to have override methods to add hooks to it.
    }

    /**
     * {@inheritDoc}
     */
    public void onResume() {
        super.onResume();
        // TeaCuts (AspectJ) needs to have override methods to add hooks to it.
    }

    /**
     * {@inheritDoc}
     */
    public void onDestroy() {
        super.onDestroy();
        // TeaCuts (AspectJ) needs to have override methods to add hooks to it.
    }
}
