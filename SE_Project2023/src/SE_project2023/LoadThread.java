/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023;

/**
 *
 * @author chris
 */
public class LoadThread implements Runnable {

    @Override
    public void run() {
        RuleList.getRuleList().loadRules("rules.bin");
    }
    
}
