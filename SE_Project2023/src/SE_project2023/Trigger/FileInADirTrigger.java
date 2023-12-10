/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023.Trigger;

import java.io.File;

/**
 *
 * @author giova
 */
public class FileInADirTrigger implements Trigger {

    private String fileName;
    private File directory;

    public FileInADirTrigger(File fileName, String path) {
        this.fileName = fileName.getName();
        this.directory = new File(path);
    }

    @Override
    public boolean isVerified() {
        // Ottieni un array di file nella directory
            File[] files = directory.listFiles();
            boolean fileFound = false;
            if (files != null) {
                // Cerca il file desiderato nell'array
                
                for (File file : files) {
                    if (file.isFile() && file.getName().equals(fileName)) {
                        fileFound = true;
                        break;
                    }
                }
        
        }
    return fileFound;
    }
    @Override
    public String toString() {
        return "File In A Dir Trigger: " + "file= " + fileName + ", Dir= " + directory.getPath();
    }
}
