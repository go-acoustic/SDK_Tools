/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2015
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
package com.ibm.tealeaf.aurora.activities.main.fragments;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.ibm.tealeaf.adapter.ShopAdapter;
import com.ibm.tealeaf.aurora.AuroraHelper;
import com.ibm.tealeaf.aurora.AuroraListItem;
import com.ibm.tealeaf.aurora.R;

/**
 * Orders fragment
 * @author cyang
 *
 */
public class OrdersFragment extends ListFragment implements FragmentRefreshListener {
    private ShopAdapter mListAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders, parent, false);
        return view;
    }

    @Override
    public void fragmentIsVisible() {
        if (mListAdapter != null) {
            mListAdapter.clear();
        }
        AuroraHelper.requestOrders(this);
    }

    public void setCartListAdapter(final ArrayList<AuroraListItem> shopEntryList) {
        this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mListAdapter = new ShopAdapter(getActivity(), shopEntryList);
                setListAdapter(mListAdapter);
            }
        });
    }
}
