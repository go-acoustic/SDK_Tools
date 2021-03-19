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

public class Consts {
    //connecting to the right store
    public static final String HOSTNAME = "tealeafdemostore.demos.ibm.com";
	//public static final String HOSTNAME = "tealeafdemostore2.demos.ibm.com";
	public static final String STORE_ID = "10001";
	
	public static final String API = "https://" + Consts.HOSTNAME + "/wcs/resources/store/" + STORE_ID;
	public static final String API_NON_SECURE = "http://" + Consts.HOSTNAME + "/wcs/resources/store/" + STORE_ID;
	public static final String API_LOGIN = API + "/loginidentity";
	
	public static final String CATALOG_THUMBNAIL_DIR = HOSTNAME + "/wcsstore/Aurora/";
	
	public static final String API_PRODUCT = API_NON_SECURE + "/productview/byCategory/"; // followed by UniqueID
	public static final String API_PRODUCT_BY_ID = API_NON_SECURE + "/productview/byId/"; // followed by UniqueID
	public static final String API_CATEGORY = API_NON_SECURE + "/categoryview/byParentCategory/"; // followed by UniqueID
	public static final String API_CATEGORY_PARENT = API_NON_SECURE + "/categoryview/@top";
	
	public static final String API_CART = API + "/cart/@self";
	public static final String API_ADD_TO_CART = API + "/cart";
	public static final String API_PRECHECKOUT = API_CART + "/precheckout";
	public static final String API_CHECKOUT = API_CART + "/checkout";
	
	public static final String API_ORDERS = API + "/order/@history";
	
	public static final int MAIN_TAB_COUNT = 3;
	
	public static final String INPUT_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
}
