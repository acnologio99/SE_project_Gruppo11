/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package SE_project2023;

import SE_project2023.Regole.Rule;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author giova
 */
public class RuleListTest {
    private static final String TEST_FILE = "test_rules.bin";
    private RuleList rules;
    public RuleListTest() {
    }

    @Before
    public void setUp() {
         rules = RuleList.getRuleList();
    }
    /**
     * Test of add method, of class RuleList.
     */
    @Test(expected = NullPointerException.class)
    public void testAdd() {
        System.out.println("add");
        Rule r = new Rule();
        rules.add(r);
        assertEquals(r, rules.getLast()); //the new rule is equal to the last i the list
        rules.add(null); //expected NullPointerException
    }

    /**
     * Test of getLast method, of class RuleList.
     */
    @Test
    public void testGetLast() {
        System.out.println("getLast");
        Rule expResult = new Rule();
         rules.add(expResult);
        Rule result = rules.getLast();
        assertEquals(expResult, result);

    }

    /**
     * Test of removeLast method, of class RuleList.
     */
    @Test(expected=ArrayIndexOutOfBoundsException.class)
    public void testRemoveLast() {
        System.out.println("removeLast");
        rules.add(new Rule());
        rules.removeLast();
        assertTrue(rules.isEmpty());
        
        Rule r1 = new Rule();
        Rule r2 = new Rule();
        rules.add(r1);
        rules.add(r2);
        rules.removeLast();
        assertEquals(r1, rules.getLast());
        
        rules.removeLast(); 
        rules.removeLast();// removeLast on empty list, expected BoundsException 
    }
    @Test   
    public void testSaveRulesWhenNoRules() {
    rules.saveRules(TEST_FILE);
    File emptyFile = new File(TEST_FILE);
        try {
            emptyFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Verifica che il file sia stato creato
        File file = new File(TEST_FILE);
        assertTrue("Il file è stato creato", file.exists());
        assertEquals("Il file è vuoto", emptyFile.length(), file.length());
        //faccio la creazione di due file vuoti poiché non posso usare numeri 
        //interi per lenght dato che il file è binario e non ritorna 
        //correttamente un valore pari a 0
    }

     @Test
    public void testLoadRulesFromFileNotExists() {
        // Verifica che il caricamento da un file inesistente non aggiunga regole
        rules.loadRules("non_esiste.dat");
        assertEquals(0, rules.getRuleList().size());
    }
    @Test
    public void testLoadRulesFromEmptyFile() {
        // Creazione di un file vuoto
        File emptyFile = new File(TEST_FILE);
        try {
            emptyFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Caricamento da un file vuoto
         rules.loadRules(TEST_FILE);
        assertEquals(0, rules.getRuleList().size());
    }
    @Test
    public void testLoadInvalidRules() {
        // Scrittura dati non validi nel file
        // Considera di scrivere dati non corrispondenti a oggetti Rule
        // Caricamento da un file con dati non validi
        rules.loadRules(TEST_FILE);
        // Verifica che non siano state caricate regole non valide
        assertEquals(0, rules.getRuleList().size());
    }
    
    /**
     * Test of size method, of class RuleList.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        int expResult = 0;
        int result = rules.size();
        assertEquals(expResult, result);
        
        rules.add(new Rule());
        result = rules.size();
        assertEquals(1, result);
        
        rules.removeLast();
        result = rules.size();
        assertEquals(0, result);
        
        
    }

    /**
     * Test of isEmpty method, of class RuleList.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        boolean result = rules.isEmpty();
        assertTrue(result);
        
        rules.add(new Rule());
        assertFalse(rules.isEmpty());
       
    }

    /**
     * Test of removeAll method, of class RuleList.
     */
    @Test
    public void testRemoveAll() {
        System.out.println("removeAll");
        Rule r1 = new Rule();
        List<Rule> c = new ArrayList<>();
        rules.add(r1);
        c.add(r1);
        boolean result = rules.removeAll(c);
        assertTrue(result);
        
        assertFalse(rules.removeAll(c)); //false with empty list
       
        
        
    }

    /**
     * Test of get method, of class RuleList.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGet() {
        System.out.println("get");
        int index = 0;
        Rule r1 = new Rule();
        rules.add(r1);
        assertEquals(r1, rules.get(index));
        
        rules.get(2); //No rule in position 2, expected IndexOutOfBoundsException
        rules.get(-1); //expected IndexOutOfBoundsException
        
        
    }

    /**
     * Test of remove method, of class RuleList.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        Rule r = null;
        RuleList instance = null;
        boolean expResult = false;
        boolean result = instance.remove(r);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveRules method, of class RuleList.
     */
    @Test
    public void testSaveRules() {
        System.out.println("saveRules");
        String filename = "";
        RuleList instance = null;
        instance.saveRules(filename);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadRules method, of class RuleList.
     */
    @Test
    public void testLoadRules() {
        System.out.println("loadRules");
        String filename = "";
        RuleList instance = null;
        instance.loadRules(filename);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class RuleList.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Observable o = null;
        Object arg = null;
        RuleList instance = null;
        instance.update(o, arg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iterator method, of class RuleList.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        RuleList instance = null;
        Iterator<Rule> expResult = null;
        Iterator<Rule> result = instance.iterator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
   
}

