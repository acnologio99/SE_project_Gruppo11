package SE_project2023.Regole;

import SE_project2023.Action.Action;
import SE_project2023.Trigger.Trigger;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author emanu
 */
public class Rule extends Observable implements Serializable{
    private String name;
    private Action action;
    private Trigger trigger;
    private boolean status = true;
    private boolean flag = false;
    private long sleep = 0;
    private boolean fireOnce = false;
    private LocalDateTime wakeUp;

    List<Observer> obs;

    //Costruttori
    public Rule()  {obs = new ArrayList<>();}

    public Rule(String name, Action action, Trigger trigger) {
        this.name = name;
        this.action = action;
        this.trigger = trigger;
        this.status= true;

        obs = new ArrayList<>();
        
    }
    

    public void setSleep(Long sleep) {
        this.sleep = sleep;
    }

    //Getter
    public Action getAction() {
        return action;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public String getName() {
        return name;
    }

    public boolean getFlag() {
        return flag;
    }
    public boolean getStatus() {
        return this.status;
    }
    
    //Setter
    public void setAction(Action action) {
        this.action = action;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean ruleIsValid() {
        return this.getTrigger() != null && this.getAction() != null && this.flag;
    }
    public void setFireOnce(boolean f){
        this.fireOnce=true;
    }

    public String getSleep() {
        if(sleep == 0)
        return "No";
        else
        return "Yes";
    }
    

    public void active() {
        this.status = true;
        this.notifyObservers();
    }
    public void deactive(){
        this.status=false;
        this.notifyObservers();
    }

    @Override
    public String toString() {
        return "Rule : " + name + "; Action : " + action + "; Trigger : " + trigger + "; Status : " + status;
    }

    public boolean isVerifiedRule() {
        if (!action.isFired()) {
            return trigger.isVerified() && status;
        }
        if (fireOnce == true) {
            return trigger.isVerified() && status && !action.isFired();
        } else if(sleep!=0){
            return trigger.isVerified() && status && sleepCheck();
        }else return trigger.isVerified() && status;
        
    }

    public void fire() {
        action.fire();
        if (!(sleep == 0)) {
            wakeUp = LocalDateTime.now().plusSeconds(sleep);
            System.out.print(wakeUp);
        }

    }

    public Boolean sleepCheck() {
        return LocalDateTime.now().compareTo(wakeUp) >= 0;
    }

    /*this method attaches the observers to the rule*/
    public void attach(Observer o){      
        obs.add(o);
    }
    public void detach(){
        obs.clear();
    }

    @Override
    public void notifyObservers(){
        for(Observer o : obs){
            o.update(null,o);
        }
    }



}
