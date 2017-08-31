/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saptaslama.controller.receiver;

import com.saptaslama.controller.sender.Sender;
import com.saptaslama.model.BnFiche;
import com.saptaslama.model.BnfLine;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Shagy
 */
public class BnFicheReceiver extends Sender{
    
    public ArrayList<BnFiche> getBnFicheArrayList(JSONObject jObject) {

        ArrayList<BnFiche> tableValue = new ArrayList<>();
        BnFiche card;

        JSONArray logicalRef = (JSONArray) jObject.get("LOGICALREF");
        JSONArray specode = (JSONArray) jObject.get("SPECODE");

        for (int i = 0; i < logicalRef.size(); i++) {
            card = new BnFiche(Integer.parseInt(logicalRef.get(i).toString()), specode.get(i).toString());

            tableValue.add(card);
        }
        return tableValue;
    }
    
    public ArrayList<BnfLine> getBnfLineArrayList(JSONObject jObject) {

        ArrayList<BnfLine> tableValue = new ArrayList<>();
        BnfLine items;

        JSONArray logicalRef = (JSONArray) jObject.get("LOGICALREF");
        JSONArray specode = (JSONArray) jObject.get("SPECODE");

        for (int i = 0; i < logicalRef.size(); i++) {
            items = new BnfLine(Integer.parseInt(logicalRef.get(i).toString()), specode.get(i).toString());

            tableValue.add(items);
        }
        return tableValue;
    }
}
