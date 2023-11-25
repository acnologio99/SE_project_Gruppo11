/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package SE_project2023.Trigger;

import java.time.DateTimeException;
import java.time.LocalTime;
import static junit.framework.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author cauro
 */
public class TimeTriggerTest {
    private TimeTrigger instance;
    public TimeTriggerTest() {
    }
    
    @Before
    public void setUp() {
        instance = new TimeTrigger(null);
    }

    /**
     * Test of getTimeOfDay method, of class TimeTrigger.
     */
    @Test(expected=DateTimeException.class)
    public void testGetTimeOfDay() {
<<<<<<< HEAD
        System.out.println("getTimeOfDay");
        TimeTrigger instance = null;
        LocalTime expResult = null;
        LocalTime result = instance.getTimeOfDay();
        assertEquals(expResult, result);
        
=======

        //Impostazione di un valore di LocalTime (ad esempio, 10:30) tramite setTimeOfDay()
        LocalTime expectedTime = LocalTime.of(25, 30);
        instance.setTimeOfDay(expectedTime);
        LocalTime actualTime = instance.getTimeOfDay();
        assertEquals(expectedTime, actualTime);
        
        expectedTime = LocalTime.of(22, 61);
        instance.setTimeOfDay(expectedTime);
        actualTime = instance.getTimeOfDay();
        assertEquals(expectedTime, actualTime);
        
        expectedTime = LocalTime.of(-1, 15);
        instance.setTimeOfDay(expectedTime);
        actualTime = instance.getTimeOfDay();
        assertEquals(expectedTime, actualTime);
        
        expectedTime = LocalTime.of(1, -15);
        instance.setTimeOfDay(expectedTime);
        actualTime = instance.getTimeOfDay();
        assertEquals(expectedTime, actualTime);
        
        expectedTime = LocalTime.of(-1, -15);
        instance.setTimeOfDay(expectedTime);
        actualTime = instance.getTimeOfDay();
        assertEquals(expectedTime, actualTime);

        expectedTime = LocalTime.of(11, 50);
        instance.setTimeOfDay(actualTime);
        actualTime=instance.getTimeOfDay();
        assertEquals(expectedTime, actualTime);
>>>>>>> origin/main
    }

    /**
     *
     */
<<<<<<< HEAD
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
=======
    @Test//(expected=AssertionError.class)
>>>>>>> origin/main
    public void testIsVerified() {
<<<<<<< HEAD
        System.out.println("isVerified");
        TimeTrigger instance = null;
        boolean expResult = false;
        boolean result = instance.isVerified();
        assertEquals(expResult, result);
       
=======
        /*Orario impostato a null*/
        assertTrue(!instance.isVerified());

        
        /*Settiamo il time del trigger ad un orario diverso da quello attuale*/
        LocalTime time = LocalTime.of(00, 13);
        instance.setTimeOfDay(time);
        assertTrue(!instance.isVerified());
        //non è verificata, lancia l'eccezione

        
        LocalTime time4;
        time4 = LocalTime.of(LocalTime.now().getHour(), (LocalTime.now().getMinute()));
        instance.setTimeOfDay(time4);
        assertTrue(instance.isVerified());
                
        LocalTime time5= LocalTime.MAX;
        instance.setTimeOfDay(time5);
        assertTrue(!instance.isVerified());
        
        LocalTime time6= LocalTime.MIN;
        instance.setTimeOfDay(time6);
        assertTrue(!instance.isVerified());
        
        LocalTime time7= LocalTime.MIDNIGHT;
        instance.setTimeOfDay(time7);
        assertTrue(!instance.isVerified());
        
        
>>>>>>> origin/main
    }
    
    
    
}
