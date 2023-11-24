/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package SE_project2023;

import java.net.URL;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author giova
 */
public class FXMLActionController implements Initializable {

    @FXML
    private Button doneActionButton;
    @FXML
    private AnchorPane TextPane;
    @FXML
    private TextArea TextMessage;
    @FXML
    private Button SendMessage;
    @FXML
    private Button cancelBtnAction;
    @FXML
    private MenuButton chooseAction;
    @FXML
    private ListView<Action> actionView;
    @FXML
    private Button RemoveBtn;
    ObservableList<Action> actionList;
    RuleSingleton r;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        HashSet<Action> actions = new HashSet();
        actionList = FXCollections.observableArrayList(actions);
        actionView.setItems(actionList);
        r=RuleSingleton.getInstance();
    }    

    @FXML
    private void doneAction(ActionEvent event) {
        if (actionList.isEmpty()) { //Se non ho selezionato nessuna azione appare un warning.
            alertShow("Attenzione", "Non hai inserito nessuna azione", "Stai tornando indietro", Alert.AlertType.WARNING);
            
            
        } else {
            //io qua ho una singola azione oppure una lista di azioni, per capire quale oggetto creare potrei fare un proxy.
            //if(ruleList.size() > 1)...{  
            //Rule r = ruleList.get(ruleList.size()-1); //prendo l'ultima regola aggiunta
            r.setAction(actionList.remove(0)); //rimuovo l'unica azione dalla lista e l'assegno alla regola
            
        }
        Node sourceNode = (Node) event.getSource();
        Stage stage = (Stage) sourceNode.getScene().getWindow();

        // Chiudi la finestra corrente
        stage.close();
    }

    @FXML
    private void makeAction(ActionEvent event) {
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
    private void cancelAction(ActionEvent event) {
    }

    @FXML
    private void textAction(ActionEvent event) {
        TextPane.setVisible(true); //quando clicco sul pulsante TextAction mi esce la casella di testo.
        chooseAction.setDisable(true);
    }

    @FXML
    private void audioAction(ActionEvent event) {
        // get the file selected
        // create a File chooser
        /*FileChooser fil_chooser = new FileChooser();
        File path = fil_chooser.showOpenDialog(new Stage());
        AudioFilePane.setVisible(true);
        textFieldAudioFile.setText(path.getPath());*/
    }
    private void createActionAudio(ActionEvent event) {
        /*String path = textFieldAudioFile.getText();
        if (path.isEmpty()) {
            alertShow("Attenzione", "Non hai scelto un file", "L'azione non verrà salvata", Alert.AlertType.WARNING);
        } else {
            Action a = new ActionAudio(path);
            actionList.add(a);
        }
        textFieldAudioFile.clear();
        AudioFilePane.setVisible(false);
        alertShow("Inserimento", "", "Suono Aggiunto", Alert.AlertType.INFORMATION);*/
    }

    @FXML
    private void RemoveItem(ActionEvent event) {
        actionList.remove(actionView.getSelectionModel().getSelectedItem());
    }
     private void alertShow(String title, String header, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }
}
