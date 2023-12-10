/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package SE_project2023;

import SE_project2023.Tool.FireMultipleVerified;
import SE_project2023.Tool.FireOnceVerified;
import SE_project2023.Tool.SleepVerified;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author giova
 */
public class FXMLRuleController implements Initializable {

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
    private TextField ruleName;
    /**
     * Initializes the controller class.
     */
    RuleList rules = RuleList.getRuleList();
    @FXML
    private AnchorPane sleepPicker;
    @FXML
    private RadioButton sleepRadio;
    @FXML
    private TextField daysPicker;
    @FXML
    private TextField minutesPicker;
    @FXML
    private RadioButton fireOnceRadio;
    @FXML
    private AnchorPane rootScene;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ToggleGroup toggleGroup = new ToggleGroup();
        sleepRadio.setToggleGroup(toggleGroup);
        fireOnceRadio.setToggleGroup(toggleGroup);
        this.initalizeToggles(toggleGroup);
        sleepPicker.setVisible(false);
        this.initializePickers();

    }

    @FXML
    private void addAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAction.fxml")); //Apre il controller delle azioni

            Parent root = loader.load();
            rootScene.setDisable(true);

            Stage stage = new Stage();
            stage.setTitle("Action");
            stage.setScene(new Scene(root));
            //Cattura l'evento finestra per comunicare se l'azione è stata inserita oppure no
            stage.addEventHandler(WindowEvent.ANY,
                    new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    if (rules.getLast().getAction() != null) {
                        actionTxt.setText("Azione inserita");
                    }
                }
            });
            stage.showAndWait();
            rootScene.setDisable(false); //La scena sottostante viene disabilitata in modo che l'utente non può cliccarla.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addTrigger(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLTrigger.fxml")); //Apre il controller dei trigger
            rootScene.setDisable(true);

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Trigger");
            stage.setScene(new Scene(root));
            //Cattura l'evento finestra per comunicare se il trigger è stato inserito oppure no
            stage.addEventHandler(WindowEvent.ANY,
                    new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    if (rules.getLast().getTrigger() != null) {
                        triggerTxt.setText("Trigger inserito");
                    }
                }
            });

            stage.showAndWait();
            rootScene.setDisable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void confirmRule(ActionEvent event) {

        rules.getLast().setName(ruleName.getText()); //la regola inserita sarà sicuramente l'ultima

        Node sourceNode = (Node) event.getSource();
        Stage stage = (Stage) sourceNode.getScene().getWindow();
        
        //Aggiorna lo stato della regola in base alla selezione del toggleButton
        if (fireOnceRadio.isSelected()) {
            FireOnceVerified f = new FireOnceVerified();
            rules.getLast().setVerifiedTool(f);
        } else if (sleepRadio.isSelected()) {
            SleepVerified s = new SleepVerified();
            rules.getLast().setVerifiedTool(s);
            rules.getLast().setSleep(
                    24 * 60 * (Long.parseLong(daysPicker.getText())) + (Long.parseLong(minutesPicker.getText())));
        } else {
            FireMultipleVerified m = new FireMultipleVerified();
            rules.getLast().setVerifiedTool(m);
        }
        // Chiudi la finestra corrente
       
        stage.close();
        rootScene.setDisable(false);
    }

    @FXML
    private void cancel(ActionEvent event) {
        Node sourceNode = (Node) event.getSource();
        Stage stage = (Stage) sourceNode.getScene().getWindow();

        
        // Chiudi la finestra corrente
        stage.close();
    }

    @FXML
    private void sleepPick(ActionEvent event) {
        if (sleepRadio.isSelected()) {
            sleepPicker.setVisible(true);
        } else {
            sleepPicker.setVisible(false);
        }
    }

    //Inizializzo i picker delle sleep
    private void initializePickers() {
        daysPicker.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                // Se il nuovo testo non contiene solo cifre, reimposta il testo con il vecchio
                // valore
                daysPicker.setText(oldValue);
            }
        });
        minutesPicker.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                // Se il nuovo testo non contiene solo cifre, reimposta il testo con il vecchio
                // valore
                minutesPicker.setText(oldValue);
            }
        });
    }
    
    //Inizializzo i toggleButton sleep e FireOnce
    private void initalizeToggles(ToggleGroup toggleGroup) {
        toggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue == sleepRadio) {
                    // Quando SleepRadio è selezionato, rendi visibile sleepPicker
                    sleepPicker.setVisible(true);
                } else if (newValue == fireOnceRadio) {
                    // Quando FireOnceRadio è selezionato, rendi invisibile sleepPicker
                    sleepPicker.setVisible(false);
                }
            }
        });
    }

}
