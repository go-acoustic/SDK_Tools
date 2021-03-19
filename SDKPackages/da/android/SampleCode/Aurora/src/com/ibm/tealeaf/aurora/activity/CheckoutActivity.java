/********************************************************************************************
* Copyright (C) 2015 Acoustic, L.P. All rights reserved.
*
* NOTICE: This file contains material that is confidential and proprietary to
* Acoustic, L.P. and/or other developers. No license is granted under any intellectual or
* industrial property rights of Acoustic, L.P. except as may be provided in an agreement with
* Acoustic, L.P. Any unauthorized copying or distribution of content from this file is
* prohibited.
********************************************************************************************/
package com.ibm.tealeaf.aurora.activity;

import com.ibm.tealeaf.aurora.AuroraHelper;
import com.ibm.tealeaf.aurora.R;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class CheckoutActivity extends ActionBarActivity {
    private static String TAG = CheckoutActivity.class.getSimpleName();

    @SuppressLint("NewApi")
    @Override
    protected final void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
        }
    }

    @Override
    public final boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.checkout, menu);
        return true;
    }

    @Override
    public final boolean onOptionsItemSelected(final MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        final int id = item.getItemId();
        if (id == R.id.action_logout) {
            Log.v(TAG, "Log out clicked");
            AuroraHelper.logOut(this);
            return true;
        } else if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        private EditText mCardNumber;
        private EditText mSecurityCode;
        private EditText mMonth;
        private EditText mYear;

        public PlaceholderFragment() {
        }

        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == R.id.action_next) {
                if (validateFormFields()) {
                    startActivity(new Intent(getActivity(), ShippingActivity.class));
                    return true;
                }
                return false;
            }
            return super.onOptionsItemSelected(item);
        }

        @Override
        public final View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                final Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_checkout, container, false);
            mCardNumber = (EditText) rootView.findViewById(R.id.cardno);
            mSecurityCode = (EditText) rootView.findViewById(R.id.security_code);
            mMonth = (EditText) rootView.findViewById(R.id.month);
            mYear = (EditText) rootView.findViewById(R.id.year);
            
            return rootView;
        }

        private boolean validateFormFields() {
            if (mCardNumber.getText().toString() == null || mCardNumber.getText().toString().length() == 0) {
                mCardNumber.setError("Credit card number required");
                return false;
            }
            if (mSecurityCode.getText().toString() == null || mSecurityCode.getText().toString().length() == 0) {
                mSecurityCode.setError("Security code required");
                return false;
            }
            if (mMonth.getText().toString() == null || mMonth.getText().toString().length() == 0) {
                mMonth.setError("Expiration month required");
                return false;
            }
            if (mYear.getText().toString() == null || mYear.getText().toString().length() == 0) {
                mYear.setError("Expiration year required");
                return false;
            }
            return true;
        }
    }
}
