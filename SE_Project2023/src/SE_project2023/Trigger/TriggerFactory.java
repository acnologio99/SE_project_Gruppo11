/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023.Trigger;

import java.io.File;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author giova
 */
public class TriggerFactory {
    String selected;
    private Map<String, ArrayList<String>> creators = new HashMap<>();
    public TriggerFactory(String selected, HashMap creator){
        this.selected = selected;
        this.creators= creator;
    }
    /*
    metodo per la creazione delle action, vi è un controllo sull'azione da creare
    ed un controllo sui parametri da utilizzare che nel caso in cui siano vuoti 
    ritorna un oggetto vuoto.
    */
    public Trigger create(){
        for(String s: creators.get(selected))
            if(s.isEmpty()){
                return null;
            }
        switch (selected){
            case "Time Trigger":
                return new TimeTrigger(LocalTime.of(Integer.parseInt(creators.get(selected).get(0)), Integer.parseInt(creators.get(selected).get(1))));
            case "Day of Week Trigger":
                return new DayOfWeekTrigger(DayOfWeek.valueOf(creators.get(selected).get(0)));
            case "Day of Month Trigger":
                return new DayOfMonthTrigger(Integer.parseInt(creators.get(selected).get(0)));
            case "Day of Year Trigger":
                return new DayOfYearTrigger(LocalDate.parse(creators.get(selected).get(0)));
            case "File Trigger":
                return new FileSizeTrigger(new File(creators.get(selected).get(0)),Integer.parseInt(creators.get(selected).get(1)));
            case "File in a Dir Trigger":
                return new FileInADirTrigger(new File(creators.get(selected).get(0)),creators.get(selected).get(1));
        }
        return null;

    }
}
