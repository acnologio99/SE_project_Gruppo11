/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package SE_project2023.Action.FileAction;

import java.io.File;
import java.io.IOException;
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
    File file;

    @BeforeClass
    public static void setUpClass() {
        
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before//inizializza e crea gli oggetti necessari per i test
    public void setUp() throws IOException {
        a = new FileAction();
        file = new File("data\\doc.txt");
        file.createNewFile();
    }

    @After//elimina il file creato per il test
    public void tearDown() {
        file.delete();
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
     * Verifica che le varie strategy impostate per FileAction eseguano correttamente 
     * l'azione desiderata, o non viene eseguita nel caso null.
     */
    @Test
    public void testFire() {
        System.out.println("fire");
        JFXPanel jfxPanel = new JFXPanel();

        Platform.runLater(() -> {
            a.setSourcePath(file.getPath());
            a.setDestinationPath("\\");
            System.out.println("move");
            a.setFileStrategy(new MoveStrategy());
            a.fire();
            assertFalse(file.exists());
            file = new File(a.getDestinationPath());
            assertTrue(file.exists());

            a.setSourcePath(file.getPath());
            a.setDestinationPath(".\\data\\");
            System.out.println("copy");
            a.setFileStrategy(new CopyStrategy());
            a.fire();
            assertTrue(file.exists());
            assertTrue(file.exists());

            System.out.println("remove");
            a.setFileStrategy(new RemoveStrategy());
            a.fire();
            file = new File(a.getSourcePath());
            assertFalse(file.exists());
            
            //Caso null non fa execute
            System.out.println("null");
            a = new FileAction();
            a.fire();
            assertFalse(a.isFired());
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
        String expResult = "source : null; destination : null";
        String result = a.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFileStrategy and getFileStrategy methods, of class FileAction.
     */
    @Test
    public void testSetGetFileStrategy() {
        System.out.println("setFileStrategy and getFileStrategy");
        FileStrategy expResult = new CopyStrategy();
        a.setFileStrategy(expResult);
        FileStrategy result = a.getFileStrategy();
        assertEquals(expResult, result);
    }

}
