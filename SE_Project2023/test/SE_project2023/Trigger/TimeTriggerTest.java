/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package SE_project2023.Trigger;

import java.time.DateTimeException;
import java.time.LocalTime;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

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
    }

    /**
     *
     */
    @Test//(expected=AssertionError.class)
    public void testIsVerified() {
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
        
        
    }
    
    
    
}
