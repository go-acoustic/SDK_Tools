/********************************************************************************************
* Copyright (C) 2015 Acoustic, L.P. All rights reserved.
*
* NOTICE: This file contains material that is confidential and proprietary to
* Acoustic, L.P. and/or other developers. No license is granted under any intellectual or
* industrial property rights of Acoustic, L.P. except as may be provided in an agreement with
* Acoustic, L.P. Any unauthorized copying or distribution of content from this file is
* prohibited.
********************************************************************************************/
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
