/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023.Trigger;

import java.time.LocalTime;
<<<<<<< Updated upstream
=======
import java.time.format.DateTimeFormatter;
>>>>>>> Stashed changes

/**
 *
 * @author cauro
 */
public class TimeTrigger implements Trigger{

    private LocalTime timeOfDay;

    public TimeTrigger(LocalTime timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public LocalTime getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(LocalTime timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    @Override
    public boolean isVerified() {
<<<<<<< Updated upstream
    LocalTime ld = LocalTime.now();
        return ld.equals(this.timeOfDay);
=======
    LocalTime now = LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute());
    return now.equals(this.timeOfDay);
>>>>>>> Stashed changes
    }

  
    
}
