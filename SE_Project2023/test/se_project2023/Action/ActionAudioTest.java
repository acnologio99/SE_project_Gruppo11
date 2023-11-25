
package SE_project2023.Action;

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
public class ActionAudioTest {
    
    ActionAudio a;
    
    public ActionAudioTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        a = new ActionAudio();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of setPath and getPath method, of class ActionAudio.
     */
    @Test
    public void testSetGetPath() {
        System.out.println("setPath and getPath");
        a.setPath("C:\\");
        String expResult = "C:\\";
        String result = a.getPath();
        assertEquals(expResult, result);
    }

    /**
     * Test of isFired method, of class ActionAudio.
     */
    @Test
    public void testIsFired() {
        System.out.println("isFired");
        ActionAudio instance = new ActionAudio();
        boolean expResult = false;
        boolean result = instance.isFired();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class ActionAudio.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ActionAudio instance = new ActionAudio();
        String expResult = "ActionAudio";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of add method, of class ActionAudio.
     */
    @Test
    public void testAdd() {
        /*System.out.println("add");
        ActionAudio instance = new ActionAudio();
        instance.add();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }

    /**
     * Test of remove method, of class ActionAudio.
     */
    @Test
    public void testRemove() {
       /* System.out.println("remove");
        ActionAudio instance = new ActionAudio();
        instance.remove();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }

    /**
     * Test of getChild method, of class ActionAudio.
     */
    @Test
    public void testGetChild() {
        /*System.out.println("getChild");
        ActionAudio instance = new ActionAudio();
        Action expResult = null;
        Action result = instance.getChild();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }
    
}
