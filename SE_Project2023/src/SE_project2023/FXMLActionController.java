/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package SE_project2023;

import SE_project2023.Action.*;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
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
public class FXMLActionController implements Initializable{

    @FXML
    private TextArea TextMessage;

    ObservableList<Action> actionList;
    RuleList r;
    HashMap<String, AnchorPane> anchorPanes = new HashMap<>();

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

    private MenuExecutor menuExec; //invoker for commands
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        HashSet<Action> actions = new HashSet();
        actionList = FXCollections.observableArrayList(actions);
        r = RuleList.getRuleList();

        anchorPanes.put("TextBox Action", textPane);
        anchorPanes.put("Audio Action", audioPane);
        anchorPanes.put("File Action", filePane);

        actionListView.getItems().addAll(
                "TextBox Action",
                "Audio Action"
                //"File Action"
        );

        // Aggiungi un listener per gestire la selezione della ListView
        actionListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                handleSelection(newValue); // Gestisci la selezione dell'opzione
            }
        });
        


        menuExec = new MenuExecutor(); //vedere se togliere e fare una classe interna****
       

    }

    private void handleSelection(String selectedAction) {
        menuExec.execute(new SwitchCommand(anchorPanes, selectedAction)); //vedere se posso fare classe innestata
    }

    @FXML
    private void doneAction(ActionEvent event) {

        if (anchorPanes.get("TextBox Action").isVisible() && !"".equals(TextMessage.getText())) {
            String mess = TextMessage.getText();
            Action a = new MessageBoxAction(mess);
            r.getLast().setAction(a);
        } else if (anchorPanes.get("Audio Action").isVisible() && !"".equals(audioText.getText())) {
            Action a = new AudioAction(audioText.getText());
            r.getLast().setAction(a);
        } else if (anchorPanes.get("File Action").isVisible()
                && !"".equals(sourcePath.getText())) {
            String action = ((ToggleButton)fileChoices.getSelectedToggle()).getId().split("Toggle")[0];
            Action a = new FileAction(sourcePath.getText(), destPath.getText(), action);
            
            r.getLast().setAction(a);
        }
        cancelAction(event);
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

    @FXML
    private void fileAction(ActionEvent event) {
        if (event.getSource().toString().contains("fileSource")) {
            FileChooser fil_chooser = new FileChooser();
            File file = fil_chooser.showOpenDialog(new Stage());
            if (file != null) {
                sourcePath.setText(file.toString());
            }
        } else if (event.getSource().toString().contains("destFile")) {
            DirectoryChooser dir_chooser = new DirectoryChooser();
            File selectedDirectory = dir_chooser.showDialog(new Stage());
            if (selectedDirectory != null) {
                destPath.setText(selectedDirectory.toString());
            }
        }
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

   

}
