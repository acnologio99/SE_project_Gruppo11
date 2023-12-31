/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023.Handlers;

import SE_project2023.Action.Action;
import SE_project2023.Action.MessageBoxAction;
import SE_project2023.AlertUtil;
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
                Alert alert = AlertUtil.informationAlert("Information Dialog", act.getMsg());
                alert.showAndWait();
            });
        }
        return true;
    }

}
