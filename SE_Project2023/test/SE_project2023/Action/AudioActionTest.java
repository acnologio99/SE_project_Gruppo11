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
public class AudioActionTest {

    AudioAction a;

    public AudioActionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        a = new AudioAction();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setPath and getPath method, of class AudioAction.
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
     * Test of isFired method, of class AudioAction.
     */
    @Test
    public void testIsFired() {
        System.out.println("isFired");
        boolean expResult = false;
        boolean result = a.isFired();
        assertEquals(expResult, result);

    }

    /**
     * Test of fire method, of class AudioAction.
     */
    @Test
    public void testFire() {
        System.out.println("fire");
        boolean expResult = false;
        boolean result = a.isFired();
        assertEquals(expResult, result);

        a.setPath("data/song02.wav");
        a.fire();
        assertTrue(a.isFired());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Test of toString method, of class AudioAction.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        AudioAction instance = new AudioAction();
        String expResult = "Audio : null";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of add method, of class AudioAction.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAdd() {
        System.out.println("add");
        a.add();
    }

    /**
     * Test of remove method, of class AudioAction.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testRemove() {
        System.out.println("remove");
        a.remove();
    }

    /**
     * Test of getChild method, of class AudioAction.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testGetChild() {
        System.out.println("getChild");
        Action expResult = null;
        Action result = a.getChild();
        assertEquals(expResult, result);
    }

}
