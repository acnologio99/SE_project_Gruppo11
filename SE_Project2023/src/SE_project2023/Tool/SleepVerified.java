/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023.Tool;

import SE_project2023.Regole.Rule;
import java.time.LocalDateTime;

/**
 *
 * @author giova
 */
public class SleepVerified implements VerifiedTool{
    public SleepVerified() {
    }
    
    @Override
    public boolean verified(Rule r){
            boolean a=LocalDateTime.now().compareTo(r.getWakeUp())>=0;
            System.out.println("La regola "+ r.getName() +" ripartirà a "+ r.getWakeUp());
            if(a)
                r.setWakeUp(LocalDateTime.now().plusMinutes(r.getSleep()));
            return a;
       }

    
}
