package SE_project2023;

import SE_project2023.Regole.Rule;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;



/**
 *
 * @author emanu
 */
public class RuleList extends Observable {

    private static RuleList ruleList = null;

    private ObservableList<Rule> rules;
    private List<Observer> obs;

    private RuleList() {
        rules = FXCollections.observableArrayList();
        obs = new ArrayList<>();
    }

    public static RuleList getRuleList() {
        if (ruleList == null) {
            ruleList = new RuleList();
        }
        return ruleList;
    }

    public ObservableList<Rule> getArrayList() {
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

   

    

    

}
