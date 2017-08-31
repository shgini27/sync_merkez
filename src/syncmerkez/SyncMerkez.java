/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package syncmerkez;

import com.saptaslama.controller.MyLog;
import com.saptaslama.controller.SendScheduledTask;
import com.saptaslama.controller.SyncDepoScheduledTask;
import com.saptaslama.controller.SyncMagazaScheduledTask;
import com.saptaslama.view.SyncMerkezView;
import java.util.Timer;

/**
 *
 * @author Shagy
 */
public class SyncMerkez {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MyLog.initLog();
        
//        Timer time = new Timer(); // Instantiate Timer Object
//        Timer time2 = new Timer(); // Instantiate Timer Object
//        Timer time3 = new Timer(); // Instantiate Timer Object
//        SendScheduledTask sendData = new SendScheduledTask(); // Instantiate SheduledTask class
//        SyncDepoScheduledTask syncDepo = new SyncDepoScheduledTask();
//        SyncMagazaScheduledTask syncMagaza = new SyncMagazaScheduledTask();
//        time.schedule(sendData, 0, 1000 * 60 * 30); // Create Repetitively task for every 1 secs
//        time2.schedule(syncDepo, 1000 * 60 * 15, 1000 * 60 * 30);
//        time3.schedule(syncMagaza, 1000 * 60 * 25, 1000 * 60 * 30);

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
                    .getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SyncMerkezView.class.getName())
                    .log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new SyncMerkezView().setVisible(true);
        });

    }

}
