/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package SE_project2023.Action;

import java.io.File;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author chris
 */
public class FileAppendActionTest {

    private FileAppendAction instance;

    public FileAppendActionTest() {

    }

    @Before
    public void setUp() {
        instance = new FileAppendAction("data/TestFile.txt", "msg"); 
    }

    /**
     * Test of isFired method, of class FileAppendAction.
     */
    @Test
    public void testIsFired() {
        System.out.println("isFired");
        boolean result = instance.isFired();
        assertFalse(result);

        instance.fire();
        result = instance.isFired();
        assertTrue(result);

    }
    
    /**
     * Test of fire method, of class FileAppendAction.
     */
    @Test
    public void testFire() {
        System.out.println("fire");
        File file = new File("emptyFile.txt");
        FileAppendAction fl = new FileAppendAction(file.getPath(), "test");
        assertEquals(0, file.length()); //Testo che il file appena creato sia vuoto.

        fl.fire();
        assertTrue(file.length() != 0); //dopo aver fatto l'append controllo di aver effettivamente scritto sul file

        file.deleteOnExit(); //cancello il file

    }

    /**
     * Test of add method, of class FileAppendAction.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAdd() {
        System.out.println("add");
        instance.add();

    }

    /**
     * Test of remove method, of class FileAppendAction.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testRemove() {
        System.out.println("remove");
        instance.remove();

    }

    /**
     * Test of getChild method, of class FileAppendAction.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testGetChild() {
        System.out.println("getChild");
        instance.getChild();

    }

    /**
     * Test of getPath method, of class FileAppendAction.
     */
    @Test
    public void testGetSetPath() {
        System.out.println("getSetPath");
        instance.setPath("emptyFile.txt");
        assertEquals("emptyFile.txt", instance.getPath());

    }

    /**
     * Test of getMsg method, of class FileAppendAction.
     */
    @Test
    public void testGetSetMsg() {
        System.out.println("getSetMsg");
        instance.setMsg("test");
        assertEquals("test", instance.getMsg());
    }

}
