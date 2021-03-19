/********************************************************************************************
* Copyright (C) 2015 Acoustic, L.P. All rights reserved.
*
* NOTICE: This file contains material that is confidential and proprietary to
* Acoustic, L.P. and/or other developers. No license is granted under any intellectual or
* industrial property rights of Acoustic, L.P. except as may be provided in an agreement with
* Acoustic, L.P. Any unauthorized copying or distribution of content from this file is
* prohibited.
********************************************************************************************/
package com.ibm.tealeaf.aurora.activities.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ibm.tealeaf.aurora.AuroraHelper;
import com.ibm.tealeaf.aurora.R;
import com.ibm.tealeaf.aurora.activities.main.MainActivity;
//import com.tl.uic.Tealeaf;
import com.ibm.tealeaf.aurora.tagging.TagSession;

/**
 * Login fragment
 * 
 * @author cyang
 * 
 */
public class LoginFragment extends Fragment {
    private static final String TAG = "LoginFragment";

    private EditText mLoginUsername;
    private EditText mLoginPassword;
    private Button mLoginButton;
    private TextView mAuroraVersion;
    private TextView mTealeafSession;
    private TextView mTealeafFramework;

    private ProgressDialog mProgressDialog;

    public LoginFragment() {
        // Clear session data
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, parent, false);

        mLoginUsername = (EditText) view.findViewById(R.id.login_username);
        mLoginPassword = (EditText) view.findViewById(R.id.login_password);
        mLoginButton = (Button) view.findViewById(R.id.login_button);

        // Set Tealeaf library info
        mTealeafSession = (TextView) view.findViewById(R.id.tealeaf_sessionId);
        mTealeafFramework = (TextView) view.findViewById(R.id.tealeaf_version);
        mAuroraVersion = (TextView) view.findViewById(R.id.aurora_version);

       // mTealeafSession.setText(Tealeaf.getCurrentSessionId());
       // mTealeafFramework.setText(Tealeaf.getLibraryVersion());
        String versionName = null;
        try {
            versionName = getActivity().getPackageManager().getPackageInfo(getActivity().getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            Log.e(TAG, e.toString());
        }
        mAuroraVersion.setText(versionName);

        if (mLoginButton != null) {
            mLoginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Hide the keyboard
                    final InputMethodManager inputMethodManager = (InputMethodManager) LoginFragment.this.getActivity()
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(LoginFragment.this.getActivity().getCurrentFocus()
                            .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

                    mLoginButton.setEnabled(false);
                    loginButtonClicked();
                }
            });
        }

        // Clear session data when view is loading
        AuroraHelper.clearToken();

        return view;
    }

    /**
     * @category Object Methods
     */
    private void loginButtonClicked() {
        mProgressDialog = ProgressDialog.show(this.getActivity(), null, "Logging In", true);
        AuroraHelper.requestLogin(mLoginUsername.getText().toString(), mLoginPassword.getText().toString(), this);
        
        //TAGGING: Record the username being used to login
        TagSession.getInstance().setCustomerId(mLoginUsername.getText().toString());
    }

    // Called by AuroraHelper
    public final void login(final boolean success, final String message) {
        this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, message);

                mProgressDialog.dismiss();

                // If login was successful, transition to MainActivity
                if (success) {
                    Intent i = new Intent(LoginFragment.this.getActivity(), MainActivity.class);
                    startActivity(i);
                } else {
                    mLoginButton.setEnabled(true);
                }
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
