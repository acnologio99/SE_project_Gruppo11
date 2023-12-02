/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package SE_project2023;

import SE_project2023.Regole.Rule;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
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
    private static final String TEST_FILE = "test_rules.txt";
    private RuleList ruleManager;
    public RuleListTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    ruleManager = new RuleList(); 
    }

    @After
    public void tearDown() throws Exception {
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
        assertEquals(r, instance.getLast());
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
   
@Test   
public void testSaveRulesWhenNoRules() {
    ruleManager.saveRules(TEST_FILE);
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
        ruleManager.loadRules("non_esiste.dat");

        assertEquals(0, ruleManager.getRuleList().size());
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
        ruleManager.loadRules(TEST_FILE);

        assertEquals(0, ruleManager.getRuleList().size());
    }

    @Test
    public void testLoadInvalidRules() {
        // Scrittura dati non validi nel file
        // Considera di scrivere dati non corrispondenti a oggetti Rule

        // Caricamento da un file con dati non validi
        ruleManager.loadRules(TEST_FILE);

        // Verifica che non siano state caricate regole non valide
        assertEquals(0, ruleManager.getRuleList().size());
    }
    
   
}
