/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package SE_project2023.Action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author giova
 */
public class ActionFactoryTest {
    private final Map<String, ArrayList<String>> paramsText = new HashMap<>();
    public ActionFactoryTest() {
    }
    
    @Before
    public void setUp() {
        paramsText.put("TextBox Action", new ArrayList<>(Arrays.asList("msg")));
        paramsText.put("Audio Action", new ArrayList<>(Arrays.asList("song01.vaw")));
    }

    /**
     * Test of create method, of class ActionFactory.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        ActionFactory instance = new ActionFactory("TextBox Action", (HashMap) paramsText);
       
        Action result = instance.create();
        assertTrue(result instanceof MessageBoxAction);
        instance = new ActionFactory("Audio Action", (HashMap) paramsText);
        result = instance.create();
        assertFalse(result instanceof MessageBoxAction);
    }
    
}
