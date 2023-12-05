package SE_project2023;

import SE_project2023.Regole.Rule;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;



/**
 *
 * @author emanu
 */
public class RuleList extends Observable implements Observer, Serializable, Iterable{

    private static RuleList ruleList = null;

    private ArrayList<Rule> rules;
    

    private RuleList() {
        rules = new ArrayList<>();
        
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
    
    public int size() {
        return rules.size();
    }
    
    public void add(Rule r) {
        rules.add(r);
        r.addObserver(this);
        this.notifyObservers();
    }

    public Rule getLast() {
        return rules.get(rules.size()-1);
    }

    public void removeLast() {
        rules.remove(rules.size()-1);
        this.notifyObservers();

    }
    public void addObserver(){
        for(Rule r: rules)
            r.addObserver(this);
    }


     public void saveRules(String filename) {
        try (ObjectOutputStream objectOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)))) {

                
                objectOut.writeObject(rules);
            
        } catch (IOException e) {
            e.printStackTrace();
            // Gestione dell'eccezione durante il salvataggio delle regole
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
            while (true) {
                try {
                    rules= (ArrayList<Rule>) objectIn.readObject();
                    this.addObserver();
                } catch (EOFException e) {
                    
                    break;
                }
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
    public Iterator iterator() {
        return rules.iterator();
    }

   
}
