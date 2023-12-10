/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023;

import SE_project2023.Action.Action;
import SE_project2023.Action.ProgramAction;
import javafx.application.Platform;
import javafx.scene.control.Alert;

/**
 *
 * @author cauro
 */
public class ProgramHandler extends ActionHandler {

    public ProgramHandler(ActionHandler next) {
        super(next);
    }

    @Override
    public boolean fireAction(Action a) {
        if (!(a instanceof ProgramAction) && next != null) {

            System.out.println("dentro");
            next.fireAction(a);

        } else {
            ProgramAction act = (ProgramAction) a;
            Platform.runLater(() -> {
                Alert alert = AlertUtil.informationAlert("Information Dialog", act.getOutput());
                alert.showAndWait();
            });
        }
        return true;
    }

}
