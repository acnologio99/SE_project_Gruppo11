/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023;

import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author giova
 */
public class TriggerControlThread extends Thread{

    @Override
    public void run() {
        RuleList ruleList = RuleList.getRuleList();
        while(true) {
            
            System.out.print("hello");
            try {
                sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(TriggerControlThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
        
}
