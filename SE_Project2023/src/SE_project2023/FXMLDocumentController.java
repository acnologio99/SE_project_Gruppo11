package SE_project2023;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
import SE_project2023.Regole.Rule;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author giova
 */
public class FXMLDocumentController implements Initializable {

    private Label label;
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;
    @FXML
    private ListView<Rule> listView;

    ObservableList<Rule> ruleList;

    RuleList rules = RuleList.getRuleList();

    //lista che mostra le azioni scelte
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //inizializzazione Liste
        ruleList = FXCollections.observableArrayList(rules.getHashRules());
        //setting selezione multipla 
        listView.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
        //aggiunta regola di testing
        //setting View
        listView.setItems(ruleList);

        // Creazione del servizio per controllare la lista ogni 10 secondi
        ScheduledService<Void> service = new ScheduledService<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() {
                        // Codice per controllare la lista
                        System.out.println("Controllo lista...");

                        for (Rule r : ruleList) {
                            System.out.println(r);
                            Platform.runLater(() -> {
                                if (r.isVerifiedRule() && !r.getAction().isFired()) {
                                    r.getAction().fire();
                                }
                            });

                        }

                        return null;
                    }
                };
            }
        };

        service.setPeriod(Duration.seconds(10));
        service.start();
    }

    @FXML
    private void removeRules(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Rimozione");
        alert.setHeaderText("");
        alert.setContentText("Sei sicuro di voler rimuovere?");
        Optional<ButtonType> response = alert.showAndWait();
        if (response.isPresent()) {
            ButtonType b = response.get();
            if (b == ButtonType.OK) {
                ruleList.removeAll(listView.getSelectionModel().getSelectedItems());
            }
        }
    }

    private void alertShow(String title, String header, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.show();
    }

    @FXML
    private void addRule(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLRule.fxml"));

            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("RuleCretor");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            RuleSingleton r = RuleSingleton.getInstance();
            if (r.isValid()) {
                ruleList.add(r.getRule());
                r.clearRule();
                alertShow("Inserimento", "", "Regola correttamente inserita", Alert.AlertType.INFORMATION);
            } else {
                alertShow("Errore!", "", "Regola non inserita", Alert.AlertType.ERROR);
                r.clearRule();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
