/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package SE_project2023.Regole;

import SE_project2023.Action.*;
import SE_project2023.Trigger.TimeTrigger;
import SE_project2023.Trigger.Trigger;
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

    Rule r;

    public RuleTest() {}

  

    @Before
    public void setUp() {
        r = new Rule();
    }

  

    /**
     * Test of setSleep method, of class Rule.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetSleep() {
        System.out.println("setSleep");
        long sleep = -1;
        r.setSleep(sleep); //expected IllegalArgumentException with negative value of sleep

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
        r.setTrigger(new TimeTrigger(LocalTime.now()));
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
        r.setTrigger(null); //Action and trigger = null expected False
        boolean result = r.ruleIsValid();
        assertFalse(result);

        r.setFlag(true);

        r.setAction(new MessageBoxAction("Test"));
        r.setTrigger(null); //Trigger = null but Action setted expected False
        result = r.ruleIsValid();
        assertFalse(result);

        r.setAction(null);
        r.setTrigger(new TimeTrigger(LocalTime.now())); //Action = null but Trigger setted expected False
        result = r.ruleIsValid();
        assertFalse(result);

        r.setAction(new MessageBoxAction("Test"));
        r.setTrigger(new TimeTrigger(LocalTime.now())); //Action and trigger setted expected True
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
        r.setTrigger(new TriggerForTest()); //Trigger is verified and the rule is active expected True
        r.getTrigger().isVerified();
        boolean result = r.isVerifiedRule();
        assertTrue(result);

        r.deactive();
        result = r.isVerifiedRule();
        assertFalse(result); //Trigger is verified but the rule is not active expected False

    }

    /**
     * Test of Active and Deactive method, of class Rule.
     */
    @Test
    public void testActiveDeactive() {
        System.out.println("deactive");

        assertTrue(r.getStatus());//by default the rule is active

        r.deactive(); //deactive the rule, status = false
        assertFalse(r.getStatus());
    }

    /**
     * Test of fire method, of class Rule.
     */
    @Test
    public void testFire() {
        System.out.println("fire");
        r.setAction(new MessageBoxAction("test"));
        assertFalse(r.getAction().isFired()); //rule not fired, expected false

        r.fire();
        assertTrue(r.getAction().isFired()); //action is fired, expected true

        long sleep = 10;
        r.setSleep(sleep);
        r.setAction(new MessageBoxAction("test"));
        r.fire();
        assertTrue(r.getAction().isFired() && r.getWakeUpTime().getMinute()!=0); //action is fired one, and wakeUp time is setted.
    }

    @Test(expected = NullPointerException.class)
    public void testFireNoAction() {
        System.out.println("fire no action");
        r.fire();
    }

    /**
     * Test of sleepCheck method, of class Rule.
     */
    @Test
    public void testSleepCheck() {
        System.out.println("sleepCheck");
        long sleep = 10;
        r.setSleep(sleep);
        r.setAction(new MessageBoxAction("Test"));
        r.fire();
        assertFalse(r.sleepCheck());

    }


    /**
     * Test of setFireOnce method, of class Rule.
     */
    @Test
    public void testSetFireOnce() {
        System.out.println("setFireOnce");

    }

    /*
    *
    */
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
        assertFalse(obs.observed); //observed should be false becouse r didn't update.
        r.deactive();
        assertTrue(obs.observed); //observed is true becouse r updated.

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
