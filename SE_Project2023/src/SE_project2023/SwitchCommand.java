/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023;

import java.util.HashMap;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author chris
 */
public class SwitchCommand implements Command {
    private HashMap<String, AnchorPane> anchorPanes;
    private String selectedAction;
    
    public SwitchCommand(HashMap<String,AnchorPane> m, String selectedAction) {
        this.anchorPanes = m;
        this.selectedAction = selectedAction;
    }

    
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
