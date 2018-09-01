package com.skrezelok.mysensorservice.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class PhoneNumber {
    @Pattern(regexp = "^[0-9]*$")
    private String phoneNumber;
    private String verificationCode;
    private Integer verificationStatus;
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

    public Integer getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(Integer verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public String getVerificationRequestId() {
        return verificationRequestId;
    }

    public void setVerificationRequestId(String verificationRequestId) {
        this.verificationRequestId = verificationRequestId;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                ", verificationStatus=" + verificationStatus +
                ", verificationRequestId='" + verificationRequestId + '\'' +
                '}';
    }
}
