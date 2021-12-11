package com.example.ncovi.View.BroadcastReceiver;

import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.EditText;

import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;

public class OTPBroadCast extends BroadcastReceiver {
//public OTPBroadCastListener otpBroadCastListener ;
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        if (intent.getAction() == SmsRetriever.SMS_RETRIEVED_ACTION)
//        {
//            Bundle bundle = intent.getExtras();
//            Status sms = (Status) bundle.get(SmsRetriever.EXTRA_STATUS);
//            switch (sms.getStatusCode())
//            {
//                case CommonStatusCodes
//                        .SUCCESS:
//                    Intent messageIntent = bundle.getParcelable(SmsRetriever.EXTRA_CONSENT_INTENT);
//                    otpBroadCastListener.onSuccess(messageIntent);
//                    break;
//                case  CommonStatusCodes.TIMEOUT:
//                    otpBroadCastListener.Failure();
//                    break;
//            }
//        }
//    }
//    public interface OTPBroadCastListener{
//        void onSuccess(Intent intent);
//        void Failure();
//    }
    private static EditText editText_otp ;
    public void setEditText_otp(EditText editText){
        OTPBroadCast.editText_otp = editText;
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        SmsMessage[] smsMessages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
        for(SmsMessage smsMessage : smsMessages)
        {
            String message_body = smsMessage.getMessageBody();
            String getOtp = message_body.split(":")[1];
            editText_otp.setText(getOtp);
        }
    }
}
