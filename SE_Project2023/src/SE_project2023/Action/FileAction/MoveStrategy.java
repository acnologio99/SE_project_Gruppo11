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
            e.printStackTrace();
        }
    }

}
