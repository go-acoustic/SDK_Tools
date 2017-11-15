/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2015
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
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
