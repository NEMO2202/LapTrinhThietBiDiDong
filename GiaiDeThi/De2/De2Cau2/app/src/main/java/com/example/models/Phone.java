package com.example.models;

import java.io.Serializable;

public class Phone implements Serializable {
    String PhoneId;
    String PhoneName;
    Double PhonePrice;

    public String getPhoneId() {
        return PhoneId;
    }

    public void setPhoneId(String phoneId) {
        PhoneId = phoneId;
    }

    public String getPhoneName() {
        return PhoneName;
    }

    public void setPhoneName(String phoneName) {
        PhoneName = phoneName;
    }

    public Double getPhonePrice() {
        return PhonePrice;
    }

    public void setPhonePrice(Double phonePrice) {
        PhonePrice = phonePrice;
    }

    public Phone(String phoneId, String phoneName, Double phonePrice) {
        this.PhoneId = phoneId;
        this.PhoneName = phoneName;
        this.PhonePrice = phonePrice;
    }

    @Override
    public String toString() {
        return  PhoneName;
    }
}