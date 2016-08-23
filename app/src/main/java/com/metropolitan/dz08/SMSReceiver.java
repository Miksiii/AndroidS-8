package com.metropolitan.dz08;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by milan on 6/14/2016.
 */
public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){

        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs = null;
        String str = "SMS iz ";
        if (bundle != null){

            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];

            for (int i=0; i<msgs.length; i++){
                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);

                if (i==0){
                    str += msgs[i].getDisplayOriginatingAddress();
                    str += ": ";
                }
                str += msgs[i].getMessageBody().toString();
            }
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        }
    }
}
