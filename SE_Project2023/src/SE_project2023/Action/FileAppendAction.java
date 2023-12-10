/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023.Action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chris
 */
public class FileAppendAction implements Action, Serializable {

    private String path;
    private String msg;
    private boolean isFired = false;
    private File file;

    //Se il file non esiste lo crea.
    public FileAppendAction(String path, String msg) {
        this.path = path;
        this.msg = msg;
        file = new File(path);
        if (!file.exists()) {
            try {
                // Se il file non esiste, crea un nuovo file con il nome specificato
                file.createNewFile();
                System.out.print("Nuovo file creato in " + file.getPath());
            } catch (IOException ex) {
                Logger.getLogger(FileAppendAction.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    public String getPath() {
        return path;
    }

    public String getMsg() {
        return msg;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public boolean isFired() {
        return this.isFired;
    }

    @Override
    public void fire() {
        
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true)); //True significa che lo scrive in append.
            bw.write(msg);
            bw.close();
            System.out.println("Append riuscita");
            isFired=true;
        } catch (IOException ex) {
            Logger.getLogger(FileAppendAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    

    @Override
    public void add() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Action getChild() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "FileAppend: " + path + ", msg=" + msg ;
    }
    
    

}
