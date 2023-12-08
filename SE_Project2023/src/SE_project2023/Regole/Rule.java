package SE_project2023.Regole;

import SE_project2023.Action.Action;
import SE_project2023.Tool.VerifiedTool;
import SE_project2023.Trigger.Trigger;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Observable;

/**
 *
 * @author emanu
 */
public class Rule extends Observable implements Serializable {
    private String name;
    private Action action;
    private Trigger trigger;
    private boolean status = true;
    private long sleep = 0;
    private LocalDateTime wakeUp;
    private VerifiedTool vT;
   

    //Costruttori
    public Rule() {
    }

    public Rule(String name, Action action, Trigger trigger) {
        this.name = name;
        this.action = action;
        this.trigger = trigger;
        this.status = true;
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
    
    public boolean getStatus() {
        return this.status;
    }
    
      public LocalDateTime getWakeUp() {
        return wakeUp;
    }
      
     public long getSleep() {
        return sleep;
    }   
    
    //Setter
    public void setAction(Action action) {
        this.action = action;
        this.setChanged();
        this.notifyObservers();
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
        this.setChanged();
        this.notifyObservers();
    }

    public void setName(String Name) {
        this.name = Name;
        this.setChanged();
        this.notifyObservers();
    }
    
    public void setVerifiedTool(VerifiedTool v){
        this.vT= v;
    }
    
    public void setWakeUp(LocalDateTime wakeUp) {
        this.wakeUp = wakeUp;
    }
    
    public void setSleep(Long sleep) {
        this.sleep = sleep;
        setWakeUp(LocalDateTime.now().plusMinutes(sleep));
    }

    public boolean ruleIsValid() {
        return this.getTrigger() != null && this.getAction() != null;
    }


    

    public void active() {
        this.status = true;
        this.setChanged();
        this.notifyObservers();
    }
    public void deactive() {
        this.status = false;
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public String toString() {
        return "Rule : " + name + "; Action : " + action + "; Trigger : " + trigger + "; Status : " + status;
    }

    public boolean isVerifiedRule() {
        if (!action.isFired()) 
            return trigger.isVerified() && status;
        else
            return trigger.isVerified() && status && vT.verified(this);
    }

    public void fire() {
        action.fire();
        this.setChanged();
        this.notifyObservers();

    }
}
