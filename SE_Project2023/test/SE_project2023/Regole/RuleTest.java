/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package SE_project2023.Regole;

import SE_project2023.Action.*;
import SE_project2023.Trigger.TimeTrigger;
import SE_project2023.Trigger.Trigger;
import java.time.LocalTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author emanu
 */
public class RuleTest {

    Rule r;

    public RuleTest() {
    }

    @Before
    public void setUp() {
        r = new Rule();
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
     * Test of isVerifiedRule method, of class Rule.
     */
    @Test
    public void testIsVerifiedRule() {
        System.out.println("isVerifiedRule");
        boolean expResult = false;
        testSetGetTrigger();
        boolean result = r.isVerifiedRule();
        assertEquals(expResult, result);

        //Valid Rule
        System.out.println("isVerifiedRule");
        expResult = false;
        testSetGetAction();
        result = r.isVerifiedRule();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSleep method, of class Rule.
     */
    @Test
    public void testSetSleep() {
        System.out.println("setSleep");
        Long sleep = null;
        Rule instance = new Rule();
        instance.setSleep(sleep);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAction method, of class Rule.
     */
    @Test
    public void testGetAction() {
        System.out.println("getAction");
        Rule instance = new Rule();
        Action expResult = null;
        Action result = instance.getAction();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTrigger method, of class Rule.
     */
    @Test
    public void testGetTrigger() {
        System.out.println("getTrigger");
        Rule instance = new Rule();
        Trigger expResult = null;
        Trigger result = instance.getTrigger();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Rule.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Rule instance = new Rule();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFlag method, of class Rule.
     */
    @Test
    public void testGetFlag() {
        System.out.println("getFlag");
        Rule instance = new Rule();
        boolean expResult = false;
        boolean result = instance.getFlag();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAction method, of class Rule.
     */
    @Test
    public void testSetAction() {
        System.out.println("setAction");
        Action action = null;
        Rule instance = new Rule();
        instance.setAction(action);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTrigger method, of class Rule.
     */
    @Test
    public void testSetTrigger() {
        System.out.println("setTrigger");
        Trigger trigger = null;
        Rule instance = new Rule();
        instance.setTrigger(trigger);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Rule.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String Name = "";
        Rule instance = new Rule();
        instance.setName(Name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFlag method, of class Rule.
     */
    @Test
    public void testSetFlag() {
        System.out.println("setFlag");
        boolean flag = false;
        Rule instance = new Rule();
        instance.setFlag(flag);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of ruleIsValid method, of class Rule.
     */
    @Test
    public void testRuleIsValid() {
        System.out.println("ruleIsValid");
        Rule instance = new Rule();
        boolean expResult = false;
        boolean result = instance.ruleIsValid();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStatus method, of class Rule.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        Rule instance = new Rule();
        boolean expResult = false;
        boolean result = instance.getStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of active method, of class Rule.
     */
    @Test
    public void testActive() {
        System.out.println("active");
        Rule instance = new Rule();
        instance.active();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deactive method, of class Rule.
     */
    @Test
    public void testDeactive() {
        System.out.println("deactive");
        Rule instance = new Rule();
        instance.deactive();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fire method, of class Rule.
     */
    @Test
    public void testFire() {
        System.out.println("fire");
        Rule instance = new Rule();
        instance.fire();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sleepCheck method, of class Rule.
     */
    @Test
    public void testSleepCheck() {
        System.out.println("sleepCheck");
        Rule instance = new Rule();
        Boolean expResult = null;
        Boolean result = instance.sleepCheck();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of attach method, of class Rule.
     */
    @Test
    public void testAttach() {
        System.out.println("attach");

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFireOnce method, of class Rule.
     */
    @Test
    public void testSetFireOnce() {
        System.out.println("setFireOnce");
        boolean f = false;
        Rule instance = new Rule();
        instance.setFireOnce(f);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSleep method, of class Rule.
     */
    @Test
    public void testGetSleep() {
        System.out.println("getSleep");
        Rule instance = new Rule();
        String expResult = "";
        String result = instance.getSleep();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
