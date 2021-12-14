package pl.smile.SmileApp.model;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class TwilioAcc {
    private final String accSid =  System.getenv("TWILIO_ACCOUNT_SID");
    private final String authToken = System.getenv("TWILIO_AUTH_TOKEN");
    private final String trialNumber = "+15073354937";

}




