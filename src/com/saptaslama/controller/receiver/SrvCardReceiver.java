/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saptaslama.controller.receiver;

import com.saptaslama.controller.sender.Sender;
import com.saptaslama.model.SrvCard;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Shagy
 */
public class SrvCardReceiver extends Sender{
    public ArrayList<SrvCard> getSrvCardArrayList(JSONObject jObject) {

        ArrayList<SrvCard> tableValue = new ArrayList<>();
        SrvCard items;

        JSONArray logicalRef = (JSONArray) jObject.get("LOGICALREF");
        JSONArray specode = (JSONArray) jObject.get("SPECODE");
               

        for (int i = 0; i < specode.size(); i++) {
            items = new SrvCard(Integer.valueOf(logicalRef.get(i).toString()), 
                    specode.get(i).toString());

            tableValue.add(items);
        }
        return tableValue;
    }
}
