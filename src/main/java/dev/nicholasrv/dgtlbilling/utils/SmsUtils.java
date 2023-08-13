package dev.nicholasrv.dgtlbilling.utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import static com.twilio.rest.api.v2010.account.Message.creator;

public class SmsUtils {
    public static final String FROM_NUMBER = "+19403536288";
    public static final String SID_KEY = "AC634e831d5ddfa47c121a585c4451ee63";
    public static final String TOKEN_KEY = "4394bc4680e012f04f600aee907fb6c2";

    public static void sendSMS(String to, String messageBody){
        Twilio.init(SID_KEY, TOKEN_KEY);
        Message message = creator(new PhoneNumber("+1" + to), new PhoneNumber(FROM_NUMBER), messageBody).create();
        System.out.println(message);
    }
}
