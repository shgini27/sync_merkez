/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.saptaslama.controller.sync;

import com.saptaslama.controller.CompanyConstants;
import com.saptaslama.controller.DbConnection;
import com.saptaslama.controller.receiver.ItemsReceiver;
import com.saptaslama.model.Items;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author Shagy
 */
public class SyncItems {

    private ItemsReceiver items;

    //using local date for manupulation
    private DbConnection dbLocal;

    //items
    private final String tableNameItems = CompanyConstants.COMPANY + "ITEMS";
    private final String fileNameItems = "Items.json";
    private final String filePath;

    private Connection connection;

    public SyncItems(ItemsReceiver items, String filePath) {

        this.items = items;

        this.filePath = filePath;

        dbLocal = new DbConnection();
    }

    //initialize metod
    public void init() {
        //geting connection to local db
        connection = dbLocal.getConnection();

        //sync ITEMS table
        syncItem(items.getItemsArrayList(items.getJsonObjectFromFile(filePath + fileNameItems)));

        //CLOSE ALL CONNECTIONS
        dbLocal.closeConnection(connection);
        dbLocal = null;
        items = null;
    }

    // metod to sync data from file to local db
    public void syncItem(ArrayList<Items> itemCloud) {
        for (int i = 0; i < itemCloud.size(); i++) {

            int itemLogRef = dbLocal.getPrimaryKeyInt(connection, tableNameItems,
                    String.valueOf(itemCloud.get(i).getIsonr()), "LOGICALREF", "LOGICALREF");

            if (itemLogRef != 0) {
                dbLocal.updateSource(connection, tableNameItems, "ISONR",
                        itemCloud.get(i).getLogicalref(), itemLogRef);
            } else {
                System.err.println("INSERT ITEMS IS NOT ALLOWED!!!");
            }
        }
    }
}
