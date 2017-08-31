/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saptaslama.controller.receiver;

import com.saptaslama.controller.sender.Sender;
import com.saptaslama.model.Items;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Shagy
 */
public class ItemsReceiver extends Sender{
    public ArrayList<Items> getItemsArrayList(JSONObject jObject) {

        ArrayList<Items> tableValue = new ArrayList<>();
        Items items;

        JSONArray logicalRef = (JSONArray) jObject.get("LOGICALREF");
        JSONArray arrayListIsonr = (JSONArray) jObject.get("ISONR");

        for (int i = 0; i < arrayListIsonr.size(); i++) {
            items = new Items(Integer.valueOf(logicalRef.get(i).toString()), 
                    arrayListIsonr.get(i).toString());

            tableValue.add(items);
        }
        return tableValue;
    }
}
