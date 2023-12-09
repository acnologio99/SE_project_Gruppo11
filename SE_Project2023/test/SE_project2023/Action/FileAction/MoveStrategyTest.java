/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package SE_project2023.Action.FileAction;

import java.io.File;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author emanu
 */
public class MoveStrategyTest {
    
    public MoveStrategyTest() {
    }
    
    MoveStrategy ms;
    File file;
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
        ms = new MoveStrategy();
        file = new File("data\\doc.txt");
        file.createNewFile();
    }
    
    @After
    public void tearDown() {
        file.delete();
    }

    /**
     * Test of execute method, of class MoveStrategy.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        String sourcePath = file.getPath();
        String destinationPath = file.getName();
        ms.execute(file, sourcePath, destinationPath);
        
        assertFalse(file.exists());
        file = new File("doc.txt");
        assertTrue(file.exists());
    }
    
}
