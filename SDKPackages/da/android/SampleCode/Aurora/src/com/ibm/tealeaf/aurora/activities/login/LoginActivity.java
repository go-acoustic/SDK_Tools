/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2015
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
package com.ibm.tealeaf.aurora.activities.login;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.ibm.tealeaf.aurora.R;
//import com.tl.uic.Tealeaf;
//import com.tl.uic.model.ScreenviewType;

import com.ibm.tealeaf.aurora.tagging.TagPageView;

/**
 * Login activity.
 * @author cyang
 *
 */
public class LoginActivity extends FragmentActivity {

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_container);
				
		this.getSupportFragmentManager().beginTransaction()
			.add(R.id.fragment_container, new LoginFragment())
			.commit();
		
        // this will get layout of page after it being created.
		//Tealeaf.logScreenview(this, getLogicalPageName(), ScreenviewType.LOAD);
        //Tealeaf.logScreenLayoutOnCreate(this, "LoginActivity");
		
		//TAGGING: Invoke PageView Tag and signal start of a new Tagging Session
		final TagPageView tag = new TagPageView(this, true); //true signifies that this is the start of a new tagging session
		tag.executeTag();
	}
	
    protected void onPause() {
        //Tealeaf.onPause(this, getLogicalPageName());
        super.onPause();
    }

    protected void onResume() {
        //Tealeaf.onResume(this, getLogicalPageName());
        super.onResume();
    }

    protected void onDestroy() {
        //Tealeaf.onDestroy(this, getLogicalPageName());
        super.onDestroy();
    }
    
	private String logicalPageName;

    /**
     * Logical page name of the Activity.
     * 
     * @return Logical page name of the Activity.
     */
    public final String getLogicalPageName() {
        if ((this.logicalPageName == null) || (this.logicalPageName.equals(""))) {
            this.logicalPageName = this.getClass().getName().substring(this.getClass().getName().lastIndexOf(".") + 1);
        }
        return this.logicalPageName;
    }

    /**
     * Logical page name of the Activity.
     * 
     * @param logicalPageName Logical page name of the Activity.
     */
    public final void setLogicalPageName(final String logicalPageName) {
        this.logicalPageName = logicalPageName;
    }
}
