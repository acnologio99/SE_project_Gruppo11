package SE_project2023.Action;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
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
        boolean expResult = false;
        boolean result = a.isFired();
        assertEquals(expResult, result);

    }

    /**
     * Test of fire method, of class ActionAudio.
     */
    @Test
    public void testFire() {
        JFXPanel jfxPanel = new JFXPanel();
        System.out.println("fire");
        boolean expResult = false;
        boolean result = a.isFired();
        assertEquals(expResult, result);

        Platform.runLater(() -> {
            a.setPath("C:/Users/emanu/Desktop/some.mp3");//custom path file audio
            a.fire();
            assertTrue(a.isFired());
        });
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Test of toString method, of class ActionAudio.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ActionAudio instance = new ActionAudio();
        String expResult = "ActionAudio : null";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of add method, of class ActionAudio.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAdd() {
        System.out.println("add");
        a.add();
    }

    /**
     * Test of remove method, of class ActionAudio.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testRemove() {
        System.out.println("remove");
        a.remove();
    }

    /**
     * Test of getChild method, of class ActionAudio.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testGetChild() {
        System.out.println("getChild");
        Action expResult = null;
        Action result = a.getChild();
        assertEquals(expResult, result);
    }

}
