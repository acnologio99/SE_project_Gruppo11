/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023.Handlers;

import SE_project2023.Action.Action;

/**
 * Factory della chain of responsability con un Handler innestato per gestire tutte le richieste
 * che gli handler precedenti non possono catturare.
 * @author chris
 */
public class ActionHandlerFactory {

    public static ActionHandler createActionHandler() {

        class NestedCatchAllHandler extends ActionHandler { // Handler innestato che cattura tutte le richieste non
                                                            // verificate.
            public NestedCatchAllHandler(ActionHandler next) {
                super(next);
            }

            @Override
            public boolean fireAction(Action a) {
                return false;
            }
        }

        ActionHandler catchAll = new NestedCatchAllHandler(null); //Costruzione della catena.
        ActionHandler msg = new MessageHandler(catchAll);
        ActionHandler prog = new ProgramHandler(msg);
        return new AudioHandler(prog);
    }
}
