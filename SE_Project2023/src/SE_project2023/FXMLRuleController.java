/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package SE_project2023;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

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
    RuleList r = RuleList.getRuleList();
    @FXML
    private AnchorPane sleepPicker;
    @FXML
    private RadioButton sleepRadio;
    @FXML
    private TextField daysPicker;
    @FXML
    private TextField minutesPicker;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        sleepPicker.setVisible(false);
        daysPicker.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                // Se il nuovo testo non contiene solo cifre, reimposta il testo con il vecchio valore
                daysPicker.setText(oldValue);
            }
        });
        minutesPicker.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                // Se il nuovo testo non contiene solo cifre, reimposta il testo con il vecchio valore
                minutesPicker.setText(oldValue);
            }
        });
       
      

    }

    @FXML
    private void addAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLAction.fxml"));

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Action");
            stage.setScene(new Scene(root));
            stage.addEventHandler(WindowEvent.ANY,
                    new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    if (r.getLast().getAction() != null) {
                        actionTxt.setText("Azione inserita");
                    }
                }
            });
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addTrigger(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLTrigger.fxml"));

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Trigger");
            stage.setScene(new Scene(root));
            stage.addEventHandler(WindowEvent.ANY,
                    new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    if (r.getLast().getTrigger() != null) {
                        triggerTxt.setText("Trigger inserito");
                    }
                }
            });

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void confirmRule(ActionEvent event) {
        //ruleList.add(r);

        //alertShow("Inserimento", "", "Regola correttamente inserita", Alert.AlertType.INFORMATION);
        
        r.getLast().setName(ruleName.getText());

        Node sourceNode = (Node) event.getSource();
        Stage stage = (Stage) sourceNode.getScene().getWindow();
        
        r.getLast().setFlag(true);
        if(sleepRadio.isSelected())
            r.getLast().setSleep(24*60*(Long.parseLong(daysPicker.getText()))+(Long.parseLong(minutesPicker.getText())));
        // Chiudi la finestra corrente
        stage.close();
    }

    @FXML
    private void cancel(ActionEvent event) {
        Node sourceNode = (Node) event.getSource();
        Stage stage = (Stage) sourceNode.getScene().getWindow();

        RuleList r= RuleList.getRuleList();
        r.removeLast();
        r.getLast().setFlag(false);
        
        // Chiudi la finestra corrente
        stage.close();
    }

    public void close() {

    }

    @FXML
    private void sleepPick(ActionEvent event) {
        if(sleepRadio.isSelected())
            sleepPicker.setVisible(true);
        else sleepPicker.setVisible(false);
    }

    
}
