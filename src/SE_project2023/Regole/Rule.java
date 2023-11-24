package SE_project2023.Regole;
import SE_project2023.Action.Action;
import SE_project2023.Trigger.Trigger;
/**
 *
 * @author emanu
 */
public class Rule {
    public String name;
    public Action action;
    public Trigger trigger;

    //Costruttori
    public Rule() {}

    public Rule(String name) {
        this.name = name;
    }

    public Rule(String name, Action action, Trigger trigger) {
        this.name = name;
        this.action = action;
        this.trigger = trigger;
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

    @Override
        public String toString() {
            return "Regola : " + name + " azioni " + action;
        }
        
    public boolean isVerifiedRule(){
        return this.trigger.isVerified();
    }
}
