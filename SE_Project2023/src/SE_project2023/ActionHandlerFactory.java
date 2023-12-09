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
public class ActionHandlerFactory {
    
    
    public static ActionHandler createActionHandler(){
     
       
     class NestedCatchAllHandler extends ActionHandler { //Handler innestato che cattura tutte le richieste non vrificate.
            public NestedCatchAllHandler(ActionHandler next) {
                super(next);
            }
           
            @Override
            public boolean fireAction(Rule r) {
                return false;
            }
     }
        
        
     ActionHandler catchAll = new NestedCatchAllHandler(null);
     ActionHandler msg =new MessageHandler(catchAll);
     ActionHandler aud = new AudioHandler(msg);
     return new ProgramHandler(aud);
    }
}
