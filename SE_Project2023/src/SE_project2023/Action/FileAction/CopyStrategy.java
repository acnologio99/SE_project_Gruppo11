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
 * La classe CopyStrategy implementa l’interfaccia FileStrategy e la classe Serializable. 
 * Questa classe e' usata per le operazioni di copia di un file da un percorso 
 * sorgente a un percorso di destinazione. 
 * 
 * Il metodo execute sovrascritto, esegue la copia del file, 
 * utilizzando i metodi della classe Files per copiare il file, 
 * e sostituirlo in caso esista un file con lo stesso nome nella destinazione.
 * 
 * @author emanu
 */
public class CopyStrategy implements FileStrategy, Serializable {

    public CopyStrategy() {
    }
    
    @Override
    public void execute(File file, String sourcePath, String destinationPath) {
        try {
            System.out.println("File copiato in : "+
                    Files.copy(Paths.get(sourcePath),
                            Paths.get(destinationPath), StandardCopyOption.REPLACE_EXISTING));

        } catch (IOException e) {
            System.out.println("File non trovato");
        }
    }
    
}
