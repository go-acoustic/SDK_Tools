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
import com.tl.digitalanalytics.app.MainActivity;
import com.tl.digitalanalytics.model.Order;
import com.tl.digitalanalytics.model.Product;
import com.tl.digitalanalytics.model.ShoppingCart;

/**
 * 
 * @author Sohil Shah (sohishah@us.ibm.com)
 * 
 */
public class TagOrderCompletion {

	private ShoppingCart cart;
	private Order order;
	private Activity activity;

	public TagOrderCompletion(final Activity activity, ShoppingCart cart,
			Order order) {
		this.activity = activity;
		this.cart = cart;
		this.order = order;
	}

	public void executeTag() {
		try {

			Boolean success = true;

			// Setup Data to be tagged
			String orderId = this.order.getId(); // required
			String subtotal = this.order.getSubTotal(); // optional, null-able
			String customerId = this.order.getCustomerId(); // required
			String currency = this.order.getCurrencyCode(); // required

			// Fire a Shop Action9 for Product in the cart
			Product[] products = this.cart.getProducts();
			for (Product product : products) {
				String productId = product.getId(); // required
				String productName = product.getName(); // required
				String categoryId = product.getCategoryId(); // required
				String quantity = "" + product.getQuantity(); // optional,
																// null-able
				String baseUnitPrice = product.getBaseUnitPrice(); // optional,
																	// null-able
				String[] attributes = product.getAttributes(); // optional,
																// null-able

				success = DigitalAnalytics.fireShopAction9(productId, productName,
						quantity, baseUnitPrice, categoryId, orderId, subtotal,
						customerId, currency, attributes);
			}
			
			success = this.fireOrderTag();

			this.finish(success);

		} catch (Exception e) {
			Log.e(Constants.LOG_TAG, e.getMessage(), e);
			this.finish(false);
		}
	}

	private Boolean fireOrderTag() {
		String orderId = this.order.getId(); // required
		String subtotal = this.order.getSubTotal(); // optional, null-able
		String shippingCharge = this.order.getShippingCharge(); // optional,
																// null-able
		String customerId = this.order.getCustomerId(); // required
		String city = this.order.getCustomerCity(); // required
		String state = this.order.getCustomerState(); // required
		String zip = this.order.getCustomerZip(); // required
		String currency = this.order.getCurrencyCode(); // required
		String[] attributes = this.order.getAttributes();// optional, null-able

		// fire the order tag
		return DigitalAnalytics.fireOrder(orderId, subtotal, shippingCharge, customerId,
				city, state, zip, currency, attributes);
	}

    private void finish(boolean success) {
        // This is for functional testing/user feedback only. In practice,
        // tagging should just be silent
        Helper.myAlertDialog(activity, success, MainActivity.class, R.string.tag_order_placement_success, R.string.tag_order_placement_failed);

        // Clean up
        cart.clear();
    }
}
