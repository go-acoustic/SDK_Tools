/********************************************************************************************
* Copyright (C) 2016 Acoustic, L.P. All rights reserved.
*
* NOTICE: This file contains material that is confidential and proprietary to
* Acoustic, L.P. and/or other developers. No license is granted under any intellectual or
* industrial property rights of Acoustic, L.P. except as may be provided in an agreement with
* Acoustic, L.P. Any unauthorized copying or distribution of content from this file is
* prohibited.
********************************************************************************************/
package com.tl.digitalanalytics.tagging;

import android.app.Activity;
import android.util.Log;

import com.da.kitchensink.R;
import com.digitalanalytics.api.DigitalAnalytics;
import com.tl.digitalanalytics.model.Product;

/**
 * 
 * @author Sohil Shah (sohishah@us.ibm.com)
 * 
 */
public class TagShopAction5 {
	private Product product;
	private Activity activity;
	
	public TagShopAction5(final Activity activity, Product product)
	{
		this.activity = activity;
		this.product = product;
	}

	public void executeTag() {
		try {
			Boolean success = true;

			// Perform Tagging
			String productId = product.getId(); //required
			String productName = product.getName(); //required
			String categoryId = product.getCategoryId(); //required
			String currency = "USD"; //required, hard coded for sake of simplicity
			String baseUnitPrice = product.getBaseUnitPrice(); //optional, null-able
			String quantity = ""+product.getQuantity(); //optional, null-able
			String[] attributes = product.getAttributes(); //optional, null-able
			success = DigitalAnalytics.fireShopAction5(productId, productName, quantity, baseUnitPrice, categoryId, currency, attributes);

			this.finish(success);

		} catch (Exception e) {
			Log.e(Constants.LOG_TAG, e.getMessage(), e);
			this.finish(false);
		}
	}

	private void finish(boolean success) {
		// This is for functional testing/user feedback only. In practice,
		// tagging should just be silent
	    Helper.myAlertDialog(activity, success, null, R.string.tag_shop_action5_success, R.string.tag_shop_action5_failed);
	}
}
