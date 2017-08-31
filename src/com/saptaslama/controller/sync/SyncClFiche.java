/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saptaslama.controller.sync;

import com.saptaslama.controller.CompanyConstants;
import com.saptaslama.controller.DbConnection;
import com.saptaslama.controller.receiver.ClFicheReceiver;
import com.saptaslama.model.ClFiche;
import com.saptaslama.model.ClfLine;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.json.simple.JSONObject;

/**
 *
 * @author Shagy
 */
public class SyncClFiche {
    private ClFicheReceiver clFiche;

    private DbConnection dbLocal;

    private Connection connection;

    private String[] columnClFiche, columnClfLine;

    private final String tableNameClFiche = CompanyConstants.COMPANY
            + CompanyConstants.SUBCOMPANY + "CLFICHE";
    private final String tableNameClfLine = CompanyConstants.COMPANY
            + CompanyConstants.SUBCOMPANY + "CLFLINE";

    private final String tableNameClFicheSeq = tableNameClFiche + "SEQ";
    private final String tableNameClfLineSeq = tableNameClfLine + "SEQ";

    private final String fileNameClFiche = "ClFiche.json";
    private final String fileNameClfLine = "ClfLine.json";
    
    private final String filePath;
    private final int code;

    public SyncClFiche(ClFicheReceiver clFiche, String filePath, int code) {
        this.clFiche = clFiche;
        this.filePath = filePath;
        this.code = code;

        dbLocal = new DbConnection();
    }

    public void init() {
        ResultSet resultSetClFiche;
        ResultSet resultSetClfLine;
        connection = dbLocal.getConnection();
        resultSetClFiche = dbLocal.querySelectAll(connection, tableNameClFiche);
        resultSetClfLine = dbLocal.querySelectAll(connection, tableNameClfLine);

        System.out.println("\n*********" + tableNameClFiche + " table column names***************");
        columnClFiche = clFiche.getColumnNames(resultSetClFiche);

        System.out.println("\n*********" + tableNameClfLine + " table column names***************");
        columnClfLine = clFiche.getColumnNames(resultSetClfLine);

        //here sync methods
        syncClFiche(clFiche.getClFicheArrayList(clFiche.getJsonObjectFromFile(filePath
                + fileNameClFiche)), clFiche.getJsonObjectFromFile(filePath
                + fileNameClFiche));
        
        syncClfLine(clFiche.getClfLineArrayList(clFiche.getJsonObjectFromFile(filePath
                + fileNameClfLine)), clFiche.getJsonObjectFromFile(filePath
                + fileNameClfLine));

        dbLocal.closeConnection(connection);
        dbLocal.closeResultSet(resultSetClFiche);
        dbLocal.closeResultSet(resultSetClfLine);
        dbLocal = null;
        clFiche = null;
        columnClFiche = null;
        columnClfLine = null;
    }

    //method to sync and insert CLFICHE
    public void syncClFiche(ArrayList<ClFiche> clFicheFromCloud, JSONObject jsonClFiche) {
        for (int i = 0; i < clFicheFromCloud.size(); i++) {
            int clFicheLogRef = dbLocal.getPrimaryKeyInt(connection, tableNameClFiche,
                    String.valueOf(clFicheFromCloud.get(i).getSpecode()), "LOGICALREF", "SPECCODE");

            if (clFicheLogRef != 0) {
                System.err.println("UPDATE CLFICHE TABLE");
                System.err.println("DELETE logicalRef: " + clFicheLogRef + "AND INSERT AGAIN!");

                dbLocal.deleteLogRef(connection, tableNameClFiche, "LOGICALREF", clFicheLogRef);
                dbLocal.insertCards(connection, jsonClFiche, tableNameClFiche,
                        columnClFiche, clFicheLogRef, 0, 0, i);
            } else {
                System.err.println("INSERT CLFICHE TABLE");
                int newClFicheLogRef = dbLocal.getPrimaryKeyInt(connection, tableNameClFicheSeq,
                        "1", "LASTLREF", "ID");

                newClFicheLogRef = newClFicheLogRef + 1;

                dbLocal.insertCards(connection, jsonClFiche, tableNameClFiche,
                        columnClFiche, newClFicheLogRef, 0, 0, i);
                //  update CLFICHESEQ tables LASTLREF value with new value
                dbLocal.updateKey(connection, newClFicheLogRef, tableNameClFicheSeq);
            }
        }
    }

