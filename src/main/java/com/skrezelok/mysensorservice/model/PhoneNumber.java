package com.skrezelok.mysensorservice.model;

import org.springframework.stereotype.Component;

public class PhoneNumber {
    private String phoneNumber;
    private String verificationCode;
    private int verificationStatus;
    private String verificationRequestId;

    public PhoneNumber() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public int getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(int verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public String getVerificationRequestId() {
        return verificationRequestId;
    }

    public void setVerificationRequestId(String verificationRequestId) {
        this.verificationRequestId = verificationRequestId;
    }
}
