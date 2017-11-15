/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
package tl.hybridhtmlembeddedauto;

import android.app.Application;
import com.tl.uic.Tealeaf;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
      super.onCreate();
      Tealeaf.enable();
    }
    @Override
    public void onLowMemory() {

      super.onLowMemory();
    }
    @Override
    public void onTerminate() {
     
      super.onTerminate();
    }
}
