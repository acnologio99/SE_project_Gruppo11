/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023.Action;

import SE_project2023.Action.FileAction.FileAction;
import SE_project2023.Action.FileAction.StrategyFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author giova
 */
/*
E' una Factory che in base al nome dell'azione scelta crea l'azione corrispondente

*/
public class ActionFactory {
    String selected;
    private Map<String, ArrayList<String>> creators = new HashMap<>();
    public ActionFactory(String selected, HashMap creator){
        this.selected = selected;
        this.creators= creator;
    }
    /*
    metodo per la creazione delle action, vi è un controllo sull'azione da creare
    ed un controllo sui parametri da utilizzare che nel caso in cui siano vuoti 
    ritorna un oggetto vuoto.
    */
    public Action create(){
        for(String s: creators.get(selected))
            if(s.isEmpty()){
                return null;
            }
        switch (selected){
            case "TextBox Action":
                return new MessageBoxAction(creators.get(selected).get(0));
            case "Audio Action":
                return new AudioAction(creators.get(selected).get(0));
            case "File Action":
                StrategyFactory sf = new StrategyFactory();
                return new FileAction(creators.get(selected).get(0),creators.get(selected).get(1),sf.getStrategy(creators.get(selected).get(2)));
            case "Append Action":
                return new FileAppendAction(creators.get(selected).get(0),creators.get(selected).get(1));
            case "Program Action":
                return new ProgramAction(creators.get(selected).get(0),Arrays.asList(creators.get(selected).get(1)));
        }
        return null;

    }
}