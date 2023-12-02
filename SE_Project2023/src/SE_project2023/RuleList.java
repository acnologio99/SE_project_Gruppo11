package SE_project2023;

import SE_project2023.Regole.Rule;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emanu
 */
public class RuleList {

    private static RuleList ruleList = null;

    private List<Rule> rules;

    private RuleList() {
        rules = new ArrayList<Rule>();
    }

    public static RuleList getRuleList() {
        if (ruleList == null) {
            ruleList = new RuleList();
        }
        return ruleList;
    }

    public List<Rule> getArrayList() {
        return rules;
    }

    public void add(Rule r) {
        rules.add(r);
    }

    public Rule getLast() {
        return rules.get(rules.size()-1);
    }

    public void removeLast() {
        rules.remove(rules.size()-1);
    }

     public void saveRules(String filename) {
        try (ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(filename))) {
            objectOut.writeObject(rules);
        } catch (IOException e) {
            e.printStackTrace();
            // Gestione dell'eccezione durante il salvataggio delle regole
        }
    }

    public void loadRules(String filename) {
        try (ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(filename))) {
            List<Rule> loadedRules = (List<Rule>) objectIn.readObject();
            rules.addAll(loadedRules);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            // Gestione dell'eccezione durante il caricamento delle regole
        }
    }

}
