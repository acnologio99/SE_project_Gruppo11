package SE_project2023;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
import SE_project2023.Action.Action;
import SE_project2023.Regole.Rule;
import SE_project2023.Trigger.Trigger;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

    ObservableList<Rule> ruleList;

    RuleList rules = RuleList.getRuleList();
    @FXML
    private TableColumn<Rule, String> nameCln;
    @FXML
    private TableColumn<Rule, Action> actionCln;
    @FXML
    private TableColumn<Rule, Trigger> triggerCln;
    @FXML
    private TableColumn<Rule, Boolean> statusCln;
    @FXML
    private TableView<Rule> tableView;

    //lista che mostra le azioni scelte
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //inizializzazione Liste
        ruleList = FXCollections.observableArrayList(rules.getArrayList());
        //setting selezione multipla 
        tableView.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
        //aggiunta regola di testing
        //setting View
        nameCln.setCellValueFactory(new PropertyValueFactory<>("Name"));
        actionCln.setCellValueFactory(new PropertyValueFactory<>("Action"));
        triggerCln.setCellValueFactory(new PropertyValueFactory<>("Trigger"));
        statusCln.setCellValueFactory(new PropertyValueFactory<>("Status"));
        tableView.setItems(ruleList);

        serviceControl();

        // Creazione del servizio per controllare la lista ogni 10 secondi
    }

    private void serviceControl() {
        ScheduledService<Void> service = new ScheduledService<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() {
                        // Codice per controllare la lista
                        System.out.println("Controllo lista...");

                        for (Rule r : rules.getArrayList()) {
                            if (r.ruleIsValid()) {
                                Platform.runLater(() -> {
                                    if (r.isVerifiedRule()) {
                                        r.fire();
                                        r.deactive();
                                        tableView.refresh();
                                    }
                                });
                            }
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
                rules.getArrayList().removeAll(tableView.getSelectionModel().getSelectedItems());
                updateTableView();
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
            Rule r = new Rule();
            rules.add(r);

            Stage stage = new Stage();
            stage.setTitle("RuleCreator");
            stage.setScene(new Scene(root));
            stage.showAndWait();
            if (rules.getLast().ruleIsValid()) {
                updateTableView();
                alertShow("Inserimento", "", "Regola correttamente inserita", Alert.AlertType.INFORMATION);
            } else {
                alertShow("Errore!", "", "Regola non inserita", Alert.AlertType.ERROR);

                rules.removeLast();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void updateTableView() {
        ObservableList<Rule> observableRules = FXCollections.observableArrayList(rules.getArrayList());
        tableView.setItems(observableRules);

    }

    @FXML
    private void setOnRule(ActionEvent event) {
        Rule r = tableView.getSelectionModel().getSelectedItem();
        r.active();
        tableView.refresh();

    }

    @FXML
    private void set(ActionEvent event) {
        Rule r = tableView.getSelectionModel().getSelectedItem();
        r.deactive();
        tableView.refresh();
    }
}
