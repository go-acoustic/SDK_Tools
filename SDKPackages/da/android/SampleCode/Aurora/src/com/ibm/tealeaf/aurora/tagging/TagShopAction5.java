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
