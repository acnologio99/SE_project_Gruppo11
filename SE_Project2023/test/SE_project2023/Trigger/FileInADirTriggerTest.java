/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package SE_project2023.Trigger;

import java.io.File;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author giova
 */
public class FileInADirTriggerTest {
    File file;
    
    public FileInADirTriggerTest() {
    }
    
    @Before
    public void setUp() throws IOException {
        file= new File("./data/testDir.txt");
        file.createNewFile();
    }

    /**
     * Test of isVerified method, of class FileInADirTrigger.
     */
    @Test
    public void testIsVerified() {
        System.out.println("isVerified");
        FileInADirTrigger instance = new FileInADirTrigger(file, file.getParent());
        boolean expResult = true;
        boolean result = instance.isVerified();
        assertEquals(expResult, result);
        instance = new FileInADirTrigger(file, "./SE_project2023");
        expResult = false;
        result = instance.isVerified();
        assertEquals(expResult, result);
    }

}
