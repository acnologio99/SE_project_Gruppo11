/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package SE_project2023;

import SE_project2023.Action.MessageBoxAction;
import SE_project2023.Regole.Rule;
import SE_project2023.Trigger.TimeTrigger;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author giova
 */
public class RuleListTest {
    
    public RuleListTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of getRuleList method, of class RuleList.
     */
    @Test
    public void testGetRuleList() {
        System.out.println("getRuleList");
       
        
    }

    /**
     * Test of getArrayList method, of class RuleList.
     */
    @Test
    public void testGetArrayList() {
        System.out.println("getArrayList");
        RuleList instance = RuleList.getRuleList();
        List<Rule> expResult = instance.getArrayList();
        List<Rule> result = instance.getArrayList();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of add method, of class RuleList.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        Rule r = new Rule();
        RuleList instance = RuleList.getRuleList();
        instance.add(r);
        assertEquals(r,instance.getLast());
        
    }

    /**
     * Test of getLast method, of class RuleList.
     */
    @Test
    public void testGetLast() {
        System.out.println("getLast");
        RuleList instance = RuleList.getRuleList();
        Rule expResult = new Rule();
        instance.add(expResult);
        Rule result = instance.getLast();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of removeLast method, of class RuleList.
     */
    @Test
    public void testRemoveLast() {
        
        System.out.println("removeLast");
        RuleList instance = RuleList.getRuleList();
        instance.getArrayList().clear();
        instance.add(new Rule());
        instance.removeLast();
        assertTrue(instance.getArrayList().isEmpty());
    }

    /**
     * Test of iterator method, of class RuleList.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        
        
    }
    
}