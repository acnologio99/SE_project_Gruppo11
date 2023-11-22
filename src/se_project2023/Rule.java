package SE_project2023;

/**
 *
 * @author emanu
 */
public class Rule {
    String name;
    Action action;
    Trigger trigger;

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
            return "Regola : " + name;
        }
        
    public boolean isVerifiedRule(){
        if(this.trigger.isVerified()){
            this.action.fire();
            return true;
        }
        return false;
    }
}
