package SE_project2023;

import SE_project2023.Regole.Rule;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emanu
 */
public class RuleList extends Observable implements Observer, Serializable, Iterable<Rule> {

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

    
    public int size() {
        return rules.size();
    }

    public boolean isEmpty() {
        return rules.isEmpty();
    }

    public boolean removeAll(Collection c) {
        Boolean a = rules.removeAll(c);
        this.setChanged();
        this.notifyObservers();
        return a;
    }

    public void add(Rule r) {
        rules.add(r);
        r.addObserver(this);
        this.setChanged();
        this.notifyObservers();
    }

    public Rule getLast() {
        return rules.get(rules.size() - 1);
    }

    public void removeLast() {
        rules.remove(rules.size() - 1);
        this.setChanged();
        this.notifyObservers();

    }

    public Rule get(int index) {
        Rule r = rules.get(index);
        this.setChanged();
        this.notifyObservers();

        return r;
    }

    public boolean remove(Rule r) {
        boolean rmv = rules.remove(r);
        this.setChanged();
        this.notifyObservers();
        return rmv;
    }
    
    public void clear(){
        rules.clear();
        this.setChanged();
        this.notifyObservers();
    }
    
    

    public void saveRules(String filename) {
        try (ObjectOutputStream objectOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)))) {

            objectOut.writeObject(rules);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(RuleList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RuleList.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    public void loadRules(String filename) {
        try {
            File file = new File(filename);
            if (!file.exists()) {
                // Se il file non esiste, crea un nuovo file con il nome specificato
                file.createNewFile();
            }

            try (ObjectInputStream objectIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))) {

                try {
                    List<Rule> l = (List<Rule>) objectIn.readObject(); //lista d'appoggio in modo da poter osservare di nuovo tutte le regole.
                    for (Rule r : l) {
                        this.add(r);
                    }
                } catch (EOFException e) {
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();

            }
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }

    @Override
    public Iterator<Rule> iterator() {
        return rules.iterator();
    }

}
