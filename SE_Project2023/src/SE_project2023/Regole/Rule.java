package SE_project2023.Regole;

import SE_project2023.Action.Action;
import SE_project2023.Tool.VerifiedTool;
import SE_project2023.Trigger.Trigger;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Observable;

/**
 * La regola è Osservabile in quanto principalmente deve comunicare tutti i suoi aggiornamenti a RuleList.
 * @author emanu
 */
public class Rule extends Observable implements Serializable {

    private String name;
    private Action action;
    private Trigger trigger;
    private boolean status = true; //Indica se è attiva o disattiva
    private boolean flag = false; //Indica se la regola è valida oppure no
    private long sleep = 0; //Indica se la regola è in sleep e per quanto tempo
    private LocalDateTime wakeUp; // Indica quando dovrà risvegliarsi.
    private VerifiedTool vT; //Ogni volta che la regola viene controllata ha bisogno di un controllo diverso in base al suo stato.

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
    
    public boolean getFlag() {
        return flag;
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

    public void setVerifiedTool(VerifiedTool v) {
        this.vT = v;
    }

    public void setWakeUp(LocalDateTime wakeUp) {
        this.wakeUp = wakeUp;
    }

    public void setSleep(Long sleep) {
        if(sleep < 0)
            throw new IllegalArgumentException();
        this.sleep = sleep;
        setWakeUp(LocalDateTime.now().plusMinutes(sleep));
    }
    
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean ruleIsValid() {
        return this.getTrigger() != null && this.getAction() != null && this.flag;
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
    
    // La regola, per essere eseguita, deve verificare condizioni diverse in base allo stato in cui si trova.
    public boolean isVerifiedRule() {
        if (!action.isFired()) {
            return trigger.isVerified() && status;
        } else {
            return trigger.isVerified() && status && vT.verified(this);
        }
    }

    public void fire() {
        action.fire();
        this.setChanged();
        this.notifyObservers(this);

    }
}
