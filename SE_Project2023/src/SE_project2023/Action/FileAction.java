package SE_project2023.Action;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javafx.scene.control.Alert;

/**
 *
 * @author emanu
 */
public class FileAction implements Action {

    private String sourcePath;
    private String destinationPath;
    private boolean isFired = false;
    private String action;

    public FileAction() {
    }

    public FileAction(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public FileAction(String sourcePath, String destinationPath) {
        this.sourcePath = sourcePath;
        this.destinationPath = destinationPath;
    }

    public FileAction(String sourcePath, String destinationPath, String action) {
        if (!"".equals(destinationPath)) {
            this.destinationPath = destinationPath + "\\";
            this.sourcePath = sourcePath;
            this.action = action;
        } else {
            this.sourcePath = sourcePath;
            this.action = action;
        }
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public String getDestinationPath() {
        return destinationPath;
    }

    public void setDestinationPath(String destinationPath) {
        this.destinationPath = destinationPath;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public boolean isFired() {
        return this.isFired;
    }

    @Override
    public void fire() {
        File file = new File(this.sourcePath);
        Alert alert;
        String mess = " choosen file...";
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Operating on file");
        alert.setHeaderText(null);

        if (this.destinationPath.lastIndexOf("\\") == (this.destinationPath.length() - 1)) {
            this.destinationPath += "\\" + file.getName();
        }
        switch (action) {
            case "copy":
                copyF();
                alert.setContentText("Copying" + mess);
                break;
            case "move":
                copyF();
                removeF(file);
                alert.setContentText("Moving" + mess);
                break;
            case "remove":
                removeF(file);
                alert.setContentText("Removing" + mess);
                break;
            default:
                break;
        }
        this.isFired = true;
        alert.show();
    }

    private void copyF() {
        try {
            System.out.println(
                    Files.copy(Paths.get(this.sourcePath),
                            Paths.get(this.destinationPath), StandardCopyOption.REPLACE_EXISTING));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeF(File file) {
        if (file.delete()) {
            System.out.println("File cancellato con successo");
        } else {
            System.out.println("Errore durante la cancellazione del file");
        }
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
        return "FileAction : " + "source : " + sourcePath + "; destination : " + destinationPath;
    }

}
