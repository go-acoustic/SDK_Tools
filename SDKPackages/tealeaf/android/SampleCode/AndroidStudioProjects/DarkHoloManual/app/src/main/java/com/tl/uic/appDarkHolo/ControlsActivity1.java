/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2016
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
package com.tl.uic.appDarkHolo;

import com.tl.uic.appDarkHolo.R;
import com.tl.uic.appDarkHolo.util.TLHelper;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * @author ohernandezltmac
 *
 */
public class ControlsActivity1 extends BaseFragment {
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.controls1);
		setLogicalPageName("c1");
	    
	    TextView t = (TextView) findViewById(R.id.textView1);
	    t.setOnClickListener(TLHelper.getOnClickListener());
	    t = (TextView) findViewById(R.id.textView2);
	    t.setOnClickListener(TLHelper.getOnClickListener());
	    t = (TextView) findViewById(R.id.textView3);
	    t.setOnClickListener(TLHelper.getOnClickListener());
	    t = (TextView) findViewById(R.id.textView4);
	    t.setOnClickListener(TLHelper.getOnClickListener());
	    
	    Button b = (Button) findViewById(R.id.button5);
	    b.setOnClickListener(TLHelper.getOnClickListener());
	    
	    b = (Button) findViewById(R.id.button6);
	    b.setOnClickListener(TLHelper.getOnClickListener());
	    
	    ImageButton ib = (ImageButton) findViewById(R.id.imageButton);
	    ib.setOnClickListener(TLHelper.getOnClickListener());
	    
	    ImageView iv = (ImageView) findViewById(R.id.imageView1);
	    iv.setOnClickListener(TLHelper.getOnClickListener());
	    
	    ToggleButton tb = (ToggleButton) findViewById(R.id.toggleButton1);
	    tb.setOnClickListener(TLHelper.getOnClickListener());
	    
	    MediaController mc = (MediaController) findViewById(R.id.mediaController1);
	    mc.setOnClickListener(TLHelper.getOnClickListener());
	    
	    CheckBox cb = (CheckBox) findViewById(R.id.checkBox1);
	    cb.setOnClickListener(TLHelper.getOnClickListener());
	}
}
