/********************************************************************************************
* Copyright (C) 2015 Acoustic, L.P. All rights reserved.
*
* NOTICE: This file contains material that is confidential and proprietary to
* Acoustic, L.P. and/or other developers. No license is granted under any intellectual or
* industrial property rights of Acoustic, L.P. except as may be provided in an agreement with
* Acoustic, L.P. Any unauthorized copying or distribution of content from this file is
* prohibited.
********************************************************************************************/
package com.ibm.tealeaf.model;

@SuppressWarnings("unused")
public class ShippingModel {
    private String address;
    private String city;
    private String state;
    private int zipcode;
    
    private String getAddress() {
        return address;
    }
    private void setAddress(String address) {
        this.address = address;
    }
    private String getCity() {
        return city;
    }
    private void setCity(String city) {
        this.city = city;
    }
    private String getState() {
        return state;
    }
    private void setState(String state) {
        this.state = state;
    }
    private int getZipcode() {
        return zipcode;
    }
    private void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }
}