    //method to sync and insert CLFLINE
    public void syncClfLine(ArrayList<ClfLine> clfLineFromCloud, JSONObject jsonClfLine) {
        for (int i = 0; i < clfLineFromCloud.size(); i++) {
            int clfLineLogRef = dbLocal.getPrimaryKeyInt(connection, tableNameClfLine,
                    String.valueOf(clfLineFromCloud.get(i).getSpecode()), "LOGICALREF", "SPECODE");
            int clientLogRef = dbLocal.getPrimaryKeyInt(connection, CompanyConstants.COMPANY + "CLCARD",
                    String.valueOf(code - clfLineFromCloud.get(i).getClientRef()), "LOGICALREF", "TAXNR");
            
            if(clientLogRef == 0){
                clientLogRef = dbLocal.getPrimaryKeyInt(connection, CompanyConstants.COMPANY + "CLCARD",
                    String.valueOf(clfLineFromCloud.get(i).getClientRef()), "LOGICALREF", "TAXNR");
            }

            if (clfLineLogRef != 0) {
                System.err.println("UPDATE CLFLINE TABLE");
                System.err.println("DELETE logicalRef: " + clfLineLogRef + "AND INSERT AGAIN!");

                dbLocal.deleteLogRef(connection, tableNameClfLine, "LOGICALREF", clfLineLogRef);

                int sourcefRef;
                switch (clfLineFromCloud.get(i).getModuleNr()) {
                    case 4://invoice
                        sourcefRef = dbLocal.getPrimaryKeyInt(connection, CompanyConstants.COMPANY
                                + CompanyConstants.SUBCOMPANY + "INVOICE",
                                String.valueOf(code - clfLineFromCloud.get(i).getSourceFRef()), 
                                "LOGICALREF", "TRACKNR");
                        System.err.println("invoice fishRef: " + sourcefRef);
                        break;
                    case 5://clfline(take CLFICHE logRef)
                        sourcefRef = dbLocal.getPrimaryKeyInt(connection, tableNameClFiche,
                                String.valueOf(code - clfLineFromCloud.get(i).getSourceFRef()), 
                                "LOGICALREF", "SPECCODE");
                        System.err.println("clfLine fishRef: " + sourcefRef);
                        break;
                    case 7://bnfline
                        sourcefRef = dbLocal.getPrimaryKeyInt(connection, CompanyConstants.COMPANY
                                + CompanyConstants.SUBCOMPANY + "BNFLINE", 
                                String.valueOf(clfLineFromCloud.get(i).getSourceFRef()), 
                                "LOGICALREF", "SPECODE");
                        System.err.println("bnfLine fishRef: " + sourcefRef);
                        break;
                    default://kslines(and all others)
                        sourcefRef = 0;
                        System.err.println("ksLine fishRef: " + sourcefRef);
                        break;
                }
                dbLocal.insertKsLine(connection, jsonClfLine, tableNameClfLine,
                        columnClfLine, clfLineLogRef, clientLogRef, 0, sourcefRef, i);
            } else {
                System.err.println("INSERT CLFLINE TABLE");
                int newClfLineLogRef = dbLocal.getPrimaryKeyInt(connection, tableNameClfLineSeq,
                        "1", "LASTLREF", "ID");

                newClfLineLogRef = newClfLineLogRef + 1;

                int sourcefRef;
                switch (clfLineFromCloud.get(i).getModuleNr()) {
                    case 4://invoice
                        sourcefRef = dbLocal.getPrimaryKeyInt(connection, CompanyConstants.COMPANY
                                + CompanyConstants.SUBCOMPANY + "INVOICE", 
                                String.valueOf(code - clfLineFromCloud.get(i).getSourceFRef()), 
                                "LOGICALREF", "TRACKNR");
                        System.err.println("invoice fishRef: " + sourcefRef);
                        break;
                    case 5://clfline(take CLFICHE logRef)
                        sourcefRef = dbLocal.getPrimaryKeyInt(connection, tableNameClFiche,
                                String.valueOf(code - clfLineFromCloud.get(i).getSourceFRef()), 
                                "LOGICALREF", "SPECCODE");
                        System.err.println("clfLine fishRef: " + sourcefRef);
                        break;
                    case 7://bnfline
                        sourcefRef = dbLocal.getPrimaryKeyInt(connection, CompanyConstants.COMPANY
                                + CompanyConstants.SUBCOMPANY + "BNFLINE", 
                                String.valueOf(clfLineFromCloud.get(i).getSourceFRef()), 
                                "LOGICALREF", "SPECODE");
                        System.err.println("bnfLine fishRef: " + sourcefRef);
                        break;
                    default://kslines(and all others)
                        sourcefRef = 0;
                        System.err.println("ksLine fishRef: " + sourcefRef);
                        break;
                }
                dbLocal.insertKsLine(connection, jsonClfLine, tableNameClfLine,
                        columnClfLine, newClfLineLogRef, clientLogRef, 0, sourcefRef, i);
                //  update CLFLINESEQ tables LASTLREF value with new value
                dbLocal.updateKey(connection, newClfLineLogRef, tableNameClfLineSeq);
            }
        }
    }
}
