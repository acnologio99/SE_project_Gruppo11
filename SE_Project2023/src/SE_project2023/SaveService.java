/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023;

import SE_project2023.Regole.SaveThread;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 *
 * @author chris
 */
public class SaveService extends Service {

    @Override
    protected Task createTask() {
        return new Task<Void>(){
            @Override
            protected Void call() throws Exception {
                Thread save = new Thread(new SaveThread());
                save.start();
                save.join();
                return null;
                
            }
            
    };
    
}
}