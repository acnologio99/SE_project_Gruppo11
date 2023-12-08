/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package SE_project2023.Trigger;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author giova
 */
public class DayOfMonthTriggerTest {
    
    public DayOfMonthTriggerTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of isVerified method, of class DayOfMonthTrigger.
     */
    @Test
    public void testIsVerified() {
        System.out.println("isVerified");
        DayOfMonthTrigger instance = new DayOfMonthTrigger(LocalDate.now().getDayOfMonth());
        boolean expResult = true;
        boolean result = instance.isVerified();
        assertEquals(expResult, result);
        instance = new DayOfMonthTrigger(LocalDate.now().getDayOfMonth()+1);
        expResult = false;
        result = instance.isVerified();
        assertEquals(expResult, result);
    }
    
}
