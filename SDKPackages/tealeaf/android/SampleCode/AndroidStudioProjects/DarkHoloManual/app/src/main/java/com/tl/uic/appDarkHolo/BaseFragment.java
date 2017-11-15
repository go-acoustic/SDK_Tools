/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
package com.tl.uic.appDarkHolo;

import com.tl.uic.Tealeaf;

import android.app.Activity;

public class BaseFragment extends Activity {
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
        Tealeaf.onPause(this, getLogicalPageName());
        super.onPause();
    }

    /**
     * {@inheritDoc}
     */
    public void onResume() {
        Tealeaf.onResume(this, getLogicalPageName());
        super.onResume();
    }

    /**
     * {@inheritDoc}
     */
    public void onDestroy() {
        Tealeaf.onDestroy(this, getLogicalPageName());
        super.onDestroy();
    }
}
