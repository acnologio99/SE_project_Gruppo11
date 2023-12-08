/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package SE_project2023;

import SE_project2023.Trigger.*;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
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

    private int flagTrigger;
    private LocalTime temp;
    ObservableList<Trigger> triggerList;
    RuleList r;
    
    HashMap<String, AnchorPane> anchorPanes = new HashMap<>();
    private MenuExecutor menuExec;
    @FXML
    private AnchorPane dayOfWeekPane;
    @FXML
    private ListView<DayOfWeek> daysOfWeek;
    @FXML
    private Text datePickerPrompt1;
    @FXML
    private ComboBox<String> daysOfMonth;
    @FXML
    private Text datePickerPrompt;
    @FXML
    private DatePicker datePicker;
    @FXML
    private AnchorPane dayOfMonthPane;
    @FXML
    private AnchorPane dayOfYearPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*Inizializzazione di un observable list*/
        HashSet<Trigger> triggers = new HashSet();
        triggerList = FXCollections.observableArrayList(triggers);
        /*Prendiamo una regola temporanea a cui aggiungere il trigger*/
        r = RuleList.getRuleList();

        /*Popola le timebox con i valori delle ore e dei minuti*/
        populateTimeComboBox(timeComboBox1,0, 24);
        populateTimeComboBox(timeComboBox2,0, 60);
        populateTimeComboBox(daysOfMonth,1, 32);

        /*Aggiungiamo alla ListView dei trigger i nomi dei vari tipi di trigger*/
        triggerListView.getItems().addAll(
                "Time Trigger",
                "Day of Week Trigger",
                "Day of Month Trigger",
                "Day of Year Trigger"
        );

        anchorPanes.put("Time Trigger", comboBoxPane);
        anchorPanes.put("Day of Week Trigger", dayOfWeekPane);
        anchorPanes.put("Day of Month Trigger", dayOfMonthPane);
        anchorPanes.put("Day of Year Trigger", dayOfYearPane);

        /*Popolamento Lista giorni della settimana*/
        daysOfWeek.getItems().addAll(Arrays.asList(DayOfWeek.values()));
        
        /*La variabile temp per l'inizializzazione del trigger viene impostata di default all'orario attuale*/
        temp = LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute());

        /*Settiamo i valori delle ComboBox all'orario attuale e Giorno corrente*/
        timeComboBox1.setPromptText(Integer.toString(LocalTime.now().getHour()));
        timeComboBox2.setPromptText(Integer.toString(LocalTime.now().getMinute()));
        DayOfWeek currentDayOfWeek = LocalDate.now().getDayOfWeek();
        int selectedIndex = currentDayOfWeek.getValue() - 1; // Indice inizia da 0
        daysOfWeek.getSelectionModel().select(selectedIndex);
        daysOfMonth.setValue(Integer.toString(LocalDate.now().getDayOfMonth()));
        daysOfMonth.setPromptText(Integer.toString(LocalDate.now().getDayOfMonth()));
        datePicker.setValue(LocalDate.now());
        /*Listener aggiunto per la gestione della visibilità degli elementi dell'interfaccia,
        chiama una funzione che setta la visibilità a TRUE*/
        triggerListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                handleTriggerSelection(newValue); // Gestisci la selezione dell'opzione
            }
        });

        menuExec = new MenuExecutor();

    }

    private void handleTriggerSelection(String selectedTrigger) {
        menuExec.execute(new SwitchCommand(anchorPanes, selectedTrigger));
    }

    @FXML
    private void doneTrigger(ActionEvent event) {

        if (anchorPanes.get("Time Trigger").isVisible()) {
            
            Trigger t = new TimeTrigger(temp);
            triggerList.add(t);
            r.getLast().setTrigger(t);
             System.out.println(r.getLast().getTrigger());
        } else if (anchorPanes.get("Day of Week Trigger").isVisible()) {
            
            Trigger t = new DayOfWeekTrigger(daysOfWeek.getSelectionModel().getSelectedItem());
            triggerList.add(t);
            r.getLast().setTrigger(t);
        } else if (anchorPanes.get("Day of Month Trigger").isVisible()) {
            
            Trigger t = new DayOfMonthTrigger(Integer.parseInt(daysOfMonth.getValue()));
            triggerList.add(t);
            r.getLast().setTrigger(t);
           
        }else if (anchorPanes.get("Day of Year Trigger").isVisible()) {
            
            Trigger t = new DayOfYearTrigger(datePicker.getValue());
            triggerList.add(t);
            r.getLast().setTrigger(t);
           
        }
       



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

    private void populateTimeComboBox(ComboBox<String> comboBox,int start, int pop) {
        while(start<pop){
            String formattedTime = String.format("%02d", start);
            comboBox.getItems().add(formattedTime);
            start++;
        }
    }

    @FXML
    private void timePick1(ActionEvent event) {
        /*Gestito negli if per evitare il lancio di eccezioni al "primo click" di selezione.
        Scegliendo l'orario da una sola delle due ComboBox disponibili, il valore dell'altra ComboBox
        è impostato al valore dell'orario corrente*/
        if (timeComboBox1.getValue() == null) {
            temp = LocalTime.of((LocalTime.now().getHour()), Integer.parseInt(timeComboBox2.getValue()));
        } else if (timeComboBox2.getValue() == null) {
            temp = LocalTime.of(Integer.parseInt(timeComboBox1.getValue()), LocalTime.now().getMinute());
        } else {
            temp = LocalTime.of(Integer.parseInt(timeComboBox1.getValue()), Integer.parseInt(timeComboBox2.getValue()));
        }
    }
}
