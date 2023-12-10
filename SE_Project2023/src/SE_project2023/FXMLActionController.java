/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package SE_project2023;

import SE_project2023.Action.*;
import java.io.File;
import java.net.URL;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author giova
 */
public class FXMLActionController implements Initializable {

    private ObservableList<Action> actionList;
    private RuleList rules;
    private HashMap<String, AnchorPane> anchorPanes = new HashMap<>();

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
    @FXML
    private AnchorPane textPane;
    @FXML
    private AnchorPane filePane;
    @FXML
    private TextField sourcePath;
    @FXML
    private TextField destPath;
    @FXML
    private Button fileSource;
    @FXML
    private Button destFile;
    @FXML
    private ToggleButton moveToggle;
    @FXML
    private ToggleGroup fileChoices;
    @FXML
    private ToggleButton removeToggle;
    @FXML
    private ToggleButton copyToggle;
    @FXML
    private TextArea textMessage;
    @FXML
    private AnchorPane appendPane;
    @FXML
    private TextArea textMessage2;
    @FXML
    private TextField sourcePath1;
    @FXML
    private Button fileAppendSource;
    @FXML
    private AnchorPane programPane;
    @FXML
    private TextField commandsField;
    @FXML
    private Button execFileButton1;
    @FXML
    private TextField sourcePathExe;

    private Map<String, ArrayList<String>> params = new HashMap<>();
    private MenuExecutor menuExec; // invoker for commands

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Action Factory
        HashSet<Action> actions = new HashSet();

        actionList = FXCollections.observableArrayList(actions);
        rules = RuleList.getRuleList();

        populatePanes();
        populateListView();

        // Aggiungi un listener per gestire la selezione della ListView
        actionListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                textMessage.clear();
                destPath.clear();
                sourcePath1.clear();
                handleSelection(newValue); // Gestisci la selezione dell'opzione
            }
        });

        //Richiama l'invoker dei command, il comando di switch è uguale sia per i trigger che per le azioni.
        menuExec = new MenuExecutor();

    }
    
    private void handleSelection(String selectedAction) {
        menuExec.execute(new SwitchCommand(anchorPanes, selectedAction));
    }

    @FXML
    private void doneAction(ActionEvent event) {
        if (!actionListView.getSelectionModel().getSelectedItems().isEmpty()) {
            populateCreator();
            Action a = new ActionFactory(actionListView.getSelectionModel().getSelectedItem(), (HashMap) params).create();
            rules.getLast().setAction(a);
        }
        cancelAction(event);
    }

    @FXML
    private void cancelAction(ActionEvent event) {
        Node sourceNode = (Node) event.getSource();
        Stage stage = (Stage) sourceNode.getScene().getWindow();
        stage.close();
    }
    //Eventi associati ai pulsanti di scelta file, directory e programma.
    @FXML
    private void audioAction(ActionEvent event) {
        chooser(new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.aiff", "*.au"),
                audioText);
    }

    @FXML
    private void chooseFolder(ActionEvent event) {
        DirectoryChooser dir_chooser = new DirectoryChooser();
        File selectedDirectory = dir_chooser.showDialog(new Stage());
        if (selectedDirectory != null) {
            destPath.setText(selectedDirectory.toString());
        }
    }

    @FXML
    private void fileSetAction(ActionEvent event) {
        chooser(null, sourcePath1);
    }

    @FXML
    private void fileAppendAction(ActionEvent event) {
        chooser(null, sourcePath);
    }

    @FXML
    private void toggleHandle(ActionEvent event) {
        if (removeToggle.isSelected()) {
            destPath.setDisable(true);
            destFile.setDisable(true);
        } else {
            destPath.setDisable(false);
            destFile.setDisable(false);
        }
    }
    //La scelta dei programmi consente di scegliere ogni tipo di file, se il file non è un programma appare un allert d'errore.
    @FXML
    private void execAction(ActionEvent event) {
        chooser(null, sourcePathExe);
    }

    // Un file chooser a cui è possibile passare un estensione in modo filtrare i file selezionabili

    private void chooser(FileChooser.ExtensionFilter ef, TextField tf) {
        FileChooser fil_chooser = new FileChooser();
        if (ef != null) {
            fil_chooser.getExtensionFilters().add(ef);
        }
        File file = fil_chooser.showOpenDialog(new Stage());
        if (file != null) {
            tf.setText(file.toString());
        }
    }

    //Associa ogni azione una lista di parametri che servono per costruirla in modo da poterla passare
    //al momento della costruzione.

    private void populateCreator() {
        params.put("TextBox Action",new ArrayList<>(Arrays.asList(textMessage.getText())));
        params.put("Audio Action", new ArrayList<>(Arrays.asList(audioText.getText())));
        params.put("File Action", new ArrayList<>(Arrays.asList(sourcePath1.getText(), destPath.getText(),
                ((ToggleButton) fileChoices.getSelectedToggle()).getId().split("Toggle")[0])));
        params.put("Append Action", new ArrayList<>(Arrays.asList(sourcePath.getText(), textMessage2.getText())));
        params.put("Program Action",
                new ArrayList<>(Arrays.asList(sourcePathExe.getText(), commandsField.getText())));
    }

    //Associa ogni azione al pane di riferimento in modo da poter gestire la visibilità

    private void populatePanes() {
        anchorPanes.put("TextBox Action", textPane);
        anchorPanes.put("Audio Action", audioPane);
        anchorPanes.put("File Action", filePane);
        anchorPanes.put("Append Action", appendPane);
        anchorPanes.put("Program Action", programPane);
    }

    //Menu che appare all'utente per scegliere l'azione, la lista viene inizializzata con tutti i tipi di azione possibili.

    private void populateListView() {
        actionListView.getItems().addAll(
                "TextBox Action",
                "Audio Action",
                "File Action",
                "Append Action",
                "Program Action");
    }

}
