/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
package com.tl.digitalanalytics.app;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.da.kitchensink.R;
import com.tl.digitalanalytics.model.Catalog;
import com.tl.digitalanalytics.model.Category;
import com.tl.digitalanalytics.model.ShoppingCart;
import com.tl.digitalanalytics.tagging.TagPageView;

/**
 * 
 * @author Sohil Shah (sohishah@us.ibm.com)
 *
 */
public class MainActivity extends ActionBarActivity 
{
	private Catalog catalog;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //Update the title of the Activity
        this.getSupportActionBar().setSubtitle(R.string.category);
        
        //Setup the Catalog
        this.catalog = Catalog.getInstance(this, "catalog.xml");
        
        //wire up
        ListView listView = (ListView)this.findViewById(R.id.listView1);
        listView.setAdapter(new SourceAdapter(this.catalog.getCategories()));
        listView.setOnItemClickListener(new ListListener());
        
        //TAGGING: Invoke Tagging upon loading this Page
        TagPageView tagPageView = new TagPageView(this,true); //true indicates that loading this page means its a start of a Tagging Session
        tagPageView.executeTag();
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
    
    private class SourceAdapter extends BaseAdapter
	{
    	private Category[] categories;
    	
		public SourceAdapter(Category[] categories)
		{
			this.categories = categories;
		}
		
		@Override
		public int getCount() 
		{
			return this.categories.length;
		}

		@Override
		public Object getItem(int position) 
		{
			return this.categories[position];
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
				LayoutInflater inflater = MainActivity.this.getLayoutInflater();
				rowView = inflater.inflate(R.layout.activity_main_list_item, parent, false);
				
				TextView categoryView = (TextView)rowView.findViewById(R.id.name);
				
				//setup the ViewHolder
				ViewHolder viewHolder = new ViewHolder();
				viewHolder.categoryView = categoryView;
				rowView.setTag(viewHolder);
			}
			
			//fill up the data into the row
			ViewHolder viewHolder = (ViewHolder)rowView.getTag();
			
			viewHolder.categoryView.setText(this.categories[position].getName());
			
			return rowView;
		}
	}
	
	private static class ViewHolder
	{
		private TextView categoryView;
	}
	
	private class ListListener implements OnItemClickListener
	{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) 
		{
			//Get the category Id of the selected Category
			Category category = (Category)parent.getItemAtPosition(position);
			String categoryId = category.getId();
			
			//Start the ProductsActivity associated with this Category
			Intent intent = new Intent(MainActivity.this, ProductsActivity.class);
			intent.putExtra("categoryId", categoryId);
			startActivity(intent);
		}
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
		ShoppingCart.getInstance().clear();

		super.onDestroy();
	}
}
