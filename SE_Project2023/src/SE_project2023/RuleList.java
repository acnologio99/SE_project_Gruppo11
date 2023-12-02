package SE_project2023;

import SE_project2023.Regole.Rule;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;



/**
 *
 * @author emanu
 */
public class RuleList extends Observable implements Observer, Serializable{

    private static RuleList ruleList = null;

    private ArrayList<Rule> rules;
    private List<Observer> obs;

    private RuleList() {
        rules = new ArrayList<>();
        obs = new ArrayList<>();
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
        this.notifyObservers();
    }

    public Rule getLast() {
        return rules.get(rules.size()-1);
    }

    public void removeLast() {
        rules.remove(rules.size()-1);
        this.notifyObservers();

    }

     public void attach(Observer o){
        obs.add(o);
    }

   @Override
    public void notifyObservers(){
        for(Observer o : obs){
            o.update(null, o);
        }
    }





     public void saveRules(String filename) {
        try (ObjectOutputStream objectOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(filename)))) {
            for(Rule r: rules){
                r.detach();
                objectOut.writeObject(r);
            }
            System.out.print("Ho salvato bro");
        } catch (IOException e) {
            e.printStackTrace();
            // Gestione dell'eccezione durante il salvataggio delle regole
        }
    }

    public void loadRules(String filename) {
        try (ObjectInputStream objectIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream(filename)))) {
            while(true){
                try{Rule r = (Rule) objectIn.readObject();
                    r.attach(this);
                    rules.add(r);
                 } catch (EOFException e) {
                // EOFException indica la fine del file
                break;
            }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            // Gestione dell'eccezione durante il caricamento delle regole
        }
    }
    

    @Override
    public void update(Observable o, Object arg) {
        notifyObservers();
    }

}
