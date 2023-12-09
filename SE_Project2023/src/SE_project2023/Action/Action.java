/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package SE_project2023.Action;

import java.io.Serializable;
import javafx.beans.Observable;

/**
 *
 * @author chris
 */
public interface Action extends Serializable {
    
    public boolean isFired();
    public void fire();
    public void add();
    public void remove();
    public Action getChild();
    
}
