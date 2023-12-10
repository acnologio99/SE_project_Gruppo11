/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023;

import java.util.HashMap;
import javafx.scene.layout.AnchorPane;

/**
 * Command utilizzato dall' ActionController e dal TriggerController, ogni volta che si passa da un'azione a quella successiva nella lista
 * Gli elementi dell'azione precedente vengono nascosti e vengono mostrati quelli dell'azione selezionata.
 * Riceve come parametri il nome dell'azione (o del trigger) selezionati nella lista e il corrispondente AnchorPane.
 * @author chris
 */
public class SwitchCommand implements Command {
    private HashMap<String, AnchorPane> anchorPanes;
    private String selectedAction;
    
    public SwitchCommand(HashMap<String,AnchorPane> panels, String selectedAction) {
        this.anchorPanes = panels;
        this.selectedAction = selectedAction;
    }

    //Controlla gli anchorPane nella mappe e li imposta come non visibili, successivamente controlla se l'azione
    //selezionata è presente nella mappe. Se è presente attiva la visibilità dell'AnchorPane corrispondente, di conseguenza
    //saranno mostrati tutti i suoi elementi.
    @Override
    public void execute() {
         for (AnchorPane pane : anchorPanes.values()) {
            pane.setVisible(false);
        }
        if (anchorPanes.containsKey(selectedAction)) {
            anchorPanes.get(selectedAction).setVisible(true);
        }
    }
    
}
