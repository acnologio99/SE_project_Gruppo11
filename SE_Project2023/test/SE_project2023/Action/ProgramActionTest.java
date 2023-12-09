/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package SE_project2023.Action;

import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cauro
 */
public class ProgramActionTest {
    
    public ProgramActionTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of isFired method, of class ProgramAction.
     */
    @Test
    public void testIsFired() {
        System.out.println("isFired");
        ProgramAction instance = null;
        boolean expResult = false;
        boolean result = instance.isFired();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fire method, of class ProgramAction.
     */
    @Test
    public void testFire() {
        String programPath = "C:\\Users\\cauro\\OneDrive\\Documenti\\NetBeansProjects\\JavaFXApplication5\\createfile.exe";
        List<String> commandLineArgs = Arrays.asList("1");

        ProgramAction programAction = new ProgramAction(programPath, commandLineArgs);
        programAction.fire();

        // Verifica che l'azione sia stata eseguita con successo
        assertTrue(programAction.isFired());

        // Verifica che ci sia stato output
        assertNotNull(programAction.getOutput());
        assertTrue(!programAction.getOutput().isEmpty());
    }
    
      
    /**
     * Test of getProgramPath method, of class ProgramAction.
     */
    @Test
    public void testGetProgramPath() {
        System.out.println("getProgramPath");
        ProgramAction instance = null;
        String expResult = "";
        String result = instance.getProgramPath();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setProgramPath method, of class ProgramAction.
     */
    @Test
    public void testSetProgramPath() {
        System.out.println("setProgramPath");
        String programPath = "";
        ProgramAction instance = null;
        instance.setProgramPath(programPath);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class ProgramAction.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        ProgramAction instance = null;
        instance.add();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class ProgramAction.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        ProgramAction instance = null;
        instance.remove();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getChild method, of class ProgramAction.
     */
    @Test
    public void testGetChild() {
        System.out.println("getChild");
        ProgramAction instance = null;
        Action expResult = null;
        Action result = instance.getChild();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
