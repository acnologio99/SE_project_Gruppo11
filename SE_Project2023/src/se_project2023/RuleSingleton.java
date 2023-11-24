/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package SE_project2023;

import SE_project2023.Action.Action;
import SE_project2023.Regole.Rule;
import SE_project2023.Trigger.Trigger;

/**
 *
 * @author giova
 */
public class RuleSingleton {
    private static RuleSingleton ruleSing;
    Rule r;
    private RuleSingleton() {
        r=new Rule();
    }
    public void setName(String Name){
        r.setName(Name);
    }
    public void setAction(Action a){
        r.setAction(a);
    }
    public void setTrigger(Trigger t){
        r.setTrigger(t);
    }
    public boolean getTrigger(){
        if(r.getTrigger() != null){
            return true;
        }else return false;
        
    }
    
    public boolean getAction(){
        if(r.getAction() != null){
            return true;
        }else return false;
        
    }
    
    public Rule getRule(){
        Rule temp=r;
        clearRule();
        return temp;
    }
    
    public void clearRule(){
        r=new Rule();
    }
    
    public static RuleSingleton getInstance() {
         if(ruleSing==null)
             ruleSing=new RuleSingleton();
         return ruleSing;
    }
    
  
}
