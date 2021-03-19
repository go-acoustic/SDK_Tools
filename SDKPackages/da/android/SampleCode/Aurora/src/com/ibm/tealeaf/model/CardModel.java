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
