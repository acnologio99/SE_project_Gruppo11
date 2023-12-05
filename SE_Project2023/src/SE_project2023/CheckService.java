/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023;

import SE_project2023.Regole.Rule;
import javafx.application.Platform;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.control.TableView;
import javafx.util.Duration;

/**
 *
 * @author giova
 */
public class CheckService extends ScheduledService {
    private RuleList r= RuleList.getRuleList();
    private TableView tv;

    public CheckService(TableView tv) {
        this.tv=tv;
    }
    
        @Override
        protected Task<Void> createTask() {
            return new Task<Void>() {
                @Override
                protected Void call() {
                    // Codice per controllare la lista
                    System.out.println("Controllo lista...");

                    for (Rule r : r.getArrayList()) {
                        if (r.ruleIsValid()) {
                            Platform.runLater(() -> {
                                if (r.isVerifiedRule()) {
                                    r.fire();
                                    r.deactive();
                                   
                                    
                                    
                                }
                            });
                        }
                    }

                    return null;
                }
            };
        }
    }

    


   

