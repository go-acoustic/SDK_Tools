/*******************************************************************************
 * Licensed Materials - Property of IBM
 * 5725-K23
 * (c) Copyright IBM Corp. 2013, 2014
 * US Government Users Restricted Rights - Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
package com.tl.uic.appDarkHoloAuto;

import java.util.ArrayList;

import com.tl.uic.app.model.Items;
import com.tl.uic.app.model.Items.Item;
import com.tl.uic.appDarkHoloAuto.R;
import com.tl.uic.appDarkHoloAuto.util.TLHelper;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ControlsActivity6 extends BaseActivity {
	private ArrayList<? extends Item> data;
	private Items items = Items.getInstance();
	private String packageName;
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.controls6);
		setLogicalPageName("c6");
		packageName = this.getApplicationContext().getPackageName();
	    data = items.getCategories();
		
	    ListView lv = (ListView) findViewById(R.id.listing1);
	    lv.setAdapter(new MyCustomAdapter(this));
	}
	
	static class Vholder {
		TextView name;
		ImageView thumb;
	}
	
	public class MyCustomAdapter extends BaseAdapter {
		Context context;
		MyCustomAdapter(Context context) {
			this.context = context;
		}
		
		public int getCount() {
			return data.size();
		}

		public Object getItem(int position) {
			return data.get(position);
		}

		public long getItemId(int position) {
			return data.get(position).getId();
		}

		public View getView(final int position, View convertView, ViewGroup parent) {
			Vholder vh;

			if (convertView == null) {
				LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.listitem, null);
				vh = new Vholder();
				vh.name = (TextView) convertView.findViewById(R.id.item_name);
				vh.thumb = (ImageView) convertView.findViewById(R.id.item_icon);
				convertView.setTag(vh);
				try {
		            final Resources resources = getResources();
		            int idItemName = resources.getIdentifier("item_name_" + position, "id", packageName);
		            int idItemIcon = resources.getIdentifier("item_icon_" + position, "id", packageName);
		            vh.name.setId(idItemName);
		            vh.thumb.setId(idItemIcon);
		        } catch (final Exception e) {
		        	Log.d("SP", "Trying to get id property value.", e);
		        }
			} else {
				vh = (Vholder) convertView.getTag();
			}

			vh.name.setText(data.get(position).getName());
			vh.thumb.setImageResource(data.get(position).getThumbnail());

			convertView.setOnClickListener(TLHelper.getOnClickListener());
			return convertView;
		}
	}
}
