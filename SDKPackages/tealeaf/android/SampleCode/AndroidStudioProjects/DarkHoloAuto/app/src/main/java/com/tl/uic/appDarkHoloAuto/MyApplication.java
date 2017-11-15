/*******************************************************************************
 * Licensed Materials - Property of IBM
 * 5725-K23
 * (c) Copyright IBM Corp. 2013, 2014
 * US Government Users Restricted Rights - Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
package com.tl.uic.appDarkHoloAuto;

import com.tl.uic.Tealeaf;
import com.tl.uic.app.UICApplication;

import android.app.Application;

public class MyApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
        // TeaCuts (AspectJ) needs to have override methods to add hooks to it.

		// Enable Tealeaf library
		Tealeaf.enable();
	}

    /**
     * {@inheritDoc}
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        // TeaCuts (AspectJ) needs to have override methods to add hooks to it.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        // TeaCuts (AspectJ) needs to have override methods to add hooks to it.
    }
}
