/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023;

import java.util.Observable;
import java.util.Observer;

/**
 * SaveThread è un thread di salvataggio che richiama la funzione "saveRules" di RuleList.
 * Il thread è Observer in quanto salva automaticamente ogni volta che c'è un cambiamento nella lista (RuleList)
 * e di conseguenza anche ogni volta che c'è un cambiamento nelle regole sottostanti.
 * @author chris
 */
public class SaveThread implements Runnable, Observer {

    private RuleList rules;

    public SaveThread() {
        rules = RuleList.getRuleList();
        rules.addObserver(this); //Diventa Observer di RuleList

    }

    @Override
    public void run() {
        System.out.println("Salvo");
        rules.saveRules("rules.bin");
    }

    @Override
    public void update(Observable o, Object arg) {
        this.run(); //Ogni volta che RuleList o una regola si aggiornano, il thread salva.
    }

}
