/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023.Action.FileAction;

import java.io.File;
import java.io.Serializable;

/**
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
