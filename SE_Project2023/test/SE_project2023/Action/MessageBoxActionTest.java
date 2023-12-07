/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package SE_project2023.Action;



import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author chris
 */
public class MessageBoxActionTest {

    MessageBoxAction instance;

    public MessageBoxActionTest() {
    }

    @Before
    public void setUp() {
        instance = new MessageBoxAction("");
    }

    /**
     * Test of getMsg method, of class MessageBoxAction.
     */
    @Test
    public void testGetSetMsg() {
        System.out.println("getsetMsg");

        /* first test case expected msg*/
        String msg = "Helloo";
        instance.setMsg(msg);
        String expResult = "Helloo";
        String result = instance.getMsg();
        assertEquals(expResult, result);

        /*second test case expected "" when msg is null*/
        msg = null;
        instance.setMsg(msg);
        result = instance.getMsg();
        assertEquals("", result);

        /*third case with constructor*/
        MessageBoxAction instance2 = new MessageBoxAction(null);
        result = instance.getMsg();
        assertEquals("",result);

    }

    /**
     * Test of fire method, of class MessageBoxAction.
     */
    @Test
    public void testFire() {
        
        System.out.println("fire");
        boolean expResult = false;
        boolean result = instance.isFired();
        assertEquals(expResult, result);
    }

    /**
     * Test of add method, of class MessageBoxAction.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAdd() {
        System.out.println("add");

        instance.add();

    }

    /**
     * Test of remove method, of class MessageBoxAction.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testRemove() {
        System.out.println("remove");

        instance.remove();

    }

    /**
     * Test of getChild method, of class MessageBoxAction.
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testGetChild() {
        System.out.println("getChild");

        Action expResult = null;
        Action result = instance.getChild();
        assertEquals(expResult, result);

    }

    /**
     * Test of toString method, of class MessageBoxAction.
     */
    @Test
    public void testToString() {
        System.out.println("toString");

        String expResult = "ActionMessageBox: ";
        String result = instance.toString();
        assertEquals(expResult, result);

    }

    /**
     * Test of isFired method, of class MessageBoxAction.
     */
    @Test //the method is tested in testFire() too;
    public void testIsFired() {
        System.out.println("isFired");

        boolean expResult = false;
        boolean result = instance.isFired();
        assertEquals(expResult, result);

    }


  

}
