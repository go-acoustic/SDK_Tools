/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2015
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
package com.ibm.tealeaf.aurora.activities.main.fragments;

import android.support.v4.app.Fragment;

import com.ibm.tealeaf.aurora.AuroraHelper;
import com.ibm.tealeaf.aurora.AuroraListItem;
import com.ibm.tealeaf.aurora.activities.main.TabContainerFragment;

public class CategoryListFragment extends ShopListFragment implements FragmentRefreshListener {
	
	public CategoryListFragment() {
		super();
		AuroraHelper.requestParentCategory(this);
	}
	public CategoryListFragment(int categoryId, String categoryName, String previousCategoryName) {
		super(categoryId, categoryName, previousCategoryName);
		AuroraHelper.requestCategory(categoryId+"", this);
	}
	
	@Override
	protected void listItemClicked(AuroraListItem entryClicked, int position) {
		Fragment fragment;
		if(mIsProduct) {
			fragment = new ProductFragment(this.adapter.getItem(position));
		} else {
			fragment = new CategoryListFragment(Integer.parseInt(entryClicked.getId()), entryClicked.getTitle(), this.getShopListTitle());
		}
		
		TabContainerFragment.replace(this, fragment, mContainerId);
	}
	
	@Override
	public void fragmentIsVisible() {
		// TODO Auto-generated method stub
		
	}

}
