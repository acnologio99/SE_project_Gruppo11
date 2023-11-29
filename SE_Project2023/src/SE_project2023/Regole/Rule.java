package SE_project2023.Regole;
import SE_project2023.Action.Action;
import SE_project2023.Trigger.Trigger;
import java.time.LocalDateTime;
/**
 *
 * @author emanu
 */
public class Rule {
    private String name;
    private Action action;
    private Trigger trigger;
    private boolean status= true;
    private boolean flag = false;
    private long sleep = 0;
    private boolean fireOnce=false;
    private LocalDateTime wakeUp;

    //Costruttori
    public Rule() {}

    public Rule(String name, Action action, Trigger trigger) {
        this.name = name;
        this.action = action;
        this.trigger = trigger;
        this.status= true;
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
    public boolean getStatus(){
        return this.status;
    }
    public void active(){
        this.status=true;
    }
    public void deactive(){
        this.status=false;
    }
    @Override
        public String toString() {
            return "Rule : " + name + "; Action : " + action + "; Trigger : " + trigger + "; Status : " + status;
        }
        
    public boolean isVerifiedRule(){
        if(sleep==0 || !action.isFired())
            return trigger.isVerified() && status ;
        if(fireOnce=true)
            return trigger.isVerified() && status && action.isFired();
        else{
            
            return trigger.isVerified() && status && sleepCheck();
        }
    }
    public void fire(){
        action.fire();
        if(!(sleep==0)){
            wakeUp= LocalDateTime.now().plusMinutes(sleep);
            System.out.print(wakeUp);
        }
        
    }
    public Boolean sleepCheck(){
        return LocalDateTime.now().compareTo(wakeUp)>=0;
    }
}
