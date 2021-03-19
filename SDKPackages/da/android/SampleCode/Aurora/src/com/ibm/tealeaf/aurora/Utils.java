/********************************************************************************************
* Copyright (C) 2015 Acoustic, L.P. All rights reserved.
*
* NOTICE: This file contains material that is confidential and proprietary to
* Acoustic, L.P. and/or other developers. No license is granted under any intellectual or
* industrial property rights of Acoustic, L.P. except as may be provided in an agreement with
* Acoustic, L.P. Any unauthorized copying or distribution of content from this file is
* prohibited.
********************************************************************************************/
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
