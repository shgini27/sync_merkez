/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saptaslama.controller.receiver;

import com.saptaslama.controller.sender.Sender;
import com.saptaslama.model.ClFiche;
import com.saptaslama.model.ClfLine;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Shagy
 */
public class ClFicheReceiver extends Sender{
    
    public ArrayList<ClFiche> getClFicheArrayList(JSONObject jObject) {

        ArrayList<ClFiche> tableValue = new ArrayList<>();
        ClFiche card;

        JSONArray logicalRef = (JSONArray) jObject.get("LOGICALREF");
        JSONArray ficheNo = (JSONArray) jObject.get("FICHENO");
        JSONArray date = (JSONArray) jObject.get("DATE_");
        JSONArray doCode = (JSONArray) jObject.get("DOCODE");
        JSONArray trCode = (JSONArray) jObject.get("TRCODE");
        JSONArray specode = (JSONArray) jObject.get("SPECCODE");
        JSONArray cyphCode = (JSONArray) jObject.get("CYPHCODE");
        JSONArray branch = (JSONArray) jObject.get("BRANCH");
        JSONArray department = (JSONArray) jObject.get("DEPARTMENT");
        JSONArray genexp1 = (JSONArray) jObject.get("GENEXP1");
        JSONArray genexp2 = (JSONArray) jObject.get("GENEXP2");
        JSONArray genexp3 = (JSONArray) jObject.get("GENEXP3");
        JSONArray genexp4 = (JSONArray) jObject.get("GENEXP4");
        JSONArray debit = (JSONArray) jObject.get("DEBIT");
        JSONArray credit = (JSONArray) jObject.get("CREDIT");
        JSONArray repDebit = (JSONArray) jObject.get("REPDEBIT");
        JSONArray repCredit = (JSONArray) jObject.get("REPCREDIT");
        JSONArray capiblockCreatedby = (JSONArray) jObject.get("CAPIBLOCK_CREATEDBY");
        JSONArray capiblockCreadeddate = (JSONArray) jObject.get("CAPIBLOCK_CREADEDDATE");
        JSONArray capiblockCreatedhour = (JSONArray) jObject.get("CAPIBLOCK_CREATEDHOUR");
        JSONArray capiblockCreatedmin = (JSONArray) jObject.get("CAPIBLOCK_CREATEDMIN");
        JSONArray capiblockCreatedsec = (JSONArray) jObject.get("CAPIBLOCK_CREATEDSEC");
        JSONArray capiblockModifiedby = (JSONArray) jObject.get("CAPIBLOCK_MODIFIEDBY");
        JSONArray capiblockModifieddate = (JSONArray) jObject.get("CAPIBLOCK_MODIFIEDDATE");
        JSONArray capiblockModifiedhour = (JSONArray) jObject.get("CAPIBLOCK_MODIFIEDHOUR");
        JSONArray capiblockModifiedmin = (JSONArray) jObject.get("CAPIBLOCK_MODIFIEDMIN");
        JSONArray capiblockModifiedsec = (JSONArray) jObject.get("CAPIBLOCK_MODIFIEDSEC");
        JSONArray accounted = (JSONArray) jObject.get("ACCOUNTED");
        JSONArray invoRef = (JSONArray) jObject.get("INVOREF");
        JSONArray cashAccRef = (JSONArray) jObject.get("CASHACCREF");
        JSONArray cashCentRef = (JSONArray) jObject.get("CASHCENREF");
        JSONArray printCnt = (JSONArray) jObject.get("PRINTCNT");
        JSONArray cancelled = (JSONArray) jObject.get("CANCELLED");
        JSONArray cancelledAcc = (JSONArray) jObject.get("CANCELLEDACC");
        JSONArray accFicheRef = (JSONArray) jObject.get("ACCFICHEREF");
        JSONArray genexcTyp = (JSONArray) jObject.get("GENEXCTYP");
        JSONArray lineExcTyp = (JSONArray) jObject.get("LINEEXCTYP");
        JSONArray textInc = (JSONArray) jObject.get("TEXTINC");
        JSONArray siteId = (JSONArray) jObject.get("SITEID");
        JSONArray recStatus = (JSONArray) jObject.get("RECSTATUS");
        JSONArray orgLogicRef = (JSONArray) jObject.get("ORGLOGICREF");
        JSONArray wfStatus = (JSONArray) jObject.get("WFSTATUS");
        JSONArray time = (JSONArray) jObject.get("TIME");
        JSONArray clCardRef = (JSONArray) jObject.get("CLCARDREF");
        JSONArray bankAccRef = (JSONArray) jObject.get("BANKACCREF");
        JSONArray bnAccRef = (JSONArray) jObject.get("BNACCREF");
        JSONArray bnCenterRef = (JSONArray) jObject.get("BNCENTERREF");
        JSONArray tradingGrp = (JSONArray) jObject.get("TRADINGGRP");
        JSONArray poscommAccRef = (JSONArray) jObject.get("POSCOMMACCREF");
        JSONArray poscommCenRef = (JSONArray) jObject.get("POSCOMMCENREF");
        JSONArray pointCommAccRef = (JSONArray) jObject.get("POINTCOMMACCREF");
        JSONArray pointCommCenRef = (JSONArray) jObject.get("POINTCOMMCENREF");
        JSONArray projectRef = (JSONArray) jObject.get("PROJECTREF");
        JSONArray status = (JSONArray) jObject.get("STATUS");
        JSONArray wFlowCrdRef = (JSONArray) jObject.get("WFLOWCRDREF");
        JSONArray orgLogoId = (JSONArray) jObject.get("ORGLOGOID");
        JSONArray affectCollatRl = (JSONArray) jObject.get("AFFECTCOLLATRL");
        JSONArray grpFirmTrans = (JSONArray) jObject.get("GRPFIRMTRANS");
        JSONArray affectRisk = (JSONArray) jObject.get("AFFECTRISK");
        JSONArray posterminalNr = (JSONArray) jObject.get("POSTERMINALNR");
        JSONArray posterminalNum = (JSONArray) jObject.get("POSTERMINALNUM");

        for (int i = 0; i < logicalRef.size(); i++) {
            card = new ClFiche(
                    Integer.parseInt(logicalRef.get(i).toString()), ficheNo.get(i).toString(),
                    date.get(i).toString(), doCode.get(i).toString(),
                    trCode.get(i).toString(), specode.get(i).toString(),
                    cyphCode.get(i).toString(), Short.parseShort(branch.get(i).toString()),
                    Short.parseShort(department.get(i).toString()), genexp1.get(i).toString(),
                    genexp2.get(i).toString(), genexp3.get(i).toString(),
                    genexp4.get(i).toString(), Float.parseFloat(debit.get(i).toString()),
                    Float.parseFloat(credit.get(i).toString()), Float.parseFloat(repDebit.get(i).toString()),
                    Float.parseFloat(repCredit.get(i).toString()), Short.parseShort(capiblockCreatedby.get(i).toString()),
                    capiblockCreadeddate.get(i).toString(), Short.parseShort(capiblockCreatedhour.get(i).toString()),
                    Short.parseShort(capiblockCreatedmin.get(i).toString()), Short.parseShort(capiblockCreatedsec.get(i).toString()),
                    Short.parseShort(capiblockModifiedby.get(i).toString()), capiblockModifieddate.get(i).toString(),
                    Short.parseShort(capiblockModifiedhour.get(i).toString()), Short.parseShort(capiblockModifiedmin.get(i).toString()),
                    Short.parseShort(capiblockModifiedsec.get(i).toString()), Short.parseShort(accounted.get(i).toString()),
                    Integer.parseInt(invoRef.get(i).toString()), Integer.parseInt(cashAccRef.get(i).toString()),
                    Integer.parseInt(cashCentRef.get(i).toString()), Short.parseShort(printCnt.get(i).toString()),
                    Short.parseShort(cancelled.get(i).toString()), Short.parseShort(cancelledAcc.get(i).toString()),
                    Integer.parseInt(accFicheRef.get(i).toString()), Short.parseShort(genexcTyp.get(i).toString()),
                    Short.parseShort(lineExcTyp.get(i).toString()), Short.parseShort(textInc.get(i).toString()),
                    Short.parseShort(siteId.get(i).toString()), Short.parseShort(recStatus.get(i).toString()),
                    Integer.parseInt(orgLogicRef.get(i).toString()), Integer.parseInt(wfStatus.get(i).toString()),
                    Integer.parseInt(time.get(i).toString()), Integer.parseInt(clCardRef.get(i).toString()),
                    Integer.parseInt(bankAccRef.get(i).toString()), Integer.parseInt(bnAccRef.get(i).toString()), 
                    Integer.parseInt(bnCenterRef.get(i).toString()), tradingGrp.get(i).toString(),
                    Integer.parseInt(poscommAccRef.get(i).toString()), Integer.parseInt(poscommCenRef.get(i).toString()),
                    Integer.parseInt(pointCommAccRef.get(i).toString()), Integer.parseInt(pointCommCenRef.get(i).toString()),
                    Integer.parseInt(projectRef.get(i).toString()), Short.parseShort(status.get(i).toString()),
                    Integer.parseInt(wFlowCrdRef.get(i).toString()), orgLogoId.get(i).toString(),
                    Short.parseShort(affectCollatRl.get(i).toString()), Short.parseShort(grpFirmTrans.get(i).toString()),
                    Short.parseShort(affectRisk.get(i).toString()), Integer.parseInt(posterminalNr.get(i).toString()),
                    posterminalNum.get(i).toString());

            tableValue.add(card);
        }
        return tableValue;
    }
    
