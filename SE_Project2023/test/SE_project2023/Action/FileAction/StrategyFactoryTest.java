/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package SE_project2023.Action.FileAction;

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
public class StrategyFactoryTest {
    
    StrategyFactory instance;
    
    public StrategyFactoryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {//inizializza l'oggetto
        instance = new StrategyFactory();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getStrategy method, of class StrategyFactory.
     * Controlla che viene creata l'istanza corretta a seconda della condizione 
     * oppure lancia l'eccezione.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGetStrategy() {
        System.out.println("getStrategy");
        
        String action = "copy";
        FileStrategy result = instance.getStrategy(action);
        assertTrue(result instanceof CopyStrategy);
        
        action = "move";
        result = instance.getStrategy(action);
        assertTrue(result instanceof MoveStrategy);
        
        action = "remove";
        result = instance.getStrategy(action);
        assertTrue(result instanceof RemoveStrategy);
        
        action = "";
        result = instance.getStrategy(action);
    }
    
}
