/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package SE_project2023.Tool;

import SE_project2023.Regole.Rule;
import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author giova
 */
public class SleepVerifiedTest {
    
    public SleepVerifiedTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of Verified method, of class SleepVerified.
     */
    @Test
    public void testVerified() {
        System.out.println("Verified");
        Rule r = new Rule();
        /*SleepVerified instance = new SleepVerified();
        boolean expResult = false;
        boolean result = instance.Verified(r);
        assertEquals(expResult, result);*/
        SleepVerified instance = new SleepVerified();
        Rule r1 = new Rule();
        r.setWakeUp(LocalDateTime.now());
        boolean expResult = true;
        boolean result = instance.verified(r);
        assertEquals(expResult, result);
        
        
    }
    
}
