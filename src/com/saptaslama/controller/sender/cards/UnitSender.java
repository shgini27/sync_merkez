/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saptaslama.controller.sender.cards;

import com.saptaslama.controller.CompanyConstants;
import com.saptaslama.controller.DbConnection;
import com.saptaslama.controller.sender.Sender;
import com.saptaslama.model.JsonObject;
import com.saptaslama.model.UnitSetF;
import com.saptaslama.model.UnitSetL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;

/**
 *
 * @author Shagy
 */
public class UnitSender extends Sender{
    
    public void init() {
        Connection connection;
        ResultSet resultSetUnitSetF;
        ResultSet resultSetUnitSetL;
        
        String tableNameF = CompanyConstants.COMPANY + "UNITSETF";
        String tableNameL = CompanyConstants.COMPANY + "UNITSETL";
        
        String fileNameF = "UnitSetF.json";
        String fileNameL = "UnitSetL.json";
        
        String [] columnF;
        String [] columnL;

        DbConnection db = new DbConnection();

        connection = db.getConnection();
        
        resultSetUnitSetF = db.querySelectAll(connection, tableNameF);
        resultSetUnitSetL = db.querySelectAll(connection, tableNameL);

        columnF = getColumnNames(resultSetUnitSetF);
        columnL = getColumnNames(resultSetUnitSetL);

        sendJsonFile(getUnitSetFJson(getUnitSetFArrayList(resultSetUnitSetF, columnF), columnF), 
                CompanyConstants.FILE_PATH_SEND + fileNameF);
        sendJsonFile(getUnitSetLJson(getUnitSetLArrayList(resultSetUnitSetL, columnL), columnL), 
                CompanyConstants.FILE_PATH_SEND + fileNameL);

        db.closeConnection(connection);
        db.closeResultSet(resultSetUnitSetF);
        db.closeResultSet(resultSetUnitSetL);
    }
    
//***********************************UNITSETF***********************************
    public ArrayList<UnitSetF> getUnitSetFArrayList(ResultSet resultSet, String[] columnNames) {

        ArrayList<UnitSetF> tableValue = new ArrayList<>();
        UnitSetF unit;

        try {
            while (resultSet.next()) {
                unit = new UnitSetF(resultSet.getInt(columnNames[0]),
                        resultSet.getString(columnNames[1]), resultSet.getString(columnNames[2]),
                        resultSet.getShort(columnNames[3]), resultSet.getShort(columnNames[4]),
                        resultSet.getString(columnNames[5]), resultSet.getString(columnNames[6]),
                        resultSet.getShort(columnNames[7]), resultSet.getString(columnNames[8]),
                        resultSet.getShort(columnNames[9]), resultSet.getShort(columnNames[10]),
                        resultSet.getShort(columnNames[11]), resultSet.getShort(columnNames[12]),
                        resultSet.getString(columnNames[13]), resultSet.getShort(columnNames[14]),
                        resultSet.getShort(columnNames[15]), resultSet.getShort(columnNames[16]),
                        resultSet.getShort(columnNames[17]), resultSet.getShort(columnNames[18]),
                        resultSet.getInt(columnNames[19]), resultSet.getInt(columnNames[20]));
                tableValue.add(unit);
            }
            return tableValue;
        } catch (SQLException ex) {
            Logger.getLogger(UnitSender.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
            return null;
        }
        
    }
    
    public JsonObject getUnitSetFJson(ArrayList<UnitSetF> list, String[] columnName) {
        JsonObject jsonObject = new JsonObject();
        JSONArray arrayLogicalRef = new JSONArray();
        JSONArray arrayCode = new JSONArray();
        JSONArray arrayName = new JSONArray();
        JSONArray arrayCardType = new JSONArray();
        JSONArray arraySpecItem = new JSONArray();
        JSONArray arraySpeCode = new JSONArray();
        JSONArray arrayCyphCode = new JSONArray();
        JSONArray arrayCapiblockCreatedby = new JSONArray();
        JSONArray arrayCapiblockCreatedDate = new JSONArray();
        JSONArray arrayCapiblockCreatedHour = new JSONArray();
        JSONArray arrayCapiblockCreatedMin = new JSONArray();
        JSONArray arrayCapiblockCreatedSec = new JSONArray();
        JSONArray arrayCapiblockModifiedby = new JSONArray();
        JSONArray arrayCapiblockModifiedDate = new JSONArray();
        JSONArray arrayCapiblockModifiedHour = new JSONArray();
        JSONArray arrayCapiblockModifiedMin = new JSONArray();
        JSONArray arrayCapiblockModifiedSec = new JSONArray();
        JSONArray arraySiteId = new JSONArray();
        JSONArray arrayRecStatus = new JSONArray();
        JSONArray arrayOrgLogicalRef = new JSONArray();
        JSONArray arrayWfStatus = new JSONArray();


        for (int a = 0; a < list.size(); a++) {

            //here we setting the specode with local logicalRef (example: if depo 
            //then we adding "D" infront of LogREf)
            arraySpeCode.add(a, String.valueOf(list.get(a).getLogicalref()));
            
            arrayLogicalRef.add(list.get(a).getLogicalref());
            arrayName.add(list.get(a).getName());
            arrayCardType.add(list.get(a).getCardtype());
            arraySpecItem.add(list.get(a).getSpecitem());
            arrayCode.add(list.get(a).getCode());
            arrayCyphCode.add(list.get(a).getCyphcode());
            arrayCapiblockCreatedby.add(list.get(a).getCapiblockCreatedby());
            arrayCapiblockCreatedDate.add(list.get(a).getCapiblockCreadeddate());
            arrayCapiblockCreatedHour.add(list.get(a).getCapiblockCreatedhour());
            arrayCapiblockCreatedMin.add(list.get(a).getCapiblockCreatedmin());
            arrayCapiblockCreatedSec.add(list.get(a).getCapiblockCreatedsec());
            arrayCapiblockModifiedby.add(list.get(a).getCapiblockModifiedby());
            arrayCapiblockModifiedDate.add(list.get(a).getCapiblockModifieddate());
            arrayCapiblockModifiedHour.add(list.get(a).getCapiblockModifiedhour());
            arrayCapiblockModifiedMin.add(list.get(a).getCapiblockModifiedmin());
            arrayCapiblockModifiedSec.add(list.get(a).getCapiblockModifiedsec());
            arraySiteId.add(list.get(a).getSiteid());
            arrayRecStatus.add(list.get(a).getRecstatus());
            arrayOrgLogicalRef.add(list.get(a).getOrglogicref());
            arrayWfStatus.add(list.get(a).getWfstatus());
        }
        jsonObject.setObject(columnName[0], arrayLogicalRef);
        jsonObject.setObject(columnName[1], arrayCode);
        jsonObject.setObject(columnName[2], arrayName);
        jsonObject.setObject(columnName[3], arrayCardType);
        jsonObject.setObject(columnName[4], arraySpecItem);
        jsonObject.setObject(columnName[5], arraySpeCode);
        jsonObject.setObject(columnName[6], arrayCyphCode);
        jsonObject.setObject(columnName[7], arrayCapiblockCreatedby);
        jsonObject.setObject(columnName[8], arrayCapiblockCreatedDate);
        jsonObject.setObject(columnName[9], arrayCapiblockCreatedHour);
        jsonObject.setObject(columnName[10], arrayCapiblockCreatedMin);
        jsonObject.setObject(columnName[11], arrayCapiblockCreatedSec);
        jsonObject.setObject(columnName[12], arrayCapiblockModifiedby);
        jsonObject.setObject(columnName[13], arrayCapiblockModifiedDate);
        jsonObject.setObject(columnName[14], arrayCapiblockModifiedHour);
        jsonObject.setObject(columnName[15], arrayCapiblockModifiedMin);
        jsonObject.setObject(columnName[16], arrayCapiblockModifiedSec);
        jsonObject.setObject(columnName[17], arraySiteId);
        jsonObject.setObject(columnName[18], arrayRecStatus);
        jsonObject.setObject(columnName[19], arrayOrgLogicalRef);
        jsonObject.setObject(columnName[20], arrayWfStatus);

        return jsonObject;
    }
    
//***********************************UNITSETL***********************************
    public ArrayList<UnitSetL> getUnitSetLArrayList(ResultSet resultSet, String[] columnNames) {

        ArrayList<UnitSetL> tableValue = new ArrayList<>();
        UnitSetL unit;

        try {

            columnNames = getColumnNames(resultSet);
            while (resultSet.next()) {
                unit = new UnitSetL(resultSet.getInt(columnNames[0]),
                        resultSet.getString(columnNames[1]), resultSet.getString(columnNames[2]),
                        resultSet.getInt(columnNames[3]), resultSet.getShort(columnNames[4]),
                        resultSet.getShort(columnNames[5]), resultSet.getDouble(columnNames[6]),
                        resultSet.getDouble(columnNames[7]), resultSet.getInt(columnNames[8]),
                        resultSet.getDouble(columnNames[9]), resultSet.getDouble(columnNames[10]),
                        resultSet.getDouble(columnNames[11]), resultSet.getDouble(columnNames[12]),
                        resultSet.getDouble(columnNames[13]), resultSet.getInt(columnNames[14]),
                        resultSet.getInt(columnNames[15]), resultSet.getInt(columnNames[16]),
                        resultSet.getInt(columnNames[17]), resultSet.getInt(columnNames[18]),
                        resultSet.getInt(columnNames[19]), resultSet.getShort(columnNames[20]));
                tableValue.add(unit);
            }
            return tableValue;
        } catch (SQLException ex) {
            Logger.getLogger(UnitSender.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
            return null;
        }
    }
    
    public JsonObject getUnitSetLJson(ArrayList<UnitSetL> list, String[] columnName) {
        JsonObject jsonObject = new JsonObject();
        JSONArray arrayList1 = new JSONArray();
        JSONArray arrayList2 = new JSONArray();
        JSONArray arrayList3 = new JSONArray();
        JSONArray arrayList4 = new JSONArray();
        JSONArray arrayList5 = new JSONArray();
        JSONArray arrayList6 = new JSONArray();
        JSONArray arrayList7 = new JSONArray();
        JSONArray arrayList8 = new JSONArray();
        JSONArray arrayList9 = new JSONArray();
        JSONArray arrayList10 = new JSONArray();
        JSONArray arrayList11 = new JSONArray();
        JSONArray arrayList12 = new JSONArray();
        JSONArray arrayList13 = new JSONArray();
        JSONArray arrayList14 = new JSONArray();
        JSONArray arrayList15 = new JSONArray();
        JSONArray arrayList16 = new JSONArray();
        JSONArray arrayList17 = new JSONArray();
        JSONArray arrayList18 = new JSONArray();
        JSONArray arrayList19 = new JSONArray();
        JSONArray arrayList20 = new JSONArray();
        JSONArray arrayList21 = new JSONArray();

        for (int a = 0; a < list.size(); a++) {
            //here we setting the specode with local logicalRef (example: if depo 
            //then we adding "D" infront of LogREf)
            arrayList9.add(list.get(a).getLogicalref());
            
            arrayList1.add(list.get(a).getLogicalref());
            arrayList2.add(list.get(a).getCode());
            arrayList3.add(list.get(a).getName());
            arrayList4.add(list.get(a).getUnitsetref());
            arrayList5.add(list.get(a).getLinenr());
            arrayList6.add(list.get(a).getMainunit());
            arrayList7.add(list.get(a).getConvfact1());
            arrayList8.add(list.get(a).getConvfact2());
            arrayList10.add(list.get(a).getLength());
            arrayList11.add(list.get(a).getHeight());
            arrayList12.add(list.get(a).getArea());
            arrayList13.add(list.get(a).getVolume());
            arrayList14.add(list.get(a).getWeight());
            arrayList15.add(list.get(a).getWidthref());
            arrayList16.add(list.get(a).getLengthref());
            arrayList17.add(list.get(a).getHeightref());
            arrayList18.add(list.get(a).getArearef());
            arrayList19.add(list.get(a).getVolumeref());
            arrayList20.add(list.get(a).getWeightref());
            arrayList21.add(list.get(a).getDivunit());
        }
        jsonObject.setObject(columnName[0], arrayList1);
        jsonObject.setObject(columnName[1], arrayList2);
        jsonObject.setObject(columnName[2], arrayList3);
        jsonObject.setObject(columnName[3], arrayList4);
        jsonObject.setObject(columnName[4], arrayList5);
        jsonObject.setObject(columnName[5], arrayList6);
        jsonObject.setObject(columnName[6], arrayList7);
        jsonObject.setObject(columnName[7], arrayList8);
        jsonObject.setObject(columnName[8], arrayList9);
        jsonObject.setObject(columnName[9], arrayList10);
        jsonObject.setObject(columnName[10], arrayList11);
        jsonObject.setObject(columnName[11], arrayList12);
        jsonObject.setObject(columnName[12], arrayList13);
        jsonObject.setObject(columnName[13], arrayList14);
        jsonObject.setObject(columnName[14], arrayList15);
        jsonObject.setObject(columnName[15], arrayList16);
        jsonObject.setObject(columnName[16], arrayList17);
        jsonObject.setObject(columnName[17], arrayList18);
        jsonObject.setObject(columnName[18], arrayList19);
        jsonObject.setObject(columnName[19], arrayList20);
        jsonObject.setObject(columnName[20], arrayList21);

        return jsonObject;
    }
}
