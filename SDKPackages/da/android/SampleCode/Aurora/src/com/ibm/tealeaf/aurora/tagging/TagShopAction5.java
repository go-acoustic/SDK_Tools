/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2015
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
package com.ibm.tealeaf.aurora.tagging;

import android.util.Log;

import com.ibm.tealeaf.aurora.AuroraListItem;
import com.digitalanalytics.api.DigitalAnalytics;

/**
 * 
 * @author Sohil Shah (sohishah@us.ibm.com)
 * 
 */
public class TagShopAction5 {
    private AuroraListItem product;

    public TagShopAction5(final AuroraListItem product) {
        this.product = product;
    }

    public void executeTag() {
        try {
            Boolean success = false;

            // Perform Tagging
            String productId = product.getId();
            String productName = product.getTitle();
            String categoryId = TagConstants.CATEGORY;
            String currency = "USD";
            String baseUnitPrice = product.getPrice();
            String quantity = "1";
            success = DigitalAnalytics.fireShopAction5(productId, productName, quantity, baseUnitPrice, categoryId,
                    currency, null);
            if (!success) {
                Log.e(TagConstants.LOG_TAG, "Failure: ShopActiont5 Tag: " + productName);
            }

        } catch (Exception e) {
            Log.e(TagConstants.LOG_TAG, e.getMessage(), e);
        }
    }
}
