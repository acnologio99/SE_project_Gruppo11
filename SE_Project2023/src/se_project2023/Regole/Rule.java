package SE_project2023.Regole;
import SE_project2023.Action.Action;
import SE_project2023.Trigger.Trigger;
/**
 *
 * @author emanu
 */
public class Rule {
    private String name;
    private Action action;
    private Trigger trigger;

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
    public boolean ruleIsValid(){
        return this.getTrigger()!=null && this.getAction()!=null;
    }

    @Override
        public String toString() {
            return "Rule : " + name + "; Action : " + action + "; Trigger : " + trigger;
        }
        
    public boolean isVerifiedRule(){
        return this.trigger.isVerified();
    }
}
