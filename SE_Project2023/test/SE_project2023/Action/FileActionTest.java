/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package SE_project2023.Action;

import java.io.File;
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
public class FileActionTest {

    public FileActionTest() {
    }

    FileAction a;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        a = new FileAction();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setSourcePath and getSourcePath methods, of class FileAction.
     */
    @Test
    public void testSetGetSourcePath() {
        System.out.println("setSourcePath and getSourcePath");
        a.setSourcePath("C:\\");
        String expResult = "C:\\";
        String result = a.getSourcePath();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDestinationPath and getDestinationPath methods, of class
     * FileAction.
     */
    @Test
    public void testSetGetDestinationPath() {
        System.out.println("setDestinationPath and getDestinationPath");
        a.setDestinationPath("C:\\");
        String expResult = "C:\\";
        String result = a.getDestinationPath();
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
     * Test of fire method, of class FileAction.
     */
    @Test
    public void testFire() {
        System.out.println("fire");
        JFXPanel jfxPanel = new JFXPanel();

        Platform.runLater(() -> {
            a.setSourcePath("C:\\Users\\emanu\\Desktop\\doc.txt");
            a.setDestinationPath("C:\\Users\\emanu\\Downloads\\doc.txt");
            System.out.println("move");
            a.setAction("move");
            a.fire();
            File f = new File(a.getSourcePath());
            assertFalse(f.exists());
            f = new File(a.getDestinationPath());
            assertTrue(f.exists());

            a.setSourcePath("C:\\Users\\emanu\\Downloads\\doc.txt");
            a.setDestinationPath("C:\\Users\\emanu\\Desktop\\doc.txt");
            System.out.println("copy");
            a.setAction("copy");
            a.fire();
            f = new File(a.getSourcePath());
            assertTrue(f.exists());
            f = new File(a.getDestinationPath());
            assertTrue(f.exists());

            System.out.println("remove");
            a.setAction("remove");
            a.fire();
            f = new File(a.getDestinationPath());
            assertFalse(f.exists());
        });
        try {
            Thread.sleep(5000); //devo aspettare che il metodo viene richiamato sul thread e che abbia il tempo di eseguire il test
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test of add method, of class FileAction.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAdd() {
        a.add();
    }

    /**
     * Test of remove method, of class FileAction.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testRemove() {
        a.remove();
    }

    /**
     * Test of getChild method, of class FileAction.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testGetChild() {
        a.getChild();
    }

    /**
     * Test of toString method, of class FileAction.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "FileAction : source : null; destination : null";
        String result = a.toString();
        assertEquals(expResult, result);
    }

}
