/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package SE_project2023;

import SE_project2023.Action.*;
import SE_project2023.Action.FileAction.FileAction;
import SE_project2023.Action.FileAction.StrategyFactory;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
        anchorPanes.put("Append Action", appendPane);
        anchorPanes.put("Program Action", programPane);

        actionListView.getItems().addAll(
                "TextBox Action",
                "Audio Action",
                "File Action",
                "Append Action",
                "Program Action"

        );

        // Aggiungi un listener per gestire la selezione della ListView
        actionListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                handleSelection(newValue); // Gestisci la selezione dell'opzione
            }
        });

        menuExec = new MenuExecutor();

    }

    private void handleSelection(String selectedAction) {
        menuExec.execute(new SwitchCommand(anchorPanes, selectedAction));
    }

     @FXML
    private void doneAction(ActionEvent event) {
        
        String selectedAction = actionListView.getSelectionModel().getSelectedItem();
        ArrayList<String> actionParams = new ArrayList<>();
        Action a = null;

        if (selectedAction != null) {
            switch (selectedAction) {
                case "TextBox Action":
                    createActionForTextBox(actionParams);
                      a = new MessageBoxAction(actionParams.get(1));
                    break;
                case "Audio Action":
                    createActionForAudio(actionParams);
                     a = new AudioAction(actionParams.get(1));
                    break;
                case "File Action":
                    createActionForFile(actionParams);
                //a = new FileAction(actionParams.get(1),actionParams.get(2),actionParams.get(3));
                    break;
                case "Append Action":
                    createActionForFileAppend(actionParams);
                    a = new FileAppendAction(actionParams.get(1),actionParams.get(2));
                    break;
                case "Program Action":
                createActionForProgram(actionParams);
                a = new ProgramAction(actionParams.get(1), Arrays.asList(actionParams.get(2).split("\\s+")));
                break;
                default:
                    break;
            }

     r.getLast().setAction(a);
    cancelAction(event);
    }
    }

    //ACTION VISIBILITY
    @FXML
    private void cancelAction(ActionEvent event) {
        Node sourceNode = (Node) event.getSource();
        Stage stage = (Stage) sourceNode.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void audioAction(ActionEvent event) {
        FileChooser fil_chooser = new FileChooser();
        fil_chooser.getExtensionFilters()
                .add(new FileChooser.ExtensionFilter("Audio Files",
                        "*.mp3", "*.wav", "*.flac", "*.aac"));
        File file = fil_chooser.showOpenDialog(new Stage());
        if (file != null) {
            audioText.setText(file.toString());
        }
    }

    private void fileAction(ActionEvent event, TextField source) {
        FileChooser fil_chooser = new FileChooser();
        File file = fil_chooser.showOpenDialog(new Stage());
        if (file != null) {
            source.setText(file.toString());
        }
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
    private void toggleHandle(ActionEvent event) {
        if (removeToggle.isSelected()) {
            destPath.setDisable(true);
            destFile.setDisable(true);
        } else {
            destPath.setDisable(false);
            destFile.setDisable(false);
        }
    }

    @FXML
    private void fileSetAction(ActionEvent event) {
        fileAction(event, sourcePath1);
    }

    @FXML
    private void fileAppendAction(ActionEvent event) {
        fileAction(event, sourcePath);
    }

    @FXML
    private void execAction(ActionEvent event) {
        FileChooser fil_chooser = new FileChooser();
        fil_chooser.getExtensionFilters()
                .add(new FileChooser.ExtensionFilter("Program Files",
                        "*.exe"));
        File file = fil_chooser.showOpenDialog(new Stage());
        if (file != null) {
            sourcePathExe.setText(file.toString());
    }
    }
  private void createActionForTextBox(ArrayList<String> actionParams) {
    if (!textMessage.getText().isEmpty()) {
        actionParams.add("MessageBox");
        actionParams.add(textMessage.getText());
    }
}

private void createActionForAudio(ArrayList<String> actionParams) {
    if (!audioText.getText().isEmpty()) {
        actionParams.add("Audio");
        actionParams.add(audioText.getText());
    }
}

private void createActionForFile(ArrayList<String> actionParams) {
    if (!sourcePath.getText().isEmpty() && !destPath.getText().isEmpty()) {
        actionParams.add("File");
        actionParams.add(sourcePath.getText());
        actionParams.add(destPath.getText());
        actionParams.add(((ToggleButton) fileChoices.getSelectedToggle()).getId().split("Toggle")[0]);
    }
}

private void createActionForProgram(ArrayList<String> actionParams) {
        if(!sourcePathExe.getText().isEmpty() )
        actionParams.add("Program");
        actionParams.add(sourcePathExe.getText()); // Il percorso del file .exe
        if (commandsField.getText().isEmpty()){
            actionParams.add("");
        } else {
        actionParams.add(commandsField.getText()); // I parametri da passare all'eseguibile
        }
    
}

private void createActionForFileAppend(ArrayList<String> actionParams){
    if (!textMessage2.getText().isEmpty()) {
        actionParams.add("Append");
        actionParams.add(sourcePath.getText());
        actionParams.add(textMessage2.getText());
    }
}

}
