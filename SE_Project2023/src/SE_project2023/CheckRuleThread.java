/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023;

import SE_project2023.Regole.Rule;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Thread utilizzato per effetturare il controllo periodico sulle regole.
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
            if (!rules.isEmpty()) {
                for (Rule r : rules) {
                    if (r.ruleIsValid() && r.isVerifiedRule()) { //Se la regola è valida ovvero se ha sia azione che trigger, e se le condizioni 
                        r.fire();                                //per cui deve essere eseguita sono verificate (come la sleep, o il trigger) allora questa viene eseguita.   
                    } else ;
                }
            }
        }
    }
}
