package SE_project2023.Action.FileAction;

import SE_project2023.Action.Action;
import java.io.File;
import java.io.Serializable;

/**
 * La classe FileAction è progettata per rappresentare un’azione che può essere
 * eseguita su un file. Implementa l’interfaccia Action e la classe
 * Serializable, per consentirne il salvataggio per un'altra sessione.
 *
 * La classe gestisce le operazioni sui file, come lo spostamento, la copia e la
 * rimozione da un percorso sorgente a un percorso di destinazione, utilizzando
 * una strategia definita dall’interfaccia FileStrategy.
 *
 * @author emanu
 */
public class FileAction implements Action, Serializable {

    private String sourcePath;// Percorso sorgente del file 
    private String destinationPath; // Percorso dela cartella di destinazione
    private boolean isFired = false; //tiene traccia se l'azione è stata attivata
    private FileStrategy fileStrategy; // Strategia da utilizzare per la manipolazione del file

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
        if (!destinationPath.isEmpty()) {// Se il percorso di destinazione non è vuoto, 
            //aggiunge un separatore di percorso alla fine
            this.destinationPath = destinationPath + "\\";
            this.sourcePath = sourcePath;
            this.fileStrategy = fileStrategy;
        } else {
            this.sourcePath = sourcePath;
            this.fileStrategy = fileStrategy;
        }
    }

    //Getter e Setter
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

    //Esegue l'azione in base alla strategia impostata
    @Override
    public void fire() {
        if (this.getSourcePath() != null) {
            File file = new File(this.sourcePath);

            if (this.destinationPath != null) {//Per le operazioni di spostamento e copia del file,
                //e' necessario che il path di destinazione abbia il nome del file alla fine del percorso
                if (this.destinationPath.lastIndexOf("\\") == (this.destinationPath.length() - 1)) {
                    this.destinationPath += file.getName();
                }
            }

            if (fileStrategy != null) {
                fileStrategy.execute(file, this.sourcePath, this.destinationPath);
                this.isFired = true;
            }
        }

    }

    //I metodi 'add', 'remove' e 'getChild' non sono supportati e lanciano UnsupportedOperationException.
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
