/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2015
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
package com.tl.digitalanalytics.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;

import com.da.kitchensink.R;
import com.tl.digitalanalytics.model.Catalog;
import com.tl.digitalanalytics.model.Category;
import com.tl.digitalanalytics.model.Product;
import com.tl.digitalanalytics.model.ShoppingCart;
import com.tl.digitalanalytics.tagging.TagPageView;
import com.tl.digitalanalytics.tagging.TagProductView;
import com.tl.digitalanalytics.tagging.TagShopAction5;

/**
 * 
 * @author Sohil Shah (sohishah@us.ibm.com)
 *
 */
public class ProductsActivity extends ActionBarActivity 
{
	private Category category;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_products);
		
		//Extract the Category to be used to show the appropriate Products
		Intent intent = this.getIntent();
		String categoryId = intent.getStringExtra("categoryId");
		this.category = Catalog.getInstance().getCategory(categoryId);
		
		//Update the title of the Activity
        this.getSupportActionBar().setSubtitle(this.category.getName());
        
        //wire up
        ListView listView = (ListView)this.findViewById(R.id.listView1);
        listView.setAdapter(new SourceAdapter(this.category.getProducts()));
        listView.setOnItemClickListener(new ListListener());
        
        //TAGGING: Invoke Tagging upon loading this Page
        TagPageView tagPageView = new TagPageView(this,false); //false indicates that loading this page is associated with an existing Tagging Session
        tagPageView.executeTag();
        
        TagProductView tagProducts = new TagProductView(this, this.category.getProducts());
        tagProducts.executeTag();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		MenuInflater menuInflater = this.getMenuInflater();
		menuInflater.inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		int itemId = item.getItemId();
		
		switch(itemId)
		{
			case R.id.my_cart:
				Intent intent = new Intent(this, ShoppingCartActivity.class);
				this.startActivity(intent);
			break;
			
			default:
			break;
		}
		
		return true;
	}
	
	
	
	@Override
	protected Dialog onCreateDialog(int id, Bundle bundle) 
	{
		final Product product = (Product)bundle.getSerializable("product");
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		//Setup the Data Collection View of the Dialog
		LayoutInflater inflater = this.getLayoutInflater();
		View dialogView = inflater.inflate(R.layout.add_to_cart_dialog, null);
		final EditText quantityText = (EditText)dialogView.findViewById(R.id.editText1);
		
		builder.setTitle(R.string.add_to_cart);
		
		builder.setView(dialogView);
		
		builder.setPositiveButton(R.string.add_to_cart, new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String quantity = quantityText.getText().toString();
				
				//Generate a Product to be added to the Shopping Cart
				Product cartCopy = (Product)product.clone();
				cartCopy.setQuantity(Integer.parseInt(quantity));
				
				//Add the Product to the Shopping Cart
				ShoppingCart.getInstance().addToCart(cartCopy);
				
				//TAGGING: Tag this action of adding the product to your cart
				TagShopAction5 tag = new TagShopAction5(ProductsActivity.this, cartCopy);
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
				LayoutInflater inflater = ProductsActivity.this.getLayoutInflater();
				rowView = inflater.inflate(R.layout.activity_products_list_item, parent, false);
				
				TextView name = (TextView)rowView.findViewById(R.id.name);
				TextView price = (TextView)rowView.findViewById(R.id.price);
				
				//setup the ViewHolder
				ViewHolder viewHolder = new ViewHolder();
				viewHolder.name = name;
				viewHolder.price = price;
				rowView.setTag(viewHolder);
			}
			
			//fill up the data into the row
			ViewHolder viewHolder = (ViewHolder)rowView.getTag();
			
			Product product = products[position];
			viewHolder.name.setText(product.getName());
			viewHolder.price.setText("$"+product.getBaseUnitPrice());
			
			return rowView;
		}
	}
	
	private static class ViewHolder
	{
		private TextView name;
		private TextView price;
	}
	
	private class ListListener implements OnItemClickListener
	{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) 
		{
			//Get the Product that was selected
			Product product = (Product)parent.getItemAtPosition(position);
			
			//Collect the Quantity to be associated with the product before adding it to the cart
			Bundle bundle = new Bundle();
			bundle.putSerializable("product", product);
			showDialog(position, bundle);
		}
	}
}
