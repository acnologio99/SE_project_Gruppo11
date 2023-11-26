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
    private boolean flag = false;

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

    public boolean getFlag() {
        return flag;
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
    
    public boolean ruleIsValid(){
        return this.getTrigger()!=null && this.getAction()!=null && this.flag;
    }

    @Override
        public String toString() {
            return "Rule : " + name + "; Action : " + action + "; Trigger : " + trigger;
        }
        
    public boolean isVerifiedRule(){
        return this.trigger.isVerified();
    }
}
