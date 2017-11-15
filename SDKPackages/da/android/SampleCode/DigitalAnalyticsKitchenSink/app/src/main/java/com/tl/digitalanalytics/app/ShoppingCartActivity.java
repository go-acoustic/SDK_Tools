/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
package com.tl.digitalanalytics.app;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.da.kitchensink.R;
import com.tl.digitalanalytics.model.Product;
import com.tl.digitalanalytics.model.ShoppingCart;
import com.tl.digitalanalytics.model.Order;
import com.tl.digitalanalytics.tagging.TagOrderCompletion;

/**
 * 
 * @author Sohil Shah (sohishah@us.ibm.com)
 *
 */
public class ShoppingCartActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shopping_cart);
		
		//Grab the ShoppingCart to be displayed
		ShoppingCart cart = ShoppingCart.getInstance();
		
		//Show the cart Subtotal
		float subtotal = cart.calculateSubtotal();
		String subtotalLabel = this.getResources().getString(R.string.subtotal);
		this.getSupportActionBar().setSubtitle(subtotalLabel+": $"+subtotal);
		
		//wire up
        ListView listView = (ListView)this.findViewById(R.id.listView1);
        listView.setAdapter(new SourceAdapter(cart.getProducts()));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		MenuInflater menuInflater = this.getMenuInflater();
		menuInflater.inflate(R.menu.cart, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		int itemId = item.getItemId();
		
		switch(itemId)
		{
			case R.id.checkout:
				if(!ShoppingCart.getInstance().isEmpty())
				{
					this.showDialog(0);
				}
			break;
			
			default:
			break;
		}
		
		return true;
	}
	
	@Override
	protected Dialog onCreateDialog(int id, Bundle bundle) 
	{
		//TODO: FIX Tealeaf Instrumentation Issue. The EditText events are not being captured in this AlertDialog

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		//Setup the Data Collection View of the Dialog
		LayoutInflater inflater = this.getLayoutInflater();
		View dialogView = inflater.inflate(R.layout.checkout_dialog, null);
		
		//Setup the Customer fields to be read
		final EditText customerId = (EditText)dialogView.findViewById(R.id.editText1);
		final EditText customerCity = (EditText)dialogView.findViewById(R.id.editText2);
		final EditText customerState = (EditText)dialogView.findViewById(R.id.editText3);
		final EditText customerZip = (EditText)dialogView.findViewById(R.id.editText4);
		
		builder.setTitle(R.string.checkout);
		
		builder.setView(dialogView);
		
		builder.setPositiveButton(R.string.checkout, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				ShoppingCart cart = ShoppingCart.getInstance();
				
				//Generate the Order to be Submitted
				Order order = cart.generateOrder();
				order.setCustomerId(customerId.getText().toString());
				order.setCustomerCity(customerCity.getText().toString());
				order.setCustomerState(customerState.getText().toString());
				order.setCustomerZip(customerZip.getText().toString());
				
				//Go ahead an submit this order
				
				//Tagging: Perform Tagging here
				TagOrderCompletion tag = new TagOrderCompletion(ShoppingCartActivity.this, cart, order);
				tag.executeTag();
			}
		});
		
		builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// DO nothing
			}
		});
		
		return builder.create();
	}
	
	private class SourceAdapter extends BaseAdapter
	{
		private Product[] products;
		
		public SourceAdapter(Product[] products)
		{
			this.products = products;
		}
		
		@Override
		public int getCount() 
		{
			return this.products.length;
		}

		@Override
		public Object getItem(int position) 
		{
			return this.products[position];
		}

		@Override
		public long getItemId(int position) 
		{
			return position;
		}

		@Override
		public View getView(int position, View rowView, ViewGroup parent) 
		{
			if(rowView == null)
			{
				LayoutInflater inflater = ShoppingCartActivity.this.getLayoutInflater();
				rowView = inflater.inflate(R.layout.activity_shopping_cart_list_item, parent, false);
				
				TextView quantity = (TextView)rowView.findViewById(R.id.textView1);
				TextView name = (TextView)rowView.findViewById(R.id.textView2);
				TextView price = (TextView)rowView.findViewById(R.id.textView3);
				
				//setup the ViewHolder
				ViewHolder viewHolder = new ViewHolder();
				viewHolder.quantity = quantity;
				viewHolder.name = name;
				viewHolder.price = price;
				rowView.setTag(viewHolder);
			}
			
			//fill up the data into the row
			ViewHolder viewHolder = (ViewHolder)rowView.getTag();
			
			Product product = products[position];
			viewHolder.quantity.setText(""+product.getQuantity());
			viewHolder.name.setText(product.getName());
			viewHolder.price.setText("$"+product.getTotalPrice());
			
			return rowView;
		}
	}
	
	private static class ViewHolder
	{
		private TextView quantity;
		private TextView name;
		private TextView price;
	}

	@Override
	protected void onResume()
	{
		super.onResume();
	}

	@Override
	protected void onPause()
	{
		super.onPause();
	}


	@Override
	protected void onDestroy()
	{
		super.onDestroy();
	}
}
