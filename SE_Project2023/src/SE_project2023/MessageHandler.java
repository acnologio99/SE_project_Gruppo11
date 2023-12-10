/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023;

import SE_project2023.Action.Action;
import SE_project2023.Action.MessageBoxAction;
import javafx.application.Platform;
import javafx.scene.control.Alert;

/**
 * Handler della catena che gestisce MessageBoxAction mostrano un'allert con il messaggio contenuto nell'azione.
 * @author chris
 */
public class MessageHandler extends ActionHandler {

    public MessageHandler(ActionHandler next) {
        super(next);

    }

    @Override
    public boolean fireAction(Action a) {
        System.out.println(a instanceof MessageBoxAction);
        if (!(a instanceof MessageBoxAction) && next != null) {

            next.fireAction(a);

        } else {
            MessageBoxAction act = (MessageBoxAction) a;
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText(act.getMsg());
                alert.showAndWait();
            });
        }
        return true;
    }

}
