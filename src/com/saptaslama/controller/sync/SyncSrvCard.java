/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saptaslama.controller.sync;

import com.saptaslama.controller.CompanyConstants;
import com.saptaslama.controller.DbConnection;
import com.saptaslama.controller.receiver.SrvCardReceiver;
import com.saptaslama.model.SrvCard;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author Shagy
 */
public class SyncSrvCard {
    private SrvCardReceiver srvCard;

    private DbConnection dbLocal;

    private Connection connection;

    private final String tableNameSrvCard = CompanyConstants.COMPANY + "SRVCARD";
    
    private final String fileNameSrvCard = "SrvCard.json";
    private final String filePath;

    public SyncSrvCard(SrvCardReceiver srvCard, String filePath) {
        this.srvCard = srvCard;
        this.filePath = filePath;

        dbLocal = new DbConnection();
    }

    public void init() {
        connection = dbLocal.getConnection();

        //sync metod call here
        syncSrvCard(srvCard.getSrvCardArrayList(srvCard.getJsonObjectFromFile(filePath + fileNameSrvCard)));

        dbLocal.closeConnection(connection);
        dbLocal = null;
        srvCard = null;
    }

    //method to sync and insert SRVCARD
    public void syncSrvCard(ArrayList<SrvCard> srvCardFromCloud) {
        for (int i = 0; i < srvCardFromCloud.size(); i++) {
            int srvCardLogRef = dbLocal.getPrimaryKeyInt(connection, tableNameSrvCard,
                    String.valueOf(srvCardFromCloud.get(i).getSpecode()), "LOGICALREF", "LOGICALREF");

            if (srvCardLogRef != 0) {
                System.err.println("UPDATE SRVCARD's SPECODE TABLE");

                dbLocal.updateSource(connection, tableNameSrvCard, "SPECODE", 
                        srvCardFromCloud.get(i).getLogicalRef(), srvCardLogRef);
            } else {
                System.err.println("INSERT SRVCARD TABLE IS NOT ALLOWED HERE!!!");
            }
        }
    }
}
