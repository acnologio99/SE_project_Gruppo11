/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023;

import SE_project2023.Regole.Rule;

/**
 *
 * @author chris
 */
public abstract class ActionHandler {
    ActionHandler next;

    public ActionHandler(ActionHandler next) {
        this.next = next;
    }
    
    
    
   public abstract void handleRequest(Rule r);
        
    
}
