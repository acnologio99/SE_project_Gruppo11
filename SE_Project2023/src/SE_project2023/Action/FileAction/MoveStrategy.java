/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023.Action.FileAction;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * La classe MoveStrategy implementa l’interfaccia FileStrategy e la classe Serializable. 
 * Questa classe e' usata per le operazioni di spostamento di un file da un percorso 
 * sorgente a un percorso di destinazione. 
 * 
 * Il metodo execute sovrascritto, esegue lo spostamento, 
 * utilizzando i metodi della classe Files per spostare il file.
 * 
 * @author emanu
 */
public class MoveStrategy implements FileStrategy, Serializable {

    public MoveStrategy() {
    }

    @Override
    public void execute(File file, String sourcePath, String destinationPath) {
        try {
            System.out.println("File spostato in : "+
                    Files.copy(Paths.get(sourcePath),
                            Paths.get(destinationPath), StandardCopyOption.REPLACE_EXISTING));
            file.delete();
        } catch (IOException e) {
            System.out.println("File non trovato");
        }
    }

}
