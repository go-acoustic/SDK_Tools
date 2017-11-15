/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
package com.tl.uic.appDarkHolo;


import com.tl.uic.Tealeaf;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ControlsActivity5 extends BaseFragment {
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.controls5);
		setLogicalPageName("c5");

		final Activity activity = this;
	    Button button = (Button) findViewById(R.id.button1);
	    button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Tealeaf.logEvent(v);
				Builder builder = new Builder(v.getContext());
				String title = getString(R.string.alertTitle);
				String message = getString(R.string.alertMessage1);
			    builder.setMessage(message)
			    	   .setTitle(title)
			    	   .setCancelable(false)
			    	   .setNegativeButton("OK", 
			    			   new DialogInterface.OnClickListener() {
			    		   public void onClick(DialogInterface dialog, int id) {
			    			   Tealeaf.logDialogEvent(dialog, id);
			    			   dialog.cancel();
			    			   Tealeaf.logScreenLayout(activity.getParent(), "c5", 200);
			    		   }
			    	   });
			    final AlertDialog dialog = builder.create();
			    Tealeaf.logScreenLayoutSetOnShowListener(activity.getParent(), dialog);
			    dialog.show();
			}
		});
	    
	    button = (Button) findViewById(R.id.button2);
	    button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Tealeaf.logEvent(v);
				Builder builder = new Builder(v.getContext());
				String title = getString(R.string.alertTitle);
				String message = getString(R.string.alertMessage2);
			    builder.setMessage(message)
			    	   .setTitle(title)
			    	   .setCancelable(false)
			    	   .setPositiveButton("Dismiss", 
			    			   new DialogInterface.OnClickListener() {
			    		   public void onClick(DialogInterface dialog, int id) {
			    			   Tealeaf.logDialogEvent(dialog, id);
			    			   dialog.cancel();
			    			   Tealeaf.logScreenLayout(activity.getParent(),"c5", 200);
			    		   }
			    	   })
			    	   .setNegativeButton("OK", 
			    			   new DialogInterface.OnClickListener() {
			    		   public void onClick(DialogInterface dialog, int id) {
			    			   Tealeaf.logDialogEvent(dialog, id);
			    			   dialog.cancel();
			    			   Tealeaf.logScreenLayout(activity.getParent(), "c5", 200);
			    		   }
			    	   });
			    final AlertDialog dialog = builder.create();
			    Tealeaf.logScreenLayoutSetOnShowListener(activity.getParent(), dialog);
			    dialog.show();
			}
		});
	}
}
