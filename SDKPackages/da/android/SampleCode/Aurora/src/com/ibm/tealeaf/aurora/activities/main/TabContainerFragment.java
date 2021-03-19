/********************************************************************************************
* Copyright (C) 2015 Acoustic, L.P. All rights reserved.
*
* NOTICE: This file contains material that is confidential and proprietary to
* Acoustic, L.P. and/or other developers. No license is granted under any intellectual or
* industrial property rights of Acoustic, L.P. except as may be provided in an agreement with
* Acoustic, L.P. Any unauthorized copying or distribution of content from this file is
* prohibited.
********************************************************************************************/
package com.ibm.tealeaf.aurora.activities.main;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ibm.tealeaf.aurora.R;
import com.ibm.tealeaf.aurora.activities.main.fragments.FragmentRefreshListener;

// This fragment class is used to create a wrapper fragment 
// for each tab in the MainActivity to transact on

public class TabContainerFragment extends Fragment {
	private final int RESOURCE_ID;

	FragmentRefreshListener mContentFragment;
	
	// Dynamically assigns the resource id, this is used in the replace function
	TabContainerFragment(int resourceId, FragmentRefreshListener contentFragment) {
		RESOURCE_ID = resourceId;
		mContentFragment = contentFragment;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_container, container, false);
		view.setId(RESOURCE_ID);
		
		// Encapsulate the contentFragment and display it 
		this.getActivity().getSupportFragmentManager().beginTransaction()
			.replace(RESOURCE_ID, (Fragment) mContentFragment)
			.commit();

		return view;
	}
	
	// This is a helper method for altering the current fragment 
	// that is displayed inside the tab container
	public static void replace(Fragment currentFragment, Fragment newFragment, int containerId) {
		currentFragment.getActivity().getSupportFragmentManager().beginTransaction()
			.replace(containerId, newFragment)
			.addToBackStack(null) // Pressing back will return to this fragment
			.commit();
	}
}
