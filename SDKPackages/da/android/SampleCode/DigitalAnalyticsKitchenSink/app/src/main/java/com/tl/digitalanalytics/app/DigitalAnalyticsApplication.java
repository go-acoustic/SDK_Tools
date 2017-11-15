/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
package com.tl.digitalanalytics.app;

import android.app.Application;
import com.tl.uic.Tealeaf;

import com.digitalanalytics.api.DigitalAnalytics;

/**
 * @author Sohil Shah (sohishah@us.ibm.com)
 * 
 */
public class DigitalAnalyticsApplication extends Application {

    @Override
    public final void onCreate() {
        // Startup the Digital Analytics Module
        DigitalAnalytics.startup(this);

        //Startup the Tealeaf Module
        Tealeaf tealeaf = new Tealeaf(this);
        Tealeaf.enable();


        super.onCreate();
    }

    @Override
    public final void onTerminate() {
        // Shutdown the Digital Analytics Module
        DigitalAnalytics.shutdown();

        //Disable the Tealeaf Module
        Tealeaf.disable();

        super.onTerminate();
    }

    @Override
    public void onLowMemory()
    {
        Tealeaf.onLowMemory();

        super.onLowMemory();
    }
}
