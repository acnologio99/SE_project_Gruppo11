/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023;

/**
 * Thread di caricamento delle regole in RuleList, chiama la funzione loadRule nella classe RuleList.
 * La funzione viene richiamata in un thread in quanto le regole da caricare potrebbero essere tante e bloccare il programma.
 * @author chris
 */
public class LoadThread implements Runnable {

    @Override
    public void run() {
            RuleList.getRuleList().loadRules("rules.bin");
        }
}
    
