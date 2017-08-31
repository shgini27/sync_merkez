/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saptaslama.controller.sender.cards;

import com.saptaslama.controller.CompanyConstants;
import com.saptaslama.controller.DbConnection;
import com.saptaslama.controller.sender.Sender;
import com.saptaslama.model.CrdacRef;
import com.saptaslama.model.EmCenter;
import com.saptaslama.model.JsonObject;
import com.saptaslama.model.KsCard;
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
public class KsCardSender extends Sender{
    
    public void init() {
        Connection con;
        ResultSet resultSetKsCard;
        ResultSet resultSetEmCenter;
        ResultSet resultSetCrdacRef;
        
        String tabelNameKsCard = CompanyConstants.COMPANY + "KSCARD";
        String tabelNameEmCenter = CompanyConstants.COMPANY + "EMCENTER";
        String tabelNameCrdacRef = CompanyConstants.COMPANY + "CRDACREF";
        
        String fileNameKsCard = "KsCard.json";
        String fileNameEmCenter = "EmCenter.json";
        String fileNameCrdacRef = "CrdacRef.json";
        
        DbConnection db = new DbConnection();

        con = db.getConnection();
        
        resultSetKsCard = db.querySelectAll(con, tabelNameKsCard);
        resultSetEmCenter = db.querySelectAll(con, tabelNameEmCenter);
        resultSetCrdacRef = db.querySelectAll(con, tabelNameCrdacRef);
        
        sendJsonFile(getKsCardJson(getKsCardArrayList(resultSetKsCard)), 
                CompanyConstants.FILE_PATH_SEND + fileNameKsCard);
        sendJsonFile(getEmCenterJson(getEmCenterArrayList(resultSetEmCenter)), 
                CompanyConstants.FILE_PATH_SEND + fileNameEmCenter);
        sendJsonFile(getCrdacRefJson(getCrdacRefArrayList(resultSetCrdacRef)), 
                CompanyConstants.FILE_PATH_SEND + fileNameCrdacRef);
        
        db.closeConnection(con);
        db.closeResultSet(resultSetKsCard);
        db.closeResultSet(resultSetEmCenter);
        db.closeResultSet(resultSetCrdacRef);
    }
    
//*******************************KSCARD*****************************************
    public ArrayList<KsCard> getKsCardArrayList(ResultSet resultSet) {

        ArrayList<KsCard> tableValue = new ArrayList<>();
        KsCard unit;

        try {
            while (resultSet.next()) {
                unit = new KsCard(
                        resultSet.getInt("LOGICALREF"), resultSet.getString("CODE"), 
                        resultSet.getString("NAME"), resultSet.getString("SPECODE"), 
                        resultSet.getString("CYPHCODE"), resultSet.getString("EXPLAIN"), 
                        resultSet.getString("ADDR1"), resultSet.getString("ADDR2"), 
                        resultSet.getShort("CAPIBLOCK_CREATEDBY"), resultSet.getString("CAPIBLOCK_CREADEDDATE"), 
                        resultSet.getShort("CAPIBLOCK_CREATEDHOUR"), resultSet.getShort("CAPIBLOCK_CREATEDMIN"), 
                        resultSet.getShort("CAPIBLOCK_CREATEDSEC"), resultSet.getShort("CAPIBLOCK_MODIFIEDBY"), 
                        resultSet.getString("CAPIBLOCK_MODIFIEDDATE"), resultSet.getShort("CAPIBLOCK_MODIFIEDHOUR"), 
                        resultSet.getShort("CAPIBLOCK_MODIFIEDMIN"), resultSet.getShort("CAPIBLOCK_MODIFIEDSEC"), 
                        resultSet.getShort("ACTIVE"), resultSet.getShort("SITEID"), 
                        resultSet.getShort("RECSTATUS"), resultSet.getInt("ORGLOGICREF"), 
                        resultSet.getInt("WFSTATUS"), resultSet.getShort("CCURRENCY"), 
                        resultSet.getShort("CURRATETYPE"), resultSet.getShort("FIXEDCURRTYPE"));
                tableValue.add(unit);
            }
            return tableValue;
        } catch (SQLException ex) {
            Logger.getLogger(KsCardSender.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
            return null;
        }
    }
    
    public JsonObject getKsCardJson(ArrayList<KsCard> list) {
        JsonObject jsonObject = new JsonObject();
        JSONArray logicalRef = new JSONArray();
        JSONArray code = new JSONArray();
        JSONArray name = new JSONArray();
        JSONArray specode = new JSONArray();
        JSONArray cyphCode = new JSONArray();
        JSONArray explain = new JSONArray();
        JSONArray addr1 = new JSONArray();
        JSONArray addr2 = new JSONArray();
        JSONArray capiblockCreatedby = new JSONArray();
        JSONArray capiblockCreadeddate = new JSONArray();
        JSONArray capiblockCreatedhour = new JSONArray();
        JSONArray capiblockCreatedmin = new JSONArray();
        JSONArray capiblockCreatedsec = new JSONArray();
        JSONArray capiblockModifiedby = new JSONArray();
        JSONArray capiblockModifieddate = new JSONArray();
        JSONArray capiblockModifiedhour = new JSONArray();
        JSONArray capiblockModifiedmin = new JSONArray();
        JSONArray capiblockModifiedsec = new JSONArray();
        JSONArray active = new JSONArray();
        JSONArray siteId = new JSONArray();
        JSONArray recStatus = new JSONArray();
        JSONArray orgLogicRef = new JSONArray();
        JSONArray wfStatus = new JSONArray();
        JSONArray cCurrancy = new JSONArray();
        JSONArray curRateType = new JSONArray();
        JSONArray fixedCurrType = new JSONArray();

        for (int a = 0; a < list.size(); a++) {
            
            //here we setting the specode with local logicalRef (example: if depo then we adding "D" infront of LogREf)
            specode.add(String.valueOf(list.get(a).getLogicalRef()));

            logicalRef.add(list.get(a).getLogicalRef());
            name.add(list.get(a).getName());
            code.add(list.get(a).getCode());
            cyphCode.add(list.get(a).getName());
            explain.add(list.get(a).getExplain());
            addr1.add(list.get(a).getAddr1());
            addr2.add(list.get(a).getAddr2());
            capiblockCreatedby.add(list.get(a).getCapiblockCreatedby());
            capiblockCreadeddate.add(list.get(a).getCapiblockCreadeddate());
            capiblockCreatedhour.add(list.get(a).getCapiblockCreatedhour());
            capiblockCreatedmin.add(list.get(a).getCapiblockCreatedmin());
            capiblockCreatedsec.add(list.get(a).getCapiblockCreatedsec());
            capiblockModifiedby.add(list.get(a).getCapiblockModifiedby());
            capiblockModifieddate.add(list.get(a).getCapiblockModifieddate());
            capiblockModifiedhour.add(list.get(a).getCapiblockModifiedhour());
            capiblockModifiedmin.add(list.get(a).getCapiblockModifiedmin());
            capiblockModifiedsec.add(list.get(a).getCapiblockModifiedsec());
            active.add(list.get(a).getActive());
            siteId.add(list.get(a).getSiteId());
            recStatus.add(list.get(a).getRecStatus());
            orgLogicRef.add(list.get(a).getOrgLogicRef());
            wfStatus.add(list.get(a).getWfStatus());
            cCurrancy.add(list.get(a).getcCurrancy());
            curRateType.add(list.get(a).getCurRateType());
            fixedCurrType.add(list.get(a).getFixedCurrType());            
        }
        jsonObject.setObject("LOGICALREF", logicalRef);
        jsonObject.setObject("CODE", code);
        jsonObject.setObject("NAME", name);
        jsonObject.setObject("SPECODE", specode);
        jsonObject.setObject("CYPHCODE", cyphCode);
        jsonObject.setObject("EXPLAIN", explain);
        jsonObject.setObject("ADDR1", addr1);
        jsonObject.setObject("ADDR2", addr2);
        jsonObject.setObject("CAPIBLOCK_CREATEDBY", capiblockCreatedby);
        jsonObject.setObject("CAPIBLOCK_CREADEDDATE", capiblockCreadeddate);
        jsonObject.setObject("CAPIBLOCK_CREATEDHOUR", capiblockCreatedhour);
        jsonObject.setObject("CAPIBLOCK_CREATEDMIN", capiblockCreatedmin);
        jsonObject.setObject("CAPIBLOCK_CREATEDSEC", capiblockCreatedsec);
        jsonObject.setObject("CAPIBLOCK_MODIFIEDBY", capiblockModifiedby);
        jsonObject.setObject("CAPIBLOCK_MODIFIEDDATE", capiblockModifieddate);
        jsonObject.setObject("CAPIBLOCK_MODIFIEDHOUR", capiblockModifiedhour);
        jsonObject.setObject("CAPIBLOCK_MODIFIEDMIN", capiblockModifiedmin);
        jsonObject.setObject("CAPIBLOCK_MODIFIEDSEC", capiblockModifiedsec);
        jsonObject.setObject("ACTIVE", active);
        jsonObject.setObject("SITEID", siteId);
        jsonObject.setObject("RECSTATUS", recStatus);
        jsonObject.setObject("ORGLOGICREF", orgLogicRef);
        jsonObject.setObject("WFSTATUS", wfStatus);
        jsonObject.setObject("CCURRENCY", cCurrancy);
        jsonObject.setObject("CURRATETYPE", curRateType);
        jsonObject.setObject("FIXEDCURRTYPE", fixedCurrType);
        return jsonObject;
    }
    
//*******************************EMCENTER***************************************
    public ArrayList<EmCenter> getEmCenterArrayList(ResultSet resultSet) {

        ArrayList<EmCenter> tableValue = new ArrayList<>();
        EmCenter line;

        try {
            while (resultSet.next()) {
                line = new EmCenter(
                        resultSet.getInt("LOGICALREF"), resultSet.getString("CODE"), 
                        resultSet.getString("DEFINITION_"), resultSet.getString("SPECODE"), 
                        resultSet.getString("CYPHCODE"), resultSet.getString("UNITS"), 
                        resultSet.getInt("ADDINFOREF"), resultSet.getInt("EXTENREF"),
                        resultSet.getShort("CAPIBLOCK_CREATEDBY"), resultSet.getString("CAPIBLOCK_CREADEDDATE"), //date format
                        resultSet.getShort("CAPIBLOCK_CREATEDHOUR"), resultSet.getShort("CAPIBLOCK_CREATEDMIN"), 
                        resultSet.getShort("CAPIBLOCK_CREATEDSEC"), resultSet.getShort("CAPIBLOCK_MODIFIEDBY"), 
/*date format*/         resultSet.getString("CAPIBLOCK_MODIFIEDDATE"), resultSet.getShort("CAPIBLOCK_MODIFIEDHOUR"), 
                        resultSet.getShort("CAPIBLOCK_MODIFIEDMIN"), resultSet.getShort("CAPIBLOCK_MODIFIEDSEC"), 
                        resultSet.getShort("ACTIVE"), resultSet.getShort("SITEID"), 
                        resultSet.getShort("RECSTATUS"), resultSet.getInt("ORGLOGICALREF"), 
                        resultSet.getInt("WFSTATUS"));
                tableValue.add(line);
            }
            return tableValue;
        } catch (SQLException ex) {
            Logger.getLogger(KsCardSender.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
            return null;
        }
    }
    
    public JsonObject getEmCenterJson(ArrayList<EmCenter> list) {
        JsonObject jsonObject = new JsonObject();
        JSONArray logicalRef = new JSONArray();
        JSONArray code = new JSONArray();
        JSONArray definition = new JSONArray();
        JSONArray specode = new JSONArray();
        JSONArray cyphCode = new JSONArray();
        JSONArray units = new JSONArray();
        JSONArray addInfoRef = new JSONArray();
        JSONArray extenRef = new JSONArray();
        JSONArray capiblockCreatedby = new JSONArray();
        JSONArray capiblockCreadeddate = new JSONArray();
        JSONArray capiblockCreatedhour = new JSONArray();
        JSONArray capiblockCreatedmin = new JSONArray();
        JSONArray capiblockCreatedsec = new JSONArray();
        JSONArray capiblockModifiedby = new JSONArray();
        JSONArray capiblockModifieddate = new JSONArray();
        JSONArray capiblockModifiedhour = new JSONArray();
        JSONArray capiblockModifiedmin = new JSONArray();
        JSONArray capiblockModifiedsec = new JSONArray();
        JSONArray active = new JSONArray();
        JSONArray siteId = new JSONArray();
        JSONArray recStatus = new JSONArray();
        JSONArray orgLogicRef = new JSONArray();
        JSONArray wfStatus = new JSONArray();

        for (int a = 0; a < list.size(); a++) {

            //here we setting the specode with local logicalRef (example: if depo then we adding "D" infront of LogREf)
            if (list.get(a).getSpecode().equals("")) {
                specode.add(a, String.valueOf(list.get(a).getLogicalRef()));
            }else{
                specode.add(list.get(a).getSpecode());
            }//LOGREF//******************************Specode
            logicalRef.add(list.get(a).getLogicalRef());
            definition.add(list.get(a).getDefinition());
            code.add(list.get(a).getCode());
            cyphCode.add(list.get(a).getCyphCode());
            units.add(list.get(a).getUnits());
            addInfoRef.add(list.get(a).getAddInfoRef());
            extenRef.add(list.get(a).getExtenRef());
            capiblockCreatedby.add(list.get(a).getCapiblockCreatedby());
            capiblockCreadeddate.add(list.get(a).getCapiblockCreadeddate());
            capiblockCreatedhour.add(list.get(a).getCapiblockCreatedhour());
            capiblockCreatedmin.add(list.get(a).getCapiblockCreatedmin());
            capiblockCreatedsec.add(list.get(a).getCapiblockCreatedsec());
            capiblockModifiedby.add(list.get(a).getCapiblockModifiedby());
            capiblockModifieddate.add(list.get(a).getCapiblockModifieddate());
            capiblockModifiedhour.add(list.get(a).getCapiblockModifiedhour());
            capiblockModifiedmin.add(list.get(a).getCapiblockModifiedmin());
            capiblockModifiedsec.add(list.get(a).getCapiblockModifiedsec());
            active.add(list.get(a).getActive());
            siteId.add(list.get(a).getSiteId());
            recStatus.add(list.get(a).getRecStatus());
            orgLogicRef.add(list.get(a).getOrgLogicRef());
            wfStatus.add(list.get(a).getWfStatus());
        }
        jsonObject.setObject("LOGICALREF", logicalRef);
        jsonObject.setObject("CODE", code);
        jsonObject.setObject("DEFINITION_", definition);
        jsonObject.setObject("SPECODE", specode);
        jsonObject.setObject("CYPHCODE", cyphCode);
        jsonObject.setObject("UNITS", units);
        jsonObject.setObject("ADDINFOREF", addInfoRef);
        jsonObject.setObject("EXTENREF", extenRef);
        jsonObject.setObject("CAPIBLOCK_CREATEDBY", capiblockCreatedby);
        jsonObject.setObject("CAPIBLOCK_CREADEDDATE", capiblockCreadeddate);
        jsonObject.setObject("CAPIBLOCK_CREATEDHOUR", capiblockCreatedhour);
        jsonObject.setObject("CAPIBLOCK_CREATEDMIN", capiblockCreatedmin);
        jsonObject.setObject("CAPIBLOCK_CREATEDSEC", capiblockCreatedsec);
        jsonObject.setObject("CAPIBLOCK_MODIFIEDBY", capiblockModifiedby);
        jsonObject.setObject("CAPIBLOCK_MODIFIEDDATE", capiblockModifieddate);
        jsonObject.setObject("CAPIBLOCK_MODIFIEDHOUR", capiblockModifiedhour);
        jsonObject.setObject("CAPIBLOCK_MODIFIEDMIN", capiblockModifiedmin);
        jsonObject.setObject("CAPIBLOCK_MODIFIEDSEC", capiblockModifiedsec);
        jsonObject.setObject("ACTIVE", active);
        jsonObject.setObject("SITEID", siteId);
        jsonObject.setObject("RECSTATUS", recStatus);
        jsonObject.setObject("ORGLOGICALREF", orgLogicRef);
        jsonObject.setObject("WFSTATUS", wfStatus);
        return jsonObject;
    }
    
//*******************************CRDACREF***************************************
    public ArrayList<CrdacRef> getCrdacRefArrayList(ResultSet resultSet) {

        ArrayList<CrdacRef> tableValue = new ArrayList<>();
        CrdacRef line;

        try {
            while (resultSet.next()) {
                line = new CrdacRef(
                        resultSet.getInt("LOGICALREF"), resultSet.getShort("TRCODE"),
                        resultSet.getInt("CARDREF"), resultSet.getShort("TYP"),
                        resultSet.getInt("ACCOUNTREF"), resultSet.getInt("CENTERREF"),
                        resultSet.getShort("SITEID"), resultSet.getShort("RECSTATUS"),
                        resultSet.getInt("ORGLOGICREF"), resultSet.getInt("PROJECTREF"));
                tableValue.add(line);
            }
            return tableValue;    
        } catch (SQLException ex) {
            Logger.getLogger(KsCardSender.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
            return null;
        }
    }
    
    public JsonObject getCrdacRefJson(ArrayList<CrdacRef> list) {
        JsonObject jsonObject = new JsonObject();
        JSONArray logicalRef = new JSONArray();
        JSONArray trCode = new JSONArray();
        JSONArray cardRef = new JSONArray();
        JSONArray typ = new JSONArray();
        JSONArray accountRef = new JSONArray();
        JSONArray centerRef = new JSONArray();
        JSONArray siteId = new JSONArray();
        JSONArray recStatus = new JSONArray();
        JSONArray orgLogicRef = new JSONArray();
        JSONArray projectRef = new JSONArray();

        for (int a = 0; a < list.size(); a++) {
            
            logicalRef.add(list.get(a).getLogicalRef());
            trCode.add(list.get(a).getTrCode());
            cardRef.add(list.get(a).getCardRef());
            typ.add(list.get(a).getTyp());
            accountRef.add(list.get(a).getAccountRef());
            centerRef.add(list.get(a).getCenterRef());
            siteId.add(list.get(a).getSiteId());
            recStatus.add(list.get(a).getRecStatus());
            orgLogicRef.add(list.get(a).getOrgLogicRef());
            projectRef.add(list.get(a).getProjectRef());
        }
        jsonObject.setObject("LOGICALREF", logicalRef);
        jsonObject.setObject("TRCODE", trCode);
        jsonObject.setObject("CARDREF", cardRef);
        jsonObject.setObject("TYP", typ);
        jsonObject.setObject("ACCOUNTREF", accountRef);
        jsonObject.setObject("CENTERREF", centerRef);
        jsonObject.setObject("SITEID", siteId);
        jsonObject.setObject("RECSTATUS", recStatus);
        jsonObject.setObject("ORGLOGICREF", orgLogicRef);
        jsonObject.setObject("PROJECTREF", projectRef);
        return jsonObject;
    }
}
