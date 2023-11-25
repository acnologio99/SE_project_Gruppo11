/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package se_project2023.Action;

import SE_project2023.Action.Action;
import SE_project2023.Action.ActionMessageBox;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author chris
 */
public class ActionMessageBoxTest {
    ActionMessageBox instance;
    
    public ActionMessageBoxTest() {
    }
    
    @Before
    public void setUp() {
        instance = new ActionMessageBox("");
    }

    /**
     * Test of getMsg method, of class ActionMessageBox.
     */
    @Test
    public void testGetMsg() {
        System.out.println("getMsg");
       
        String expResult = "";
        String result = instance.getMsg();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMsg method, of class ActionMessageBox.
     */
    @Test
    public void testSetMsg() {
        System.out.println("setMsg");
        String msg = "";
        ActionMessageBox instance = null;
        instance.setMsg(msg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fire method, of class ActionMessageBox.
     */
    @Test
    public void testFire() {
        System.out.println("fire");
        ActionMessageBox instance = null;
        instance.fire();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class ActionMessageBox.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        ActionMessageBox instance = null;
        instance.add();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class ActionMessageBox.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        ActionMessageBox instance = null;
        instance.remove();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getChild method, of class ActionMessageBox.
     */
    @Test
    public void testGetChild() {
        System.out.println("getChild");
        ActionMessageBox instance = null;
        Action expResult = null;
        Action result = instance.getChild();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class ActionMessageBox.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ActionMessageBox instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isFired method, of class ActionMessageBox.
     */
    @Test
    public void testIsFired() {
        System.out.println("isFired");
        ActionMessageBox instance = null;
        boolean expResult = false;
        boolean result = instance.isFired();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
