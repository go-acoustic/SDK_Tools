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
public class TagProductView {
	private Activity activity;
	private Product[] products;

	public TagProductView(final Activity activity, Product[] products) {
		this.activity = activity;
		this.products = products;
	}

	public void executeTag() {
		try {

			Boolean success = true;
			
			// Perform Tagging
			String pageId = this.activity.getLocalClassName(); // required
			for (Product product : this.products) {
				String productId = product.getId(); // required
				String productName = product.getName(); // required
				String categoryId = product.getCategoryId(); // required
				String[] attributes = product.getAttributes(); // optional,null-able

				success = DigitalAnalytics.fireProductview(pageId, productId, productName,
						categoryId, attributes);
			}

			this.finish(success);

		} catch (Exception e) {
			Log.e(Constants.LOG_TAG, e.getMessage(), e);
			this.finish(false);
		}
	}

	private void finish(boolean success) {
		// This is for functional testing/user feedback only. In practice,
		// tagging should just be silent
	    Helper.myAlertDialog(activity, success, null, R.string.tag_product_view_success, R.string.tag_product_view_failed);
	}
}
