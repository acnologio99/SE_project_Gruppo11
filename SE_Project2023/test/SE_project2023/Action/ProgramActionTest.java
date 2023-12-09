/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package SE_project2023.Action;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cauro
 */
public class ProgramActionTest {
    ProgramAction instance;
    ArrayList<String> commands;
    public ProgramActionTest() {
    }
    
    @Before
    public void setUp() {
        commands=new ArrayList<>();
        commands.add("1");
        instance = new ProgramAction("",commands);
        
    }

    /**
     * Test of isFired method, of class ProgramAction.
     */
    @Test
    public void testIsFired() {
        System.out.println("isFired");

        boolean expResult = false;
        boolean result = instance.isFired();
        assertEquals(expResult, result);
    }

    /**
     * Test of fire method, of class ProgramAction.
     */
    @Test
    public void testFire() {
        instance.setProgramPath(".\\data\\createfile.exe");

        instance.fire();

        // Verifica che l'azione sia stata eseguita con successo
        assertTrue(instance.isFired());

        // Verifica che ci sia stato output
        assertNotNull(instance.getOutput());
        assertTrue(!instance.getOutput().isEmpty());
    }
    
      
    /**
     * Test of getProgramPath method, of class ProgramAction.
     */
    @Test
    public void testGetProgramPath() {
        System.out.println("getProgramPath");
        instance.setProgramPath("cia");
        String expResult = "cia";
        String result = instance.getProgramPath();
        assertEquals(expResult, result);
    }

    /**
     * Test of setProgramPath method, of class ProgramAction.
     */
    

    /**
     * Test of add method, of class ProgramAction.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAdd() {
        System.out.println("add");

        instance.add();
    }

    /**
     * Test of remove method, of class ProgramAction.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testRemove() {
         System.out.println("remove");

        instance.remove();
    }

    /**
     * Test of getChild method, of class ProgramAction.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testGetChild() {
        System.out.println("getChild");

        Action expResult = null;
        Action result = instance.getChild();
        assertEquals(expResult, result);
    }
    
}
