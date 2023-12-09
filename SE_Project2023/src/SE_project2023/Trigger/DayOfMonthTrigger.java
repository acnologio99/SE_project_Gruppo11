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
public class DayOfMonthTrigger implements Trigger {

    private int dOM;

    public DayOfMonthTrigger(int d) {
        this.dOM = d;
    }

    @Override
    public boolean isVerified() {
        return LocalDate.now().getDayOfMonth() == dOM;
    }

    @Override
    public String toString() {
        return "DayOfMonthTrigger: " + dOM;
    }

}
