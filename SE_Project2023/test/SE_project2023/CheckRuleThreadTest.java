/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package SE_project2023;

import SE_project2023.Action.MessageBoxAction;
import SE_project2023.Regole.Rule;
import SE_project2023.Trigger.DayOfMonthTrigger;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author chris
 */
public class CheckRuleThreadTest {
    private RuleList rules;
    private Thread instance; //CheckRuleThread
    
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
    @Test (expected = InterruptedException.class)
    public void testRun() throws InterruptedException {
        Rule r1 = new Rule("test", new MessageBoxAction("test"), new DayOfMonthTrigger(LocalDate.now().getDayOfMonth()));
        rules.add(r1);
        instance.start(); //avvio il mio thread CheckRule

        TimeUnit.SECONDS.sleep(30);
        
        instance.interrupt(); //lo interrompo altrimenti continua sempre
        assertFalse(r1.getAction().isFired()); //Siccome il thread è verificato, l'azione deve essere eseguita dal thread.
        

    }
    
}
