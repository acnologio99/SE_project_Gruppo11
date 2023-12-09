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
public class RemoveStrategyTest {

    public RemoveStrategyTest() {
    }

    RemoveStrategy rs;
    File file;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws IOException {
        rs = new RemoveStrategy();
        file = new File("data\\doc.txt");
        file.createNewFile();
    }

    @After
    public void tearDown() {
        file.delete();
    }

    /**
     * Test of execute method, of class RemoveStrategy.
     */
    @Test
    public void testExecute() {
        System.out.println("execute");
        String sourcePath = file.getPath();
        rs.execute(file, sourcePath, null);

        assertFalse(file.exists());
    }

}