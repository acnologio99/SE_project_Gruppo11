/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package SE_project2023.Trigger;

import java.io.File;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author giova
 */
public class FileSizeTriggerTest {
    
    public FileSizeTriggerTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of isVerified method, of class FileSizeTrigger.
     */
    @Test
    public void testIsVerified() {
        System.out.println("isVerified");
        File file= new File("data/song02.wav");
        FileSizeTrigger instance = new FileSizeTrigger( file , (int) (file.length()/1024));
        boolean expResult = true;
        boolean result = instance.isVerified();
        assertEquals(expResult, result);
        instance = new FileSizeTrigger( file , (int) (file.length()/1024)+1);
        expResult = false;
        result = instance.isVerified();
        assertEquals(expResult, result);
    }
    
}
