/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package SE_project2023;

import SE_project2023.Trigger.TriggerCreator;
import SE_project2023.Trigger.*;
import java.io.File;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
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
    private ComboBox<String> timeComboBox1;
    @FXML
    private ComboBox<String> timeComboBox2;
    @FXML
    private AnchorPane comboBoxPane;
    @FXML
    private AnchorPane dayOfWeekPane;
    @FXML
    private ListView<DayOfWeek> daysOfWeek;
    @FXML
    private ComboBox<String> daysOfMonth;
    @FXML
    private DatePicker datePicker;
    @FXML
    private AnchorPane dayOfMonthPane;
    @FXML
    private AnchorPane dayOfYearPane;
    @FXML
    private AnchorPane fileSizePane;
    @FXML
    private Button fileChooserButton;
    @FXML
    private TextField fileSizeField;
     @FXML
    private TextField fileSourceDir;
    @FXML
    private Button fileChooserButton1;
    @FXML
    private AnchorPane fileInADirPane;
    @FXML
    private TextField fileSourceSize;
    @FXML
    private TextField dirText;
    @FXML
    private Button dirChooser;
    
    private int flagTrigger;
    private LocalTime temp;
    private ObservableList<Trigger> triggerList;
    private RuleList rules;
    private final Map<String, ArrayList<String>> params = new HashMap<>();
    private final HashMap<String, AnchorPane> anchorPanes = new HashMap<>();
    private MenuExecutor menuExec;
   
    

    
    

    /**
     * Initializes the controller class.
     * 
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        /* Inizializzazione di un observable list */
        HashSet<Trigger> triggers = new HashSet();
        triggerList = FXCollections.observableArrayList(triggers);
        rules = RuleList.getRuleList();

        /* Popola le timebox con i valori delle ore e dei minuti */
        populateComboBox(timeComboBox1, 0, 24);
        populateComboBox(timeComboBox2, 0, 60);
        populateComboBox(daysOfMonth, 1, 32);

        /* Settiamo il FileSizeField con solo valori numerici */
        fileSizeField.setPromptText("KiloBytes Unit");
        fileSizeField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                // Se il nuovo testo non contiene solo cifre, reimposta il testo con il vecchio
                // valore
                fileSizeField.setText(oldValue);
            }
        });

        /* Aggiungiamo alla ListView dei trigger i nomi dei vari tipi di trigger */
        populateListView();
        populatePanes();

        /* Popolamento Lista giorni della settimana */
        daysOfWeek.getItems().addAll(Arrays.asList(DayOfWeek.values()));

        /*
         * La variabile temp per l'inizializzazione del trigger viene impostata di
         * default all'orario attuale
         */
        temp = LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute());

        /* Settiamo i valori delle ComboBox all'orario attuale e Giorno corrente */
        timeComboBox1.setPromptText(Integer.toString(LocalTime.now().getHour()));
        timeComboBox2.setPromptText(Integer.toString(LocalTime.now().getMinute()));
        timeComboBox1.setValue(Integer.toString(LocalTime.now().getHour()));
        timeComboBox2.setValue(Integer.toString(LocalTime.now().getMinute()));
        DayOfWeek currentDayOfWeek = LocalDate.now().getDayOfWeek();
        int selectedIndex = currentDayOfWeek.getValue() - 1; // Indice inizia da 0
        daysOfWeek.getSelectionModel().select(selectedIndex);
        daysOfMonth.setValue(Integer.toString(LocalDate.now().getDayOfMonth()));
        daysOfMonth.setPromptText(Integer.toString(LocalDate.now().getDayOfMonth()));
        datePicker.setValue(LocalDate.now());
        /*
         * Listener aggiunto per la gestione della visibilità degli elementi
         * dell'interfaccia,
         * chiama una funzione che setta la visibilità a TRUE
         */
        triggerListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                fileSizeField.clear();
                fileSourceSize.clear();
                dirText.clear();
                fileSourceDir.clear();
                handleTriggerSelection(newValue); // Gestisci la selezione dell'opzione
            }
        });
        menuExec = new MenuExecutor();
    }
    
    //Gestisce lo switch nella selezione dei trigger nell'interfaccia.


    private void handleTriggerSelection(String selectedTrigger) {
        menuExec.execute(new SwitchCommand(anchorPanes, selectedTrigger));
    }

    @FXML
    private void doneTrigger(ActionEvent event) {
        if (!triggerListView.getSelectionModel().getSelectedItems().isEmpty()) {
            populateCreator();
            Trigger t = new TriggerFactory(triggerListView.getSelectionModel().getSelectedItem(), (HashMap) params).create();
            rules.getLast().setTrigger(t);
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

    //inizializzazione della ComboBox

    private void populateComboBox(ComboBox<String> comboBox, int start, int pop) {
        while (start < pop) {
            String formattedTime = String.format("%02d", start);
            comboBox.getItems().add(formattedTime);
            start++;
        }
    }

    @FXML
    private void selectFile(ActionEvent event) {
        FileChooser fil_chooser = new FileChooser();
        File file = fil_chooser.showOpenDialog(new Stage());
        if (file != null) {
            if(event.getTarget().equals(fileChooserButton))
                fileSourceSize.setText(file.toString());
            if(event.getTarget().equals(fileChooserButton1)){
                fileSourceDir.setText(file.getPath());
                dirText.setText(file.getParent());
                dirChooser.setDisable(false);
                
            }
        }
        

    }
    @FXML
    private void selectDirectory(ActionEvent event) {
        DirectoryChooser dir_chooser = new DirectoryChooser();
        File selectedDirectory = dir_chooser.showDialog(new Stage());
        if (selectedDirectory != null) {
            dirText.setText(selectedDirectory.toString());
        }
    }

    //Associa ogni azione una lista di parametri che servono per costruirla in modo da poterla passare
    //al momento della costruzione.
    
    private void populateCreator() {
        params.put("Time Trigger", new ArrayList<>(Arrays.asList(timeComboBox1.getValue(),timeComboBox2.getValue())));
        params.put("Day of Week Trigger",new ArrayList<>(Arrays.asList(daysOfWeek.getSelectionModel().getSelectedItem().toString())));
        params.put("Day of Month Trigger",new ArrayList<>(Arrays.asList(daysOfMonth.getValue())));
        params.put("Day of Year Trigger",new ArrayList<>(Arrays.asList(datePicker.getValue().toString())));
        params.put("File Trigger",new ArrayList<>(Arrays.asList(fileSourceSize.getText(), fileSizeField.getText())));
        params.put("File in a Dir Trigger",new ArrayList<>(Arrays.asList(fileSourceDir.getText(), dirText.getText())));
        
    }

    private void populatePanes() {
        anchorPanes.put("Time Trigger", comboBoxPane);
        anchorPanes.put("Day of Week Trigger", dayOfWeekPane);
        anchorPanes.put("Day of Month Trigger", dayOfMonthPane);
        anchorPanes.put("Day of Year Trigger", dayOfYearPane);
        anchorPanes.put("File Trigger", fileSizePane);
        anchorPanes.put("File in a Dir Trigger", fileInADirPane);
    }

    private void populateListView() {
        triggerListView.getItems().addAll(
                "Time Trigger",
                "Day of Week Trigger",
                "Day of Month Trigger",
                "Day of Year Trigger",
                "File Trigger",
                "File in a Dir Trigger");
    }

    
}
