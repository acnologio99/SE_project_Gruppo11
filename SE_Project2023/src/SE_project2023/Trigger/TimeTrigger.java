/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023.Trigger;

import java.time.LocalTime;

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
        LocalTime now = LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute());
        return now.equals(this.timeOfDay);
    }

    @Override
    public String toString() {
        return "TimeTrigger : " + this.timeOfDay;
    }

}
