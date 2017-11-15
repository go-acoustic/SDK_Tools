/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2015
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
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
