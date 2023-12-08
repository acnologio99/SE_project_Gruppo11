/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 *
 * @author chris
 */
public class LoadService extends Service {

    @Override
    protected Task createTask() {
        return new Task<Void>(){
            @Override
            protected Void call() throws Exception {
                Thread load = new Thread(new LoadThread());
                load.start();
                load.join();
                return null;
                
            }
            
    };
    
}
}
