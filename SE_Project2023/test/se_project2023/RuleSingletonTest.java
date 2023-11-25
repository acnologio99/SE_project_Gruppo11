
package SE_project2023;

import SE_project2023.Action.*;
import SE_project2023.Regole.Rule;
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
public class RuleSingletonTest {
    
    RuleSingleton r;
    
    public RuleSingletonTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        r = RuleSingleton.getInstance();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setName method, of class RuleSingleton.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        r.setName("Nome");
        String expResult = "Nome";
        String result = r.getRule().getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setAction method, of class RuleSingleton.
     */
    @Test
    public void testSetAction() {
        System.out.println("setAction and getAction");
        Action expResult = new ActionAudio();
        r.setAction(expResult);
        Action result = r.getRule().getAction();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTrigger method, of class RuleSingleton.
     */
    @Test
    public void testSetTrigger() {
        System.out.println("setTrigger");
        Trigger expResult = new TimeTrigger(LocalTime.now());
        r.setTrigger(expResult);
        Trigger result = r.getRule().getTrigger();
        assertEquals(expResult, result);
    }

    /**
     * Test of getRule method, of class RuleSingleton.
     */
    @Test
    public void testGetRule() {
        System.out.println("getRule");
        Rule result = r.getRule();
        assertNotNull(result);
    }

    /**
     * Test of clearRule method, of class RuleSingleton.
     */
    @Test
    public void testClearRule() {
        System.out.println("clearRule");
        r.setAction(new ActionAudio("C:\\"));
        r.setTrigger(new TimeTrigger(LocalTime.now()));
        r.clearRule();
        assertNull(r.getRule().getAction());
        assertNull(r.getRule().getTrigger());
    }

    /**
     * Test of getInstance method, of class RuleSingleton.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        RuleSingleton expResult = r;
        RuleSingleton result = RuleSingleton.getInstance();
        assertEquals(expResult, result);
    }

    /**
     * Test of isValid method, of class RuleSingleton.
     */
    @Test
    public void testIsValid() {
        System.out.println("isValid");
        boolean expResult = true;
        r.setAction(new ActionAudio("C:\\"));
        r.setTrigger(new TimeTrigger(LocalTime.now()));
        boolean result = r.isValid();
        assertEquals(expResult, result);
    }
}
