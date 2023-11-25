/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package SE_project2023;

import SE_project2023.Trigger.TimeTrigger;
import SE_project2023.Trigger.Trigger;
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
import javafx.scene.layout.AnchorPane;
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
    private Button Done;
    @FXML
    private Button Cancel;
    @FXML
    private Button fileTriggerButton;
    @FXML
    private ComboBox<String> timeComboBox1;
    @FXML
    private ComboBox<String> timeComboBox2;
    @FXML
    private AnchorPane comboBoxPane;
    
    private LocalTime temp;
    ObservableList<Trigger> triggerList;
    RuleSingleton r;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*Inizializzazione di un observable list*/
        HashSet<Trigger> triggers = new HashSet();
        triggerList = FXCollections.observableArrayList(triggers);
        
        /*Prendiamo una regola temporanea a cui aggiungere il trigger*/
        r=RuleSingleton.getInstance();
        
        /*Popola le timebox con i valori delle ore e dei minuti*/
        populateTimeComboBox(timeComboBox1,24);
        populateTimeComboBox(timeComboBox2,60);
        
        /*Aggiungiamo alla ListView dei trigger i nomi dei vari tipi di trigger*/
        triggerListView.getItems().addAll(
                "Time Trigger"                    
        );
        
        /*La variabile temp per l'inizializzazione del trigger viene impostata di default all'orario attuale*/
        temp = LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute());
        
        /*Settiamo i valori delle due timeComboBox all'orario attuale*/
        timeComboBox1.setPromptText(Integer.toString(LocalTime.now().getHour()));
        timeComboBox2.setPromptText(Integer.toString(LocalTime.now().getMinute()));

        
        /*Listener aggiunto per la gestione della visibilità degli elementi dell'interfaccia,
        chiama una funzione che setta la visibilità a TRUE*/
        triggerListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                handleTriggerSelection(newValue); // Gestisci la selezione dell'opzione
            }
        });


       
            
      
    }



    private void handleTriggerSelection(String selectedTrigger) {
        if (null != selectedTrigger) switch (selectedTrigger) {
            case "Time Trigger":
                comboBoxPane.setVisible(true);
                fileTriggerButton.setVisible(false);
                break;
            case "Program Trigger":
                comboBoxPane.setVisible(false);
                fileTriggerButton.setVisible(false);
                break;
            case "File Trigger":
                comboBoxPane.setVisible(false);
                fileTriggerButton.setVisible(true);
                break;
            default:
                break;
        }
    }
   
    

    @FXML
    private void doneTrigger(ActionEvent event) {
        TimeTrigger t = new TimeTrigger(temp);
        triggerList.add(t);
        alertShow("","L'orario scelto è: " + temp.toString(),"",Alert.AlertType.INFORMATION);
        //System.out.println("ORARIO SELEZIONATO:"+temp);
        r.setTrigger(t);
        
        
        Node sourceNode = (Node) event.getSource();
        Stage stage = (Stage) sourceNode.getScene().getWindow();
        stage.close();// Chiudi la finestra corrente             
    }

    @FXML
    private void cancelTrigger(ActionEvent event) {
        Node sourceNode = (Node) event.getSource();
        Stage stage = (Stage) sourceNode.getScene().getWindow();
        stage.close();
    }

    
    
  /*metodo per selezionare le cartelle del sistema, ci servirà con i programTrigger*/
    @FXML
    private void selectFolder(ActionEvent event) {
    /*    DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Seleziona una cartella");
        File selectedDirectory = directoryChooser.showDialog(fileTriggerButton.getScene().getWindow());

        if (selectedDirectory != null) {
            // Fa qualcosa con la cartella selezionata
            System.out.println("Cartella selezionata: " + selectedDirectory.getAbsolutePath());
        }
    */
    }
    

     private void alertShow(String title, String header, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

    private void populateTimeComboBox(ComboBox<String> comboBox,int pop) {
        for (int hm = 0; hm < pop; hm++){
            String formattedTime = String.format("%02d", hm);
            comboBox.getItems().add(formattedTime);
        }
}

    @FXML
    private void timePick1(ActionEvent event) {
    /*Gestito negli if per evitare il lancio di eccezioni al "primo click" di selezione.
        Scegliendo l'orario da una sola delle due ComboBox disponibili, il valore dell'altra ComboBox
        è impostato al valore dell'orario corrente*/
    if(timeComboBox1.getValue()==null){
    temp= LocalTime.of((LocalTime.now().getHour()),Integer.parseInt(timeComboBox2.getValue()));
    } else if (timeComboBox2.getValue()==null){    
        temp= LocalTime.of(Integer.parseInt(timeComboBox1.getValue()),LocalTime.now().getMinute());
    } else {
        temp= LocalTime.of(Integer.parseInt(timeComboBox1.getValue()),Integer.parseInt(timeComboBox2.getValue()));
    }
}
}

    

