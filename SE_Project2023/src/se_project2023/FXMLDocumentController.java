package SE_project2023;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */

import SE_project2023.Regole.Rule;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author giova
 */
public class FXMLDocumentController implements Initializable {

    private Label label;
    @FXML
    private Button saveButton;
    @FXML
    private Button loadButton;
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;
    @FXML
    private ListView<Rule> listView;
    
    
    
    @FXML
    private AnchorPane triggerPane;
    @FXML
    private Button timeTriggerBtn;
    @FXML
    private Button cancelBtnTrigger;
   

    ObservableList<Rule> ruleList;
    
    RuleList rules = RuleList.getRuleList();

    //lista che mostra le azioni scelte
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //inizializzazione Liste
        ruleList = FXCollections.observableArrayList(rules.getHashRules());
        //setting selezione multipla 
        listView.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
        //aggiunta regola di testing
        //setting View
        listView.setItems(ruleList);
        
        // Creazione del servizio per controllare la lista ogni 10 secondi
        ScheduledService<Void> service = new ScheduledService<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() {
                        // Codice per controllare la lista
                        System.out.println("Controllo lista...");
                        
                        for(Rule r: ruleList){
                            System.out.println(r);
                            Platform.runLater(() -> {
                            //if(r.isVerifiedRule()) {
                                r.action.fire();
                            //}
                            });
                            
                        }
                            
                        // Esempio: Aggiungi un nuovo elemento alla lista ogni 10 secondi
                        return null;
                    }
                };
            }
        };

        // Imposta l'intervallo di esecuzione del servizio a 10 secondi
        service.setPeriod(Duration.seconds(10));
        // Avvia il servizio
        service.start();
    }
    @FXML
    private void saveRules(ActionEvent event) {
    }
    @FXML
    private void loadButton(ActionEvent event) {
    }
    @FXML
    private void removeRules(ActionEvent event) {
        ruleList.removeAll(listView.getSelectionModel().getSelectedItems());

    }


    private void addTrigger(ActionEvent event) {
        triggerPane.setVisible(true);
    }

    @FXML
    private void timeTrigger(ActionEvent event) {
        
    }
    @FXML
    private void CancelTrigger(ActionEvent event) {
        triggerPane.setVisible(false);
    }
    private void alertShow(String title, String header, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }
    @FXML
    private void addRule(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLRule.fxml"));
            
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("RuleCretor");
            stage.setScene(new Scene(root));
            stage.showAndWait();
            
            RuleSingleton r=RuleSingleton.getInstance();
            ruleList.add(r.getRule());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }
    
   
}
