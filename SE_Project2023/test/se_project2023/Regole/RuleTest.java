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
    
    public RuleTest() {}
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        r = new Rule();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setAction and getAction method, of class Rule.
     */
    @Test
    public void testSetGetAction() {
        System.out.println("setAction and getAction");
        Action expResult = new ActionAudio();
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
        String expResult = "Rule : Nome; Action : null; Trigger : null";
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
    
}
