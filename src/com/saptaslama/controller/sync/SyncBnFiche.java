/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saptaslama.controller.sync;

import com.saptaslama.controller.CompanyConstants;
import com.saptaslama.controller.DbConnection;
import com.saptaslama.controller.receiver.BnFicheReceiver;
import com.saptaslama.model.BnFiche;
import com.saptaslama.model.BnfLine;
import java.sql.Connection;
import java.util.ArrayList;
import org.json.simple.JSONObject;

/**
 *
 * @author Shagy
 */
public class SyncBnFiche {
    private BnFicheReceiver bnFiche;
    
    private DbConnection dbLocal;

    private Connection connection;
    
    private final String tableNameBnFiche = CompanyConstants.COMPANY + 
            CompanyConstants.SUBCOMPANY + "BNFICHE";
    private final String tableNameBnfLine = CompanyConstants.COMPANY + 
            CompanyConstants.SUBCOMPANY + "BNFLINE";
    
    private final String fileNameBnFiche = "BnFiche.json";
    private final String fileNameBnfLine = "BnfLine.json";
    
    private final String filePath;

    public SyncBnFiche(BnFicheReceiver bnFiche, String filePath) {
        this.bnFiche = bnFiche;
        this.filePath = filePath;
        
        dbLocal = new DbConnection();
    }
    
    public void init() {
        
        connection = dbLocal.getConnection();

        //here sync methods
        syncBnFiche(bnFiche.getBnFicheArrayList(bnFiche.getJsonObjectFromFile(filePath
                + fileNameBnFiche)), bnFiche.getJsonObjectFromFile(filePath + fileNameBnFiche));
        
        syncBnfLine(bnFiche.getBnfLineArrayList(bnFiche.getJsonObjectFromFile(filePath
                + fileNameBnfLine)), bnFiche.getJsonObjectFromFile(filePath + fileNameBnfLine));

        dbLocal.closeConnection(connection);
        dbLocal = null;
        bnFiche = null;
    }
    
    //method to sync and insert BNFICHE
    public void syncBnFiche(ArrayList<BnFiche> bnFicheFromCloud, JSONObject jObject) {
        for (int i = 0; i < bnFicheFromCloud.size(); i++) {
            int bnFicheLogRef = dbLocal.getPrimaryKeyInt(connection, tableNameBnFiche,
                    String.valueOf(bnFicheFromCloud.get(i).getSpecode()), "LOGICALREF", "LOGICALREF");

            if (bnFicheLogRef != 0) {
                System.err.println("UPDATE BNFICHE TABLE");
                
                dbLocal.updateSource(connection, tableNameBnFiche, "SPECODE", 
                        bnFicheFromCloud.get(i).getLogicalRef(), bnFicheLogRef);
            } else {
                System.err.println("INSERT BNFICHE TABLE");
                System.err.println("INSERT IS NOT ALLOWED HERE!!!");
            }
        }
    }
    
    //method to sync and insert BNFLINE
    public void syncBnfLine(ArrayList<BnfLine> bnfLineFromCloud, JSONObject jObject) {
        for (int i = 0; i < bnfLineFromCloud.size(); i++) {
            int bnfLineLogRef = dbLocal.getPrimaryKeyInt(connection, tableNameBnfLine,
                    String.valueOf(bnfLineFromCloud.get(i).getSpecode()), "LOGICALREF", "LOGICALREF");

            if (bnfLineLogRef != 0) {
                System.err.println("UPDATE BNFLINE TABLE");
                dbLocal.updateSource(connection, tableNameBnfLine, "SPECODE", 
                        bnfLineFromCloud.get(i).getLogicalRef(), bnfLineLogRef);
            } else {
                System.err.println("INSERT BNFLINE TABLE");
                System.err.println("INSERT IS NOT ALLOWED HERE!!!");
            }
        }
    }
}
