/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package SE_project2023;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    
    RuleSingleton r;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        r = RuleSingleton.getInstance();
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
                    if (r.getRule().getAction() != null) {
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
                    if (r.getRule().getTrigger() != null) {
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

        r.setName(ruleName.getText());

        Node sourceNode = (Node) event.getSource();
        Stage stage = (Stage) sourceNode.getScene().getWindow();

        r.setFlag(true);

        // Chiudi la finestra corrente
        stage.close();
    }

    @FXML
    private void cancel(ActionEvent event) {
        Node sourceNode = (Node) event.getSource();
        Stage stage = (Stage) sourceNode.getScene().getWindow();

        r.clearRule();
        r.setFlag(false);

        // Chiudi la finestra corrente
        stage.close();
    }

    private void alertShow(String title, String header, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

    public void close() {

    }
}
