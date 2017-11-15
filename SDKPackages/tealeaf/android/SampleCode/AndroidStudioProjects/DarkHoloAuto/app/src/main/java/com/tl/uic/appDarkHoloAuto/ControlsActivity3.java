/*******************************************************************************
 * Licensed Materials - Property of IBM
 * 5725-K23
 * (c) Copyright IBM Corp. 2013, 2014
 * US Government Users Restricted Rights - Use, duplication or disclosure restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
package com.tl.uic.appDarkHoloAuto;

import com.tl.uic.appDarkHoloAuto.R;
import com.tl.uic.appDarkHoloAuto.util.TLHelper;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;


public class ControlsActivity3 extends BaseActivity {
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.controls3);
		setLogicalPageName("c3");
	    
	    EditText et = (EditText) findViewById(R.id.editText1);
	    
	    TLHelper.addFocusAndRegister(et, this);
	    et = (EditText) findViewById(R.id.editText2);
	    TLHelper.addFocusAndRegister(et, this);
	    et = (EditText) findViewById(R.id.editText3);
	    TLHelper.addFocusAndRegister(et, this);
	    et = (EditText) findViewById(R.id.editText4);
	    TLHelper.addFocusAndRegister(et, this);
	    et = (EditText) findViewById(R.id.editText5);
	    TLHelper.addFocusAndRegister(et, this);
	    et = (EditText) findViewById(R.id.editText6);
	    TLHelper.addFocusAndRegister(et, this);
	    et = (EditText) findViewById(R.id.editText7);
	    TLHelper.addFocusAndRegister(et, this);
	    et = (EditText) findViewById(R.id.editText8);
	    TLHelper.addFocusAndRegister(et, this);
	    et = (EditText) findViewById(R.id.editText9);
	    TLHelper.addFocusAndRegister(et, this);
	    et = (EditText) findViewById(R.id.editText10);
	    TLHelper.addFocusAndRegister(et, this);
	    et = (EditText) findViewById(R.id.editText11);
	    TLHelper.addFocusAndRegister(et, this);
	    et = (EditText) findViewById(R.id.editText12);
	    TLHelper.addFocusAndRegister(et, this);
	    et = (EditText) findViewById(R.id.editText13);
	    TLHelper.addFocusAndRegister(et, this);
	    
	    AutoCompleteTextView acet = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView1);
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, COUNTRIES);
	    acet.setAdapter(adapter);
	    TLHelper.addFocusAndRegister(acet, this);
	    
	    MultiAutoCompleteTextView mactv = (MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView1);
	    mactv.setAdapter(adapter);
	    mactv.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
	    TLHelper.addFocusAndRegister(mactv, this);
	}
	
	private static final String[] COUNTRIES = new String[] {
        "Belgium", "France", "Italy", "Germany", "Spain"
    };
}
