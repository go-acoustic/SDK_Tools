/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
package com.tl.digitalanalytics.tagging;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

public class Helper {

    /**
     * Helper view Alert Dialog for functional testing or tag processing acknowledgement. 
     * 
     * @param activity Calling activity
     * @param success Wether action was success or not
     * @param cls Destination class to be invoke on Intent
     * @param tagSuccess
     * @param tagFailed
     */
    public static void myAlertDialog(final Activity activity, boolean success, final Class <?> cls, final int tagSuccess, final int tagFailed) {


        //TODO: FIX Tealeaf Instrumentation Issue. When this code is activated, TeaCuts breaks with a Stack Trace and App does not load


        /*AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
        
        // set dialog message
        alertDialogBuilder.setCancelable(false)
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // if this button is clicked, just close
                        // the dialog box and do nothing
                        dialog.cancel();
                        
                        if (cls != null) {
                            // Go to Start
                            Intent intent = new Intent(activity, cls);
    
                            // set the flag to clean up the history stack
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                    | Intent.FLAG_ACTIVITY_CLEAR_TOP);
    
                            activity.startActivity(intent);
                        }
                    }
                });

        // tagging should just be silent
        if (success) {
            // set dialog message
            alertDialogBuilder.setMessage(tagSuccess);
        } else {
            alertDialogBuilder.setMessage(tagFailed);

        }
        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();*/
    }
}
