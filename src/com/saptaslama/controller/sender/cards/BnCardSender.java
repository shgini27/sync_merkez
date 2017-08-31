/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saptaslama.controller.sender.cards;

import com.saptaslama.controller.CompanyConstants;
import com.saptaslama.controller.DbConnection;
import com.saptaslama.controller.sender.Sender;
import com.saptaslama.model.BankAcc;
import com.saptaslama.model.BnCard;
import com.saptaslama.model.JsonObject;
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
public class BnCardSender extends Sender{
    
    public void init() {
        Connection con;
        ResultSet resultSetBnCard;
        ResultSet resultSetBankAcc;
        
        String tabelNameBnCard = CompanyConstants.COMPANY + "BNCARD";
        String tabelNameBankAcc = CompanyConstants.COMPANY + "BANKACC";
        
        String fileNameBnCard = "BnCard.json";
        String fileNameBankAcc = "BankAcc.json";
        
        DbConnection db = new DbConnection();
        
        /*
        Date d = new Date();
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        System.err.println("Today: " + date.format(d.getTime()));
        
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -4);
        System.err.println("1 day BEFORE: " + date.format(cal.getTime()));
        */
        
        /*
        SELECT * FROM LG_001_UNITSETF 
        WHERE CAPIBLOCK_MODIFIEDDATE 
        BETWEEN '2017-01-25' AND '2017-02-18' 
        OR CAPIBLOCK_CREADEDDATE BETWEEN '2017-01-25' AND '2017-02-18' 
        OR CAPIBLOCK_MODIFIEDDATE IS NULL
        */
        /*
        SELECT * FROM LG_001_UNITSETL WHERE UNITSETREF 
        IN(SELECT LOGICALREF FROM LG_001_UNITSETF 
        WHERE CAPIBLOCK_MODIFIEDDATE 
        BETWEEN '2017-01-25' AND '2017-02-18' 
        OR CAPIBLOCK_CREADEDDATE BETWEEN '2017-01-25' AND '2017-02-18'
        OR CAPIBLOCK_MODIFIEDDATE IS NULL)
        */

        con = db.getConnection();
        resultSetBnCard = db.querySelectAll(con, tabelNameBnCard);
        resultSetBankAcc = db.querySelectAll(con, tabelNameBankAcc);
        
        sendJsonFile(getBnCardJson(getBnCardArrayList(resultSetBnCard)), 
                CompanyConstants.FILE_PATH_SEND + fileNameBnCard);
        sendJsonFile(getBankAccJson(getBankAccArrayList(resultSetBankAcc)), 
                CompanyConstants.FILE_PATH_SEND + fileNameBankAcc);
        
        db.closeConnection(con);
        db.closeResultSet(resultSetBnCard);
        db.closeResultSet(resultSetBankAcc);
    }
    
//**********************************BNCARD**************************************
    public ArrayList<BnCard> getBnCardArrayList(ResultSet resultSet) {

        ArrayList<BnCard> tableValue = new ArrayList<>();
        BnCard unit;

        try {
            while (resultSet.next()) {
                unit = new BnCard(
                        resultSet.getInt("LOGICALREF"), resultSet.getShort("ACTIVE"), 
                        resultSet.getString("CODE"), resultSet.getString("DEFINITION_"), 
                        resultSet.getString("BRANCH"), resultSet.getString("SPECODE"), 
                        resultSet.getString("CYPHCODE"), resultSet.getString("BRANCHNO"), 
                        resultSet.getString("ADDR1"), resultSet.getString("ADDR2"), 
                        resultSet.getString("CITY"), resultSet.getString("COUNTRY"), 
                        resultSet.getString("POSTCODE"), resultSet.getString("TELNRS1"), 
                        resultSet.getString("TELNRS2"), resultSet.getString("FAXNR"), 
                        resultSet.getString("INCHARGE"), resultSet.getString("EMAILADDR"),
                        resultSet.getString("WEBADDR"), resultSet.getShort("TEXTINC"),
                        resultSet.getShort("CAPIBLOCK_CREATEDBY"), resultSet.getString("CAPIBLOCK_CREADEDDATE"), 
                        resultSet.getShort("CAPIBLOCK_CREATEDHOUR"), resultSet.getShort("CAPIBLOCK_CREATEDMIN"), 
                        resultSet.getShort("CAPIBLOCK_CREATEDSEC"), resultSet.getShort("CAPIBLOCK_MODIFIEDBY"), 
                        resultSet.getString("CAPIBLOCK_MODIFIEDDATE"), resultSet.getShort("CAPIBLOCK_MODIFIEDHOUR"), 
                        resultSet.getShort("CAPIBLOCK_MODIFIEDMIN"), resultSet.getShort("CAPIBLOCK_MODIFIEDSEC"), 
                        resultSet.getShort("SITEID"), resultSet.getShort("RECSTATUS"), 
                        resultSet.getInt("ORGLOGICREF"), resultSet.getInt("WFSTATUS"), 
                        resultSet.getString("CNTRYCODE"), resultSet.getString("TOWN"), 
                        resultSet.getString("DISTRICT"));
                tableValue.add(unit);
            }
            return tableValue;
        } catch (SQLException ex) {
            Logger.getLogger(BnCardSender.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
            return null;
        }
    }
    
    public JsonObject getBnCardJson(ArrayList<BnCard> list) {
        JsonObject jsonObject = new JsonObject();
        JSONArray logicalRef = new JSONArray();
        JSONArray active = new JSONArray();
        JSONArray code = new JSONArray();
        JSONArray definition = new JSONArray();
        JSONArray branch = new JSONArray();
        JSONArray specode = new JSONArray();
        JSONArray cyphCode = new JSONArray();
        JSONArray branchNo = new JSONArray();
        JSONArray addr1 = new JSONArray();
        JSONArray addr2 = new JSONArray();
        JSONArray city = new JSONArray();
        JSONArray country = new JSONArray();
        JSONArray postCode = new JSONArray();
        JSONArray telNrs1 = new JSONArray();
        JSONArray telNrs2 = new JSONArray();
        JSONArray faxNr = new JSONArray();
        JSONArray incharge = new JSONArray();
        JSONArray emailAddr = new JSONArray();
        JSONArray webAddr = new JSONArray();
        JSONArray textInc = new JSONArray();
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
        JSONArray siteId = new JSONArray();
        JSONArray recStatus = new JSONArray();
        JSONArray orgLogicRef = new JSONArray();
        JSONArray wfStatus = new JSONArray();
        JSONArray cntryCode = new JSONArray();
        JSONArray town = new JSONArray();
        JSONArray district = new JSONArray();

        for (int a = 0; a < list.size(); a++) {

            //here we setting the specode with local logicalRef (example: if depo 
            //then we adding "D" infront of LogREf)
            specode.add(String.valueOf(list.get(a).getLogicalRef()));
            
            logicalRef.add(list.get(a).getLogicalRef());
            active.add(list.get(a).getActive());
            definition.add(list.get(a).getDefinition());
            branch.add(list.get(a).getBranch());
            code.add(list.get(a).getCode());
            cyphCode.add(list.get(a).getCyphCode());
            branchNo.add(list.get(a).getBranchNo());
            addr1.add(list.get(a).getAddr1());
            addr2.add(list.get(a).getAddr2());
            city.add(list.get(a).getCity());
            country.add(list.get(a).getCountry());
            postCode.add(list.get(a).getPostCode());
            telNrs1.add(list.get(a).getTelNrs1());
            telNrs2.add(list.get(a).getTelNrs2());
            faxNr.add(list.get(a).getFaxNr());
            incharge.add(list.get(a).getIncharge());
            emailAddr.add(list.get(a).getEmailAddr());
            webAddr.add(list.get(a).getWebAddr());
            textInc.add(list.get(a).getTextInc());
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
            siteId.add(list.get(a).getSiteId());
            recStatus.add(list.get(a).getRecStatus());
            orgLogicRef.add(list.get(a).getOrgLogicRef());
            wfStatus.add(list.get(a).getWfStatus());
            cntryCode.add(list.get(a).getCntryCode());
            town.add(list.get(a).getTown());
            district.add(list.get(a).getDistrict());
        }
        jsonObject.setObject("LOGICALREF", logicalRef);
        jsonObject.setObject("ACTIVE", active);
        jsonObject.setObject("CODE", code);
        jsonObject.setObject("DEFINITION_", definition);
        jsonObject.setObject("BRANCH", branch);
        jsonObject.setObject("SPECODE", specode);
        jsonObject.setObject("CYPHCODE", cyphCode);
        jsonObject.setObject("BRANCHNO", branchNo);
        jsonObject.setObject("ADDR1", addr1);
        jsonObject.setObject("ADDR2", addr2);
        jsonObject.setObject("CITY", city);
        jsonObject.setObject("COUNTRY", country);
        jsonObject.setObject("POSTCODE", postCode);
        jsonObject.setObject("TELNRS1", telNrs1);
        jsonObject.setObject("TELNRS2", telNrs2);
        jsonObject.setObject("FAXNR", faxNr);
        jsonObject.setObject("INCHARGE", incharge);
        jsonObject.setObject("EMAILADDR", emailAddr);
        jsonObject.setObject("WEBADDR", webAddr);
        jsonObject.setObject("TEXTINC", textInc);
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
        jsonObject.setObject("SITEID", siteId);
        jsonObject.setObject("RECSTATUS", recStatus);
        jsonObject.setObject("ORGLOGICREF", orgLogicRef);
        jsonObject.setObject("WFSTATUS", wfStatus);
        jsonObject.setObject("CNTRYCODE", cntryCode);
        jsonObject.setObject("TOWN", town);
        jsonObject.setObject("DISTRICT", district);
        return jsonObject;
    }
    
//**********************************BANKACC*************************************
    public ArrayList<BankAcc> getBankAccArrayList(ResultSet resultSet) {

        ArrayList<BankAcc> tableValue = new ArrayList<>();
        BankAcc unit;

        try {
            while (resultSet.next()) {
                unit = new BankAcc(
                        resultSet.getInt("LOGICALREF"), resultSet.getShort("CARDTYPE"), 
                        resultSet.getString("CODE"), resultSet.getString("DEFINITION_"),
                        resultSet.getString("SPECODE"), resultSet.getString("CYPHCODE"), 
                        resultSet.getInt("BANKREF"), resultSet.getFloat("CHECKMARGIN"), 
                        resultSet.getFloat("NOTEMARGIN"), resultSet.getFloat("CHECKLIMIT"), 
                        resultSet.getFloat("NOTELIMIT"), resultSet.getFloat("CUSTINTEREST"), 
                        resultSet.getFloat("SKINTEREST"), resultSet.getFloat("CKINTEREST"), 
                        resultSet.getFloat("STOPAJPER"), resultSet.getFloat("FONPER"), 
                        resultSet.getShort("CURRENCY"), resultSet.getInt("EXTENREF"), 
                        resultSet.getShort("CAPIBLOCK_CREATEDBY"), resultSet.getString("CAPIBLOCK_CREADEDDATE"), 
                        resultSet.getShort("CAPIBLOCK_CREATEDHOUR"), resultSet.getShort("CAPIBLOCK_CREATEDMIN"), 
                        resultSet.getShort("CAPIBLOCK_CREATEDSEC"), resultSet.getShort("CAPIBLOCK_MODIFIEDBY"), 
                        resultSet.getString("CAPIBLOCK_MODIFIEDDATE"), resultSet.getShort("CAPIBLOCK_MODIFIEDHOUR"), 
                        resultSet.getShort("CAPIBLOCK_MODIFIEDMIN"), resultSet.getShort("CAPIBLOCK_MODIFIEDSEC"), 
                        resultSet.getShort("ACTIVE"), resultSet.getString("ACCOUNTNO"), 
                        resultSet.getShort("TEXTINC"), resultSet.getShort("SITEID"), 
                        resultSet.getShort("RECSTATUS"), resultSet.getInt("ORGLOGICREF"), 
                        resultSet.getInt("WFSTATUS"), resultSet.getShort("KKUSAGE"), 
                        resultSet.getFloat("COLLATRLLIMIT"), resultSet.getShort("CURRATETYPE"), 
                        resultSet.getFloat("WTHCLTRLINTEREST"), resultSet.getFloat("WTHCLTRLLIMIT"));
                tableValue.add(unit);
            }
            return tableValue;
        } catch (SQLException ex) {
            Logger.getLogger(BnCardSender.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
            return null;
        }
    }
    
    public JsonObject getBankAccJson(ArrayList<BankAcc> list) {
        JsonObject jsonObject = new JsonObject();
        JSONArray logicalRef = new JSONArray();
        JSONArray cardType = new JSONArray();
        JSONArray code = new JSONArray();
        JSONArray definition = new JSONArray();
        JSONArray specode = new JSONArray();
        JSONArray cyphCode = new JSONArray();
        JSONArray bankRef = new JSONArray();
        JSONArray checkMargin = new JSONArray();
        JSONArray noteMargin = new JSONArray();
        JSONArray checkLimit = new JSONArray();
        JSONArray noteLimit = new JSONArray();
        JSONArray custInterest = new JSONArray();
        JSONArray skInterest = new JSONArray();
        JSONArray ckInterest = new JSONArray();
        JSONArray stopajper = new JSONArray();
        JSONArray fonper = new JSONArray();
        JSONArray currency = new JSONArray();
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
        JSONArray accountNo = new JSONArray();
        JSONArray textInc = new JSONArray();
        JSONArray siteId = new JSONArray();
        JSONArray recStatus = new JSONArray();
        JSONArray orgLogicRef = new JSONArray();
        JSONArray wfStatus = new JSONArray();
        JSONArray kkUsage = new JSONArray();
        JSONArray collatRlLimit = new JSONArray();
        JSONArray curRateType = new JSONArray();
        JSONArray wthcltrlInterest = new JSONArray();
        JSONArray wthcltrlLimit = new JSONArray();

        for (int a = 0; a < list.size(); a++) {

            //here we setting the specode with local logicalRef (example: if depo then we adding "D" infront of LogREf)
            if (list.get(a).getSpecode().equals("")) {
                specode.add(a, String.valueOf(list.get(a).getLogicalRef()));
            }else{
                specode.add(list.get(a).getSpecode());
            }//LOGREF//******************************specode
            logicalRef.add(list.get(a).getLogicalRef());
            cardType.add(list.get(a).getCardType());
            definition.add(list.get(a).getDefinition());
            code.add(list.get(a).getCode());
            cyphCode.add(list.get(a).getCyphCode());
            bankRef.add(list.get(a).getBankRef());
            checkMargin.add(list.get(a).getCheckMargin());
            noteMargin.add(list.get(a).getNoteMargin());
            checkLimit.add(list.get(a).getCheckLimit());
            noteLimit.add(list.get(a).getNoteLimit());
            custInterest.add(list.get(a).getCustInterest());
            skInterest.add(list.get(a).getSkInterest());
            ckInterest.add(list.get(a).getCkInterest());
            stopajper.add(list.get(a).getStopajper());
            fonper.add(list.get(a).getFonper());
            currency.add(list.get(a).getCurrency());
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
            accountNo.add(list.get(a).getAccountNo());
            textInc.add(list.get(a).getTextInc());
            siteId.add(list.get(a).getSiteId());
            recStatus.add(list.get(a).getRecStatus());
            orgLogicRef.add(list.get(a).getOrgLogicRef());
            wfStatus.add(list.get(a).getWfStatus());
            kkUsage.add(list.get(a).getKkUsage());
            collatRlLimit.add(list.get(a).getCollatRlLimit());
            curRateType.add(list.get(a).getCurRateType());
            wthcltrlInterest.add(list.get(a).getWthcltrlInterest());
            wthcltrlLimit.add(list.get(a).getWthcltrlLimit());
        }
        jsonObject.setObject("LOGICALREF", logicalRef);
        jsonObject.setObject("CARDTYPE", cardType);
        jsonObject.setObject("CODE", code);
        jsonObject.setObject("DEFINITION_", definition);
        jsonObject.setObject("SPECODE", specode);
        jsonObject.setObject("CYPHCODE", cyphCode);
        jsonObject.setObject("BANKREF", bankRef);
        jsonObject.setObject("CHECKMARGIN", checkMargin);
        jsonObject.setObject("NOTEMARGIN", noteMargin);
        jsonObject.setObject("CHECKLIMIT", checkLimit);
        jsonObject.setObject("NOTELIMIT", noteLimit);
        jsonObject.setObject("CUSTINTEREST", custInterest);
        jsonObject.setObject("SKINTEREST", skInterest);
        jsonObject.setObject("CKINTEREST", ckInterest);
        jsonObject.setObject("STOPAJPER", stopajper);
        jsonObject.setObject("FONPER", fonper);
        jsonObject.setObject("CURRENCY", currency);
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
        jsonObject.setObject("ACCOUNTNO", accountNo);
        jsonObject.setObject("TEXTINC", textInc);
        jsonObject.setObject("SITEID", siteId);
        jsonObject.setObject("RECSTATUS", recStatus);
        jsonObject.setObject("ORGLOGICREF", orgLogicRef);
        jsonObject.setObject("WFSTATUS", wfStatus);
        jsonObject.setObject("KKUSAGE", kkUsage);
        jsonObject.setObject("COLLATRLLIMIT", collatRlLimit);
        jsonObject.setObject("CURRATETYPE", curRateType);
        jsonObject.setObject("WTHCLTRLINTEREST", wthcltrlInterest);
        jsonObject.setObject("WTHCLTRLLIMIT", wthcltrlLimit);
        return jsonObject;
    }
}
