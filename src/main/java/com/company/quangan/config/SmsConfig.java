package com.company.quangan.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "sms.esms")
public class SmsConfig {
    private boolean enabled;
    private String apiKey;
    private String secretKey;
    private int smsType = 8;
    private String brandname;
    private boolean sandbox = true;
}
