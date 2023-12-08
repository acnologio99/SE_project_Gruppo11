package SE_project2023.Action;

import java.io.Serializable;

/**
 *
 * @author emanu
 */
public class AudioAction implements Action, Serializable {

    private String path;
    private boolean isFired = false;
    private int flagAlert = 0;

    public AudioAction(String path) {
        this.path = path;
    }

    public AudioAction() {
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean isFired() {
        return this.isFired;
    }

    @Override
    public void fire() {
        // Crea un oggetto File con il percorso memorizzato nel campo path
        System.out.println("music!");
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
        return "Audio : " + this.path;
    }

}
