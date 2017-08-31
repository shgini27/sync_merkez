/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saptaslama.controller.sync;

import com.saptaslama.controller.CompanyConstants;
import com.saptaslama.controller.DbConnection;
import com.saptaslama.controller.receiver.UnitReceiver;
import com.saptaslama.model.UnitSetF;
import com.saptaslama.model.UnitSetL;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author Shagy
 */
public class SyncUnit {
    private UnitReceiver unitSet;

    private DbConnection dbLocal;

    private Connection connection;

    private final String tableNameUnitF = CompanyConstants.COMPANY + "UNITSETF";
    private final String tableNameUnitL = CompanyConstants.COMPANY + "UNITSETL";

    private final String fileNameUnitF = "UnitSetF.json";
    private final String fileNameUnitL = "UnitSetL.json";
    private final String filePath;

    public SyncUnit(UnitReceiver unitSet, String filePath) {
        this.unitSet = unitSet;
        this.filePath = filePath;

        dbLocal = new DbConnection();
    }

    public void init() {
        connection = dbLocal.getConnection();

        //sync methods are here
        syncUnitF(unitSet.getUnitSetFArrayList(unitSet.getJsonObjectFromFile(filePath + fileNameUnitF)));
        syncUnitL(unitSet.getUnitSetLArrayList(unitSet.getJsonObjectFromFile(filePath + fileNameUnitL)));

        dbLocal.closeConnection(connection);

        //clear from memory (init objects to null)
        dbLocal = null;
        unitSet = null;
    }

    //method to sync and insert UNITSETF table values
    public void syncUnitF(ArrayList<UnitSetF> unitFFromCloud) {
        for (int i = 0; i < unitFFromCloud.size(); i++) {
            int unitFLogRef = dbLocal.getPrimaryKeyInt(connection, tableNameUnitF,
                    String.valueOf(unitFFromCloud.get(i).getSpecode()), "LOGICALREF", "LOGICALREF");

            if (unitFLogRef != 0) {
                System.err.println("UPDATE UNITSETF TABLE");
                
                dbLocal.updateSource(connection, tableNameUnitF, "SPECODE", 
                        unitFFromCloud.get(i).getLogicalref(), unitFLogRef);
            } else {
                System.err.println("INSERT UNITSETF TABLE IS NOT ALLOWED!!!");
            }
        }
    }

    //method to sync and insert UNITSETL table values
    public void syncUnitL(ArrayList<UnitSetL> unitLFromCloud) {
        for (int i = 0; i < unitLFromCloud.size(); i++) {
            int unitLLogRef = dbLocal.getPrimaryKeyInt(connection, tableNameUnitL,
                    String.valueOf(unitLFromCloud.get(i).getWidth()), "LOGICALREF", "LOGICALREF");

            if (unitLLogRef != 0) {
                System.err.println("UPDATE UNITSETF TABLE");
                
                dbLocal.updateSource(connection, tableNameUnitL, "WIDTH", 
                        unitLFromCloud.get(i).getLogicalref(), unitLLogRef);
            } else {
                System.err.println("INSERT UNITSETF TABLE IS NOT ALLOWED!!!");
            }
        }
    }
}
