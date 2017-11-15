/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2015
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
package com.ibm.tealeaf.aurora;
import java.text.DecimalFormat;


public class Utils {
	public static String toCurrency(String value) {
		return toCurrency(Double.parseDouble(value));
	}
	
	public static String toCurrency(double value) {
		DecimalFormat df = new DecimalFormat("#.00");
		return df.format(value);
	}
}
