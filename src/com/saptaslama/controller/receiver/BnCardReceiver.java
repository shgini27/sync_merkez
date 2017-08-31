/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saptaslama.controller.receiver;

import com.saptaslama.controller.sender.Sender;
import com.saptaslama.model.BankAcc;
import com.saptaslama.model.BnCard;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Shagy
 */
public class BnCardReceiver extends Sender{
    public ArrayList<BnCard> getBnCardArrayList(JSONObject jObject) {

        ArrayList<BnCard> tableValue = new ArrayList<>();
        BnCard items;

        JSONArray logicalRef = (JSONArray) jObject.get("LOGICALREF");
        JSONArray specode = (JSONArray) jObject.get("SPECODE");

        for (int i = 0; i < specode.size(); i++) {
            items = new BnCard(Integer.valueOf(logicalRef.get(i).toString()), 
                    specode.get(i).toString());

            tableValue.add(items);
        }
        return tableValue;
    }
    
    public ArrayList<BankAcc> getBankAccArrayList(JSONObject jObject) {

        ArrayList<BankAcc> tableValue = new ArrayList<>();
        BankAcc items;

        JSONArray logicalRef = (JSONArray) jObject.get("LOGICALREF");
        JSONArray specode = (JSONArray) jObject.get("SPECODE");

        for (int i = 0; i < specode.size(); i++) {
            items = new BankAcc(Integer.valueOf(logicalRef.get(i).toString()), 
                    specode.get(i).toString());

            tableValue.add(items);
        }
        return tableValue;
    }
}
