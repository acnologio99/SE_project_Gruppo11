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
import java.util.Observer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author giova
 */
public class RuleListTest {

    private String testFile = "test_rules.bin";
    private RuleList rules;
    private File file;

    public RuleListTest() {
    }

    @Before
    public void setUp() {
        rules = RuleList.getRuleList();
        file = new File(testFile);
    }

    @After
    public void tearDown() {
        rules.clear();
        file.delete();
    }

    /**
     * Test of add method, of class RuleList.
     */
    @Test(expected = NullPointerException.class)
    public void testAdd() {
        System.out.println("add");
        Rule r = new Rule();
        rules.add(r);
        assertEquals(r, rules.getLast()); // the new rule is equal to the last i the list
        rules.add(null); // expected NullPointerException
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
    @Test(expected = ArrayIndexOutOfBoundsException.class)
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
        file.delete();
        rules.saveRules(testFile);
        assertTrue(file.exists()); // verifico che il file, se non esiste, è stato creato
    }

    @Test
    public void testLoadRulesFromFileNotExists() {
        // caricamento da un file non esistente
        File f = new File("testFake.bin");
        f.delete();
        rules.loadRules("testFake.bin"); // la load gestisce internamente l'eccezione nel caso il file non esiste con
                                         // return;
        // il file verrà creato nella save.

    }

    @Test
    public void testLoadRulesFromEmptyFile() throws IOException {
        // Creazione di un file vuoto
        // file.delete() è nella tearDown quindi sono sicuro che il file non esista.
        file.createNewFile();
        // Caricamento da un file vuoto
        rules.loadRules(testFile);
        assertEquals(0, rules.size()); // Il file non ha regole quindi la size della lista deve essere 0
    }

    @Test
    public void testSaveLoadRules() throws IOException {
        // Creazione di un file vuoto
        // rules.clear() è nella tearDown(), sono sicuro che rules non abbia regole.

        Rule r = new Rule();
        rules.add(r);
        rules.saveRules(testFile);
        // Caricamento da un file con 1 regola
        rules.clear(); // pulisco la lista in modo da testare se effettivamente ha aggiunto una regola
        rules.loadRules(testFile);
        assertEquals(1, rules.size()); // Il file ha una sola regola quindi la dimensione della vista deve essere 1
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

        assertFalse(rules.removeAll(c)); // false with empty list

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

        rules.get(2); // No rule in position 2, expected IndexOutOfBoundsException
        rules.get(-1); // expected IndexOutOfBoundsException

    }

    /**
     * Test of remove method, of class RuleList.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        Rule r = new Rule();
        rules.add(r);
        assertEquals(1, rules.size()); // aggiungo una regola quindi la size è 1.

        boolean res = rules.remove(r);
        assertTrue(res); // mi assicuro che la remove sia andata a buon fine
        assertEquals(0, rules.size()); // dopo aver rimosso, la size devo essere 0, inoltre essendo la regola soltanto
                                       // una,
        // sono sicuro che abbia rimosso proprio quella regola.

        Rule r1 = new Rule();
        res = rules.remove(r1); // provo a rimuovere una regola non presente nella lista
        assertFalse(res); // la remove non deve andare a buon fine

        res = rules.remove(null);
        assertFalse(res); // la remove non deve andare a buon fine

    }

    /**
     * Test of update method, of class RuleList. In questo metodo testo la
     * proprietà di rules di essere observer ed observable.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        class InnerObserver implements Observer {

            boolean observed = false;

            @Override
            public void update(Observable o, Object arg) {
                observed = true;
            }
        }
        InnerObserver obs = new InnerObserver();
        rules.addObserver(obs); // aggiungo la classe innestata come osservatore di rules.
        assertFalse(obs.observed); // observed deve essere falso perchè non ho fatto nessun update.

        Rule r = new Rule();
        rules.add(r); // aggiungo una regola alla lista, ora observed deve diventare true.
        assertTrue(obs.observed); // observed is true becouse r updated.

        // inoltre la lista osserva anche la regola e, ogni volta che una regola si
        // aggiorna, comunica il cambiamento anche a chi osserva la lista.
        obs.observed = false;
        r.deactive();
        assertTrue(obs.observed); // la regola è passata da "attiva" ad "inattiva", observed deve tornare a true
                                  // perchè la lista ha comunicato il cambiamento.

    }

    /**
     * Test of iterator method, of class RuleList.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        Rule r = new Rule();
        rules.add(r);
        List<Rule> l = new ArrayList<>();
        assertEquals(1, rules.size());
        for (Rule r1 : rules) {
            l.add(r1);
        }
        assertEquals(l.size(), rules.size()); // dopo il for each, siccome iterando su rules ho aggiunto una regola in
                                              // list, la loro size deve essere uguale.
    }

}
