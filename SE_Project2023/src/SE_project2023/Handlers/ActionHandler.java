/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023.Handlers;

import SE_project2023.Action.Action;

/**
 * Classe astratta per la chain of responsability delle azioni.
 * Viene usata dal controller per gestire la visibilità di allert o componenti javafx.
 * @author chris
 */
public abstract class ActionHandler {

    ActionHandler next;

    public ActionHandler(ActionHandler next) {
        this.next = next;
    }

    public abstract boolean fireAction(Action a); 

}
