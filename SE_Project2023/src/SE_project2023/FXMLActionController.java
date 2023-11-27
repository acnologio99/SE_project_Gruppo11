/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package SE_project2023;

import SE_project2023.Action.*;
import java.io.File;
import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
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
    @FXML
    private TextField audioText;
    @FXML
    private AnchorPane audioPane;

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
                handleActionSelection(newValue); // Gestisci la selezione dell'opzione
            }
        });

    }

    private void handleActionSelection(String selectedAction) {
        if ("TextBox Action".equals(selectedAction)) {
            flagAction = 1;
            TextMessage.setVisible(true);
            audioPane.setVisible(false);
        } else if ("Audio Action".equals(selectedAction)) {
            flagAction = 2;
            TextMessage.setVisible(false);
            audioPane.setVisible(true);
        }
    }

    @FXML
    private void doneAction(ActionEvent event) {

        if (flagAction == 1 && !"".equals(TextMessage.getText())) {
            String mess = TextMessage.getText();
            Action a = new MessageBoxAction(mess);
            r.setAction(a);
        } else if (flagAction == 2 && !"".equals(audioText.getText())) {
            Action a = new AudioAction(audioText.getText());
            r.setAction(a);
        }

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

    @FXML
    private void audioAction(ActionEvent event) {
        // get the file selected
        // create a File chooser
        FileChooser fil_chooser = new FileChooser();
        fil_chooser.getExtensionFilters()
                .add(new FileChooser.ExtensionFilter("Audio Files",
                        "*.mp3", "*.wav", "*.flac", "*.aac"));
        File file = fil_chooser.showOpenDialog(new Stage());
        if (file != null) {
            audioText.setText(file.toString());
        }

    }
}
