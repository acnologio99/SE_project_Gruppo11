package SE_project2023.Action;

import java.io.File;
import javafx.scene.control.Alert;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author emanu
 */
public class AudioAction implements Action {

    private String path;
    private boolean isFired = false;
    private int flagAlert = 0;

    public AudioAction(String path) {
        this.path = path;
    }

    public AudioAction() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean isFired() {
        return this.isFired;
    }

    @Override
    public void fire() {
        // Crea un oggetto File con il percorso memorizzato nel campo path
        File file = new File(this.path);
        // Crea un oggetto Media dal file 
        Media media = new Media(file.toURI().toString());
        // Crea un oggetto MediaPlayer con l'oggetto Media ed inizia a riprodurre l'audio
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        // Se flagAlert è 0, imposta flagAlert a 1 e crea un oggetto Alert
        if (flagAlert == 0) {
            flagAlert = 1;
            Alert alert;
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Reminder");
            alert.setHeaderText(null);
            alert.setContentText("Playing choosen audio...");
            alert.showAndWait();
        }
        //Ferma la riproduzione e setta isFired a true
        mediaPlayer.stop();
        this.isFired = true;
    }

    @Override
    public void add() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Action getChild() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String toString() {
        return "ActionAudio : " + this.path;
    }

}
