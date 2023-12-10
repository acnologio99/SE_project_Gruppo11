/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023.Trigger;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 *
 * @author giova
 */
public class DayOfWeekTrigger implements Trigger {

    private DayOfWeek dOW;

    @Override
    public boolean isVerified() {
        return LocalDate.now().getDayOfWeek().equals(dOW);
    }

    public DayOfWeekTrigger(DayOfWeek dOW) {
        this.dOW = dOW;
    }

    @Override
    public String toString() {
        return "DayOfWeekTrigger: " + dOW;
    }

}
