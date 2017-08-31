/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saptaslama.controller.sync;

import com.saptaslama.controller.CompanyConstants;
import com.saptaslama.controller.DbConnection;
import com.saptaslama.controller.receiver.BnCardReceiver;
import com.saptaslama.model.BankAcc;
import com.saptaslama.model.BnCard;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author Shagy
 */
public class SyncBankCard {

    private BnCardReceiver bnCard;

    private DbConnection dbLocal;

    private Connection connection;

    private final String tableNameBnCard = CompanyConstants.COMPANY + "BNCARD";
    private final String tableNameBankAcc = CompanyConstants.COMPANY + "BANKACC";

    private final String fileNameBnCard = "BnCard.json";
    private final String fileNameBankAcc = "BankAcc.json";
    
    private final String filePath;

    public SyncBankCard(BnCardReceiver bnCard, String filePath) {
        this.bnCard = bnCard;
        this.filePath = filePath;

        dbLocal = new DbConnection();
    }

    public void init() {
        
        connection = dbLocal.getConnection();

        // here is the metod to sync
        syncBnCard(bnCard.getBnCardArrayList(bnCard.getJsonObjectFromFile(filePath + fileNameBnCard)));
        syncBankAcc(bnCard.getBankAccArrayList(bnCard.getJsonObjectFromFile(filePath + fileNameBankAcc)));

        dbLocal.closeConnection(connection);
        dbLocal = null;
        bnCard = null;
    }

    // metod to sync and insert BNCARD table values
    public void syncBnCard(ArrayList<BnCard> bnCardFromCloud) {
        for (int i = 0; i < bnCardFromCloud.size(); i++) {
            int bnCardLogRef = dbLocal.getPrimaryKeyInt(connection, tableNameBnCard,
                    String.valueOf(bnCardFromCloud.get(i).getSpecode()), "LOGICALREF", "LOGICALREF");

            if (bnCardLogRef != 0) {
                System.err.println("UPDATE BNCARD's SPECODE TABLE");

                dbLocal.updateSource(connection, tableNameBnCard, "SPECODE", 
                        bnCardFromCloud.get(i).getLogicalRef(), bnCardLogRef);
            } else {
                System.err.println("INSERT BNCARD TABLE");
                System.err.println("INSERT IS NOT ALLOWED HERE!!!");
            }
        }
    }

    // metod to sync and insert BANKACC table values
    public void syncBankAcc(ArrayList<BankAcc> bankAccFromCloud) {
        for (int i = 0; i < bankAccFromCloud.size(); i++) {
            int bankAccLogRef = dbLocal.getPrimaryKeyInt(connection, tableNameBankAcc,
                    String.valueOf(bankAccFromCloud.get(i).getSpecode()), "LOGICALREF", "LOGICALREF");

            if (bankAccLogRef != 0) {
                System.err.println("UPDATE BANKACC's SPECODE TABLE");
                    dbLocal.updateSource(connection, tableNameBankAcc, "SPECODE", 
                            bankAccFromCloud.get(i).getLogicalRef(), bankAccLogRef);
            } else {
                System.err.println("INSERT BANKACC TABLE");
                System.err.println("INSERT IS NOT ALLOWED HERE!!!");
            }
        }
    }
}
