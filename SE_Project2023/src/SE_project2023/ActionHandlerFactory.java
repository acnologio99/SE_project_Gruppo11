/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023;

import SE_project2023.Action.Action;

/**
 *
 * @author chris
 */
public class ActionHandlerFactory {
    private static ActionHandlerFactory actionHandlerFactory = null;
    private ActionHandler catchAll;


    public static ActionHandler createActionHandler(){


     class NestedCatchAllHandler extends ActionHandler { //Handler innestato che cattura tutte le richieste non verificate.
            public NestedCatchAllHandler(ActionHandler next) {
                super(next);
            }

            @Override
            public boolean fireAction(Action a) {
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
