package SE_project2023;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
import java.io.File;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private AnchorPane rulesWindow;
    @FXML
    private Button rulesBtn;
    @FXML
    private Button triggerBtn;
    @FXML
    private Text actionTxt;
    @FXML
    private Text triggerTxt;
    @FXML
    private Button confirmBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private AnchorPane actionPane;
    @FXML
    private Button cancelBtnAction;
    @FXML
    private AnchorPane triggerPane;
    @FXML
    private Button timeTriggerBtn;
    @FXML
    private Button cancelBtnTrigger;
    @FXML
    private TextField ruleName;

    ObservableList<Rule> ruleList;
    ObservableList<Action> actionList;

    @FXML
    private Button doneActionButton;
    @FXML
    private TextArea TextMessage;
    @FXML
    private Button SendMessage;
    @FXML
    private AnchorPane TextPane;
    @FXML
    private AnchorPane AudioFilePane;
    @FXML
    private TextField textFieldAudioFile;
    @FXML
    private ListView<Action> actionView; //lista che mostra le azioni scelte
    @FXML
    private Button RemoveBtn;
    @FXML
    private MenuButton chooseAction;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        RuleList rules = RuleList.getRuleList();
        HashSet<Action> actions = new HashSet();
        //inizializzazione Liste
        ruleList = FXCollections.observableArrayList(rules.getHashRules());
        actionList = FXCollections.observableArrayList(actions);
        
        //setting selezione multipla
        actionView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listView.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
        //aggiunta regola di testing
        //setting View
        listView.setItems(ruleList);
        actionView.setItems(actionList);
        //inizializzazione thread
        /*TriggerControlThread t=new TriggerControlThread();
        t.setDaemon(true);
        t.start();*/

    }

    @FXML
    private void saveRules(ActionEvent event) {
    }

    @FXML
    private void loadButton(ActionEvent event) {
    }

    @FXML
    private void addRule(ActionEvent event) {
        //Viene resa visibile la Window dell'aggiunta regole
        rulesWindow.setVisible(true);

    }

    @FXML
    private void removeRules(ActionEvent event) {
        
        ruleList.removeAll(listView.getSelectionModel().getSelectedItems());

    }

    @FXML
    private void addAction(ActionEvent event) {
        actionPane.setVisible(true);
        //lo metti in una variabile
        //metti messaggio azione selezionata
        
        //DEVO TROVARE UN MODO PER FAR VEDERE LE AZIONE SELEZIONATE QUANDO CLICCO DI NUOVO SU AZIONI
    }

    @FXML
    private void addTrigger(ActionEvent event) {
        triggerPane.setVisible(true);
    }

    @FXML
    private void confirmRule(ActionEvent event) {

        Rule regola = new Rule(ruleName.getText());
        ruleList.add(regola);
        alertShow("Inserimento", "", "Regola correttamente inserita", Alert.AlertType.INFORMATION);
        rulesWindow.setVisible(false);
    }

    @FXML
    private void cancel(ActionEvent event) {
        rulesWindow.setVisible(false);
    }

    @FXML
    private void cancelAction(ActionEvent event) {
        TextMessage.setVisible(false);
        chooseAction.setDisable(false);
    }

    @FXML
    private void timeTrigger(ActionEvent event) {
    }

    @FXML
    private void CancelTrigger(ActionEvent event) {
        triggerPane.setVisible(false);
    }

    @FXML
    private void doneAction(ActionEvent event) {

        if (actionList.isEmpty()) { //Se non ho selezionato nessuna azione appare un warning.
            alertShow("Attenzione", "Non hai inserito nessuna azione", "Stai tornando indietro", Alert.AlertType.WARNING);
            actionPane.setVisible(false);
            
        } else {
            //io qua ho una singola azione oppure una lista di azioni, per capire quale oggetto creare potrei fare un proxy.
            //if(ruleList.size() > 1)...{  
            Rule r = ruleList.get(ruleList.size()-1); //prendo l'ultima regola aggiunta
            r.setAction(actionList.remove(0)); //rimuovo l'unica azione dalla lista e l'assegno alla regola
            rulesWindow.setVisible(true);
            actionPane.setVisible(false);
        }
    }

    @FXML
    private void makeAction(ActionEvent event) {
        //Questo è collegato alla casella di testo
        String mess = TextMessage.getText();
        if (mess.isEmpty()) { //si potrebbe aggiungere il controllo per vedere se sono solo spazi
            alertShow("Attenzione", "Hai inserito un messaggio vuoto", "L'azione non verrà salvata", Alert.AlertType.WARNING);
        } else {
            alertShow("", "Azione aggiunta!", "", Alert.AlertType.INFORMATION);
            Action a = new ActionMessageBox(mess);
            TextPane.setVisible(false);
            chooseAction.setDisable(false);
            actionList.add(a);
        }
        TextMessage.clear();
        TextPane.setVisible(false);
    }

    @FXML
    private void textAction(ActionEvent event) {
        TextPane.setVisible(true); //quando clicco sul pulsante TextAction mi esce la casella di testo.
        chooseAction.setDisable(true); //quando scelgo l'azione si disabilita il menù.
       
    }

    @FXML
    private void audioAction(ActionEvent event) {
        // get the file selected
        // create a File chooser
        FileChooser fil_chooser = new FileChooser();
        File path = fil_chooser.showOpenDialog(new Stage());
        AudioFilePane.setVisible(true);
        textFieldAudioFile.setText(path.getPath());
    }

    private void createActionAudio(ActionEvent event) {
        String path = textFieldAudioFile.getText();
        if (path.isEmpty()) {
            alertShow("Attenzione", "Non hai scelto un file", "L'azione non verrà salvata", Alert.AlertType.WARNING);
        } else {
            Action a = new ActionAudio(path);
            actionList.add(a);
        }
        textFieldAudioFile.clear();
        AudioFilePane.setVisible(false);
        alertShow("Inserimento", "", "Suono Aggiunto", Alert.AlertType.INFORMATION);
    }

    private void alertShow(String title, String header, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

   

    @FXML
    private void RemoveItem(ActionEvent event) {
        actionList.remove(actionView.getSelectionModel().getSelectedItem());
    }
    
   
}