    public ArrayList<ClfLine> getClfLineArrayList(JSONObject jObject) {

        ArrayList<ClfLine> tableValue = new ArrayList<>();
        ClfLine line;

        JSONArray logicalRef = (JSONArray) jObject.get("LOGICALREF");
        JSONArray clientRef = (JSONArray) jObject.get("CLIENTREF");
        JSONArray claccRef = (JSONArray) jObject.get("CLACCREF");
        JSONArray clCenterRef = (JSONArray) jObject.get("CLCENTERREF");
        JSONArray cashCenterRef = (JSONArray) jObject.get("CASHCENTERREF");
        JSONArray cashAccountRef = (JSONArray) jObject.get("CASHACCOUNTREF");
        JSONArray virManRef = (JSONArray) jObject.get("VIRMANREF");
        JSONArray sourceFRef = (JSONArray) jObject.get("SOURCEFREF");
        JSONArray date = (JSONArray) jObject.get("DATE_");
        JSONArray department = (JSONArray) jObject.get("DEPARTMENT");
        JSONArray branch = (JSONArray) jObject.get("BRANCH");
        JSONArray moduleNr = (JSONArray) jObject.get("MODULENR");
        JSONArray trCode = (JSONArray) jObject.get("TRCODE");
        JSONArray lineNr = (JSONArray) jObject.get("LINENR");
        JSONArray specode = (JSONArray) jObject.get("SPECODE");
        JSONArray cyphCode = (JSONArray) jObject.get("CYPHCODE");
        JSONArray tranNo = (JSONArray) jObject.get("TRANNO");
        JSONArray doCode = (JSONArray) jObject.get("DOCODE");
        JSONArray lineExp = (JSONArray) jObject.get("LINEEXP");
        JSONArray accounted = (JSONArray) jObject.get("ACCOUNTED");
        JSONArray sign = (JSONArray) jObject.get("SIGN");
        JSONArray amount = (JSONArray) jObject.get("AMOUNT");
        JSONArray trCurr = (JSONArray) jObject.get("TRCURR");
        JSONArray trRate = (JSONArray) jObject.get("TRRATE");
        JSONArray trNet = (JSONArray) jObject.get("TRNET");
        JSONArray reportRate = (JSONArray) jObject.get("REPORTRATE");
        JSONArray reportNet = (JSONArray) jObject.get("REPORTNET");
        JSONArray extenRef = (JSONArray) jObject.get("EXTENREF");
        JSONArray payDefRef = (JSONArray) jObject.get("PAYDEFREF");
        JSONArray accFicheRef = (JSONArray) jObject.get("ACCFICHEREF");
        JSONArray printCnt = (JSONArray) jObject.get("PRINTCNT");
        JSONArray capiblockCreatedby = (JSONArray) jObject.get("CAPIBLOCK_CREATEDBY");
        JSONArray capiblockCreadeddate = (JSONArray) jObject.get("CAPIBLOCK_CREADEDDATE");
        JSONArray capiblockCreatedhour = (JSONArray) jObject.get("CAPIBLOCK_CREATEDHOUR");
        JSONArray capiblockCreatedmin = (JSONArray) jObject.get("CAPIBLOCK_CREATEDMIN");
        JSONArray capiblockCreatedsec = (JSONArray) jObject.get("CAPIBLOCK_CREATEDSEC");
        JSONArray capiblockModifiedby = (JSONArray) jObject.get("CAPIBLOCK_MODIFIEDBY");
        JSONArray capiblockModifieddate = (JSONArray) jObject.get("CAPIBLOCK_MODIFIEDDATE");
        JSONArray capiblockModifiedhour = (JSONArray) jObject.get("CAPIBLOCK_MODIFIEDHOUR");
        JSONArray capiblockModifiedmin = (JSONArray) jObject.get("CAPIBLOCK_MODIFIEDMIN");
        JSONArray capiblockModifiedsec = (JSONArray) jObject.get("CAPIBLOCK_MODIFIEDSEC");
        JSONArray cancelled = (JSONArray) jObject.get("CANCELLED");
        JSONArray trgFlag = (JSONArray) jObject.get("TRGFLAG");
        JSONArray tradingGrp = (JSONArray) jObject.get("TRADINGGRP");
        JSONArray lineExpType = (JSONArray) jObject.get("LINEEXCTYP");
        JSONArray onlyOnePayLine = (JSONArray) jObject.get("ONLYONEPAYLINE");
        JSONArray discFlag = (JSONArray) jObject.get("DISCFLAG");
        JSONArray discRate = (JSONArray) jObject.get("DISCRATE");
        JSONArray vatRate = (JSONArray) jObject.get("VATRATE");
        JSONArray cashAmount = (JSONArray) jObject.get("CASHAMOUNT");
        JSONArray discAccRef = (JSONArray) jObject.get("DISCACCREF");
        JSONArray discCentRef = (JSONArray) jObject.get("DISCCENREF");
        JSONArray vatrAccRef = (JSONArray) jObject.get("VATRACCREF");
        JSONArray vatrCenRef = (JSONArray) jObject.get("VATRCENREF");
        JSONArray paymentRef = (JSONArray) jObject.get("PAYMENTREF");
        JSONArray vatAmount = (JSONArray) jObject.get("VATAMOUNT");
        JSONArray siteId = (JSONArray) jObject.get("SITEID");
        JSONArray recStatus = (JSONArray) jObject.get("RECSTATUS");
        JSONArray orgLogicRef = (JSONArray) jObject.get("ORGLOGICREF");
        JSONArray infidx = (JSONArray) jObject.get("INFIDX");
        JSONArray poscommAccRef = (JSONArray) jObject.get("POSCOMMACCREF");
        JSONArray poscommCenRef = (JSONArray) jObject.get("POSCOMMCENREF");
        JSONArray pointCommAccRef = (JSONArray) jObject.get("POINTCOMMACCREF");
        JSONArray pointCommCenRef = (JSONArray) jObject.get("POINTCOMMCENREF");
        JSONArray cheqInfo = (JSONArray) jObject.get("CHEQINFO");
        JSONArray creditCno = (JSONArray) jObject.get("CREDITCNO");
        JSONArray clprjRef = (JSONArray) jObject.get("CLPRJREF");
        JSONArray status = (JSONArray) jObject.get("STATUS");
        JSONArray eximFileRef = (JSONArray) jObject.get("EXIMFILEREF");
        JSONArray eximProcNr = (JSONArray) jObject.get("EXIMPROCNR");
        JSONArray month = (JSONArray) jObject.get("MONTH_");
        JSONArray year = (JSONArray) jObject.get("YEAR_");
        JSONArray fundsShareRat = (JSONArray) jObject.get("FUNDSHARERAT");
        JSONArray affectCollatRl = (JSONArray) jObject.get("AFFECTCOLLATRL");
        JSONArray grpFirmTrans = (JSONArray) jObject.get("GRPFIRMTRANS");
        JSONArray reflVatAccRef = (JSONArray) jObject.get("REFLVATACCREF");
        JSONArray reflVatOthAccRef = (JSONArray) jObject.get("REFLVATOTHACCREF");
        JSONArray affectRisk = (JSONArray) jObject.get("AFFECTRISK");
        JSONArray batchNr = (JSONArray) jObject.get("BATCHNR");
        JSONArray approveNr = (JSONArray) jObject.get("APPROVENR");
        JSONArray batchNum = (JSONArray) jObject.get("BATCHNUM");
        JSONArray approveNum = (JSONArray) jObject.get("APPROVENUM");

        for (int i = 0; i < logicalRef.size(); i++) {
            line = new ClfLine(
                    Integer.parseInt(logicalRef.get(i).toString()), Integer.parseInt(clientRef.get(i).toString()),
                    Integer.parseInt(claccRef.get(i).toString()), Integer.parseInt(clCenterRef.get(i).toString()),
                    Integer.parseInt(cashCenterRef.get(i).toString()), Integer.parseInt(cashAccountRef.get(i).toString()),
                    Integer.parseInt(virManRef.get(i).toString()), Integer.parseInt(sourceFRef.get(i).toString()), 
                    date.get(i).toString(),
                    Short.parseShort(department.get(i).toString()), Short.parseShort(branch.get(i).toString()),
                    Short.parseShort(moduleNr.get(i).toString()), Short.parseShort(trCode.get(i).toString()),
                    Short.parseShort(lineNr.get(i).toString()), specode.get(i).toString(),
                    cyphCode.get(i).toString(), tranNo.get(i).toString(), doCode.get(i).toString(), lineExp.get(i).toString(),
                    Short.parseShort(accounted.get(i).toString()), Short.parseShort(sign.get(i).toString()),
                    Float.parseFloat(amount.get(i).toString()), Short.parseShort(trCurr.get(i).toString()),
                    Float.parseFloat(trRate.get(i).toString()), Float.parseFloat(trNet.get(i).toString()),
                    Float.parseFloat(reportRate.get(i).toString()), Float.parseFloat(reportNet.get(i).toString()),
                    Integer.parseInt(extenRef.get(i).toString()), Integer.parseInt(payDefRef.get(i).toString()),
                    Integer.parseInt(accFicheRef.get(i).toString()), Short.parseShort(printCnt.get(i).toString()),
                    Short.parseShort(capiblockCreatedby.get(i).toString()), capiblockCreadeddate.get(i).toString(),
                    Short.parseShort(capiblockCreatedhour.get(i).toString()), Short.parseShort(capiblockCreatedmin.get(i).toString()),
                    Short.parseShort(capiblockCreatedsec.get(i).toString()), Short.parseShort(capiblockModifiedby.get(i).toString()),
                    capiblockModifieddate.get(i).toString(), Short.parseShort(capiblockModifiedhour.get(i).toString()),
                    Short.parseShort(capiblockModifiedmin.get(i).toString()), Short.parseShort(capiblockModifiedsec.get(i).toString()),
                    Short.parseShort(cancelled.get(i).toString()), Short.parseShort(trgFlag.get(i).toString()),
                    tradingGrp.get(i).toString(), Short.parseShort(lineExpType.get(i).toString()),
                    Short.parseShort(onlyOnePayLine.get(i).toString()), Short.parseShort(discFlag.get(i).toString()),
                    Float.parseFloat(discRate.get(i).toString()), Float.parseFloat(vatRate.get(i).toString()),
                    Float.parseFloat(cashAmount.get(i).toString()), Integer.parseInt(discAccRef.get(i).toString()),
                    Integer.parseInt(discCentRef.get(i).toString()), Integer.parseInt(vatrAccRef.get(i).toString()),
                    Integer.parseInt(vatrCenRef.get(i).toString()), Integer.parseInt(paymentRef.get(i).toString()),
                    Float.parseFloat(vatAmount.get(i).toString()), Short.parseShort(siteId.get(i).toString()), 
                    Short.parseShort(recStatus.get(i).toString()), Integer.parseInt(orgLogicRef.get(i).toString()),
                    Float.parseFloat(infidx.get(i).toString()), Integer.parseInt(poscommAccRef.get(i).toString()),
                    Integer.parseInt(poscommCenRef.get(i).toString()), Integer.parseInt(pointCommAccRef.get(i).toString()),
                    Integer.parseInt(pointCommCenRef.get(i).toString()), cheqInfo.get(i).toString(),
                    creditCno.get(i).toString(), Integer.parseInt(clprjRef.get(i).toString()),
                    Short.parseShort(status.get(i).toString()), Integer.parseInt(eximFileRef.get(i).toString()), 
                    Short.parseShort(eximProcNr.get(i).toString()),
                    Short.parseShort(month.get(i).toString()), Short.parseShort(year.get(i).toString()),
                    Float.parseFloat(fundsShareRat.get(i).toString()), Short.parseShort(affectCollatRl.get(i).toString()),
                    Short.parseShort(grpFirmTrans.get(i).toString()), Integer.parseInt(reflVatAccRef.get(i).toString()),
                    Integer.parseInt(reflVatOthAccRef.get(i).toString()),
                    Short.parseShort(affectRisk.get(i).toString()), Integer.parseInt(batchNr.get(i).toString()),
                    Integer.parseInt(approveNr.get(i).toString()), batchNum.get(i).toString(),
                    approveNum.get(i).toString());

            tableValue.add(line);
        }
        return tableValue;
    }
}
