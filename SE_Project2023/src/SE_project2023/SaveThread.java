/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author chris
 */
public class SaveThread implements Runnable, Observer {

    private RuleList rules;

    public SaveThread() {
        rules = RuleList.getRuleList();
        rules.addObserver(this);

    }

    @Override
    public void run() {
        System.out.println("Salvo");
        RuleList.getRuleList().saveRules("rules.bin");
    }

    @Override
    public void update(Observable o, Object arg) {
        this.run(); //Save every time the ruleList gets an update.
    }

}
