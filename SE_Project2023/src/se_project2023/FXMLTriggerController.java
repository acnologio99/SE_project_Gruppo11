/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package SE_project2023;

import SE_project2023.Action.Action;
import SE_project2023.Trigger.TimeTrigger;
import SE_project2023.Trigger.Trigger;
import com.jfoenix.controls.JFXTimePicker;
import java.io.File;
import java.net.URL;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author giova
 */
public class FXMLTriggerController implements Initializable {

    @FXML
    private ListView<String> triggerListView;
    @FXML
    private JFXTimePicker timePicker;
    @FXML
    private Button Done;
    @FXML
    private Button Cancel;
    
    
    ObservableList<Trigger> triggerList;
    RuleSingleton r;
    @FXML
    private Button fileTriggerButton;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        HashSet<Trigger> triggers = new HashSet();
        triggerList = FXCollections.observableArrayList(triggers);
        
        r=RuleSingleton.getInstance();
        
        
       triggerListView.getItems().addAll(
                "Time Trigger"
                      
        );

        // Imposta l'iniziale visibilità del timePicker e del fileTriggerButton su false
        timePicker.setVisible(false);
        fileTriggerButton.setVisible(false);

        // Aggiungi un listener per gestire la selezione della ListView
        triggerListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                handleTriggerSelection(newValue); // Gestisci la selezione dell'opzione
            }
        });

        // Aggiungi un listener per controllare l'orario selezionato
        timePicker.setOnAction(event -> {
            if (timePicker.getValue() != null) {
                checkSelectedTime(timePicker.getValue()); // Chiamata alla funzione esterna per controllare l'orario selezionato
            }
        });
    }

    private void checkSelectedTime(LocalTime selectedTime) {
        // Qui puoi eseguire le azioni desiderate in base all'orario selezionato
        System.out.println("Orario selezionato: " + selectedTime);
        // Esegui la tua logica qui con l'orario selezionato
    }

    private void handleTriggerSelection(String selectedTrigger) {
        if ("Time Trigger".equals(selectedTrigger)) {
            timePicker.setVisible(true);
            fileTriggerButton.setVisible(false);
        } else if ("Program Trigger".equals(selectedTrigger)) {
            timePicker.setVisible(false);
            fileTriggerButton.setVisible(false);
        } else if ("File Trigger".equals(selectedTrigger)) {
            timePicker.setVisible(false);
            fileTriggerButton.setVisible(true);
        }
    }
   
    @FXML
    public LocalTime timePick(ActionEvent event) {
        LocalTime temp = timePicker.getValue();
        return temp;
    }

    @FXML
    private void doneTrigger(ActionEvent event) {
        LocalTime temp = timePick(event);
        TimeTrigger t = new TimeTrigger(temp);
        triggerList.add(t);
        r.setTrigger(t);
        Node sourceNode = (Node) event.getSource();
        Stage stage = (Stage) sourceNode.getScene().getWindow();

        // Chiudi la finestra corrente
        stage.close();
        // Chiudi la finestra corrente        
    }

    @FXML
    private void cancelTrigger(ActionEvent event) {
        Node sourceNode = (Node) event.getSource();
        Stage stage = (Stage) sourceNode.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void selectFolder(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Seleziona una cartella");
        File selectedDirectory = directoryChooser.showDialog(fileTriggerButton.getScene().getWindow());

        if (selectedDirectory != null) {
            // Fa qualcosa con la cartella selezionata
            System.out.println("Cartella selezionata: " + selectedDirectory.getAbsolutePath());
        }
    }

     private void alertShow(String title, String header, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }
}
