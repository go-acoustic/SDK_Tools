/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
package com.tl.uic.appDarkHolo;

import com.tl.uic.Tealeaf;
import com.tl.uic.app.UICApplication;

public class MyApplication extends UICApplication {
	@Override
	public void onCreate() {
		super.onCreate();
		//  Not needed when using UICApplication
		//  new Tealeaf(this);
		Tealeaf.enable();
	}
}
