/********************************************************************************************
* Copyright (C) 2015 Acoustic, L.P. All rights reserved.
*
* NOTICE: This file contains material that is confidential and proprietary to
* Acoustic, L.P. and/or other developers. No license is granted under any intellectual or
* industrial property rights of Acoustic, L.P. except as may be provided in an agreement with
* Acoustic, L.P. Any unauthorized copying or distribution of content from this file is
* prohibited.
********************************************************************************************/
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
