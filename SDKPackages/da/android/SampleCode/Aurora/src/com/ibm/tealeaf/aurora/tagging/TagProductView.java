/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2015
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
package com.ibm.tealeaf.aurora.tagging;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.util.Log;

import com.ibm.tealeaf.aurora.AuroraListItem;
import com.digitalanalytics.api.DigitalAnalytics;

/**
 * 
 * @author Sohil Shah (sohishah@us.ibm.com)
 * 
 */
public class TagProductView {
    private Fragment fragment;
    private ArrayList<AuroraListItem> products;

    public TagProductView(final Fragment fragment, final ArrayList<AuroraListItem> products) {
        this.fragment = fragment;
        this.products = products;
    }

    public TagProductView(final Fragment fragment, final AuroraListItem product) {
        final ArrayList<AuroraListItem> products = new ArrayList<AuroraListItem>();
        products.add(product);

        this.fragment = fragment;
        this.products = products;
    }

    public void executeTag() {
        try {
            Boolean success = false;

            String pageId = this.fragment.getClass().getName();
            for (AuroraListItem product : this.products) {
                String productId = product.getId(); // required
                String productName = product.getTitle(); // required

                success = DigitalAnalytics.fireProductview(pageId, productId, productName, TagConstants.CATEGORY, null);
                if (!success) {
                    Log.e(TagConstants.LOG_TAG, "Failure: ProductView Tag: "+productName);
                }
            }

        } catch (final Exception e) {
            Log.e(TagConstants.LOG_TAG, e.getMessage(), e);
        }
    }
}
