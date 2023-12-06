/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023;

import SE_project2023.Action.Action;
import SE_project2023.Action.FileAction;
import SE_project2023.Action.MessageBoxAction;
import SE_project2023.Regole.Rule;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Platform;
import javafx.scene.control.Alert;

/**
 *
 * @author chris
 */
public class MessageHandler extends ActionHandler  {
    
    public MessageHandler(ActionHandler next) {
        super(next);
        
        
    }

    @Override
    public void fireAction(Rule r) {
        
        if(!(r.getAction() instanceof MessageBoxAction) && next!=null){
            next.fireAction(r);
        }else{
        MessageBoxAction act = (MessageBoxAction) r.getAction();
        Platform.runLater(() -> {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(act.getMsg());
        alert.showAndWait();
        });}
        
    }

   
    
    
    
}
