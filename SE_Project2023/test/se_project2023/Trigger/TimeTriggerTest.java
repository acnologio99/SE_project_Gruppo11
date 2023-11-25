/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package se_project2023.Trigger;

import SE_project2023.Trigger.TimeTrigger;
import java.time.LocalTime;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author chris
 */
public class TimeTriggerTest {
    
    public TimeTriggerTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of getTimeOfDay method, of class TimeTrigger.
     */
    @Test
    public void testGetTimeOfDay() {
        System.out.println("getTimeOfDay");
        TimeTrigger instance = null;
        LocalTime expResult = null;
        LocalTime result = instance.getTimeOfDay();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of setTimeOfDay method, of class TimeTrigger.
     */
    @Test
    public void testSetTimeOfDay() {
        System.out.println("setTimeOfDay");
        LocalTime timeOfDay = null;
        TimeTrigger instance = null;
        instance.setTimeOfDay(timeOfDay);
        
    }

    /**
     * Test of isVerified method, of class TimeTrigger.
     */
    @Test
    public void testIsVerified() {
        System.out.println("isVerified");
        TimeTrigger instance = null;
        boolean expResult = false;
        boolean result = instance.isVerified();
        assertEquals(expResult, result);
       
    }
    
}
