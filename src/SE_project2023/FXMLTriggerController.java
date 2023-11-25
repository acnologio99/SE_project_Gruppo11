/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package SE_project2023;

<<<<<<< Updated upstream
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
=======
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
>>>>>>> Stashed changes

/**
 * FXML Controller class
 *
 * @author giova
 */
public class FXMLTriggerController implements Initializable {

<<<<<<< Updated upstream
=======
    @FXML
    private ListView<String> triggerListView;
    private ComboBox<String> timeComboBox;
    @FXML
    private Button Done;
    @FXML
    private Button Cancel;
    
    ObservableList<Trigger> triggerList;
    RuleSingleton r;
    @FXML
    private Button fileTriggerButton;
    @FXML
    private ComboBox<String> timeComboBox1;
    @FXML
    private ComboBox<String> timeComboBox2;
    @FXML
    private AnchorPane comboBoxPane;
    
    private LocalTime temp;
>>>>>>> Stashed changes
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
<<<<<<< Updated upstream
        // TODO
    }    
    private void alertShow(String title, String header, String content, Alert.AlertType type) {
=======
        HashSet<Trigger> triggers = new HashSet();
        triggerList = FXCollections.observableArrayList(triggers);
        r=RuleSingleton.getInstance();
        populateTimeComboBox(timeComboBox1,24);
        populateTimeComboBox(timeComboBox2,60);
        triggerListView.getItems().addAll(
                "Time Trigger"
                      
        );
        
        temp = LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute());
        timeComboBox1.setPromptText(Integer.toString(LocalTime.now().getHour()));
        timeComboBox2.setPromptText(Integer.toString(LocalTime.now().getMinute()));

        // Imposta l'iniziale visibilità del timePicker e del fileTriggerButton su false
        comboBoxPane.setVisible(false);
        fileTriggerButton.setVisible(false);

        // Aggiungi un listener per gestire la selezione della ListView
        triggerListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                handleTriggerSelection(newValue); // Gestisci la selezione dell'opzione
            }
        });

       
            
      
    }

   /* private void checkSelectedTime(LocalTime selectedTime) {
        // Qui puoi eseguire le azioni desiderate in base all'orario selezionato
        System.out.println("Orario selezionato: " + selectedTime);
        // Esegui la tua logica qui con l'orario selezionato
    }*/

    private void handleTriggerSelection(String selectedTrigger) {
        if ("Time Trigger".equals(selectedTrigger)) {
            comboBoxPane.setVisible(true);
            fileTriggerButton.setVisible(false);
        } else if ("Program Trigger".equals(selectedTrigger)) {
            comboBoxPane.setVisible(false);
            fileTriggerButton.setVisible(false);
        } else if ("File Trigger".equals(selectedTrigger)) {
            comboBoxPane.setVisible(false);
            fileTriggerButton.setVisible(true);
        }
    }
   
    

    @FXML
    private void doneTrigger(ActionEvent event) {
        TimeTrigger t = new TimeTrigger(temp);
        triggerList.add(t);
        alertShow("","L'orario scelto è: " + temp.toString(),"",Alert.AlertType.INFORMATION);
        System.out.println("ORARIO SELEZIONATO:"+temp);
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
>>>>>>> Stashed changes
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }
<<<<<<< Updated upstream
}
=======

    private void populateTimeComboBox(ComboBox<String> comboBox,int pop) {
        for (int hm = 0; hm < pop; hm++){
            String formattedTime = String.format("%02d", hm);
            comboBox.getItems().add(formattedTime);
        }
}

    @FXML
    private void timePick1(ActionEvent event) {   
    if(timeComboBox1.getValue()==null){
    temp= LocalTime.of((LocalTime.now().getHour()),Integer.parseInt(timeComboBox2.getValue()));

    } else if (timeComboBox2.getValue()==null){
    
        temp= LocalTime.of(Integer.parseInt(timeComboBox1.getValue()),LocalTime.now().getMinute());
    
    } else {
    
        temp= LocalTime.of(Integer.parseInt(timeComboBox1.getValue()),Integer.parseInt(timeComboBox1.getValue()));

    }
}
}

    

>>>>>>> Stashed changes
