/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023.Trigger;

import java.time.LocalDate;

/**
 *
 * @author giova
 */
public class DayOfYearTrigger implements Trigger {

    private LocalDate date;

    @Override
    public boolean isVerified() {
        return LocalDate.now().equals(date);
    }

    public DayOfYearTrigger(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DayOfYearTrigger: " + date;
    }

}
