/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
package com.tl.uic.appDarkHoloAuto;

import com.tl.uic.Tealeaf;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class UICAndroidControlsAppActivity extends TabActivity implements TabHost.OnTabChangeListener {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	 				 WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.tab_layout);
        
        TabHost tabHost = getTabHost();  // The activity TabHost
		TabHost.TabSpec spec;  // Reusable TabSpec for each tab
		Intent intent;  // Reusable Intent for each tab
		
		// Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, ControlsActivity1.class);
        
        // Initialize a TabSpec for each tab and add it to the TabHost
        spec = tabHost.newTabSpec("c1").setIndicator("c1").setContent(intent);
        tabHost.addTab(spec);

        intent = new Intent().setClass(this, ControlsActivity2.class);
        spec = tabHost.newTabSpec("c2").setIndicator("c2").setContent(intent);
        tabHost.addTab(spec);
        
        intent = new Intent().setClass(this, ControlsActivity3.class);
        spec = tabHost.newTabSpec("c3").setIndicator("c3").setContent(intent);
        tabHost.addTab(spec);
        
        intent = new Intent().setClass(this, ControlsActivity4.class);
        spec = tabHost.newTabSpec("c4").setIndicator("c4").setContent(intent);
        tabHost.addTab(spec);
        
        intent = new Intent().setClass(this, ControlsActivity5.class);
        spec = tabHost.newTabSpec("c5").setIndicator("c5").setContent(intent);
        tabHost.addTab(spec);
        
        intent = new Intent().setClass(this, ControlsActivity6.class);
        spec = tabHost.newTabSpec("c6").setIndicator("c6").setContent(intent);
        tabHost.addTab(spec);
        
        intent = new Intent().setClass(this, ControlsActivity7.class);
        spec = tabHost.newTabSpec("c7").setIndicator("c7").setContent(intent);
        tabHost.addTab(spec);
        
        intent = new Intent().setClass(this, ControlsActivity8.class);
        spec = tabHost.newTabSpec("c8").setIndicator("c8").setContent(intent);
        tabHost.addTab(spec);
        
        tabHost.setOnTabChangedListener(this);
    }

	@Override
	public void onTabChanged(String arg0) {
		Tealeaf.logEvent(getTabHost().getCurrentTabView(), "click");
	}

//    protected void onResume() {
//        super.onResume();
//    }
    
    /* Add touch event to collect gestures for Tealeaf.
     * 
     * (non-Javadoc)
     * @see android.app.Activity#dispatchTouchEvent(android.view.MotionEvent)
     */
    public boolean dispatchTouchEvent(MotionEvent e)
    {
        return super.dispatchTouchEvent(e);
    }
}
