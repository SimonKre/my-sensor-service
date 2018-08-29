package com.skrezelok.mysensorservice.service;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.auth.TokenAuthMethod;
import com.nexmo.client.sms.SmsClient;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class NexmoSmsService {
    private AuthMethod auth = new TokenAuthMethod("apiKey", "apiSecret");
    private SmsClient client = new NexmoClient(auth).getSmsClient();

    public SmsClient getClient () {
        return client;
    }
}
