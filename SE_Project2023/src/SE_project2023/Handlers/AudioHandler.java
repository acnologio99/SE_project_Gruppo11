/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023.Handlers;

import SE_project2023.Action.Action;
import SE_project2023.Action.AudioAction;
import SE_project2023.AlertUtil;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.util.Duration;

/**
 *
 * 
 * @author chris
 */
public class AudioHandler extends ActionHandler {

    public AudioHandler(ActionHandler next) {
        super(next);
    }

    @Override
    public boolean fireAction(Action a) {
        if (!(a instanceof AudioAction) && next != null) {

            next.fireAction(a);
        } else {
            AudioAction act = (AudioAction) a;
            Platform.runLater(() -> {
                
                Alert alert = AlertUtil.informationAlert("Reminder", "Playing choosen audio...");
                
                Duration duration = Duration.seconds(9);    //L'allert dura 9 secondi dopodichè si chiude automaticamente.
                PauseTransition transition = new PauseTransition(duration);
                transition.setOnFinished(event -> {
                    if (alert.isShowing()) {
                        alert.close();
                    }
                });
                transition.play();
                
                alert.showAndWait();
                act.stopClip();
            });
        }
        return true;
    }

}
