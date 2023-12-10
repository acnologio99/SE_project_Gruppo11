/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package SE_project2023.Regole;

import SE_project2023.Action.*;
import SE_project2023.Tool.FireMultipleVerified;
import SE_project2023.Tool.FireOnceVerified;
import SE_project2023.Tool.VerifiedTool;
import SE_project2023.Trigger.TimeTrigger;
import SE_project2023.Trigger.Trigger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Observable;
import java.util.Observer;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author emanu
 */
public class RuleTest {
    
    private Rule r;
    
    public RuleTest() {}
    
    
    @Before
    public void setUp() {
        r = new Rule();
        r.setVerifiedTool(new FireMultipleVerified()); //imposto lo stato
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testSetSleep() {
        System.out.println("setSleep");
        long sleep = -1;
        r.setSleep(sleep); //expected IllegalArgumentException con valore sleep negativo.

    }
    
    /**
     * Test of setAction and getAction method, of class Rule.
     */
    @Test
    public void testSetGetAction() {
        System.out.println("setAction and getAction");
        Action expResult = new AudioAction();
        r.setAction(expResult);
        Action result = r.getAction();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTrigger and getTrigger method, of class Rule.
     */
    @Test
    public void testSetGetTrigger() {
        System.out.println("setTrigger and getTrigger");
        Trigger expResult = new TimeTrigger(LocalTime.now());
        r.setTrigger(expResult);
        Trigger result = r.getTrigger();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Rule.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        r.setName("Nome");
        String expResult = "Rule : Nome; Action : null; Trigger : null; Status : true";
        String result = r.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName getName method, of class Rule.
     */
    @Test
    public void testSetGetName() {
        System.out.println("setName and getName");
        r.setName("Nome");
        String expResult = "Nome";
        String result = r.getName();
        assertEquals(expResult, result);
    }
    /**
     * Test of ruleIsValid method, of class Rule.
     */
    @Test
    public void testRuleIsValid() {
        System.out.println("ruleIsValid");
        r.setAction(null);
        r.setTrigger(null); //Action e trigger = null expected False
        boolean result = r.ruleIsValid();
        assertFalse(result);
        
        r.setAction(new MessageBoxAction("Test"));
        r.setTrigger(null); //Trigger = null ma Action!=null expected False
        result = r.ruleIsValid();
        assertFalse(result);
        
        r.setAction(null);
        r.setTrigger(new TimeTrigger(LocalTime.now())); //Action = null ma Trigger !=null expected False
        result = r.ruleIsValid();
        assertFalse(result);
        
        r.setAction(new MessageBoxAction("Test"));
        r.setTrigger(new TimeTrigger(LocalTime.now())); //Action and trigger !=null expected True
        result = r.ruleIsValid();
        assertTrue(result);
    }
    /**
     * Test of isVerifiedRule method, of class Rule.
     */
    @Test
    public void testIsVerifiedRule() {
        System.out.println("isVerifiedRule");
        r.setAction(new MessageBoxAction("Test"));
        r.setTrigger(new TriggerForTest()); //Trigger è verificato e la regola è attiva expected True
        r.getTrigger().isVerified();
        boolean result = r.isVerifiedRule();
        assertTrue(result);
        
        r.deactive();
        result = r.isVerifiedRule();
        assertFalse(result); //Trigger è verificato ma la regola non è attiva expected False
        
        r.active();
        VerifiedTool fireOnce = new FireOnceVerified();
        r.setVerifiedTool(fireOnce); //imposto lo stato a fireOnce
        result = r.isVerifiedRule();
        assertTrue(result); //L'azione non è stata eseguita, ma la regola è attiva e il trigger verificato. La regola deve essere eseguita.
        
        r.fire();
        result = r.isVerifiedRule();
        assertFalse(result); //L'azione è stata eseguita, non deve essere eseguita di nuovo dato che lo stato è FireOnce, expectedFalse.

    }
    @Test
    public void testActiveDeactive() {
        System.out.println("deactive");
        assertTrue(r.getStatus());//di default la regola è attiva
        r.deactive(); //disattivo la regola, status = false
        assertFalse(r.getStatus());
    }
     /**
     * Test of fire method, of class Rule.
     * */
    @Test
     public void testFire() {
        System.out.println("fire");
        r.setAction(new MessageBoxAction("test"));
        assertFalse(r.getAction().isFired()); //regola non eseguita, expected false
        
        r.fire();
        assertTrue(r.getAction().isFired()); //l'azione è stata eseguita, expected true
        long sleep = 10;
        r.setSleep(sleep);
        r.setAction(new MessageBoxAction("test"));
        r.fire();
        assertTrue(r.getAction().isFired() && r.getWakeUp().getMinute()!=0); //action è fire one, and wakeUp time è impostato
     }
     @Test(expected = NullPointerException.class)
    public void testFireNoAction() {
        System.out.println("fire no action");
        r.fire();
    }

    @Test
    public void observableTest(){
        class InnerObserver implements Observer {
            boolean observed = false;
            @Override
            public void update(Observable o, Object arg) {
                observed = true;
            }
        }
        InnerObserver obs = new InnerObserver();
        r.addObserver(obs);
        
        assertFalse(obs.observed); //observed deve essere falso perchè r non si è aggiornata.
        r.deactive();
        assertTrue(obs.observed); //observed deve essere true perchè r si è aggiornata.
    }


    @Test
    public void testGetSleep() {
        System.out.println("getSleep");
        Rule instance = new Rule();
        long expResult = 0;
        long result = instance.getSleep();
        assertTrue(expResult == result);
        
    }

    
}

