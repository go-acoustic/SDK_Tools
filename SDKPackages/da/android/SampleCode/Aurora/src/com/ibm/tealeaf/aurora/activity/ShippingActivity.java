/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2015
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
package com.ibm.tealeaf.aurora.activity;

import com.ibm.tealeaf.aurora.AuroraHelper;
import com.ibm.tealeaf.aurora.R;
import com.ibm.tealeaf.aurora.tagging.TagOrderCompletion;
import com.ibm.tealeaf.aurora.tagging.TagSession;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class ShippingActivity extends ActionBarActivity {
    private static String TAG = ShippingActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.shipping, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
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
        private EditText mAddress;
        private EditText mCity;
        private EditText mState;
        private EditText mZipcode;

        public ProgressDialog mProgressDialog;

        public PlaceholderFragment() {
        }

        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_shipping, container, false);

            mAddress = (EditText) rootView.findViewById(R.id.address);
            mCity = (EditText) rootView.findViewById(R.id.city);
            mState = (EditText) rootView.findViewById(R.id.state);
            mZipcode = (EditText) rootView.findViewById(R.id.zipcode);
            return rootView;
        }

        private boolean validateFormFields() {
            if (mAddress.getText().toString() == null || mAddress.getText().toString().length() == 0) {
                mAddress.setError("Address required");
                return false;
            }
            if (mCity.getText().toString() == null || mCity.getText().toString().length() == 0) {
                mCity.setError("City required");
                return false;
            }
            if (mState.getText().toString() == null || mState.getText().toString().length() == 0) {
                mState.setError("State required");
                return false;
            }
            if (mZipcode.getText().toString() == null || mZipcode.getText().toString().length() == 0) {
                mZipcode.setError("ZIP code required");
                return false;
            }
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            if (id == R.id.action_submit) {
                if (validateFormFields()) {
                    mProgressDialog = ProgressDialog.show(getActivity(), null, "Submitting Your Order", true);
                    AuroraHelper.requestCheckout(getActivity(), mProgressDialog);
                    
                    //TAGGING: Record the city,state,zip 
                    String city = mCity.getText().toString();
                    String state = mState.getText().toString();
                    String zip = mZipcode.getText().toString();
                    TagSession.getInstance().setCity(city);
                    TagSession.getInstance().setState(state);
                    TagSession.getInstance().setZip(zip);
                    
                    //TAGGING: Tag OrderCompletion
                    final TagOrderCompletion tag = new TagOrderCompletion();
                    tag.executeTag();
                    
                }
            }
            return super.onOptionsItemSelected(item);
        }
    }
}
