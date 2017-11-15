/*******************************************************************************
 * Licensed Materials - Property of IBM
 * (C) Copyright IBM Corp. 2015
 * US Government Users Restricted Rights - Use, duplication or disclosure
 * restricted by GSA ADP Schedule Contract with IBM Corp.
 ******************************************************************************/
package com.ibm.tealeaf.model;

@SuppressWarnings("unused")
public class CardModel {
    private int cardNumber;
    private int securityCode;
    private int month;
    private int year;
    
    private int getCardNumber() {
        return cardNumber;
    }
    private void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }
    private int getSecurityCode() {
        return securityCode;
    }
    private void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }
    private int getMonth() {
        return month;
    }
    private void setMonth(int month) {
        this.month = month;
    }
    private int getYear() {
        return year;
    }
    private void setYear(int year) {
        this.year = year;
    }
}
