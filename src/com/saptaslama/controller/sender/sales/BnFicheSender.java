/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saptaslama.controller.sender.sales;

import com.saptaslama.controller.CompanyConstants;
import com.saptaslama.controller.DbConnection;
import com.saptaslama.controller.sender.Sender;
import com.saptaslama.model.BnFiche;
import com.saptaslama.model.BnfLine;
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
public class BnFicheSender extends Sender{
    
    public void init() {
        Connection con;
        ResultSet resultSetBnFiche;
        ResultSet resultSetBnfLine;
        
        String tabelNameBnFiche = CompanyConstants.COMPANY + CompanyConstants
                .SUBCOMPANY + "BNFICHE";
        String tabelNameBnfLine = CompanyConstants.COMPANY + CompanyConstants
                .SUBCOMPANY + "BNFLINE";
        
        String fileNameBnFiche = "BnFiche.json";
        String fileNameBnfLine = "BnfLine.json";
        
        String condition = "SPECODE LIKE ''";
        
        DbConnection db = new DbConnection();

        con = db.getConnection();
        resultSetBnFiche = db.querySelectStatement(con, tabelNameBnFiche, condition);
        resultSetBnfLine = db.querySelectStatement(con, tabelNameBnfLine, condition);
        
        sendJsonFile(getBnFicheJson(getBnFicheArrayList(resultSetBnFiche)), 
                CompanyConstants.FILE_PATH_SEND + fileNameBnFiche);
        sendJsonFile(getBnfLineJson(getBnfLineArrayList(resultSetBnfLine)), 
                CompanyConstants.FILE_PATH_SEND + fileNameBnfLine);
        
        db.closeConnection(con);
        db.closeResultSet(resultSetBnFiche);
        db.closeResultSet(resultSetBnfLine);
    }
    
//*****************************BNFICHE******************************************
    public ArrayList<BnFiche> getBnFicheArrayList(ResultSet resultSet) {

        ArrayList<BnFiche> tableValue = new ArrayList<>();
        BnFiche unit;

        try {
            while (resultSet.next()) {
                unit = new BnFiche(
                        resultSet.getInt("LOGICALREF"), resultSet.getString("DATE_"), 
                        resultSet.getString("FICHENO"), resultSet.getString("SPECODE"), 
                        resultSet.getString("CYPHCODE"), resultSet.getShort("BRANCH"), 
                        resultSet.getShort("DEPARMENT"), resultSet.getShort("TRCODE"), 
                        resultSet.getShort("MODULENR"), resultSet.getInt("SOURCEFREF"), 
                        resultSet.getShort("ACCOUNTED"), resultSet.getShort("CANCELLED"), 
                        resultSet.getShort("SIGN"), resultSet.getFloat("DEBITTOT"), 
                        resultSet.getFloat("CREDITTOT"), resultSet.getString("GENEXP1"), 
                        resultSet.getString("GENEXP2"), resultSet.getString("GENEXP3"), 
                        resultSet.getString("GENEXP4"), resultSet.getShort("PRINTCNT"), 
                        resultSet.getShort("CAPIBLOCK_CREATEDBY"), resultSet.getString("CAPIBLOCK_CREADEDDATE"), 
                        resultSet.getShort("CAPIBLOCK_CREATEDHOUR"), resultSet.getShort("CAPIBLOCK_CREATEDMIN"), 
                        resultSet.getShort("CAPIBLOCK_CREATEDSEC"), resultSet.getShort("CAPIBLOCK_MODIFIEDBY"), 
                        resultSet.getString("CAPIBLOCK_MODIFIEDDATE"), resultSet.getShort("CAPIBLOCK_MODIFIEDHOUR"), 
                        resultSet.getShort("CAPIBLOCK_MODIFIEDMIN"), resultSet.getShort("CAPIBLOCK_MODIFIEDSEC"), 
                        resultSet.getShort("CANCELLEDACC"), resultSet.getInt("ACCFICHEREF"), 
                        resultSet.getShort("GENEXCTYP"), resultSet.getShort("LINEEXCTYP"), 
                        resultSet.getShort("SITEID"), resultSet.getShort("RECSTATUS"), 
                        resultSet.getInt("ORGLOGICREF"), resultSet.getFloat("REPDEBIT"), 
                        resultSet.getFloat("REPCREDIT"), resultSet.getShort("TEXTINC"), 
                        resultSet.getInt("WFSTATUS"), resultSet.getShort("CRCARDWZD"), 
                        resultSet.getInt("BNACCOUNTREF"), resultSet.getString("TRANGRPNO"), 
                        resultSet.getInt("PROJECTREF"), resultSet.getInt("COLLATROLLREF"), 
                        resultSet.getInt("COLLATTRNREF"), resultSet.getInt("BNCRREF"));
                tableValue.add(unit);
            }
            return tableValue;
        } catch (SQLException ex) {
            Logger.getLogger(BnFicheSender.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
            return null;
        }
    }
    
    public JsonObject getBnFicheJson(ArrayList<BnFiche> list) {
        JsonObject jsonObject = new JsonObject();
        JSONArray logicalRef = new JSONArray();
        JSONArray date = new JSONArray();
        JSONArray ficheNo = new JSONArray();
        JSONArray specode = new JSONArray();
        JSONArray cyphCode = new JSONArray();
        JSONArray branch = new JSONArray();
        JSONArray department = new JSONArray();
        JSONArray trCode = new JSONArray();
        JSONArray moduleNr = new JSONArray();
        JSONArray sourceRef = new JSONArray();
        JSONArray accounted = new JSONArray();
        JSONArray cancelled = new JSONArray();
        JSONArray sign = new JSONArray();
        JSONArray debitTot = new JSONArray();
        JSONArray creditTot = new JSONArray();
        JSONArray genexp1 = new JSONArray();
        JSONArray genexp2 = new JSONArray();
        JSONArray genexp3 = new JSONArray();
        JSONArray genexp4 = new JSONArray();
        JSONArray printCnt = new JSONArray();
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
        JSONArray cancelledAcc = new JSONArray();
        JSONArray accFicheRef = new JSONArray();
        JSONArray genexcTyp = new JSONArray();
        JSONArray lineExcTyp = new JSONArray();
        JSONArray siteId = new JSONArray();
        JSONArray recStatus = new JSONArray();
        JSONArray orgLogicRef = new JSONArray();
        JSONArray repDebit = new JSONArray();
        JSONArray repCredit = new JSONArray();
        JSONArray textInc = new JSONArray();
        JSONArray wfStatus = new JSONArray();
        JSONArray crCardWzd = new JSONArray();
        JSONArray bnAccountRef = new JSONArray();
        JSONArray tranGrpNo = new JSONArray();
        JSONArray projectRef = new JSONArray();
        JSONArray collatRollRef = new JSONArray();
        JSONArray collatTrnRef = new JSONArray();
        JSONArray bnCrRef = new JSONArray();

        for (int a = 0; a < list.size(); a++) {

            //here we setting the specode with local logicalRef (example: if depo 
            //then we adding "D" infront of LogREf)
            specode.add(String.valueOf(list.get(a).getLogicalRef()));
            
            logicalRef.add(list.get(a).getLogicalRef());
            date.add(list.get(a).getDate());
            ficheNo.add(list.get(a).getFicheNo());
            cyphCode.add(list.get(a).getCyphCode());
            branch.add(list.get(a).getBranch());
            department.add(list.get(a).getDepartment());
            trCode.add(list.get(a).getTrCode());
            moduleNr.add(list.get(a).getModuleNr());
            sourceRef.add(list.get(a).getSourceRef());
            accounted.add(list.get(a).getAccounted());
            cancelled.add(list.get(a).getCancelled());
            sign.add(list.get(a).getSign());
            debitTot.add(list.get(a).getDebitTot());
            creditTot.add(list.get(a).getCreditTot());
            genexp1.add(list.get(a).getGenexp1());
            genexp2.add(list.get(a).getGenexp2());
            genexp3.add(list.get(a).getGenexp3());
            genexp4.add(list.get(a).getGenexp4());
            printCnt.add(list.get(a).getPrintCnt());
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
            cancelledAcc.add(list.get(a).getCancelledAcc());
            accFicheRef.add(list.get(a).getAccFicheRef());
            genexcTyp.add(list.get(a).getGenexcTyp());
            lineExcTyp.add(list.get(a).getLineExcTyp());
            siteId.add(list.get(a).getSiteId());
            recStatus.add(list.get(a).getRecStatus());
            orgLogicRef.add(list.get(a).getOrgLogicRef());
            repDebit.add(list.get(a).getRepDebit());
            repCredit.add(list.get(a).getRepCredit());
            textInc.add(list.get(a).getTextInc());
            wfStatus.add(list.get(a).getWfStatus());
            crCardWzd.add(list.get(a).getCrCardWzd());
            bnAccountRef.add(list.get(a).getBnAccountRef());
            tranGrpNo.add(list.get(a).getTranGrpNo());
            projectRef.add(list.get(a).getProjectRef());
            collatRollRef.add(list.get(a).getCollatRollRef());
            collatTrnRef.add(list.get(a).getCollatTrnRef());
            bnCrRef.add(list.get(a).getBnCrRef());            
        }
        jsonObject.setObject("LOGICALREF", logicalRef);
        jsonObject.setObject("DATE_", date);
        jsonObject.setObject("FICHENO", ficheNo);
        jsonObject.setObject("SPECODE", specode);
        jsonObject.setObject("CYPHCODE", cyphCode);
        jsonObject.setObject("BRANCH", branch);
        jsonObject.setObject("DEPARMENT", department);
        jsonObject.setObject("TRCODE", trCode);
        jsonObject.setObject("MODULENR", moduleNr);
        jsonObject.setObject("SOURCEFREF", sourceRef);
        jsonObject.setObject("ACCOUNTED", accounted);
        jsonObject.setObject("CANCELLED", cancelled);
        jsonObject.setObject("SIGN", sign);
        jsonObject.setObject("DEBITTOT", debitTot);
        jsonObject.setObject("CREDITTOT", creditTot);
        jsonObject.setObject("GENEXP1", genexp1);
        jsonObject.setObject("GENEXP2", genexp2);
        jsonObject.setObject("GENEXP3", genexp3);
        jsonObject.setObject("GENEXP4", genexp4);
        jsonObject.setObject("PRINTCNT", printCnt);
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
        jsonObject.setObject("CANCELLEDACC", cancelledAcc);
        jsonObject.setObject("ACCFICHEREF", accFicheRef);
        jsonObject.setObject("GENEXCTYP", genexcTyp);
        jsonObject.setObject("LINEEXCTYP", lineExcTyp);
        jsonObject.setObject("SITEID", siteId);
        jsonObject.setObject("RECSTATUS", recStatus);
        jsonObject.setObject("ORGLOGICREF", orgLogicRef);
        jsonObject.setObject("REPDEBIT", repDebit);
        jsonObject.setObject("REPCREDIT", repCredit);
        jsonObject.setObject("TEXTINC", textInc);
        jsonObject.setObject("WFSTATUS", wfStatus);
        jsonObject.setObject("CRCARDWZD", crCardWzd);
        jsonObject.setObject("BNACCOUNTREF", bnAccountRef);
        jsonObject.setObject("TRANGRPNO", tranGrpNo);
        jsonObject.setObject("PROJECTREF", projectRef);
        jsonObject.setObject("COLLATROLLREF", collatRollRef);
        jsonObject.setObject("COLLATTRNREF", collatTrnRef);
        jsonObject.setObject("BNCRREF", bnCrRef);
        return jsonObject;
    }

//*****************************BNFLINE******************************************    
    public ArrayList<BnfLine> getBnfLineArrayList(ResultSet resultSet) {

        ArrayList<BnfLine> tableValue = new ArrayList<>();
        BnfLine unit;

        try {
            while (resultSet.next()) {
                unit = new BnfLine(
                        resultSet.getInt("LOGICALREF"), resultSet.getInt("BANKREF"), 
                        resultSet.getInt("BNACCREF"), resultSet.getInt("CLIENTREF"), 
                        resultSet.getInt("ACCOUNTREF"), resultSet.getInt("CENTERREF"), 
                        resultSet.getInt("BNACCOUNTREF"), resultSet.getInt("BNCENTERREF"), 
                        resultSet.getInt("VIRMANREF"), resultSet.getInt("SOURCEFREF"), 
                        resultSet.getShort("TRANSTYPE"), 
                        resultSet.getString("DATE_"), resultSet.getShort("DEPARTMENT"), 
                        resultSet.getShort("BRANCH"), resultSet.getShort("SIGN"), 
                        resultSet.getShort("TRCODE"), resultSet.getShort("MODULENR"), 
                        resultSet.getShort("LINENR"), resultSet.getString("SPECODE"), 
                        resultSet.getString("CYPHCODE"), resultSet.getString("TRANNO"), 
                        resultSet.getString("DOCODE"), resultSet.getString("LINEEXP"), 
                        resultSet.getShort("ACCOUNTED"), resultSet.getShort("TRCURR"), 
                        resultSet.getFloat("AMOUNT"), resultSet.getFloat("TRRATE"), 
                        resultSet.getFloat("TRNET"), resultSet.getFloat("REPORTRATE"), 
                        resultSet.getFloat("REPORTNET"), resultSet.getInt("EXTENREF"), 
                        resultSet.getInt("ACCFICHEREF"), resultSet.getShort("PRINTCNT"), 
                        resultSet.getShort("CAPIBLOCK_CREATEDBY"), resultSet.getString("CAPIBLOCK_CREADEDDATE"), 
                        resultSet.getShort("CAPIBLOCK_CREATEDHOUR"), resultSet.getShort("CAPIBLOCK_CREATEDMIN"), 
                        resultSet.getShort("CAPIBLOCK_CREATEDSEC"), resultSet.getShort("CAPIBLOCK_MODIFIEDBY"), 
                        resultSet.getString("CAPIBLOCK_MODIFIEDDATE"), resultSet.getShort("CAPIBLOCK_MODIFIEDHOUR"), 
                        resultSet.getShort("CAPIBLOCK_MODIFIEDMIN"), resultSet.getShort("CAPIBLOCK_MODIFIEDSEC"), 
                        resultSet.getShort("CANCELLED"), resultSet.getString("CLBNBRANCHNO"), 
                        resultSet.getString("CLBNACCOUNTNO"), resultSet.getString("BNTRACKINGNO"), 
                        resultSet.getShort("TRNSTATE"), resultSet.getString("TRADINGGRP"), 
                        resultSet.getShort("LINEEXCTYP"), resultSet.getShort("DISCFLAG"), 
                        resultSet.getFloat("DISCRATE"), resultSet.getFloat("VATRATE"), 
                        resultSet.getFloat("ARCLOSEAMOUNT"), resultSet.getInt("DISCACCREF"), 
                        resultSet.getInt("DISCCENREF"), resultSet.getInt("VATRACCREF"), 
                        resultSet.getInt("VATRCENREF"), resultSet.getInt("PAYMENTREF"), 
                        resultSet.getShort("BANKPROCTYPE"), resultSet.getShort("BANKPROCCODE"), 
                        resultSet.getString("TRANSDUEDATE"), resultSet.getShort("SITEID"), 
                        resultSet.getShort("RECSTATUS"), resultSet.getInt("ORGLOGICREF"), 
                        resultSet.getShort("OPSTAT"), resultSet.getFloat("INFIDX"), 
                        resultSet.getString("EXIMFICHENO"), resultSet.getShort("BNTRANVATINC"), 
                        resultSet.getFloat("BNTRANVATRAT"), resultSet.getInt("BNTRANVATACCREF"), 
                        resultSet.getInt("BNTRANVATCENREF"), resultSet.getFloat("BNTRANVATTOT"), 
                        resultSet.getString("CHEQINFO"), resultSet.getInt("EXIMINFOREF"), 
                        resultSet.getFloat("EXIMINFOPAR"), resultSet.getInt("EXCREREF"), 
                        resultSet.getShort("CRCARDWZD"), resultSet.getShort("COMSTYPE"), 
                        resultSet.getInt("PROVISIONREF"), resultSet.getShort("TRANGRPLINENO"), 
                        resultSet.getInt("PROJECTREF"), resultSet.getString("TRANGRPDATE"), 
                        resultSet.getString("TRANGRPNO"), resultSet.getString("BANKREFNR"), 
                        resultSet.getString("CUSTOMDOCNR"), resultSet.getInt("DABLNREF"), 
                        resultSet.getInt("TRANSREF"), resultSet.getShort("AFFECTCOLLATRL"), 
                        resultSet.getInt("COLLATROLLREF"), resultSet.getInt("COLLATTRNREF"), 
                        resultSet.getInt("COLLATCARDREF"), resultSet.getShort("GRPFIRMTRANS"), 
                        resultSet.getShort("AFFECTRISK"), resultSet.getShort("BNCRSOURCE"), 
                        resultSet.getInt("BNCRREF"), resultSet.getShort("BNCRLNTYPE"));
                tableValue.add(unit);
            }
            return tableValue;
        } catch (SQLException ex) {
            Logger.getLogger(BnFicheSender.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
            return null;
        }
    }
    
    public JsonObject getBnfLineJson(ArrayList<BnfLine> list) {
        JsonObject jsonObject = new JsonObject();
        JSONArray logicalRef = new JSONArray();
        JSONArray bankRef = new JSONArray();
        JSONArray bnAccRef = new JSONArray();
        JSONArray clientRef = new JSONArray();
        JSONArray accountRef = new JSONArray();
        JSONArray centerRef = new JSONArray();
        JSONArray bnAccountRef = new JSONArray();
        JSONArray bnCenterRef = new JSONArray();
        JSONArray virmanRef = new JSONArray();
        JSONArray sourceRef = new JSONArray();
        JSONArray transType = new JSONArray();
        JSONArray date = new JSONArray();
        JSONArray department = new JSONArray();
        JSONArray branch = new JSONArray();
        JSONArray sign = new JSONArray();
        JSONArray trCode = new JSONArray();
        JSONArray moduleNr = new JSONArray();
        JSONArray lineNr = new JSONArray();
        JSONArray specode = new JSONArray();
        JSONArray cyphCode = new JSONArray();
        JSONArray tranNo = new JSONArray();
        JSONArray doCode = new JSONArray();
        JSONArray lineExp = new JSONArray();
        JSONArray accounted = new JSONArray();
        JSONArray trCurr = new JSONArray();
        JSONArray amount = new JSONArray();
        JSONArray trRate = new JSONArray();
        JSONArray trNet = new JSONArray();
        JSONArray reportRate = new JSONArray();
        JSONArray reportNet = new JSONArray();
        JSONArray extenRef = new JSONArray();
        JSONArray accFicheRef = new JSONArray();
        JSONArray printCnt = new JSONArray();
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
        JSONArray cancelled = new JSONArray();
        JSONArray clBnBranchNo = new JSONArray();
        JSONArray clBnAccountNo = new JSONArray();
        JSONArray bnTrackingNo = new JSONArray();
        JSONArray trnState = new JSONArray();
        JSONArray tradingGrp = new JSONArray();
        JSONArray lineExcTyp = new JSONArray();
        JSONArray discFlag = new JSONArray();
        JSONArray discRate = new JSONArray();
        JSONArray vatRate = new JSONArray();
        JSONArray arCloseAmount = new JSONArray();
        JSONArray discAccRef = new JSONArray();
        JSONArray discCentRef = new JSONArray();
        JSONArray vatrAccRef = new JSONArray();
        JSONArray vatrCentRef = new JSONArray();
        JSONArray paymentRef = new JSONArray();
        JSONArray bankProcType = new JSONArray();
        JSONArray bankProcCode = new JSONArray();
        JSONArray transDueDate = new JSONArray();
        JSONArray siteId = new JSONArray();
        JSONArray recStatus = new JSONArray();
        JSONArray orgLogicRef = new JSONArray();
        JSONArray opstat = new JSONArray();
        JSONArray infdix = new JSONArray();
        JSONArray eximFicheNo = new JSONArray();
        JSONArray bnTranVatInc = new JSONArray();
        JSONArray bnTranVatRat = new JSONArray();
        JSONArray bnTranVatAccRef = new JSONArray();
        JSONArray bnTranVatCenRef = new JSONArray();
        JSONArray bnTranVatTot = new JSONArray();
        JSONArray cheqInfo = new JSONArray();
        JSONArray eximInfoRef = new JSONArray();
        JSONArray eximInfoPar = new JSONArray();
        JSONArray excreRef = new JSONArray();
        JSONArray crCardWzd = new JSONArray();
        JSONArray comsType = new JSONArray();
        JSONArray proVisionRef = new JSONArray();
        JSONArray tranGrpLineNo = new JSONArray();
        JSONArray projectRef = new JSONArray();
        JSONArray tranGrpDate = new JSONArray();
        JSONArray tranGrpNo = new JSONArray();
        JSONArray bankRefNr = new JSONArray();
        JSONArray customDocNr = new JSONArray();
        JSONArray dabLnRef = new JSONArray();
        JSONArray transRef = new JSONArray();
        JSONArray affectCollatRl = new JSONArray();
        JSONArray collatRollRef = new JSONArray();
        JSONArray collatTrnRef = new JSONArray();
        JSONArray collatCardRef = new JSONArray();
        JSONArray grpFirmTrans = new JSONArray();
        JSONArray affectRisk = new JSONArray();
        JSONArray bnCrSource = new JSONArray();
        JSONArray bnCrRef = new JSONArray();
        JSONArray bnCrLnType = new JSONArray();
        
        for (int a = 0; a < list.size(); a++) {

            //here we setting the specode with local logicalRef (example: if depo 
            //then we adding "D" infront of LogREf)
            specode.add(String.valueOf(list.get(a).getLogicalRef()));
            
            logicalRef.add(list.get(a).getLogicalRef());
            bankRef.add(list.get(a).getBankRef());
            bnAccRef.add(list.get(a).getBnAccRef());
            accountRef.add(list.get(a).getAccountRef());
            centerRef.add(list.get(a).getCenterRef());
            bnAccountRef.add(list.get(a).getBnAccountRef());
            bnCenterRef.add(list.get(a).getBnCenterRef());
            virmanRef.add(list.get(a).getVirmanRef());
            sourceRef.add(list.get(a).getSourceRef());
            transType.add(list.get(a).getTransType());
            date.add(list.get(a).getDate());
            department.add(list.get(a).getDepartment());
            branch.add(list.get(a).getBranch());
            sign.add(list.get(a).getSign());
            trCode.add(list.get(a).getTrCode());
            moduleNr.add(list.get(a).getModuleNr());
            lineNr.add(list.get(a).getLineNr());
            cyphCode.add(list.get(a).getCyphCode());
            tranNo.add(list.get(a).getTranNo());
            doCode.add(list.get(a).getDoCode());
            lineExp.add(list.get(a).getLineExp());
            accounted.add(list.get(a).getAccounted());
            trCurr.add(list.get(a).getTrCurr());
            amount.add(list.get(a).getAmount());
            trRate.add(list.get(a).getTrRate());
            trNet.add(list.get(a).getTrNet());
            reportRate.add(list.get(a).getReportRate());
            reportNet.add(list.get(a).getReportNet());
            extenRef.add(list.get(a).getExtenRef());
            accFicheRef.add(list.get(a).getAccFicheRef());
            printCnt.add(list.get(a).getPrintCnt());
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
            cancelled.add(list.get(a).getCancelled());
            clBnBranchNo.add(list.get(a).getClBnBranchNo());
            clBnAccountNo.add(list.get(a).getClBnAccountNo());
            bnTrackingNo.add(list.get(a).getBnTrackingNo());
            trnState.add(list.get(a).getTrnState());
            tradingGrp.add(list.get(a).getTradingGrp());
            lineExcTyp.add(list.get(a).getLineExcTyp());
            discFlag.add(list.get(a).getDiscFlag());
            discRate.add(list.get(a).getDiscRate());
            vatRate.add(list.get(a).getVatRate());
            arCloseAmount.add(list.get(a).getArCloseAmount());
            discAccRef.add(list.get(a).getDiscAccRef());
            discCentRef.add(list.get(a).getDiscCentRef());
            vatrAccRef.add(list.get(a).getVatrAccRef());
            vatrCentRef.add(list.get(a).getVatrCentRef());
            paymentRef.add(list.get(a).getPaymentRef());
            bankProcType.add(list.get(a).getBankProcType());
            bankProcCode.add(list.get(a).getBankProcCode());
            transDueDate.add(list.get(a).getTransDueDate());
            siteId.add(list.get(a).getSiteId());
            recStatus.add(list.get(a).getRecStatus());
            orgLogicRef.add(list.get(a).getOrgLogicRef());
            opstat.add(list.get(a).getOpstat());
            infdix.add(list.get(a).getInfdix());
            eximFicheNo.add(list.get(a).getEximFicheNo());
            bnTranVatInc.add(list.get(a).getBnTranVatInc());
            bnTranVatRat.add(list.get(a).getBnTranVatRat());
            bnTranVatAccRef.add(list.get(a).getBnTranVatAccRef());
            bnTranVatCenRef.add(list.get(a).getBnTranVatCenRef());
            bnTranVatTot.add(list.get(a).getBnTranVatTot());
            cheqInfo.add(list.get(a).getCheqInfo());
            clientRef.add(list.get(a).getClientRef());
            eximInfoRef.add(list.get(a).getEximInfoRef());
            eximInfoPar.add(list.get(a).getEximInfoPar());
            excreRef.add(list.get(a).getExcreRef());
            crCardWzd.add(list.get(a).getCrCardWzd());
            comsType.add(list.get(a).getComsType());
            proVisionRef.add(list.get(a).getProVisionRef());
            tranGrpLineNo.add(list.get(a).getTranGrpLineNo());
            projectRef.add(list.get(a).getProjectRef());
            tranGrpDate.add(list.get(a).getTranGrpDate());
            tranGrpNo.add(list.get(a).getTranGrpNo());
            bankRefNr.add(list.get(a).getBankRefNr());
            customDocNr.add(list.get(a).getCustomDocNr());
            dabLnRef.add(list.get(a).getDabLnRef());
            transRef.add(list.get(a).getTransRef());
            affectCollatRl.add(list.get(a).getAffectCollatRl());
            collatRollRef.add(list.get(a).getCollatRollRef());
            collatTrnRef.add(list.get(a).getCollatTrnRef());
            collatCardRef.add(list.get(a).getCollatCardRef());
            grpFirmTrans.add(list.get(a).getGrpFirmTrans());
            affectRisk.add(list.get(a).getAffectRisk());
            bnCrSource.add(list.get(a).getBnCrSource());
            bnCrRef.add(list.get(a).getBnCrRef());
            bnCrLnType.add(list.get(a).getBnCrLnType());
        }
        jsonObject.setObject("LOGICALREF", logicalRef);
        jsonObject.setObject("BANKREF", bankRef);
        jsonObject.setObject("BNACCREF", bnAccRef);
        jsonObject.setObject("CLIENTREF", clientRef);
        jsonObject.setObject("ACCOUNTREF", accountRef);
        jsonObject.setObject("CENTERREF", centerRef);
        jsonObject.setObject("BNACCOUNTREF", bnAccountRef);
        jsonObject.setObject("BNCENTERREF", bnCenterRef);
        jsonObject.setObject("VIRMANREF", virmanRef);
        jsonObject.setObject("SOURCEFREF", sourceRef);
        jsonObject.setObject("TRANSTYPE", transType);
        jsonObject.setObject("DATE_", date);
        jsonObject.setObject("DEPARTMENT", department);
        jsonObject.setObject("BRANCH", branch);
        jsonObject.setObject("SIGN", sign);
        jsonObject.setObject("TRCODE", trCode);
        jsonObject.setObject("MODULENR", moduleNr);
        jsonObject.setObject("LINENR", lineNr);
        jsonObject.setObject("SPECODE", specode);
        jsonObject.setObject("CYPHCODE", cyphCode);
        jsonObject.setObject("TRANNO", tranNo);
        jsonObject.setObject("DOCODE", doCode);
        jsonObject.setObject("LINEEXP", lineExp);
        jsonObject.setObject("ACCOUNTED", accounted);
        jsonObject.setObject("TRCURR", trCurr);
        jsonObject.setObject("AMOUNT", amount);
        jsonObject.setObject("TRRATE", trRate);
        jsonObject.setObject("TRNET", trNet);
        jsonObject.setObject("REPORTRATE", reportRate);
        jsonObject.setObject("REPORTNET", reportNet);
        jsonObject.setObject("EXTENREF", extenRef);
        jsonObject.setObject("ACCFICHEREF", accFicheRef);
        jsonObject.setObject("PRINTCNT", printCnt);
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
        jsonObject.setObject("CANCELLED", cancelled);
        jsonObject.setObject("CLBNBRANCHNO", clBnBranchNo);
        jsonObject.setObject("CLBNACCOUNTNO", clBnAccountNo);
        jsonObject.setObject("BNTRACKINGNO", bnTrackingNo);
        jsonObject.setObject("TRNSTATE", trnState);
        jsonObject.setObject("TRADINGGRP", tradingGrp);
        jsonObject.setObject("LINEEXCTYP", lineExcTyp);
        jsonObject.setObject("DISCFLAG", discFlag);
        jsonObject.setObject("DISCRATE", discRate);
        jsonObject.setObject("VATRATE", vatRate);
        jsonObject.setObject("ARCLOSEAMOUNT", arCloseAmount);
        jsonObject.setObject("DISCACCREF", discAccRef);
        jsonObject.setObject("DISCCENREF", discCentRef);
        jsonObject.setObject("VATRACCREF", vatrAccRef);
        jsonObject.setObject("VATRCENREF", vatrCentRef);
        jsonObject.setObject("PAYMENTREF", paymentRef);
        jsonObject.setObject("BANKPROCTYPE", bankProcType);
        jsonObject.setObject("BANKPROCCODE", bankProcCode);
        jsonObject.setObject("TRANSDUEDATE", transDueDate);
        jsonObject.setObject("SITEID", siteId);
        jsonObject.setObject("RECSTATUS", recStatus);
        jsonObject.setObject("ORGLOGICREF", orgLogicRef);
        jsonObject.setObject("OPSTAT", opstat);
        jsonObject.setObject("INFIDX", infdix);
        jsonObject.setObject("EXIMFICHENO", eximFicheNo);
        jsonObject.setObject("BNTRANVATINC", bnTranVatInc);
        jsonObject.setObject("BNTRANVATRAT", bnTranVatRat);
        jsonObject.setObject("BNTRANVATACCREF", bnTranVatAccRef);
        jsonObject.setObject("BNTRANVATCENREF", bnTranVatCenRef);
        jsonObject.setObject("BNTRANVATTOT", bnTranVatTot);
        jsonObject.setObject("CHEQINFO", cheqInfo);
        jsonObject.setObject("EXIMINFOREF", eximInfoRef);
        jsonObject.setObject("EXIMINFOPAR", eximInfoPar);
        jsonObject.setObject("EXCREREF", excreRef);
        jsonObject.setObject("CRCARDWZD", crCardWzd);
        jsonObject.setObject("COMSTYPE", comsType);
        jsonObject.setObject("PROVISIONREF", proVisionRef);
        jsonObject.setObject("TRANGRPLINENO", tranGrpLineNo);
        jsonObject.setObject("PROJECTREF", projectRef);
        jsonObject.setObject("TRANGRPDATE", tranGrpDate);
        jsonObject.setObject("TRANGRPNO", tranGrpNo);
        jsonObject.setObject("BANKREFNR", bankRefNr);
        jsonObject.setObject("CUSTOMDOCNR", customDocNr);
        jsonObject.setObject("DABLNREF", dabLnRef);
        jsonObject.setObject("TRANSREF", transRef);
        jsonObject.setObject("AFFECTCOLLATRL", affectCollatRl);
        jsonObject.setObject("COLLATROLLREF", collatRollRef);
        jsonObject.setObject("COLLATTRNREF", collatTrnRef);
        jsonObject.setObject("COLLATCARDREF", collatCardRef);
        jsonObject.setObject("GRPFIRMTRANS", grpFirmTrans);
        jsonObject.setObject("AFFECTRISK", affectRisk);
        jsonObject.setObject("BNCRSOURCE", bnCrSource);
        jsonObject.setObject("BNCRREF", bnCrRef);
        jsonObject.setObject("BNCRLNTYPE", bnCrLnType);
        return jsonObject;
    }
}
