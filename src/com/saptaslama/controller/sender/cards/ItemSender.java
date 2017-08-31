/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saptaslama.controller.sender.cards;

import com.saptaslama.controller.CompanyConstants;
import com.saptaslama.controller.DbConnection;
import com.saptaslama.controller.sender.Sender;
import com.saptaslama.model.Items;
import com.saptaslama.model.ItmUnitA;
import com.saptaslama.model.Itmclsas;
import com.saptaslama.model.JsonObject;
import com.saptaslama.model.PrcList;
import com.saptaslama.model.UnitBarcode;
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
public class ItemSender extends Sender{
    
    public void init() {
        Connection con;
        ResultSet resultSetItems;
        ResultSet resultSetItmclsas;
        ResultSet resultSetitmUnitA;
        ResultSet resultSetPrcList;
        ResultSet resultSetUnitBarcode;
        
        String tabelNameItems = CompanyConstants.COMPANY + "ITEMS";
        String tabelNameItmclsas = CompanyConstants.COMPANY + "ITMCLSAS";
        String tabelNameItmUnitA = CompanyConstants.COMPANY + "ITMUNITA";
        String tabelNamePrcList = CompanyConstants.COMPANY + "PRCLIST";
        String tabelNameUnitBarcode = CompanyConstants.COMPANY + "UNITBARCODE";
        
        String fileNameItems = "Items.json";
        String fileNameItmclsas = "Itmclsas.json";
        String fileNameItmUnitA = "ItmUnitA.json";
        String fileNamePrcList = "PrcList.json";
        String fileNameUnitBarcode = "UnitBarcode.json";
        
        DbConnection db = new DbConnection();

        con = db.getConnection();
        
        resultSetItems = db.querySelectStatement(con, tabelNameItems, "LOGICALREF != 1");
        resultSetItmclsas = db.querySelectAll(con, tabelNameItmclsas);
        resultSetitmUnitA = db.querySelectAll(con, tabelNameItmUnitA);
        resultSetPrcList = db.querySelectAll(con, tabelNamePrcList);
        resultSetUnitBarcode = db.querySelectAll(con, tabelNameUnitBarcode);
        
        sendJsonFile(getItemsJson(getItemsArrayList(resultSetItems)), 
                CompanyConstants.FILE_PATH_SEND + fileNameItems);
        sendJsonFile(getItmclsasJson(getItmclsasArrayList(resultSetItmclsas)), 
                CompanyConstants.FILE_PATH_SEND + fileNameItmclsas);
        sendJsonFile(getItmUnitAJson(getItmUnitAArrayList(resultSetitmUnitA)), 
                CompanyConstants.FILE_PATH_SEND + fileNameItmUnitA);
        sendJsonFile(getPrcListJson(getPrcListArrayList(resultSetPrcList)), 
                CompanyConstants.FILE_PATH_SEND + fileNamePrcList);
        sendJsonFile(getUnitBarcodeJson(getUnitBarcodeArrayList(resultSetUnitBarcode)), 
                CompanyConstants.FILE_PATH_SEND + fileNameUnitBarcode);

        db.closeConnection(con);
        db.closeResultSet(resultSetItems);
        db.closeResultSet(resultSetItmclsas);
        db.closeResultSet(resultSetitmUnitA);
        db.closeResultSet(resultSetPrcList);
        db.closeResultSet(resultSetUnitBarcode);
    }
    
//********************************ITEMS*****************************************
    public ArrayList<Items> getItemsArrayList(ResultSet resultSet) {

        ArrayList<Items> tableValue = new ArrayList<>();
        Items unit;
        String[] columnNames;

        try {

            columnNames = getColumnNames(resultSet);
            while (resultSet.next()) {
                unit = new Items(
                        resultSet.getInt(columnNames[0]), resultSet.getShort(columnNames[1]),
                        resultSet.getShort(columnNames[2]), resultSet.getString(columnNames[3]), 
                        resultSet.getString(columnNames[4]), resultSet.getString(columnNames[5]),
                        resultSet.getString(columnNames[6]), resultSet.getString(columnNames[7]),
                        resultSet.getString(columnNames[8]), resultSet.getShort(columnNames[9]), 
                        resultSet.getShort(columnNames[10]), resultSet.getShort(columnNames[11]), 
                        resultSet.getShort(columnNames[12]), resultSet.getDouble(columnNames[13]), 
                        resultSet.getInt(columnNames[14]), resultSet.getShort(columnNames[15]), 
                        resultSet.getShort(columnNames[16]), resultSet.getShort(columnNames[17]), 
                        resultSet.getShort(columnNames[18]), resultSet.getShort(columnNames[19]), 
                        resultSet.getDouble(columnNames[20]), resultSet.getShort(columnNames[21]), 
                        resultSet.getInt(columnNames[22]), resultSet.getInt(columnNames[23]), 
                        resultSet.getInt(columnNames[24]), resultSet.getInt(columnNames[25]), 
                        resultSet.getInt(columnNames[26]), resultSet.getInt(columnNames[27]), 
                        resultSet.getInt(columnNames[28]), resultSet.getInt(columnNames[29]), 
                        resultSet.getInt(columnNames[30]), resultSet.getInt(columnNames[31]), 
                        resultSet.getInt(columnNames[32]), resultSet.getInt(columnNames[33]), 
                        resultSet.getShort(columnNames[34]), resultSet.getShort(columnNames[35]), 
                        resultSet.getShort(columnNames[36]), resultSet.getDouble(columnNames[37]), 
                        resultSet.getShort(columnNames[38]), resultSet.getDouble(columnNames[39]), 
                        resultSet.getShort(columnNames[40]), resultSet.getShort(columnNames[41]), 
                        resultSet.getShort(columnNames[42]), resultSet.getShort(columnNames[43]), 
                        resultSet.getDouble(columnNames[44]), resultSet.getShort(columnNames[45]), 
                        resultSet.getShort(columnNames[46]), resultSet.getShort(columnNames[47]), 
                        resultSet.getShort(columnNames[48]), resultSet.getShort(columnNames[49]), 
                        resultSet.getInt(columnNames[50]), resultSet.getInt(columnNames[51]), 
                        resultSet.getDouble(columnNames[52]), resultSet.getShort(columnNames[53]), 
                        resultSet.getString(columnNames[54]), resultSet.getShort(columnNames[55]), 
                        resultSet.getShort(columnNames[56]), resultSet.getShort(columnNames[57]), 
                        resultSet.getShort(columnNames[58]), resultSet.getString(columnNames[59]), 
                        resultSet.getShort(columnNames[60]), resultSet.getShort(columnNames[61]), 
                        resultSet.getShort(columnNames[62]), resultSet.getShort(columnNames[63]), 
                        resultSet.getShort(columnNames[64]), resultSet.getInt(columnNames[65]), 
                        resultSet.getString(columnNames[66]), resultSet.getShort(columnNames[67]), 
                        resultSet.getShort(columnNames[68]), resultSet.getInt(columnNames[69]), 
                        resultSet.getDouble(columnNames[70]), resultSet.getDouble(columnNames[71]), 
                        resultSet.getShort(columnNames[72]), resultSet.getString(columnNames[73]), 
                        resultSet.getString(columnNames[74]), resultSet.getString(columnNames[75]), 
                        resultSet.getInt(columnNames[76]), resultSet.getDouble(columnNames[77]), 
                        resultSet.getInt(columnNames[78]), resultSet.getShort(columnNames[79]), 
                        resultSet.getInt(columnNames[80]), resultSet.getShort(columnNames[81]), 
                        resultSet.getDouble(columnNames[82]), resultSet.getDouble(columnNames[83]), 
                        resultSet.getDouble(columnNames[84]), resultSet.getString(columnNames[85]), 
                        resultSet.getShort(columnNames[86]), resultSet.getString(columnNames[87]), 
                        resultSet.getString(columnNames[88]), resultSet.getString(columnNames[89]), 
                        resultSet.getInt(columnNames[90]), resultSet.getShort(columnNames[91]), 
                        resultSet.getDouble(columnNames[92]), resultSet.getInt(columnNames[93]), 
                        resultSet.getDouble(columnNames[94]), resultSet.getString(columnNames[95]), 
                        resultSet.getString(columnNames[96]), resultSet.getString(columnNames[97]), 
                        resultSet.getString(columnNames[98]), resultSet.getString(columnNames[99]), 
                        resultSet.getString(columnNames[100]), resultSet.getString(columnNames[101]), 
                        resultSet.getString(columnNames[102]), resultSet.getString(columnNames[103]), 
                        resultSet.getString(columnNames[104]), resultSet.getString(columnNames[105]), 
                        resultSet.getString(columnNames[106]), resultSet.getString(columnNames[107]), 
                        resultSet.getString(columnNames[108]), resultSet.getDouble(columnNames[109]), 
                        resultSet.getShort(columnNames[110]), resultSet.getString(columnNames[111]), 
                        resultSet.getString(columnNames[112]), resultSet.getInt(columnNames[113]), 
                        resultSet.getDouble(columnNames[114]), resultSet.getDouble(columnNames[115]), 
                        resultSet.getInt(columnNames[116]), resultSet.getInt(columnNames[117]), 
                        resultSet.getInt(columnNames[118]), resultSet.getInt(columnNames[119]), 
                        resultSet.getInt(columnNames[120]), resultSet.getInt(columnNames[121]), 
                        resultSet.getInt(columnNames[122]), resultSet.getInt(columnNames[123]), 
                        resultSet.getInt(columnNames[124]), resultSet.getInt(columnNames[125]), 
                        resultSet.getString(columnNames[126]), resultSet.getShort(columnNames[127]), 
                        resultSet.getShort(columnNames[128]), resultSet.getInt(columnNames[129]), 
                        resultSet.getShort(columnNames[130]), resultSet.getInt(columnNames[131]), 
                        resultSet.getString(columnNames[132]), resultSet.getString(columnNames[133]), 
                        resultSet.getString(columnNames[134]), resultSet.getString(columnNames[135]), 
                        resultSet.getShort(columnNames[136]));
                tableValue.add(unit);
            }
            return tableValue;
        } catch (SQLException ex) {
            Logger.getLogger(ItemSender.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
            return null;
        }
    }
    
    public JsonObject getItemsJson(ArrayList<Items> list) {
        JsonObject jsonObject = new JsonObject();
        JSONArray arrayListLogicalref = new JSONArray();
        JSONArray arrayListActive = new JSONArray();
        JSONArray arrayListCardType = new JSONArray();
        JSONArray arrayListCode = new JSONArray();
        JSONArray arrayListName = new JSONArray();
        JSONArray arrayListStgrpCode = new JSONArray();
        JSONArray arrayListProducerCode = new JSONArray();
        JSONArray arrayListSpecode = new JSONArray();
        JSONArray arrayListCyphCode = new JSONArray();
        JSONArray arrayListClassType = new JSONArray();
        JSONArray arrayListPurchbrws = new JSONArray();
        JSONArray arrayListSalesbrws = new JSONArray();
        JSONArray arrayListMtrlbrws = new JSONArray();
        JSONArray arrayListVat = new JSONArray();
        JSONArray arrayListPaymentRef = new JSONArray();
        JSONArray arrayListTrackType = new JSONArray();
        JSONArray arrayListLocTracking = new JSONArray();
        JSONArray arrayListTool = new JSONArray();
        JSONArray arrayListAutoincsl = new JSONArray();
        JSONArray arrayListDivlotSize = new JSONArray();
        JSONArray arrayListShelfLife = new JSONArray();
        JSONArray arrayListShelfdate = new JSONArray();
        JSONArray arrayListDominantrefs1 = new JSONArray();
        JSONArray arrayListDominantrefs2 = new JSONArray();
        JSONArray arrayListDominantrefs3 = new JSONArray();
        JSONArray arrayListDominantrefs4 = new JSONArray();
        JSONArray arrayListDominantrefs5 = new JSONArray();
        JSONArray arrayListDominantrefs6 = new JSONArray();
        JSONArray arrayListDominantrefs7 = new JSONArray();
        JSONArray arrayListDominantrefs8 = new JSONArray();
        JSONArray arrayListDominantrefs9 = new JSONArray();
        JSONArray arrayListDominantrefs10 = new JSONArray();
        JSONArray arrayListDominantrefs11 = new JSONArray();
        JSONArray arrayListDominantrefs12 = new JSONArray();
        JSONArray arrayListImageinc = new JSONArray();
        JSONArray arrayListTextinc = new JSONArray();
        JSONArray arrayListDeprtype = new JSONArray();
        JSONArray arrayListDeprrate = new JSONArray();
        JSONArray arrayListDeprdur = new JSONArray();
        JSONArray arrayListSalvageval = new JSONArray();
        JSONArray arrayListRevalflag = new JSONArray();
        JSONArray arrayListRevdeprflag = new JSONArray();
        JSONArray arrayListPartdep = new JSONArray();
        JSONArray arrayListDeprtype2 = new JSONArray();
        JSONArray arrayListDeprrate2 = new JSONArray();
        JSONArray arrayListDeprdur2 = new JSONArray();
        JSONArray arrayListRevalflag2 = new JSONArray();
        JSONArray arrayListRevdeprflag2 = new JSONArray();
        JSONArray arrayListPartdep2 = new JSONArray();
        JSONArray arrayListApproved = new JSONArray();
        JSONArray arrayListUnitsetref = new JSONArray();
        JSONArray arrayListQccsetref = new JSONArray();
        JSONArray arrayListDistamount = new JSONArray();
        JSONArray arrayListCapiblockCreatedby = new JSONArray();
        JSONArray arrayListCapiblockCreateddate = new JSONArray();
        JSONArray arrayListCapiblockCreatedhour = new JSONArray();
        JSONArray arrayListCapiblockCreatedmin = new JSONArray();
        JSONArray arrayListCapiblockCreatedsec = new JSONArray();
        JSONArray arrayListCapiblockModifiedby = new JSONArray();
        JSONArray arrayListCapiblockModifieddate = new JSONArray();
        JSONArray arrayListCapiblockModifiedhour = new JSONArray();
        JSONArray arrayListCapiblockModifiedmin = new JSONArray();
        JSONArray arrayListCapiblockModifiedsec = new JSONArray();
        JSONArray arrayListSiteid = new JSONArray();
        JSONArray arrayListRecstatus = new JSONArray();
        JSONArray arrayListOrglogicref = new JSONArray();
        JSONArray arrayListUnivid = new JSONArray();
        JSONArray arrayListDistlotunits = new JSONArray();
        JSONArray arrayListComblotunits = new JSONArray();
        JSONArray arrayListWfstatus = new JSONArray();
        JSONArray arrayListDistpoint = new JSONArray();
        JSONArray arrayListCamppoint = new JSONArray();
        JSONArray arrayListCanuseintrns = new JSONArray();
        JSONArray arrayListIsonr = new JSONArray();
        JSONArray arrayListGroupnr = new JSONArray();
        JSONArray arrayListProdcountry = new JSONArray();
        JSONArray arrayListAddtaxref = new JSONArray();
        JSONArray arrayListQprodamnt = new JSONArray();
        JSONArray arrayListQproduom = new JSONArray();
        JSONArray arrayListQprodsrcindex = new JSONArray();
        JSONArray arrayListExtaccessflags = new JSONArray();
        JSONArray arrayListPacket = new JSONArray();
        JSONArray arrayListSalvageval2 = new JSONArray();
        JSONArray arrayListSellvat = new JSONArray();
        JSONArray arrayListReturnvat = new JSONArray();
        JSONArray arrayListLogoid = new JSONArray();
        JSONArray arrayListLidconfirmed = new JSONArray();
        JSONArray arrayListGtipcode = new JSONArray();
        JSONArray arrayListExpctgno = new JSONArray();
        JSONArray arrayListB2ccode = new JSONArray();
        JSONArray arrayListMarkref = new JSONArray();
        JSONArray arrayListImage2inc = new JSONArray();
        JSONArray arrayListAvrwhduration = new JSONArray();
        JSONArray arrayListExtcardflags = new JSONArray();
        JSONArray arrayListMinordamount = new JSONArray();
        JSONArray arrayListFreightplace = new JSONArray();
        JSONArray arrayListFreighttypcode1 = new JSONArray();
        JSONArray arrayListFreighttypcode2 = new JSONArray();
        JSONArray arrayListFreighttypcode3 = new JSONArray();
        JSONArray arrayListFreighttypcode4 = new JSONArray();
        JSONArray arrayListFreighttypcode5 = new JSONArray();
        JSONArray arrayListFreighttypcode6 = new JSONArray();
        JSONArray arrayListFreighttypcode7 = new JSONArray();
        JSONArray arrayListFreighttypcode8 = new JSONArray();
        JSONArray arrayListFreighttypcode9 = new JSONArray();
        JSONArray arrayListFreighttypcode10 = new JSONArray();
        JSONArray arrayListStatecode = new JSONArray();
        JSONArray arrayListStatename = new JSONArray();
        JSONArray arrayListExpcategory = new JSONArray();
        JSONArray arrayListLostfactor = new JSONArray();
        JSONArray arrayListTextinceng = new JSONArray();
        JSONArray arrayListEanbarcode = new JSONArray();
        JSONArray arrayListDeprclasstype = new JSONArray();
        JSONArray arrayListWflowcrdref = new JSONArray();
        JSONArray arrayListSellprvat = new JSONArray();
        JSONArray arrayListReturnprvat = new JSONArray();
        JSONArray arrayListLowlevelcodes1 = new JSONArray();
        JSONArray arrayListLowlevelcodes2 = new JSONArray();
        JSONArray arrayListLowlevelcodes3 = new JSONArray();
        JSONArray arrayListLowlevelcodes4 = new JSONArray();
        JSONArray arrayListLowlevelcodes5 = new JSONArray();
        JSONArray arrayListLowlevelcodes6 = new JSONArray();
        JSONArray arrayListLowlevelcodes7 = new JSONArray();
        JSONArray arrayListLowlevelcodes8 = new JSONArray();
        JSONArray arrayListLowlevelcodes9 = new JSONArray();
        JSONArray arrayListLowlevelcodes10 = new JSONArray();
        JSONArray arrayListOrglogoid = new JSONArray();
        JSONArray arrayListQproddepart = new JSONArray();
        JSONArray arrayListCanconfigure = new JSONArray();
        JSONArray arrayListCharsetref = new JSONArray();
        JSONArray arrayListCandeduct = new JSONArray();
        JSONArray arrayListConscoderef = new JSONArray();
        JSONArray arrayListSpecode2 = new JSONArray();
        JSONArray arrayListSpecode3 = new JSONArray();
        JSONArray arrayListSpecode4 = new JSONArray();
        JSONArray arrayListSpecode5 = new JSONArray();
        JSONArray arrayListExpense = new JSONArray();

        for (int a = 0; a < list.size(); a++) {
            
            //here we setting the specode with local logicalRef (example: if depo then we adding "D" infront of LogREf)
            arrayListIsonr.add(String.valueOf(list.get(a).getLogicalref()));

            arrayListLogicalref.add(list.get(a).getLogicalref());
            arrayListActive.add(list.get(a).getActive());
            arrayListCardType.add(list.get(a).getCardtype());
            arrayListCode.add(list.get(a).getCode());
            arrayListName.add(list.get(a).getName());
            arrayListStgrpCode.add(list.get(a).getStgrpcode());
            arrayListProducerCode.add(list.get(a).getProducercode());
            arrayListSpecode.add(list.get(a).getSpecode());
            arrayListCyphCode.add(list.get(a).getCyphcode());
            arrayListClassType.add(list.get(a).getClasstype());
            arrayListPurchbrws.add(list.get(a).getPurchbrws());
            arrayListSalesbrws.add(list.get(a).getSalesbrws());
            arrayListMtrlbrws.add(list.get(a).getMtrlbrws());
            arrayListVat.add(list.get(a).getVat());
            arrayListPaymentRef.add(list.get(a).getPaymentref());
            arrayListTrackType.add(list.get(a).getTracktype());
            arrayListLocTracking.add(list.get(a).getLoctracking());
            arrayListTool.add(list.get(a).getTool());
            arrayListAutoincsl.add(list.get(a).getAutoincsl());
            arrayListDivlotSize.add(list.get(a).getDivlotsize());
            arrayListShelfLife.add(list.get(a).getShelflife());
            arrayListShelfdate.add(list.get(a).getShelfdate());
            arrayListDominantrefs1.add(list.get(a).getDominantrefs1());
            arrayListDominantrefs2.add(list.get(a).getDominantrefs2());
            arrayListDominantrefs3.add(list.get(a).getDominantrefs3());
            arrayListDominantrefs4.add(list.get(a).getDominantrefs4());
            arrayListDominantrefs5.add(list.get(a).getDominantrefs5());
            arrayListDominantrefs6.add(list.get(a).getDominantrefs6());
            arrayListDominantrefs7.add(list.get(a).getDominantrefs7());
            arrayListDominantrefs8.add(list.get(a).getDominantrefs8());
            arrayListDominantrefs9.add(list.get(a).getDominantrefs9());
            arrayListDominantrefs10.add(list.get(a).getDominantrefs10());
            arrayListDominantrefs11.add(list.get(a).getDominantrefs11());
            arrayListDominantrefs12.add(list.get(a).getDominantrefs12());
            arrayListImageinc.add(list.get(a).getImageinc());
            arrayListTextinc.add(list.get(a).getTextinc());
            arrayListDeprtype.add(list.get(a).getDeprtype());
            arrayListDeprrate.add(list.get(a).getDeprrate());
            arrayListDeprdur.add(list.get(a).getDeprdur());
            arrayListSalvageval.add(list.get(a).getSalvageval());
            arrayListRevalflag.add(list.get(a).getRevalflag());
            arrayListRevdeprflag.add(list.get(a).getRevdeprflag());
            arrayListPartdep.add(list.get(a).getPartdep());
            arrayListDeprtype2.add(list.get(a).getDeprtype2());
            arrayListDeprrate2.add(list.get(a).getDeprrate2());
            arrayListDeprdur2.add(list.get(a).getDeprdur2());
            arrayListRevalflag2.add(list.get(a).getRevalflag2());
            arrayListRevdeprflag2.add(list.get(a).getRevdeprflag2());
            arrayListPartdep2.add(list.get(a).getPartdep2());
            arrayListApproved.add(list.get(a).getApproved());
            arrayListUnitsetref.add(list.get(a).getUnitsetref());
            arrayListQccsetref.add(list.get(a).getQccsetref());
            arrayListDistamount.add(list.get(a).getDistamount());
            arrayListCapiblockCreatedby.add(list.get(a).getCapiblockCreatedby());
            arrayListCapiblockCreateddate.add(list.get(a).getCapiblockCreateddate());
            arrayListCapiblockCreatedhour.add(list.get(a).getCapiblockCreatedhour());
            arrayListCapiblockCreatedmin.add(list.get(a).getCapiblockCreatedmin());
            arrayListCapiblockCreatedsec.add(list.get(a).getCapiblockCreatedsec());
            arrayListCapiblockModifiedby.add(list.get(a).getCapiblockModifiedby());
            arrayListCapiblockModifieddate.add(list.get(a).getCapiblockModifieddate());
            arrayListCapiblockModifiedhour.add(list.get(a).getCapiblockModifiedhour());
            arrayListCapiblockModifiedmin.add(list.get(a).getCapiblockModifiedmin());
            arrayListCapiblockModifiedsec.add(list.get(a).getCapiblockModifiedsec());
            arrayListSiteid.add(list.get(a).getSiteid());
            arrayListRecstatus.add(list.get(a).getRecstatus());
            arrayListOrglogicref.add(list.get(a).getOrglogicref());
            arrayListUnivid.add(list.get(a).getUnivid());
            arrayListDistlotunits.add(list.get(a).getDistlotunits());
            arrayListComblotunits.add(list.get(a).getComblotunits());
            arrayListWfstatus.add(list.get(a).getWfstatus());
            arrayListDistpoint.add(list.get(a).getDistpoint());
            arrayListCamppoint.add(list.get(a).getCamppoint());
            arrayListCanuseintrns.add(list.get(a).getCanuseintrns());
            arrayListGroupnr.add(list.get(a).getGroupnr());
            arrayListProdcountry.add(list.get(a).getProdcountry());
            arrayListAddtaxref.add(list.get(a).getAddtaxref());
            arrayListQprodamnt.add(list.get(a).getQprodamnt());
            arrayListQproduom.add(list.get(a).getQproduom());
            arrayListQprodsrcindex.add(list.get(a).getQprodsrcindex());
            arrayListExtaccessflags.add(list.get(a).getExtaccessflags());
            arrayListPacket.add(list.get(a).getPacket());
            arrayListSalvageval2.add(list.get(a).getSalvageval2());
            arrayListSellvat.add(list.get(a).getSellvat());
            arrayListReturnvat.add(list.get(a).getReturnvat());
            arrayListLogoid.add(list.get(a).getLogoid());
            arrayListLidconfirmed.add(list.get(a).getLidconfirmed());
            arrayListGtipcode.add(list.get(a).getGtipcode());
            arrayListExpctgno.add(list.get(a).getExpctgno());
            arrayListB2ccode.add(list.get(a).getB2ccode());
            arrayListMarkref.add(list.get(a).getMarkref());
            arrayListImage2inc.add(list.get(a).getImage2inc());
            arrayListAvrwhduration.add(list.get(a).getAvrwhduration());
            arrayListExtcardflags.add(list.get(a).getExtcardflags());
            arrayListMinordamount.add(list.get(a).getMinordamount());
            arrayListFreightplace.add(list.get(a).getFreightplace());
            arrayListFreighttypcode1.add(list.get(a).getFreighttypcode1());
            arrayListFreighttypcode2.add(list.get(a).getFreighttypcode2());
            arrayListFreighttypcode3.add(list.get(a).getFreighttypcode3());
            arrayListFreighttypcode4.add(list.get(a).getFreighttypcode4());
            arrayListFreighttypcode5.add(list.get(a).getFreighttypcode5());
            arrayListFreighttypcode6.add(list.get(a).getFreighttypcode6());
            arrayListFreighttypcode7.add(list.get(a).getFreighttypcode7());
            arrayListFreighttypcode8.add(list.get(a).getFreighttypcode8());
            arrayListFreighttypcode9.add(list.get(a).getFreighttypcode9());
            arrayListFreighttypcode10.add(list.get(a).getFreighttypcode10());
            arrayListStatecode.add(list.get(a).getStatecode());
            arrayListStatename.add(list.get(a).getStatename());
            arrayListExpcategory.add(list.get(a).getExpcategory());
            arrayListLostfactor.add(list.get(a).getLostfactor());
            arrayListTextinceng.add(list.get(a).getTextinceng());
            arrayListEanbarcode.add(list.get(a).getEanbarcode());
            arrayListDeprclasstype.add(list.get(a).getDeprclasstype());
            arrayListWflowcrdref.add(list.get(a).getWflowcrdref());
            arrayListSellprvat.add(list.get(a).getSellprvat());
            arrayListReturnprvat.add(list.get(a).getReturnprvat());
            arrayListLowlevelcodes1.add(list.get(a).getLowlevelcodes1());
            arrayListLowlevelcodes2.add(list.get(a).getLowlevelcodes2());
            arrayListLowlevelcodes3.add(list.get(a).getLowlevelcodes3());
            arrayListLowlevelcodes4.add(list.get(a).getLowlevelcodes4());
            arrayListLowlevelcodes5.add(list.get(a).getLowlevelcodes5());
            arrayListLowlevelcodes6.add(list.get(a).getLowlevelcodes6());
            arrayListLowlevelcodes7.add(list.get(a).getLowlevelcodes7());
            arrayListLowlevelcodes8.add(list.get(a).getLowlevelcodes8());
            arrayListLowlevelcodes9.add(list.get(a).getLowlevelcodes9());
            arrayListLowlevelcodes10.add(list.get(a).getLowlevelcodes10());
            arrayListOrglogoid.add(list.get(a).getOrglogoid());
            arrayListQproddepart.add(list.get(a).getQproddepart());
            arrayListCanconfigure.add(list.get(a).getCanconfigure());
            arrayListCharsetref.add(list.get(a).getCharsetref());
            arrayListCandeduct.add(list.get(a).getCandeduct());
            arrayListConscoderef.add(list.get(a).getConscoderef());
            arrayListSpecode2.add(list.get(a).getSpecode2());
            arrayListSpecode3.add(list.get(a).getSpecode3());
            arrayListSpecode4.add(list.get(a).getSpecode4());
            arrayListSpecode5.add(list.get(a).getSpecode5());
            arrayListExpense.add(list.get(a).getExpense());
        }
        jsonObject.setObject("LOGICALREF", arrayListLogicalref);
        jsonObject.setObject("ACTIVE", arrayListActive);
        jsonObject.setObject("CARDTYPE", arrayListCardType);
        jsonObject.setObject("CODE", arrayListCode);
        jsonObject.setObject("NAME", arrayListName);
        jsonObject.setObject("STGRPCODE", arrayListStgrpCode);
        jsonObject.setObject("PRODUCERCODE", arrayListProducerCode);
        jsonObject.setObject("SPECODE", arrayListSpecode);
        jsonObject.setObject("CYPHCODE", arrayListCyphCode);
        jsonObject.setObject("CLASSTYPE", arrayListClassType);
        jsonObject.setObject("PURCHBRWS", arrayListPurchbrws);
        jsonObject.setObject("SALESBRWS", arrayListSalesbrws);
        jsonObject.setObject("MTRLBRWS", arrayListMtrlbrws);
        jsonObject.setObject("VAT", arrayListVat);
        jsonObject.setObject("PAYMENTREF", arrayListPaymentRef);
        jsonObject.setObject("TRACKTYPE", arrayListTrackType);
        jsonObject.setObject("LOCTRACKING", arrayListLocTracking);
        jsonObject.setObject("TOOL", arrayListTool);
        jsonObject.setObject("AUTOINCSL", arrayListAutoincsl);
        jsonObject.setObject("DIVLOTSIZE", arrayListDivlotSize);
        jsonObject.setObject("SHELFLIFE", arrayListShelfLife);
        jsonObject.setObject("SHELFDATE", arrayListShelfdate);
        jsonObject.setObject("DOMINANTREFS1", arrayListDominantrefs1);
        jsonObject.setObject("DOMINANTREFS2", arrayListDominantrefs2);
        jsonObject.setObject("DOMINANTREFS3", arrayListDominantrefs3);
        jsonObject.setObject("DOMINANTREFS4", arrayListDominantrefs4);
        jsonObject.setObject("DOMINANTREFS5", arrayListDominantrefs5);
        jsonObject.setObject("DOMINANTREFS6", arrayListDominantrefs6);
        jsonObject.setObject("DOMINANTREFS7", arrayListDominantrefs7);
        jsonObject.setObject("DOMINANTREFS8", arrayListDominantrefs8);
        jsonObject.setObject("DOMINANTREFS9", arrayListDominantrefs9);
        jsonObject.setObject("DOMINANTREFS10", arrayListDominantrefs10);
        jsonObject.setObject("DOMINANTREFS11", arrayListDominantrefs11);
        jsonObject.setObject("DOMINANTREFS12", arrayListDominantrefs12);
        jsonObject.setObject("IMAGEINC", arrayListImageinc);
        jsonObject.setObject("TEXTINC", arrayListTextinc);
        jsonObject.setObject("DEPRTYPE", arrayListDeprtype);
        jsonObject.setObject("DEPRRATE", arrayListDeprrate);
        jsonObject.setObject("DEPRDUR", arrayListDeprdur);
        jsonObject.setObject("SALVAGEVAL", arrayListSalvageval);
        jsonObject.setObject("REVALFLAG", arrayListRevalflag);
        jsonObject.setObject("REVDEPRFLAG", arrayListRevdeprflag);
        jsonObject.setObject("PARTDEP", arrayListPartdep);
        jsonObject.setObject("DEPRTYPE2", arrayListDeprtype2);
        jsonObject.setObject("DEPRRATE2", arrayListDeprrate2);
        jsonObject.setObject("DEPRDUR2", arrayListDeprdur2);
        jsonObject.setObject("REVALFLAG2", arrayListRevalflag2);
        jsonObject.setObject("REVDEPRFLAG2", arrayListRevdeprflag2);
        jsonObject.setObject("PARTDEP2", arrayListPartdep2);
        jsonObject.setObject("APPROVED", arrayListApproved);
        jsonObject.setObject("UNITSETREF", arrayListUnitsetref);
        jsonObject.setObject("QCCSETREF", arrayListQccsetref);
        jsonObject.setObject("DISTAMOUNT", arrayListDistamount);
        jsonObject.setObject("CAPIBLOCK_CREATEDBY", arrayListCapiblockCreatedby);
        jsonObject.setObject("CAPIBLOCK_CREADEDDATE", arrayListCapiblockCreateddate);
        jsonObject.setObject("CAPIBLOCK_CREATEDHOUR", arrayListCapiblockCreatedhour);
        jsonObject.setObject("CAPIBLOCK_CREATEDMIN", arrayListCapiblockCreatedmin);
        jsonObject.setObject("CAPIBLOCK_CREATEDSEC", arrayListCapiblockCreatedsec);
        jsonObject.setObject("CAPIBLOCK_MODIFIEDBY", arrayListCapiblockModifiedby);
        jsonObject.setObject("CAPIBLOCK_MODIFIEDDATE", arrayListCapiblockModifieddate);
        jsonObject.setObject("CAPIBLOCK_MODIFIEDHOUR", arrayListCapiblockModifiedhour);
        jsonObject.setObject("CAPIBLOCK_MODIFIEDMIN", arrayListCapiblockModifiedmin);
        jsonObject.setObject("CAPIBLOCK_MODIFIEDSEC", arrayListCapiblockModifiedsec);
        jsonObject.setObject("SITEID", arrayListSiteid);
        jsonObject.setObject("RECSTATUS", arrayListRecstatus);
        jsonObject.setObject("ORGLOGICREF", arrayListOrglogicref);
        jsonObject.setObject("UNIVID", arrayListUnivid);
        jsonObject.setObject("DISTLOTUNITS", arrayListDistlotunits);
        jsonObject.setObject("COMBLOTUNITS", arrayListComblotunits);
        jsonObject.setObject("WFSTATUS", arrayListWfstatus);
        jsonObject.setObject("DISTPOINT", arrayListDistpoint);
        jsonObject.setObject("CAMPPOINT", arrayListCamppoint);
        jsonObject.setObject("CANUSEINTRNS", arrayListCanuseintrns);
        jsonObject.setObject("ISONR", arrayListIsonr);
        jsonObject.setObject("GROUPNR", arrayListGroupnr);
        jsonObject.setObject("PRODCOUNTRY", arrayListProdcountry);
        jsonObject.setObject("ADDTAXREF", arrayListAddtaxref);
        jsonObject.setObject("QPRODAMNT", arrayListQprodamnt);
        jsonObject.setObject("QPRODUOM", arrayListQproduom);
        jsonObject.setObject("QPRODSRCINDEX", arrayListQprodsrcindex);
        jsonObject.setObject("EXTACCESSFLAGS", arrayListExtaccessflags);
        jsonObject.setObject("PACKET", arrayListPacket);
        jsonObject.setObject("SALVAGEVAL2", arrayListSalvageval2);
        jsonObject.setObject("SELLVAT", arrayListSellvat);
        jsonObject.setObject("RETURNVAT", arrayListReturnvat);
        jsonObject.setObject("LOGOID", arrayListLogoid);
        jsonObject.setObject("LIDCONFIRMED", arrayListLidconfirmed);
        jsonObject.setObject("GTIPCODE", arrayListGtipcode);
        jsonObject.setObject("EXPCTGNO", arrayListExpctgno);
        jsonObject.setObject("B2CCODE", arrayListB2ccode);
        jsonObject.setObject("MARKREF", arrayListMarkref);
        jsonObject.setObject("IMAGE2INC", arrayListImage2inc);
        jsonObject.setObject("AVRWHDURATION", arrayListAvrwhduration);
        jsonObject.setObject("EXTCARDFLAGS", arrayListExtcardflags);
        jsonObject.setObject("MINORDAMOUNT", arrayListMinordamount);
        jsonObject.setObject("FREIGHTPLACE", arrayListFreightplace);
        jsonObject.setObject("FREIGHTTYPCODE1", arrayListFreighttypcode1);
        jsonObject.setObject("FREIGHTTYPCODE2", arrayListFreighttypcode2);
        jsonObject.setObject("FREIGHTTYPCODE3", arrayListFreighttypcode3);
        jsonObject.setObject("FREIGHTTYPCODE4", arrayListFreighttypcode4);
        jsonObject.setObject("FREIGHTTYPCODE5", arrayListFreighttypcode5);
        jsonObject.setObject("FREIGHTTYPCODE6", arrayListFreighttypcode6);
        jsonObject.setObject("FREIGHTTYPCODE7", arrayListFreighttypcode7);
        jsonObject.setObject("FREIGHTTYPCODE8", arrayListFreighttypcode8);
        jsonObject.setObject("FREIGHTTYPCODE9", arrayListFreighttypcode9);
        jsonObject.setObject("FREIGHTTYPCODE10", arrayListFreighttypcode10);
        jsonObject.setObject("STATECODE", arrayListStatecode);
        jsonObject.setObject("STATENAME", arrayListStatename);
        jsonObject.setObject("EXPCATEGORY", arrayListExpcategory);
        jsonObject.setObject("LOSTFACTOR", arrayListLostfactor);
        jsonObject.setObject("TEXTINCENG", arrayListTextinceng);
        jsonObject.setObject("EANBARCODE", arrayListEanbarcode);
        jsonObject.setObject("DEPRCLASSTYPE", arrayListDeprclasstype);
        jsonObject.setObject("WFLOWCRDREF", arrayListWflowcrdref);
        jsonObject.setObject("SELLPRVAT", arrayListSellprvat);
        jsonObject.setObject("RETURNPRVAT", arrayListReturnprvat);
        jsonObject.setObject("LOWLEVELCODES1", arrayListLowlevelcodes1);
        jsonObject.setObject("LOWLEVELCODES2", arrayListLowlevelcodes2);
        jsonObject.setObject("LOWLEVELCODES3", arrayListLowlevelcodes3);
        jsonObject.setObject("LOWLEVELCODES4", arrayListLowlevelcodes4);
        jsonObject.setObject("LOWLEVELCODES5", arrayListLowlevelcodes5);
        jsonObject.setObject("LOWLEVELCODES6", arrayListLowlevelcodes6);
        jsonObject.setObject("LOWLEVELCODES7", arrayListLowlevelcodes7);
        jsonObject.setObject("LOWLEVELCODES8", arrayListLowlevelcodes8);
        jsonObject.setObject("LOWLEVELCODES9", arrayListLowlevelcodes9);
        jsonObject.setObject("LOWLEVELCODES10", arrayListLowlevelcodes10);
        jsonObject.setObject("ORGLOGOID", arrayListOrglogoid);
        jsonObject.setObject("QPRODDEPART", arrayListQproddepart);
        jsonObject.setObject("CANCONFIGURE", arrayListCanconfigure);
        jsonObject.setObject("CHARSETREF", arrayListCharsetref);
        jsonObject.setObject("CANDEDUCT", arrayListCandeduct);
        jsonObject.setObject("CONSCODEREF", arrayListConscoderef);
        jsonObject.setObject("SPECODE2", arrayListSpecode2);
        jsonObject.setObject("SPECODE3", arrayListSpecode3);
        jsonObject.setObject("SPECODE4", arrayListSpecode4);
        jsonObject.setObject("SPECODE5", arrayListSpecode5);
        jsonObject.setObject("EXPENSE", arrayListExpense);
        return jsonObject;
    }
    
//*****************************ITMCLSAS*****************************************
    public ArrayList<Itmclsas> getItmclsasArrayList(ResultSet resultSet) {

        ArrayList<Itmclsas> tableValue = new ArrayList<>();
        Itmclsas unit;

        try {
            while (resultSet.next()) {
                unit = new Itmclsas(
                        resultSet.getInt("LOGICALREF"), resultSet.getInt("PARENTREF"), 
                        resultSet.getInt("CHILDREF"), resultSet.getShort("UPLEVEL"), resultSet.getShort("SITEID"), 
                        resultSet.getShort("RECSTATUS"), resultSet.getInt("ORGLOGICREF"));
                tableValue.add(unit);
            }
            return tableValue;
        } catch (SQLException ex) {
            Logger.getLogger(ItemSender.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
            return null;
        }
    }
    
    public JsonObject getItmclsasJson(ArrayList<Itmclsas> list) {
        JsonObject jsonObject = new JsonObject();
        JSONArray logicalRef = new JSONArray();
        JSONArray parentRef = new JSONArray();
        JSONArray childRef = new JSONArray();
        JSONArray upLevel = new JSONArray();
        JSONArray siteId = new JSONArray();
        JSONArray recStatus = new JSONArray();
        JSONArray orgLogicalRef = new JSONArray();

        for (int a = 0; a < list.size(); a++) {

            logicalRef.add(list.get(a).getLogicalRef());
            parentRef.add(list.get(a).getParentRef());
            childRef.add(list.get(a).getChildRef());
            upLevel.add(list.get(a).getUpLevel());
            siteId.add(list.get(a).getSiteId());
            recStatus.add(list.get(a).getRecStatus());
            orgLogicalRef.add(list.get(a).getOrgLogicRef());
        }
        jsonObject.setObject("LOGICALREF", logicalRef);
        jsonObject.setObject("PARENTREF", parentRef);
        jsonObject.setObject("CHILDREF", childRef);
        jsonObject.setObject("UPLEVEL", upLevel);
        jsonObject.setObject("SITEID", siteId);
        jsonObject.setObject("RECSTATUS", recStatus);
        jsonObject.setObject("ORGLOGICREF", orgLogicalRef);

        return jsonObject;
    }

//*****************************ITMUNITA*****************************************    
    public ArrayList<ItmUnitA> getItmUnitAArrayList(ResultSet resultSet) {

        ArrayList<ItmUnitA> tableValue = new ArrayList<>();
        ItmUnitA unitA;

        try {
            while (resultSet.next()) {
                unitA = new ItmUnitA(resultSet.getInt("LOGICALREF"), resultSet.getInt("ITEMREF"),
                        resultSet.getShort("LINENR"), resultSet.getInt("UNITLINEREF"),
                        resultSet.getString("BARCODE"), resultSet.getShort("MTRLCLAS"),
                        resultSet.getShort("PURCHCLAS"), resultSet.getShort("SALESCLAS"),
                        resultSet.getShort("MTRLPRIORITY"), resultSet.getShort("PURCHPRIORTY"),
                        resultSet.getShort("SALESPRIORITY"), resultSet.getInt("WIDTH"),
                        resultSet.getDouble("LENGTH"), resultSet.getDouble("HEIGHT"),
                        resultSet.getDouble("AREA"), resultSet.getDouble("VOLUME_"),
                        resultSet.getDouble("WEIGHT"), resultSet.getInt("WIDTHREF"),
                        resultSet.getInt("LENGTHREF"), resultSet.getInt("HEIGHTREF"),
                        resultSet.getInt("AREAREF"), resultSet.getInt("VOLUMEREF"),
                        resultSet.getInt("WEIGHTREF"), resultSet.getDouble("GROSSVOLUME"),
                        resultSet.getDouble("GROSSWEIGHT"), resultSet.getInt("GROSSVOLREF"),
                        resultSet.getInt("GROSSWGHTREF"), resultSet.getDouble("CONVFACT1"),
                        resultSet.getDouble("CONVFACT2"), resultSet.getInt("EXTACCESSFLAGS"),
                        resultSet.getShort("SITEID"), resultSet.getShort("RECSTATUS"),
                        resultSet.getInt("ORGLOGICREF"), resultSet.getString("BARCODE2"),
                        resultSet.getString("BARCODE3"), resultSet.getString("WBARCODE"),
                        resultSet.getShort("WBARCODESHIFT"), resultSet.getInt("VARIANTREF"));
                tableValue.add(unitA);
            }
            return tableValue;
        } catch (SQLException ex) {
            Logger.getLogger(ItemSender.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
            return null;
        }
    }
    
    public JsonObject getItmUnitAJson(ArrayList<ItmUnitA> list) {
        JsonObject jsonObject = new JsonObject();
        JSONArray logicalRef = new JSONArray();
        JSONArray itemRef = new JSONArray();
        JSONArray lineNr = new JSONArray();
        JSONArray unitLineRef = new JSONArray();
        JSONArray barcode = new JSONArray();
        JSONArray mtrlClas = new JSONArray();
        JSONArray purchClas = new JSONArray();
        JSONArray salesClas = new JSONArray();
        JSONArray mtrlPriority = new JSONArray();
        JSONArray purchPriority = new JSONArray();
        JSONArray salesPriority = new JSONArray();
        JSONArray width = new JSONArray();
        JSONArray length = new JSONArray();
        JSONArray height = new JSONArray();
        JSONArray area = new JSONArray();
        JSONArray volume = new JSONArray();
        JSONArray weight = new JSONArray();
        JSONArray widthRef = new JSONArray();
        JSONArray lengthRef = new JSONArray();
        JSONArray heightRef = new JSONArray();
        JSONArray areaRef = new JSONArray();
        JSONArray volumeRef = new JSONArray();
        JSONArray weightRef = new JSONArray();
        JSONArray crossVolume = new JSONArray();
        JSONArray crossWeight = new JSONArray();
        JSONArray crossVolRef = new JSONArray();
        JSONArray crossWghtRef = new JSONArray();
        JSONArray confact1 = new JSONArray();
        JSONArray confact2 = new JSONArray();
        JSONArray extAccessFlags = new JSONArray();
        JSONArray siteId = new JSONArray();
        JSONArray recStatus = new JSONArray();
        JSONArray orgLogicRef = new JSONArray();
        JSONArray barcode2 = new JSONArray();
        JSONArray barcode3 = new JSONArray();
        JSONArray wBarcode = new JSONArray();
        JSONArray wBarcodeShift = new JSONArray();
        JSONArray variantRef = new JSONArray();

        for (int a = 0; a < list.size(); a++) {
            
            //we save here logRef for later use
            if(list.get(a).getWidth() == 0){
                width.add(a, list.get(a).getLogicalRef());
            }else{
                width.add(list.get(a).getWidth());
            }
            logicalRef.add(list.get(a).getLogicalRef());
            itemRef.add(list.get(a).getItemRef());
            lineNr.add(list.get(a).getLineNr());
            unitLineRef.add(list.get(a).getUnitLineRef());
            barcode.add(list.get(a).getBarcode());
            mtrlClas.add(list.get(a).getMtrlclas());
            purchClas.add(list.get(a).getPurchclas());
            salesClas.add(list.get(a).getSalesclas());
            mtrlPriority.add(list.get(a).getMtrlPriority());
            purchPriority.add(list.get(a).getPurchPriority());
            salesPriority.add(list.get(a).getSalesPriority());
            length.add(list.get(a).getLength());
            height.add(list.get(a).getHeight());
            area.add(list.get(a).getArea());
            volume.add(list.get(a).getVolume());
            weight.add(list.get(a).getWeight());
            widthRef.add(list.get(a).getWidthref());
            lengthRef.add(list.get(a).getLengthref());
            heightRef.add(list.get(a).getHeightref());
            areaRef.add(list.get(a).getArearef());
            volumeRef.add(list.get(a).getVolumeref());
            weightRef.add(list.get(a).getWeightref());
            crossVolume.add(list.get(a).getCrossVolume());
            crossWeight.add(list.get(a).getCrossWeight());
            crossVolRef.add(list.get(a).getCrossVolumeRef());
            crossWghtRef.add(list.get(a).getCrossWeightRef());
            confact1.add(list.get(a).getConvfact1());
            confact2.add(list.get(a).getConvfact2());
            extAccessFlags.add(list.get(a).getExtAccessFlags());
            siteId.add(list.get(a).getSiteId());
            recStatus.add(list.get(a).getRecStatus());
            orgLogicRef.add(list.get(a).getOrgLogicRef());
            barcode2.add(list.get(a).getBarcode2());
            barcode3.add(list.get(a).getBarcode3());
            wBarcode.add(list.get(a).getwBarcode());
            wBarcodeShift.add(list.get(a).getwBarcodeShift());
            variantRef.add(list.get(a).getVariantRef());
        }
        jsonObject.setObject("LOGICALREF", logicalRef);
        jsonObject.setObject("ITEMREF", itemRef);
        jsonObject.setObject("LINENR", lineNr);
        jsonObject.setObject("UNITLINEREF", unitLineRef);
        jsonObject.setObject("BARCODE", barcode);
        jsonObject.setObject("MTRLCLAS", mtrlClas);
        jsonObject.setObject("PURCHCLAS", purchClas);
        jsonObject.setObject("SALESCLAS", salesClas);
        jsonObject.setObject("MTRLPRIORITY", mtrlPriority);
        jsonObject.setObject("PURCHPRIORTY", purchPriority);
        jsonObject.setObject("SALESPRIORITY", salesPriority);
        jsonObject.setObject("WIDTH", width);
        jsonObject.setObject("LENGTH", length);
        jsonObject.setObject("HEIGHT", height);
        jsonObject.setObject("AREA", area);
        jsonObject.setObject("VOLUME_", volume);
        jsonObject.setObject("WEIGHT", weight);
        jsonObject.setObject("WIDTHREF", widthRef);
        jsonObject.setObject("LENGTHREF", lengthRef);
        jsonObject.setObject("HEIGHTREF", heightRef);
        jsonObject.setObject("AREAREF", areaRef);
        jsonObject.setObject("VOLUMEREF", volumeRef);
        jsonObject.setObject("WEIGHTREF", weightRef);
        jsonObject.setObject("GROSSVOLUME", crossVolume);
        jsonObject.setObject("GROSSWEIGHT", crossWeight);
        jsonObject.setObject("GROSSVOLREF", crossVolRef);
        jsonObject.setObject("GROSSWGHTREF", crossWghtRef);
        jsonObject.setObject("CONVFACT1", confact1);
        jsonObject.setObject("CONVFACT2", confact2);
        jsonObject.setObject("EXTACCESSFLAGS", extAccessFlags);
        jsonObject.setObject("SITEID", siteId);
        jsonObject.setObject("RECSTATUS", recStatus);
        jsonObject.setObject("ORGLOGICREF", orgLogicRef);
        jsonObject.setObject("BARCODE2", barcode2);
        jsonObject.setObject("BARCODE3", barcode3);
        jsonObject.setObject("WBARCODE", wBarcode);
        jsonObject.setObject("WBARCODESHIFT", wBarcodeShift);
        jsonObject.setObject("VARIANTREF", variantRef);

        return jsonObject;
    }
    
//*****************************PRCLIST******************************************
    public ArrayList<PrcList> getPrcListArrayList(ResultSet resultSet) {

        ArrayList<PrcList> tableValue = new ArrayList<>();
        PrcList unit;

        try {
            while (resultSet.next()) {
                unit = new PrcList(
                        resultSet.getInt("LOGICALREF"), resultSet.getInt("CARDREF"), 
                        resultSet.getString("CLIENTCODE"), resultSet.getString("CLSPECODE"), 
                        resultSet.getInt("PAYPLANREF"), resultSet.getDouble("PRICE"), 
                        resultSet.getInt("UOMREF"), resultSet.getShort("INCVAT"), 
                        resultSet.getShort("CURRENCY"), resultSet.getShort("PRIORITY"), 
                        resultSet.getShort("PTYPE"), resultSet.getShort("MTRLTYPE"), 
                        resultSet.getInt("LEADTIME"), resultSet.getString("BEGDATE"), //date format
                        resultSet.getString("ENDDATE"), resultSet.getString("CONDITION"), 
                        resultSet.getString("SHIPTYP"), resultSet.getShort("SPECIALIZED"), 
                        resultSet.getShort("CAPIBLOCK_CREATEDBY"), resultSet.getString("CAPIBLOCK_CREADEDDATE"), //date format
                        resultSet.getShort("CAPIBLOCK_CREATEDHOUR"), resultSet.getShort("CAPIBLOCK_CREATEDMIN"), 
                        resultSet.getShort("CAPIBLOCK_CREATEDSEC"), resultSet.getShort("CAPIBLOCK_MODIFIEDBY"), 
/*date format*/         resultSet.getString("CAPIBLOCK_MODIFIEDDATE"), resultSet.getShort("CAPIBLOCK_MODIFIEDHOUR"), 
                        resultSet.getShort("CAPIBLOCK_MODIFIEDMIN"), resultSet.getShort("CAPIBLOCK_MODIFIEDSEC"), 
                        resultSet.getShort("SITEID"), resultSet.getShort("RECSTATUS"), 
                        resultSet.getInt("ORGLOGICREF"), resultSet.getInt("WFSTATUS"), 
                        resultSet.getShort("UNITCONVERT"), resultSet.getInt("EXTACCESSFLAGS"), 
                        resultSet.getString("CYPHCODE"), resultSet.getString("ORGLOGOID"), 
                        resultSet.getString("TRADINGGRP"), resultSet.getInt("BEGTIME"), 
                        resultSet.getInt("ENDTIME"), resultSet.getString("DEFINITION_"), 
                        resultSet.getString("CODE"), resultSet.getString("GRPCODE"), 
                        resultSet.getShort("ORDERNR"), resultSet.getString("GENIUSPAYTYPE"), 
                        resultSet.getInt("GENIUSSHPNR"), resultSet.getShort("PRCALTERTYP1"), 
                        resultSet.getDouble("PRCALTERLMT1"), resultSet.getShort("PRCALTERTYP2"), 
                        resultSet.getDouble("PRCALTERLMT2"), resultSet.getShort("PRCALTERTYP3"), 
                        resultSet.getDouble("PRCALTERLMT3"), resultSet.getShort("ACTIVE"), 
                        resultSet.getInt("PURCHCONTREF"), resultSet.getShort("BRANCH"));
                tableValue.add(unit);
            }
            return tableValue;
        } catch (SQLException ex) {
            Logger.getLogger(ItemSender.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
            return null;
        }
    }
    
    public JsonObject getPrcListJson(ArrayList<PrcList> list) {
        JsonObject jsonObject = new JsonObject();
        JSONArray logicalRef = new JSONArray();
        JSONArray cardRef = new JSONArray();
        JSONArray clientCode = new JSONArray();
        JSONArray clSpecode = new JSONArray();
        JSONArray payplanRef = new JSONArray();
        JSONArray price = new JSONArray();
        JSONArray uomRef = new JSONArray();
        JSONArray inVat = new JSONArray();
        JSONArray currency = new JSONArray();
        JSONArray priority = new JSONArray();
        JSONArray pType = new JSONArray();
        JSONArray mtrlType = new JSONArray();
        JSONArray leadTime = new JSONArray();
        JSONArray begDate = new JSONArray();
        JSONArray endDate = new JSONArray();
        JSONArray condition = new JSONArray();
        JSONArray shipTyp = new JSONArray();
        JSONArray specialized = new JSONArray();
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
        JSONArray unitConvert = new JSONArray();
        JSONArray axtAccessFlags = new JSONArray();
        JSONArray cyphCode = new JSONArray();
        JSONArray orgLogoId = new JSONArray();
        JSONArray tradingGrp = new JSONArray();
        JSONArray begTime = new JSONArray();
        JSONArray endTime = new JSONArray();
        JSONArray definition = new JSONArray();
        JSONArray code = new JSONArray();
        JSONArray grpCode = new JSONArray();
        JSONArray orderNr = new JSONArray();
        JSONArray geniusPayType = new JSONArray();
        JSONArray geniusShpnr = new JSONArray();
        JSONArray prcaltertyp1 = new JSONArray();
        JSONArray prcalterlmt1 = new JSONArray();
        JSONArray prcaltertyp2 = new JSONArray();
        JSONArray prcalterlmt2 = new JSONArray();
        JSONArray prcaltertyp3 = new JSONArray();
        JSONArray prcalterlmt3 = new JSONArray();
        JSONArray active = new JSONArray();
        JSONArray purchcontRef = new JSONArray();
        JSONArray branch = new JSONArray();

        for (int a = 0; a < list.size(); a++) {

            // here we save log ref of PRCLIST for future use
            if(list.get(a).getGeniusshpNr() == 0){
                geniusShpnr.add(a, list.get(a).getLogicalRef());
            }else{
                geniusShpnr.add(list.get(a).getGeniusshpNr());
            }
            code.add(list.get(a).getCode());
            logicalRef.add(list.get(a).getLogicalRef());
            cardRef.add(list.get(a).getCardRef());
            clientCode.add(list.get(a).getClientCode());
            clSpecode.add(list.get(a).getClSpecode());
            payplanRef.add(list.get(a).getPayPlanRef());
            price.add(list.get(a).getPrice());
            uomRef.add(list.get(a).getUomRef());
            inVat.add(list.get(a).getIncVat());
            currency.add(list.get(a).getCurrency());
            priority.add(list.get(a).getPriority());
            pType.add(list.get(a).getpType());
            mtrlType.add(list.get(a).getMtrlType());
            leadTime.add(list.get(a).getLeadTime());
            begDate.add(list.get(a).getBegDate());
            endDate.add(list.get(a).getEndDate());
            condition.add(list.get(a).getCondition());
            specialized.add(list.get(a).getSpecialized());
            shipTyp.add(list.get(a).getShiptyp());
            capiblockCreatedBy.add(list.get(a).getCapiblockCreatedby());
            capiblockCreatedDate.add(list.get(a).getCapiblockCreadeddate());
            capiblockCreatedHour.add(list.get(a).getCapiblockCreatedhour());
            capiblockCreatedMin.add(list.get(a).getCapiblockCreatedmin());
            capiblockCreatedSec.add(list.get(a).getCapiblockCreatedsec());
            capiblockModifiedBy.add(list.get(a).getCapiblockModifiedby());
            capiblockModifiedDate.add(list.get(a).getCapiblockModifieddate());
            capiblockModifiedHour.add(list.get(a).getCapiblockModifiedhour());
            capiblockModifiedMin.add(list.get(a).getCapiblockModifiedmin());
            capiblockModifiedSec.add(list.get(a).getCapiblockModifiedsec());
            siteId.add(list.get(a).getSiteId());
            recStatus.add(list.get(a).getRecStatus());
            orgLogicRef.add(list.get(a).getOrgLogicRef());
            wfStatus.add(list.get(a).getWfSatus());
            unitConvert.add(list.get(a).getUnitConvert());
            axtAccessFlags.add(list.get(a).getExtAccessFlags());
            cyphCode.add(list.get(a).getCyphCode());
            orgLogoId.add(list.get(a).getOrgLogoId());
            tradingGrp.add(list.get(a).getTrdaingGrp());
            begTime.add(list.get(a).getBegTime());
            endTime.add(list.get(a).getEndTime());
            definition.add(list.get(a).getDefinition());
            grpCode.add(list.get(a).getGrpCode());
            orderNr.add(list.get(a).getOrderNr());
            geniusPayType.add(list.get(a).getGeniusPayType());
            prcaltertyp1.add(list.get(a).getPrcaltertyp1());
            prcalterlmt1.add(list.get(a).getPrcalterlmt1());
            prcaltertyp2.add(list.get(a).getPrcaltertyp2());
            prcalterlmt2.add(list.get(a).getPrcalterlmt2());
            prcaltertyp3.add(list.get(a).getPrcaltertyp3());
            prcalterlmt3.add(list.get(a).getPrcalterlmt3());
            active.add(list.get(a).getActive());
            purchcontRef.add(list.get(a).getPurchContRef());
            branch.add(list.get(a).getBranch());
        }
        jsonObject.setObject("LOGICALREF", logicalRef);
        jsonObject.setObject("CARDREF", cardRef);
        jsonObject.setObject("CLIENTCODE", clientCode);
        jsonObject.setObject("CLSPECODE", clSpecode);
        jsonObject.setObject("PAYPLANREF", payplanRef);
        jsonObject.setObject("PRICE", price);
        jsonObject.setObject("UOMREF", uomRef);
        jsonObject.setObject("INCVAT", inVat);
        jsonObject.setObject("CURRENCY", currency);
        jsonObject.setObject("PRIORITY", priority);
        jsonObject.setObject("PTYPE", pType);
        jsonObject.setObject("MTRLTYPE", mtrlType);
        jsonObject.setObject("LEADTIME", leadTime);
        jsonObject.setObject("BEGDATE", begDate);
        jsonObject.setObject("ENDDATE", endDate);
        jsonObject.setObject("CONDITION", condition);
        jsonObject.setObject("SHIPTYP", shipTyp);
        jsonObject.setObject("SPECIALIZED", specialized);
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
        jsonObject.setObject("UNITCONVERT", unitConvert);
        jsonObject.setObject("EXTACCESSFLAGS", axtAccessFlags);
        jsonObject.setObject("CYPHCODE", cyphCode);
        jsonObject.setObject("ORGLOGOID", orgLogoId);
        jsonObject.setObject("TRADINGGRP", tradingGrp);
        jsonObject.setObject("BEGTIME", begTime);
        jsonObject.setObject("ENDTIME", endTime);
        jsonObject.setObject("DEFINITION_", definition);
        jsonObject.setObject("CODE", code);
        jsonObject.setObject("GRPCODE", grpCode);
        jsonObject.setObject("ORDERNR", orderNr);
        jsonObject.setObject("GENIUSPAYTYPE", geniusPayType);
        jsonObject.setObject("GENIUSSHPNR", geniusShpnr);
        jsonObject.setObject("PRCALTERTYP1", prcaltertyp1);
        jsonObject.setObject("PRCALTERLMT1", prcalterlmt1);
        jsonObject.setObject("PRCALTERTYP2", prcaltertyp2);
        jsonObject.setObject("PRCALTERLMT2", prcalterlmt2);
        jsonObject.setObject("PRCALTERTYP3", prcaltertyp3);
        jsonObject.setObject("PRCALTERLMT3", prcalterlmt3);
        jsonObject.setObject("ACTIVE", active);
        jsonObject.setObject("PURCHCONTREF", purchcontRef);
        jsonObject.setObject("BRANCH", branch);

        return jsonObject;
    }

//*****************************UNITBARCODE**************************************    
    public ArrayList<UnitBarcode> getUnitBarcodeArrayList(ResultSet resultSet) {

        ArrayList<UnitBarcode> tableValue = new ArrayList<>();
        UnitBarcode unit;

        try {
            while (resultSet.next()) {
                unit = new UnitBarcode(
                        resultSet.getInt("LOGICALREF"), resultSet.getInt("ITMUNITAREF"), 
                        resultSet.getInt("ITEMREF"), resultSet.getInt("VARIANTREF"), 
                        resultSet.getInt("UNITLINEREF"), resultSet.getShort("LINENR"), 
                        resultSet.getString("BARCODE"), resultSet.getInt("SITEID"), 
                        resultSet.getShort("RECSTATUS"), resultSet.getInt("ORGLOGICREF"));
                tableValue.add(unit);
            }
            return tableValue;
        } catch (SQLException ex) {
            Logger.getLogger(ItemSender.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
            return null;
        }
    }
    
    public JsonObject getUnitBarcodeJson(ArrayList<UnitBarcode> list) {
        JsonObject jsonObject = new JsonObject();
        JSONArray logicalRef = new JSONArray();
        JSONArray itmUnitARef = new JSONArray();
        JSONArray itemRef = new JSONArray();
        JSONArray variantRef = new JSONArray();
        JSONArray unitLineRef = new JSONArray();
        JSONArray lineNr = new JSONArray();
        JSONArray barcode = new JSONArray();
        JSONArray siteId = new JSONArray();
        JSONArray recStatus = new JSONArray();
        JSONArray orgLogicalRef = new JSONArray();

        for (int a = 0; a < list.size(); a++) {

            //here we save log ref of UNITBARCODE for future use
            if(list.get(a).getSiteId() == 0){
                siteId.add(a, list.get(a).getLogicalRef());
            }else{
                siteId.add(list.get(a).getSiteId());
            }
            logicalRef.add(list.get(a).getLogicalRef());
            itmUnitARef.add(list.get(a).getItmUnitARef());
            itemRef.add(list.get(a).getItemRef());
            variantRef.add(list.get(a).getVariantRef());
            unitLineRef.add(list.get(a).getUnitLineRef());
            lineNr.add(list.get(a).getLineNr());
            barcode.add(list.get(a).getBarcode());
            recStatus.add(list.get(a).getRecStatus());
            orgLogicalRef.add(list.get(a).getOrgLogicRef());
        }
        jsonObject.setObject("LOGICALREF", logicalRef);
        jsonObject.setObject("ITMUNITAREF", itmUnitARef);
        jsonObject.setObject("ITEMREF", itemRef);
        jsonObject.setObject("VARIANTREF", variantRef);
        jsonObject.setObject("UNITLINEREF", unitLineRef);
        jsonObject.setObject("LINENR", lineNr);
        jsonObject.setObject("BARCODE", barcode);
        jsonObject.setObject("SITEID", siteId);
        jsonObject.setObject("RECSTATUS", recStatus);
        jsonObject.setObject("ORGLOGICREF", orgLogicalRef);

        return jsonObject;
    }
}
