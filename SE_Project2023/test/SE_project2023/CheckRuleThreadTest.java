/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package SE_project2023;

import SE_project2023.Action.MessageBoxAction;
import SE_project2023.Regole.Rule;
import SE_project2023.Trigger.DayOfMonthTrigger;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author chris
 */
public class CheckRuleThreadTest {
    private RuleList rules;
    private Thread instance;
    
    public CheckRuleThreadTest() {
    }
    
    @Before
    public void setUp() {
        rules = RuleList.getRuleList();
        instance = new Thread(new CheckRuleThread());
        
    }

    /**
     * Test of run method, of class CheckRuleThread.
     */
    @Test
    public void testRun() {
        System.out.println("run"); 
        Rule r1 = new Rule("test", new MessageBoxAction("test"), new DayOfMonthTrigger(LocalDate.now().getDayOfMonth()));
        rules.add(r1);
        instance.start(); //avvio il mio thread CheckRule
        try {
            Thread.sleep(11000); //mando in sleep il thread principale in modo da fare almeno 1 ciclo di controllo
        } catch (InterruptedException ex) {
            Logger.getLogger(CheckRuleThreadTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        instance.interrupt(); //lo interrompo altrimenti continua sempre  
        assertTrue(r1.getAction().isFired()); //Siccome il thread è verificato, l'azione deve essere eseguita dal thread.
        

    }
    
}
