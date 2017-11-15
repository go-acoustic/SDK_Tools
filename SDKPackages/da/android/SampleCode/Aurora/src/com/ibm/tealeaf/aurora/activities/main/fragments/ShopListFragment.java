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
import android.widget.ListView;
import android.widget.TextView;

import com.ibm.tealeaf.adapter.ShopAdapter;
import com.ibm.tealeaf.aurora.AuroraListItem;
import com.ibm.tealeaf.aurora.R;
import com.ibm.tealeaf.aurora.tagging.TagProductView;

/**
 * Shop list fragment
 * @author cyang
 *
 */
public abstract class ShopListFragment extends ListFragment {
    protected ShopAdapter adapter;
    private int mListId;
    protected int mContainerId;
    protected boolean mIsProduct;
    private String mListTitle;
    private TextView mListTitleTextView;

    private TextView mPreviousListTitleTextView;
    private String mPreviousListTitle;

    protected abstract void listItemClicked(AuroraListItem entryClicked, int position);

    public ShopListFragment() {
        this.mListTitle = "Categories";
    }

    public ShopListFragment(int viewId, String viewName, String previousViewName) {
        this.mListId = viewId;
        this.mListTitle = viewName;
        this.mPreviousListTitle = previousViewName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop, container, false);

        mContainerId = container.getId();

        this.mListTitleTextView = (TextView) view.findViewById(R.id.shop_bar_title);
        this.mListTitleTextView.setText(this.mListTitle);

        this.mPreviousListTitleTextView = (TextView) view.findViewById(R.id.shop_bar_subtitle);
        this.mPreviousListTitleTextView.setText(this.mPreviousListTitle);

        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        listItemClicked(adapter.getItem(position), position);
    }

    public int getShopListId() {
        return mListId;
    }

    public String getShopListTitle() {
        return mListTitle;
    }

    // Called by AuroraHelper
    public void setShopListAdapter(final ArrayList<AuroraListItem> shopEntryList) {
        setShopListAdapter(shopEntryList, false);
    }

    public void setShopListAdapter(final ArrayList<AuroraListItem> shopEntryList, final boolean isProduct) {
        this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter = new ShopAdapter(getActivity(), shopEntryList);
                setListAdapter(adapter);
                mIsProduct = isProduct;
            }
        });
        
        //TAGGING: If Products are being displayed, fire ProductView tags
        final TagProductView tag = new TagProductView(this, shopEntryList);
        tag.executeTag();
    }
}
