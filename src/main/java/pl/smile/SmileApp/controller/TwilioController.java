//package pl.smile.SmileApp.controller;
//
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import pl.smile.SmileApp.model.TwilioAcc;
//
//@Controller
//@RequestMapping("/sendSms")
//public class TwilioController {
//
//    private final TwilioAcc twilioAcc;
//
//    public TwilioController(TwilioAcc twilioAcc) {
//        this.twilioAcc = twilioAcc;
//    }
//
//    @GetMapping("/test")
//    @ResponseBody
//    @Scheduled(cron = "00 7 * * * *")
//    public String sensSMS() {
//        Twilio.init(
//                twilioAcc.getAccSid(),
//                twilioAcc.getAuthToken());
//        Message.creator(new PhoneNumber(twilioAcc.getMyNumber()),
//                        new PhoneNumber(twilioAcc.getTrialNumber()),
//                "Przypominamy, ze za 3 dni wizyta. :) Jeżeli nie dasz rady przyjsc, prosimy o kontakt pod numerem: 999 999 999").create();
//
//        return "Udalo sie wyslac smsa";
//    }

    //Docelowo ma byc 'cron = 00 7 * * * *', by metoda wywolywala sie codziennie o 7 rano automatycznie i sprawdzala
    // czy w bazie danych nie isnieje zarezerowana wizyta od dzisiejszej daty do +3 dni plus, jezeli tak, to wysyla smsa z przypomnieniem


//}
