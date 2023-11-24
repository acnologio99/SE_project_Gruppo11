/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package SE_project2023;

/**
 *
 * @author chris
 */
public interface Action {
    
    public boolean isFired();
    public void fire();
    public void add();
    public void remove();
    public Action getChild();
    
}
