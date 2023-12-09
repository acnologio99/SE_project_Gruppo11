/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023;

import SE_project2023.Regole.Rule;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chris
 */
public class CheckRuleThread implements Runnable {

    private RuleList rules;

    public CheckRuleThread() {
     rules = RuleList.getRuleList();

    }

    @Override
    public void run() {
        while (true) {

            try {
                Thread.sleep(10000); // 10 secondi (10000 millisecondi)
            } catch (InterruptedException ex) {
                Logger.getLogger(CheckRuleThread.class.getName()).log(Level.SEVERE, null, ex);
            }

                // Codice per controllare la lista
                System.out.println("Controllo lista...");
                if(!rules.isEmpty()){
                for (Rule r : rules) {
                    if (r.ruleIsValid() && r.isVerifiedRule()) {
                        r.fire();

                    }
                    else ;
                }
             }


        }
    }
}
