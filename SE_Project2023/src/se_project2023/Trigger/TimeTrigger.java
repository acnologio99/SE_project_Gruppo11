/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023.Trigger;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author cauro
 */
public class TimeTrigger implements Trigger {

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
        LocalTime now = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedTime = now.format(formatter);
        return formattedTime.equals(this.timeOfDay.toString());
    }

    @Override
    public String toString() {
        return "TimeTrigger";
    }

}
