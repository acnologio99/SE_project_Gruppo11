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
    private static ActionHandlerFactory actionHandlerFactory = null;
    private ActionHandler catchAll;
    
    private ActionHandlerFactory() {
        
        class NestedCatchAllHandler extends ActionHandler {
            public NestedCatchAllHandler(ActionHandler next) {
                super(next);
            }

            @Override
            public boolean fireAction(Rule r) {
                return false;
            }
        }
        catchAll = new NestedCatchAllHandler(null);

    }
    
    public static ActionHandlerFactory getInstance() {
        if (actionHandlerFactory == null) {
            actionHandlerFactory = new ActionHandlerFactory();
        }
        return actionHandlerFactory;
    }
    
    /*public ActionHandler createActionHandler(ActionHandler acHandl) {
        
        ActionHandler acHandl = new MessageHandler(catchAll);
        return new AudioHandler(msg);
        
    }*/
}

