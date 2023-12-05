/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023;

import SE_project2023.Action.MessageBoxAction;
import SE_project2023.Regole.Rule;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.control.Alert;

/**
 *
 * @author chris
 */
public class MessageHandler extends ActionHandler implements Observer {
    
    public MessageHandler(ActionHandler handler) {
        super(handler);
        RuleList.getRuleList().addObserver(this);
    }

    @Override
    public void handleRequest(Rule r) {
        System.out.println("dentro");
        if(r.getAction().getClass().isInstance(new MessageBoxAction(""))){
            
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("ciao");
        alert.showAndWait();
        }else{
            next.handleRequest(r);
        }
        
    }

    @Override
    public void update(Observable o, Object arg) {
        handleRequest((Rule) o);
    }
    
    
    
}
