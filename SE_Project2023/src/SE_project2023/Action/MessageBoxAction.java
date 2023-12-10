/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023.Action;

import java.io.Serializable;

/**
 *
 * @author chris
 */
public class MessageBoxAction implements Action, Serializable {

    private String msg;
    private Boolean isFired = false;

    public MessageBoxAction(String msg) {
        if (msg == null) {
            this.msg = "";
        } else {
            this.msg = msg;
        }
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        if (msg == null) {
            this.msg = "";
        } else {
            this.msg = msg;
        }
    }

    @Override
    public boolean isFired() {
        //deve controllare fire
        return this.isFired;
    }

    @Override
    public void fire() {
        System.out.println(this.msg); //Stampa il messaggio a video.
        this.isFired = true;
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
        return "messaggio: " + msg;
    }

}
