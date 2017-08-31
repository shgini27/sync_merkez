/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saptaslama.controller;

import com.saptaslama.view.SyncMerkezView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

/**
 *
 * @author Shagy
 */
public class SendScheduledTask extends TimerTask{

    
    @Override
    public void run() {
        SyncMerkezView.setText("sending...", 1);
        SendDataManual send = new SendDataManual();
        send.sendData(0);
        
        Date d = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        System.err.println("TODAY: " + date.format(d.getTime()));

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        System.err.println("WEEK BEFORE: " + date.format(cal.getTime()));
        
        SyncMerkezView.setText(date.format(d.getTime()), 2);
        
        System.err.println("SEND COUNTER: " + CompanyConstants.SEND_COUNTER++);
    }
    
}
