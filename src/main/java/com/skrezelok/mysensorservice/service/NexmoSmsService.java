package com.skrezelok.mysensorservice.service;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.auth.TokenAuthMethod;
import com.nexmo.client.sms.SmsClient;
import com.nexmo.client.verify.CheckResult;
import com.nexmo.client.verify.VerifyResult;
import com.skrezelok.mysensorservice.model.PhoneNumber;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NexmoSmsService {
    private AuthMethod auth = new TokenAuthMethod("apiKey", "apiSecret");
    private NexmoClient client;

    public SmsClient getSmsClient() {
           return this.client.getSmsClient();
    }

    public NexmoClient getNexmoClient() {
        if (this.client == null) {
            this.client = new NexmoClient(this.auth);
        }
        return this.client;
    }

    public PhoneNumber requestPhoneVerification(PhoneNumber phoneNumber) throws IOException, NexmoClientException {
        VerifyResult request = getNexmoClient().getVerifyClient().verify(phoneNumber.getPhoneNumber(), "MySensor");
        phoneNumber.setVerificationRequestId(request.getRequestId());
        return phoneNumber;
    }

    public PhoneNumber checkVerificationCode(PhoneNumber phoneNumber) throws IOException, NexmoClientException {
        CheckResult result = getNexmoClient().getVerifyClient().check(phoneNumber.getVerificationRequestId(), phoneNumber.getVerificationCode());
        phoneNumber.setVerificationStatus(result.getStatus());
        return phoneNumber;
    }
}
