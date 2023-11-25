package SE_project2023.Action;

import java.io.File;
import javafx.scene.control.Alert;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author emanu
 */
public class ActionAudio implements Action {

    private String path;
    private boolean isFired = false;
    private int flagAlert =0;
    public ActionAudio(String path) {
        this.path = path;
    }

    public ActionAudio() {
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
        
        
        File file = new File(this.path);
        Media media = new Media(file.toURI().toString());
        final MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        if(flagAlert==0){
            flagAlert=1;
            Alert alert;
            alert = new Alert(Alert.AlertType.INFORMATION, "Reminder");
            alert.setTitle("Reminder");
            alert.setHeaderText(null);
            alert.setContentText("Playing choosen audio...");
            alert.showAndWait();
        }
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
        return "ActionAudio";
    }

}
