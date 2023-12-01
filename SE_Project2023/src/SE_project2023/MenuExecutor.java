/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023;

/**
 * Invoker for commands
 * @author chris
 */
public class MenuExecutor {

    public MenuExecutor() {
    }
    
    public void execute(Command command){
        command.execute();
    }
    
}
