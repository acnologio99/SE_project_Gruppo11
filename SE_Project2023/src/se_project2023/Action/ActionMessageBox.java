/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023.Action;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author chris
 */
public class ActionMessageBox implements Action {
    
    private String msg;
    private Boolean fired=false;
    
    public ActionMessageBox(String msg) {
        if(this.msg==null)
        this.msg = "";
        else
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg){
        if(msg == null){
        this.msg="";
        }else
        this.msg = msg;
    }
    
    @Override
    public void fire() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialogue");
        alert.setHeaderText("Message");
        alert.setContentText(msg);
        alert.showAndWait();
        this.fired=true;
        
        
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
        return "ActionMessageBox: "+ msg;
    }

    @Override
    public boolean isFired() {
        return this.fired;
    }
    
    
    
}
