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
import com.saptaslama.model.SrvCard;
import com.saptaslama.model.SrvNums;
import com.saptaslama.model.SrvTot;
import com.saptaslama.model.SrvUnitA;
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
public class SrvCardSender extends Sender{
    
    public void init() {
        Connection con;
        ResultSet resultSetSrvCard;
        ResultSet resultSetSrvNums;
        ResultSet resultSetSrvUnitA;
        ResultSet resultSetSrvTot;
        
        String tabelNameSrvCard = CompanyConstants.COMPANY + "SRVCARD";
        String tabelNameSrvNums = CompanyConstants.COMPANY + CompanyConstants
                .SUBCOMPANY + "SRVNUMS";
        String tabelNameSrvUnitA = CompanyConstants.COMPANY + "SRVUNITA";
        String tabelNameSrvTot = CompanyConstants.COMPANY + CompanyConstants
                .SUBCOMPANY + "SRVTOT";
        
        String fileNameSrvCard = "SrvCard.json";
        String fileNameSrvNums = "SrvNums.json";
        String fileNameSrvUnitA = "SrvUnitA.json";
        String fileNameSrvTot = "SrvTot.json";
        
        DbConnection db = new DbConnection();

        con = db.getConnection();
        resultSetSrvCard = db.querySelectAll(con, tabelNameSrvCard);
        resultSetSrvNums = db.querySelectAll(con, tabelNameSrvNums);
        resultSetSrvUnitA = db.querySelectAll(con, tabelNameSrvUnitA);
        resultSetSrvTot = db.querySelectAll(con, tabelNameSrvTot);
        
        sendJsonFile(getSrvCardJson(getSrvCardArrayList(resultSetSrvCard)), 
                CompanyConstants.FILE_PATH_SEND +  fileNameSrvCard);
        sendJsonFile(getSrvNumsJson(getSrvNumsArrayList(resultSetSrvNums)), 
                CompanyConstants.FILE_PATH_SEND +  fileNameSrvNums);
        sendJsonFile(getSrvUnitAJson(getSrvUnitAArrayList(resultSetSrvUnitA)), 
                CompanyConstants.FILE_PATH_SEND +  fileNameSrvUnitA);
        sendJsonFile(getSrvTotJson(getSrvTotArrayList(resultSetSrvTot)), 
                CompanyConstants.FILE_PATH_SEND +  fileNameSrvTot);

        db.closeConnection(con);
        db.closeResultSet(resultSetSrvCard);
        db.closeResultSet(resultSetSrvNums);
        db.closeResultSet(resultSetSrvUnitA);
        db.closeResultSet(resultSetSrvTot);
    }
    
//**********************************SRVCARD*************************************
    public ArrayList<SrvCard> getSrvCardArrayList(ResultSet resultSet) {

        ArrayList<SrvCard> tableValue = new ArrayList<>();
        SrvCard unit;

        try {
            while (resultSet.next()) {
                unit = new SrvCard(
                        resultSet.getInt("LOGICALREF"), resultSet.getShort("ACTIVE"),
                        resultSet.getShort("CARDTYPE"), resultSet.getString("CODE"),
                        resultSet.getString("DEFINITION_"), resultSet.getString("SPECODE"),
                        resultSet.getString("CYPHCODE"), resultSet.getFloat("VAT"), 
                        resultSet.getInt("EXTENREF"), resultSet.getInt("PAYMENTREF"), 
                        resultSet.getInt("UNITSETREF"), resultSet.getShort("CAPIBLOCK_CREATEDBY"), 
                        resultSet.getString("CAPIBLOCK_CREADEDDATE"), resultSet.getShort("CAPIBLOCK_CREATEDHOUR"), 
                        resultSet.getShort("CAPIBLOCK_CREATEDMIN"), resultSet.getShort("CAPIBLOCK_CREATEDSEC"), 
                        resultSet.getShort("CAPIBLOCK_MODIFIEDBY"), resultSet.getString("CAPIBLOCK_MODIFIEDDATE"), 
                        resultSet.getShort("CAPIBLOCK_MODIFIEDHOUR"), resultSet.getShort("CAPIBLOCK_MODIFIEDMIN"), 
                        resultSet.getShort("CAPIBLOCK_MODIFIEDSEC"), resultSet.getShort("SITEID"),
                        resultSet.getShort("RECSTATUS"), resultSet.getInt("ORGLOGICREF"), 
                        resultSet.getInt("WFSTATUS"), resultSet.getFloat("RETURNVAT"), 
                        resultSet.getShort("IMPORTEXPNS"), resultSet.getShort("AFFECTCOST"), 
                        resultSet.getInt("ADDTAXREF"), resultSet.getShort("DISTTYPE"));
                tableValue.add(unit);
            }
            return tableValue;
        } catch (SQLException ex) {
            Logger.getLogger(SrvCardSender.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
            return null;
        }
    }
    
    public JsonObject getSrvCardJson(ArrayList<SrvCard> list) {
        JsonObject jsonObject = new JsonObject();
        JSONArray logicalRef = new JSONArray();
        JSONArray active = new JSONArray();
        JSONArray cardType = new JSONArray();
        JSONArray code = new JSONArray();
        JSONArray definition = new JSONArray();
        JSONArray specode = new JSONArray();
        JSONArray cyphCode = new JSONArray();
        JSONArray vat = new JSONArray();
        JSONArray extenRef = new JSONArray();
        JSONArray paymentRef = new JSONArray();
        JSONArray unitSetRef = new JSONArray();
        JSONArray capiblockCreatedBy = new JSONArray();
        JSONArray capiblockCreatedDate = new JSONArray();
        JSONArray capiblockCreatedHour = new JSONArray();
        JSONArray capiblockCreatedMin = new JSONArray();
        JSONArray capiblockCreatedSec = new JSONArray();
        JSONArray capiblockModifiedBy = new JSONArray();
        JSONArray capiblockModifiedDate = new JSONArray();
        JSONArray capiblockModifiedHour = new JSONArray();
        JSONArray capiblockModifiedMin = new JSONArray();
        JSONArray capiblockModifiedSec = new JSONArray();
        JSONArray siteId = new JSONArray();
        JSONArray recStatus = new JSONArray();
        JSONArray orgLogicRef = new JSONArray();
        JSONArray wfStatus = new JSONArray();
        JSONArray returnVat = new JSONArray();
        JSONArray importExpns = new JSONArray();
        JSONArray affectCost = new JSONArray();
        JSONArray addTaxRef = new JSONArray();
        JSONArray distType = new JSONArray();
        
        for (int a = 0; a < list.size(); a++) {

            //here we setting the specode with local logicalRef (example: if depo 
            //then we adding "D" infront of LogREf)
            specode.add(String.valueOf(list.get(a).getLogicalRef()));
            
            logicalRef.add(list.get(a).getLogicalRef());
            active.add(list.get(a).getActive());
            cardType.add(list.get(a).getCardType());
            definition.add(list.get(a).getDefinition());
            code.add(list.get(a).getCode());
            cyphCode.add(list.get(a).getCyphCode());
            vat.add(list.get(a).getVat());
            extenRef.add(list.get(a).getExtenRef());
            paymentRef.add(list.get(a).getPaymentRef());
            unitSetRef.add(list.get(a).getUnitSetRef());
            capiblockCreatedBy.add(list.get(a).getCapiblockCreatedBy());
            capiblockCreatedDate.add(list.get(a).getCapiblockCreatedDate());
            capiblockCreatedHour.add(list.get(a).getCapiblockCreatedHour());
            capiblockCreatedMin.add(list.get(a).getCapiblockCreatedMin());
            capiblockCreatedSec.add(list.get(a).getCapiblockCreatedSec());
            capiblockModifiedBy.add(list.get(a).getCapiblockModifiedBy());
            capiblockModifiedDate.add(list.get(a).getCapiblockModifiedDate());
            capiblockModifiedHour.add(list.get(a).getCapiblockModifiedHour());
            capiblockModifiedMin.add(list.get(a).getCapiblockModifiedMin());
            capiblockModifiedSec.add(list.get(a).getCapiblockModifiedSec());
            siteId.add(list.get(a).getSiteId());
            recStatus.add(list.get(a).getRecStatus());
            orgLogicRef.add(list.get(a).getOrgLogicRef());
            wfStatus.add(list.get(a).getWfStatus());
            returnVat.add(list.get(a).getReturnVat());
            importExpns.add(list.get(a).getImportExpns());
            affectCost.add(list.get(a).getAffectCost());
            addTaxRef.add(list.get(a).getAddTaxRef());
            distType.add(list.get(a).getDistType());
            
        }
        jsonObject.setObject("LOGICALREF", logicalRef);
        jsonObject.setObject("ACTIVE", active);
        jsonObject.setObject("CARDTYPE", cardType);
        jsonObject.setObject("CODE", code);
        jsonObject.setObject("DEFINITION_", definition);
        jsonObject.setObject("SPECODE", specode);
        jsonObject.setObject("CYPHCODE", cyphCode);
        jsonObject.setObject("VAT", vat);
        jsonObject.setObject("EXTENREF", extenRef);
        jsonObject.setObject("PAYMENTREF", paymentRef);
        jsonObject.setObject("UNITSETREF", unitSetRef);
        jsonObject.setObject("CAPIBLOCK_CREATEDBY", capiblockCreatedBy);
        jsonObject.setObject("CAPIBLOCK_CREADEDDATE", capiblockCreatedDate);
        jsonObject.setObject("CAPIBLOCK_CREATEDHOUR", capiblockCreatedHour);
        jsonObject.setObject("CAPIBLOCK_CREATEDMIN", capiblockCreatedMin);
        jsonObject.setObject("CAPIBLOCK_CREATEDSEC", capiblockCreatedSec);
        jsonObject.setObject("CAPIBLOCK_MODIFIEDBY", capiblockModifiedBy);
        jsonObject.setObject("CAPIBLOCK_MODIFIEDDATE", capiblockModifiedDate);
        jsonObject.setObject("CAPIBLOCK_MODIFIEDHOUR", capiblockModifiedHour);
        jsonObject.setObject("CAPIBLOCK_MODIFIEDMIN", capiblockModifiedMin);
        jsonObject.setObject("CAPIBLOCK_MODIFIEDSEC", capiblockModifiedSec);
        jsonObject.setObject("SITEID", siteId);
        jsonObject.setObject("RECSTATUS", recStatus);
        jsonObject.setObject("ORGLOGICREF", orgLogicRef);
        jsonObject.setObject("WFSTATUS", wfStatus);
        jsonObject.setObject("RETURNVAT", returnVat);
        jsonObject.setObject("IMPORTEXPNS", importExpns);
        jsonObject.setObject("AFFECTCOST", affectCost);
        jsonObject.setObject("ADDTAXREF", addTaxRef);
        jsonObject.setObject("DISTTYPE", distType);
        
        return jsonObject;
    }
    
//**********************************SRVNUMS*************************************
    public ArrayList<SrvNums> getSrvNumsArrayList(ResultSet resultSet) {

        ArrayList<SrvNums> tableValue = new ArrayList<>();
        SrvNums unit;

        try {
            while (resultSet.next()) {
                unit = new SrvNums(
                        resultSet.getInt("LOGICALREF"), resultSet.getInt("CARDREF"),
                        resultSet.getShort("INVENNO"), resultSet.getShort("DURATION"),
                        resultSet.getFloat("ORDERED"), resultSet.getFloat("SHIPPED"),
                        resultSet.getString("LASTTRDATE"));
                tableValue.add(unit);
            }
            return tableValue;
        } catch (SQLException ex) {
            Logger.getLogger(SrvCardSender.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
            return null;
        }
    }
    
    public JsonObject getSrvNumsJson(ArrayList<SrvNums> list) {
        JsonObject jsonObject = new JsonObject();
        JSONArray logicalRef = new JSONArray();
        JSONArray cardRef = new JSONArray();
        JSONArray invenno = new JSONArray();
        JSONArray duration = new JSONArray();
        JSONArray ordered = new JSONArray();
        JSONArray shipped = new JSONArray();
        JSONArray lastTrDate = new JSONArray();
        
        for (int a = 0; a < list.size(); a++) {

            logicalRef.add(list.get(a).getLogicalRef());
            cardRef.add(list.get(a).getCardRef());
            invenno.add(list.get(a).getInvenno());
            duration.add(list.get(a).getDuration());
            ordered.add(list.get(a).getOrdered());
            shipped.add(list.get(a).getShipped());
            lastTrDate.add(list.get(a).getLastTrDate());
                        
        }
        jsonObject.setObject("LOGICALREF", logicalRef);
        jsonObject.setObject("CARDREF", cardRef);
        jsonObject.setObject("INVENNO", invenno);
        jsonObject.setObject("DURATION", duration);
        jsonObject.setObject("ORDERED", ordered);
        jsonObject.setObject("SHIPPED", shipped);
        jsonObject.setObject("LASTTRDATE", lastTrDate);
        
        return jsonObject;
    }
    
//**********************************SRVUNITA************************************
    public ArrayList<SrvUnitA> getSrvUnitAArrayList(ResultSet resultSet) {

        ArrayList<SrvUnitA> tableValue = new ArrayList<>();
        SrvUnitA unit;

        try {
            while (resultSet.next()) {
                unit = new SrvUnitA(
                        resultSet.getInt("LOGICALREF"), resultSet.getInt("SRVREF"),
                        resultSet.getShort("LINENR"), resultSet.getInt("UNITLINEREF"),
                        resultSet.getShort("PRIORITY"));
                tableValue.add(unit);
            }
            return tableValue;
        } catch (SQLException ex) {
            Logger.getLogger(SrvCardSender.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
            return null;
        }
    }
    
    public JsonObject getSrvUnitAJson(ArrayList<SrvUnitA> list) {
        JsonObject jsonObject = new JsonObject();
        JSONArray logicalRef = new JSONArray();
        JSONArray srvRef = new JSONArray();
        JSONArray lineNr = new JSONArray();
        JSONArray unitLineRef = new JSONArray();
        JSONArray priority = new JSONArray();
        
        for (int a = 0; a < list.size(); a++) {

            logicalRef.add(list.get(a).getLogicalRef());
            srvRef.add(list.get(a).getSrvRef());
            lineNr.add(list.get(a).getLineNr());
            unitLineRef.add(list.get(a).getUnitLineRef());
            priority.add(list.get(a).getPriority());
                        
        }
        jsonObject.setObject("LOGICALREF", logicalRef);
        jsonObject.setObject("SRVREF", srvRef);
        jsonObject.setObject("LINENR", lineNr);
        jsonObject.setObject("UNITLINEREF", unitLineRef);
        jsonObject.setObject("PRIORITY", priority);
        
        return jsonObject;
    }
    
//**********************************SRVTOT**************************************
    public ArrayList<SrvTot> getSrvTotArrayList(ResultSet resultSet) {

        ArrayList<SrvTot> tableValue = new ArrayList<>();
        SrvTot unit;

        try {
            while (resultSet.next()) {
                unit = new SrvTot(
                        resultSet.getInt("LOGICALREF"),
                        resultSet.getInt("CARDREF"), resultSet.getShort("INVENNO"),
                        resultSet.getShort("MONTH_"), resultSet.getFloat("TOTALS_AMOUNT"),
                        resultSet.getFloat("TOTALS_CASHAMNT"), resultSet.getFloat("TOTALS_CURRAMNT"),
                        resultSet.getShort("YEAR_"));
                tableValue.add(unit);
            }
            return tableValue;
        } catch (SQLException ex) {
            Logger.getLogger(SrvCardSender.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
            return null;
        }
    }
    
    public JsonObject getSrvTotJson(ArrayList<SrvTot> list) {
        JsonObject jsonObject = new JsonObject();
        JSONArray logicalRef = new JSONArray();
        JSONArray cardRef = new JSONArray();
        JSONArray invenno = new JSONArray();
        JSONArray month = new JSONArray();
        JSONArray totalsAmount = new JSONArray();
        JSONArray totalsCashAmnt = new JSONArray();
        JSONArray totalsCurrAmnt = new JSONArray();
        JSONArray year = new JSONArray();

        for (int a = 0; a < list.size(); a++) {

            logicalRef.add(list.get(a).getLogicalRef());
            cardRef.add(list.get(a).getCardRef());
            invenno.add(list.get(a).getInvenno());
            month.add(list.get(a).getMonth());
            totalsAmount.add(list.get(a).getTotalsAmount());
            totalsCashAmnt.add(list.get(a).getTotalsCashAmnt());
            totalsCurrAmnt.add(list.get(a).getTotalsCurrAmnt());
            year.add(list.get(a).getYear());

        }
        jsonObject.setObject("LOGICALREF", logicalRef);
        jsonObject.setObject("CARDREF", cardRef);
        jsonObject.setObject("INVENNO", invenno);
        jsonObject.setObject("MONTH_", month);
        jsonObject.setObject("TOTALS_AMOUNT", totalsAmount);
        jsonObject.setObject("TOTALS_CASHAMNT", totalsCashAmnt);
        jsonObject.setObject("TOTALS_CURRAMNT", totalsCurrAmnt);
        jsonObject.setObject("YEAR_", year);

        return jsonObject;
    }
}
