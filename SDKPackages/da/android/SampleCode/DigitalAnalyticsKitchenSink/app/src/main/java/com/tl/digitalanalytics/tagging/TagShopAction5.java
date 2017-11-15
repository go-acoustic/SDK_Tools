/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
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
