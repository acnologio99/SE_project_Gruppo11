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
public class FileSizeTrigger implements Trigger{
    File file;
    int Dimension;
    @Override
    public boolean isVerified() {
        return file.length()==Dimension;
    }
    
}
