/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saptaslama.controller;

import com.saptaslama.controller.receiver.BnCardReceiver;
import com.saptaslama.controller.receiver.ClCardReceiver;
import com.saptaslama.controller.receiver.InvoiceReceiver;
import com.saptaslama.controller.receiver.ItemsReceiver;
import com.saptaslama.controller.receiver.KsCardReceiver;
import com.saptaslama.controller.receiver.SlsManReceiver;
import com.saptaslama.controller.receiver.SrvCardReceiver;
import com.saptaslama.controller.receiver.StFicheReceiver;
import com.saptaslama.controller.receiver.UnitReceiver;
import com.saptaslama.controller.sync.SyncBankCard;
import com.saptaslama.controller.sync.SyncClCard;
import com.saptaslama.controller.sync.SyncInvoice;
import com.saptaslama.controller.sync.SyncItems;
import com.saptaslama.controller.sync.SyncKsCard;
import com.saptaslama.controller.sync.SyncSlsMan;
import com.saptaslama.controller.sync.SyncSrvCard;
import com.saptaslama.controller.sync.SyncStFiche;
import com.saptaslama.controller.sync.SyncUnit;
import com.saptaslama.view.SyncMerkezView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Shagy
 */
public class SyncDepoManual {

    private UnitReceiver unitReceiver;
    private SyncUnit syncUnit;

    private ItemsReceiver itemReceiver;
    private SyncItems syncItems;

    private SlsManReceiver slsReceiver;
    private SyncSlsMan syncSlsMan;

    private ClCardReceiver clCardReceiver;
    private SyncClCard syncClCard;

    private SrvCardReceiver srvCardReceiver;
    private SyncSrvCard syncSrvCard;

    private BnCardReceiver bnCardReceiver;
    private SyncBankCard syncBnCard;

    private KsCardReceiver ksCardReceiver;
    private SyncKsCard syncKsCard;

    private InvoiceReceiver invoiceReceive;
    private SyncInvoice syncInvoice;

    private StFicheReceiver stFicheReceive;
    private SyncStFiche syncStFiche;

    public void syncData(int source) {
        System.err.println("SYNC DEPO!");
        
        Date d = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        System.err.println("TODAY: " + date.format(d.getTime()));

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -7);
        System.err.println("WEEK BEFORE: " + date.format(cal.getTime()));

        CompanyConstants code = new CompanyConstants();
        //Sync UNITSET
        unitReceiver = new UnitReceiver();
        syncUnit = new SyncUnit(unitReceiver, CompanyConstants.DEPO_FILE_PATH_RECEIVE);
        syncUnit.init();
        unitReceiver = null;
        syncUnit = null;

        //Sync ITEMS
        itemReceiver = new ItemsReceiver();
        syncItems = new SyncItems(itemReceiver, CompanyConstants.DEPO_FILE_PATH_RECEIVE);
        syncItems.init();
        itemReceiver = null;
        syncItems = null;

        //Sync SLSMAN
        slsReceiver = new SlsManReceiver();
        syncSlsMan = new SyncSlsMan(slsReceiver, CompanyConstants.DEPO_FILE_PATH_RECEIVE);
        syncSlsMan.init();
        slsReceiver = null;
        syncSlsMan = null;

        //Sync CLCARD
        clCardReceiver = new ClCardReceiver();
        syncClCard = new SyncClCard(clCardReceiver, CompanyConstants.DEPO_FILE_PATH_RECEIVE);
        syncClCard.init();
        clCardReceiver = null;
        syncClCard = null;

        //Sync SRVCARD
        srvCardReceiver = new SrvCardReceiver();
        syncSrvCard = new SyncSrvCard(srvCardReceiver, CompanyConstants.DEPO_FILE_PATH_RECEIVE);
        syncSrvCard.init();
        srvCardReceiver = null;
        syncSrvCard = null;

        //Sync BNCARD
        bnCardReceiver = new BnCardReceiver();
        syncBnCard = new SyncBankCard(bnCardReceiver, CompanyConstants.DEPO_FILE_PATH_RECEIVE);
        syncBnCard.init();
        bnCardReceiver = null;
        syncBnCard = null;

        //Sync KSCARD
        ksCardReceiver = new KsCardReceiver();
        syncKsCard = new SyncKsCard(ksCardReceiver, CompanyConstants.DEPO_FILE_PATH_RECEIVE);
        syncKsCard.init();
        ksCardReceiver = null;
        syncKsCard = null;

        //Sync INVOICE
        invoiceReceive = new InvoiceReceiver();
        syncInvoice = new SyncInvoice(invoiceReceive, CompanyConstants.DEPO_FILE_PATH_RECEIVE, code.DEPO_CODE);
        syncInvoice.init();
        invoiceReceive = null;
        syncInvoice = null;

        //Sync STFICHE
        stFicheReceive = new StFicheReceiver();
        syncStFiche = new SyncStFiche(stFicheReceive, CompanyConstants.DEPO_FILE_PATH_RECEIVE, code.DEPO_CODE);
        syncStFiche.init();
        stFicheReceive = null;
        syncStFiche = null;

        System.err.println("SYNC HAS BEEN SUCCESSFULLY!");
        if (source == 1) {
            SyncMerkezView.setText("Updated", 4);
        }
    }
}
