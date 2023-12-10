/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023.Action.FileAction;

import java.io.File;
import java.io.Serializable;

/**
 *La classe RemoveStrategy implementa l’interfaccia FileStrategy e la classe Serializable. 
 * Questa classe e' usata per le operazioni di rimozione di un file da un percorso sorgente. 
 * 
 * Il metodo execute sovrascritto, esegue la rimozione, 
 * stampando in console il risutato dell'operazione.
 * 
 * @author emanu
 */
public class RemoveStrategy implements FileStrategy, Serializable {

    public RemoveStrategy() {
    }

    @Override
    public void execute(File file, String sourcePath, String destinationPath) {
        if (file.delete()) {
            System.out.println("File cancellato con successo");
        } else {
            System.out.println("Errore durante la cancellazione del file");
        }
    }

}
