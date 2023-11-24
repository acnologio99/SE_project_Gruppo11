/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package SE_project2023;

import SE_project2023.Action.Action;
import SE_project2023.Action.ActionMessageBox;
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

    private AnchorPane TextPane;
    @FXML
    private TextArea TextMessage;
    private MenuButton chooseAction;

    ObservableList<Action> actionList;
    RuleSingleton r;

    private int flagAction;
    @FXML
    private ListView<String> actionListView;
    @FXML
    private Button Done;
    @FXML
    private Button Cancel;
    @FXML
    private Button audioButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        HashSet<Action> actions = new HashSet();
        actionList = FXCollections.observableArrayList(actions);
        r = RuleSingleton.getInstance();

        actionListView.getItems().addAll(
                "TextBox Action",
                "Audio Action"
        );

        // Aggiungi un listener per gestire la selezione della ListView
        actionListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                handleTriggerSelection(newValue); // Gestisci la selezione dell'opzione
            }
        });

    }

    private void handleTriggerSelection(String selectedTrigger) {
        if ("TextBox Action".equals(selectedTrigger)) {
            flagAction = 1;
            TextMessage.setVisible(true);
            audioButton.setVisible(false);
        } else if ("Audio Action".equals(selectedTrigger)) {
            flagAction = 2;
            TextMessage.setVisible(false);
            audioButton.setVisible(true);
        }
    }

    @FXML
    private void doneAction(ActionEvent event) {

        if (flagAction == 1) {
            String mess = TextMessage.getText();
            Action a = new ActionMessageBox(mess);
            r.setAction(a);
        }
        alertShow("", "Azione aggiunta!", "", Alert.AlertType.INFORMATION);

        Node sourceNode = (Node) event.getSource();
        Stage stage = (Stage) sourceNode.getScene().getWindow();

        // Chiudi la finestra corrente
        stage.close();
    }

    @FXML
    private void cancelAction(ActionEvent event) {
        Node sourceNode = (Node) event.getSource();
        Stage stage = (Stage) sourceNode.getScene().getWindow();
        stage.close();
    }

    private void textAction(ActionEvent event) {
        TextPane.setVisible(true); //quando clicco sul pulsante TextAction mi esce la casella di testo.
        chooseAction.setDisable(true);
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

    private void alertShow(String title, String header, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }
}
