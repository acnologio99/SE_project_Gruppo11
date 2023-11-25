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
    public void setUp() throws Exception {
        instance = new ActionMessageBox("");
    }

    /**
     * Test of getMsg method, of class ActionMessageBox.
     */
    @Test
    public void testGetSetMsg(){
        System.out.println("getsetMsg");
        
        /* first test case expected msg*/
        String msg = "Hello";
        instance.setMsg(msg);
        String expResult = "Hello"; 
        String result = instance.getMsg();
        assertEquals(expResult, result);
        
        /*second test case expected "" when msg is null*/
        msg = null;
        instance.setMsg(msg);
        result = instance.getMsg();
        assertEquals("", result);
        
        /*third case with constructor*/
        ActionMessageBox instance2 = new ActionMessageBox(null);
        result = instance.getMsg();
        assertEquals("", result);
        
        
    }

    /**
     * Test of fire method, of class ActionMessageBox.
     */
    @Test
    public void testFire() {
        System.out.println("fire");
        instance.fire();
        assertTrue(instance.isFired());
        
    }

    /**
     * Test of add method, of class ActionMessageBox.
     */
    @Test(expected=UnsupportedOperationException.class)
    public void testAdd() {
        System.out.println("add");
        
        instance.add();
        
    }

    /**
     * Test of remove method, of class ActionMessageBox.
     */
    @Test(expected=UnsupportedOperationException.class)
    public void testRemove() {
        System.out.println("remove");
        
        instance.remove();
        
    }

    /**
     * Test of getChild method, of class ActionMessageBox.
     */
    @Test(expected=UnsupportedOperationException.class)
    public void testGetChild() {
        System.out.println("getChild");
        
        Action expResult = null;
        Action result = instance.getChild();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of toString method, of class ActionMessageBox.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
       
        String expResult = "ActionMessageBox: ";
        String result = instance.toString();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of isFired method, of class ActionMessageBox.
     */
   
    public void testIsFired() {
        System.out.println("isFired");
       
        boolean expResult = false;
        boolean result = instance.isFired();
        assertEquals(expResult, result);
        
    }
    
}