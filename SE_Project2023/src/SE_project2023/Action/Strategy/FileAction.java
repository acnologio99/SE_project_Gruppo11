package SE_project2023.Action.Strategy;

import SE_project2023.Action.Action;
import java.io.File;
import java.io.Serializable;

/**
 *
 * @author emanu
 */
public class FileAction implements Action, Serializable {

    private String sourcePath;
    private String destinationPath;
    private boolean isFired = false;
    private FileStrategy fileStrategy;

    public FileAction() {
    }

    public FileAction(String sourcePath) {
        this.sourcePath = sourcePath;
    }

    public FileAction(String sourcePath, String destinationPath) {
        this.sourcePath = sourcePath;
        this.destinationPath = destinationPath;
    }

    public FileAction(String sourcePath, String destinationPath, FileStrategy fileStrategy) {
        if (!destinationPath.isEmpty()) {
            this.destinationPath = destinationPath + "\\";
            this.sourcePath = sourcePath;
            this.fileStrategy = fileStrategy;
        } else {
            this.sourcePath = sourcePath;
            this.fileStrategy = fileStrategy;
        }
    }

    public FileStrategy getFileStrategy() {
        return fileStrategy;
    }

    public void setFileStrategy(FileStrategy fileStrategy) {
        this.fileStrategy = fileStrategy;
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

    @Override
    public boolean isFired() {
        return this.isFired;
    }

    @Override
    public void fire() {
        File file = new File(this.sourcePath);
        String mess = " choosen file...";

        if (this.destinationPath != null) {
            if (this.destinationPath.lastIndexOf("\\") == (this.destinationPath.length() - 1)) {
                this.destinationPath += "\\" + file.getName();
            }
        }

        if (fileStrategy != null) {
            fileStrategy.execute(file, this.sourcePath, this.destinationPath);
        }
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
        return "source : " + sourcePath + "; destination : " + destinationPath;
    }

}
