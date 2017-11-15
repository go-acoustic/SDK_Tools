/*******************************************************************************
 * Licensed Materials - Property of IBM
 * 5725-K23
 * (c) Copyright IBM Corp. 2017
 * US Government Users Restricted Rights - Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
package com.tl.uic.appDarkHoloAuto.util;

import com.tl.uic.Tealeaf;

import android.app.Activity;
import android.content.Context;
import android.inputmethodservice.KeyboardView;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RatingBar.OnRatingBarChangeListener;
import android.widget.SeekBar;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class TLHelper {
	
	public static OnClickListener getOnClickListener() {
		OnClickListener onClickListener = new OnClickListener() {

			@Override
			public void onClick(View view) {
				// TeaCuts (AspectJ) needs to have override methods to add hooks to it.
				// No need to add Tealeaf.logEvent
			}
		};

		return onClickListener;
	}

	public static OnItemClickListener getOnItemClickListener() {

		OnItemClickListener onItemClickListener = new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			}
		};

		return onItemClickListener;
	}
	
	public static OnItemSelectedListener onItemSelected() {
		
		OnItemSelectedListener onItemSelectedListener = new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
			}

		    public void onNothingSelected(@SuppressWarnings("rawtypes") AdapterView parent) {
		      // Do nothing.
		    }
		};
		
		return onItemSelectedListener;
    }
	
	public static OnSeekBarChangeListener getOnSeekBarChangeListener() {
		
		OnSeekBarChangeListener onClickListener = new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			}
		};
		
		return onClickListener;
	}
	
	public static OnCheckedChangeListener getOnCheckedChangeListener() {
		
		OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
			}
		};
		
		return onCheckedChangeListener;
	}
	
	public static OnRatingBarChangeListener getOnRatingBarChangeListener() {
		
		OnRatingBarChangeListener onRatingBarChangeListener = new OnRatingBarChangeListener() {
			
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

			}
		};
		
		return onRatingBarChangeListener;
	}
	
	public static OnDateChangedListener getOnDateChangedListener() {
		
		OnDateChangedListener onDateChangedListener = new OnDateChangedListener() {
			
			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

			}
		};
		
		return onDateChangedListener;
	}
	
	public static void addFocusAndRegister(TextView textView, Activity activity) {
		
		textView.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {

			}
		});
		
		Tealeaf.registerFormField(textView, activity);
	}
	
	public static void logScreenLayoutOnCreate(final Fragment fragment, final Activity activity) {
		Tealeaf.logScreenLayoutOnCreate(activity, getLogicalPageName(null, fragment));
	}
	
	public static void logScreenLayoutOnCreate(final Fragment fragment, final Activity activity, final String logicalPageName) {
		Tealeaf.logScreenLayoutOnCreate(activity, getLogicalPageName(logicalPageName, fragment));
	}
	
	public static void logScreenLayout(final Fragment fragment, final Activity activity, final String logicalPageName, int delayMS) {
		Tealeaf.logScreenLayout(activity, getLogicalPageName(logicalPageName, fragment), delayMS);
	}
	
	public static String getLogicalPageName(String logicalPageName, Fragment fragment) {
		 if ((logicalPageName == null) || (logicalPageName.equals(""))) {
	         logicalPageName = fragment.getClass().getName().substring(fragment.getClass().getName().lastIndexOf('.') + 1);
	     }
		 return logicalPageName;
	}
}
