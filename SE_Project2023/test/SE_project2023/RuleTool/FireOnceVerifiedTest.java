/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package SE_project2023.RuleTool;

import SE_project2023.RuleTool.FireOnceVerified;
import SE_project2023.Action.Action;
import SE_project2023.Action.MessageBoxAction;
import SE_project2023.Regole.Rule;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author giova
 */
public class FireOnceVerifiedTest {
    
    public FireOnceVerifiedTest() {
    }
    
    @Before
    public void setUp() {
        
    }

    /**
     * Test of Verified method, of class FireOnceVerified.
     */
    @Test
    public void testVerified() {
        System.out.println("FireOnceVerified");
        Rule r= new Rule();
        r.setAction(new MessageBoxAction("msg"));
        r.fire();
        FireOnceVerified instance = new FireOnceVerified();
        boolean expResult = false;
        boolean result = instance.verified(r);
        assertEquals(expResult, result);
       
    }
    
}
