/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023;

import SE_project2023.Action.AudioAction;
import SE_project2023.Action.FileAction;
import SE_project2023.Regole.Rule;
import java.io.File;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author chris
 */
public class AudioHandler extends ActionHandler {

    public AudioHandler(ActionHandler next) {
        super(next);
    }

    @Override
    public void fireAction(Rule r) {
        if (!(r.getAction() instanceof AudioAction) && next != null) {

            next.fireAction(r);
        } else {
            Platform.runLater(() -> {
            AudioAction act = (AudioAction) r.getAction();
            File file = new File(act.getPath());

            Media media = new Media(file.toURI().toString());

            final MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();

            Alert alert;
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Reminder");
            alert.setHeaderText(null);
            alert.setContentText("Playing choosen audio...");
            alert.showAndWait();

            mediaPlayer.stop();
            });}
    }

}