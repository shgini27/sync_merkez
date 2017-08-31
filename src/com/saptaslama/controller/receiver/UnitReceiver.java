/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saptaslama.controller.receiver;

import com.saptaslama.controller.sender.Sender;
import com.saptaslama.model.UnitSetF;
import com.saptaslama.model.UnitSetL;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Shagy
 */
public class UnitReceiver extends Sender{
    public ArrayList<UnitSetF> getUnitSetFArrayList(JSONObject jObject) {

        ArrayList<UnitSetF> tableValue = new ArrayList<>();
        UnitSetF unitSet;

        JSONArray logicalRef = (JSONArray) jObject.get("LOGICALREF");
        JSONArray arraySpeCode = (JSONArray) jObject.get("SPECODE");

        for (int i = 0; i < arraySpeCode.size(); i++) {
            unitSet = new UnitSetF(Integer.valueOf(logicalRef.get(i).toString()), 
                    arraySpeCode.get(i).toString());

            tableValue.add(unitSet);
        }

        return tableValue;
    }
    
    public ArrayList<UnitSetL> getUnitSetLArrayList(JSONObject jObject) {

        ArrayList<UnitSetL> tableValue = new ArrayList<>();
        UnitSetL unitSet;

        JSONArray logicalRef = (JSONArray) jObject.get("LOGICALREF");
        JSONArray arrayWidth = (JSONArray) jObject.get("WIDTH");

        for (int i = 0; i < arrayWidth.size(); i++) {
            unitSet = new UnitSetL(
                    Integer.parseInt(logicalRef.get(i).toString()),
                    Integer.parseInt(arrayWidth.get(i).toString()));

            tableValue.add(unitSet);
        }
        return tableValue;
    }
}
