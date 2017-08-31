/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saptaslama.controller.sync;

import com.saptaslama.controller.CompanyConstants;
import com.saptaslama.controller.DbConnection;
import com.saptaslama.controller.receiver.KsCardReceiver;
import com.saptaslama.model.KsCard;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author Shagy
 */
public class SyncKsCard {
    private KsCardReceiver ksCard;

    private DbConnection dbLocal;

    private Connection connection;

    private final String tableNameKsCard = CompanyConstants.COMPANY + "KSCARD";

    private final String fileNameKsCard = "KsCard.json";
    private final String filePath;

    public SyncKsCard(KsCardReceiver ksCard, String filePath) {
        this.ksCard = ksCard;
        
        this.filePath = filePath;

        dbLocal = new DbConnection();
    }

    public void init() {
        connection = dbLocal.getConnection();

        // here is the metod to sync
        syncKsCard(ksCard.getKsCardArrayList(ksCard.getJsonObjectFromFile(filePath + fileNameKsCard)));

        dbLocal.closeConnection(connection);
        dbLocal = null;
        ksCard = null;
    }

    private void syncKsCard(ArrayList<KsCard> ksCardListCloud) {
        for (int i = 0; i < ksCardListCloud.size(); i++) {
            int ksCardLogRef = dbLocal.getPrimaryKeyInt(connection, tableNameKsCard,
                    String.valueOf(ksCardListCloud.get(i).getSpecode()), "LOGICALREF", "LOGICALREF");

            if (ksCardLogRef != 0) {
                System.err.println("UPDATE KSCARD's SPECODE TABLE");

                dbLocal.updateSource(connection, tableNameKsCard, "SPECODE", 
                        ksCardListCloud.get(i).getLogicalRef(), ksCardLogRef);
            } else {
                System.err.println("INSERT KSCARD TABLE IS NOT ALLOWED HERE!!!");
            }
        }
    }
}
