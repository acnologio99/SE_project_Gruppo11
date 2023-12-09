package SE_project2023;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
import SE_project2023.Action.Action;
import SE_project2023.Regole.Rule;
import SE_project2023.Trigger.Trigger;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author giova
 */
public class FXMLDocumentController implements Initializable, Observer, Serializable {

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
    private TableColumn<Rule, String> statusCln;
    @FXML
    private TableView<Rule> tableView;
    @FXML
    private TableColumn<Rule, String> sleepCln;
    @FXML
    private VBox rootScene;
    private List<Rule> list;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        LoadService load = new LoadService();
        load.start();
        //inizializzazione Liste
        list = new ArrayList<>();
        load.setOnSucceeded(e -> {
            for(Rule r : rules){
                list.add(r);
            }
            ruleList = FXCollections.observableArrayList(list);
            tableView.setItems(ruleList);
        });

        //setting selezione multipla
        tableView.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
        //setting View
        nameCln.setCellValueFactory(new PropertyValueFactory<>("Name"));
        actionCln.setCellValueFactory(new PropertyValueFactory<>("Action"));
        triggerCln.setCellValueFactory(new PropertyValueFactory<>("Trigger"));
        statusCln.setCellValueFactory(cellData -> {
            boolean status = cellData.getValue().getStatus(); // Assume che "isStatus()" sia il metodo che restituisce il booleano dalla classe Rule
            return new SimpleStringProperty(status ? "On" : "Off");
        });
        sleepCln.setCellValueFactory(cellData -> {
            long sleep = cellData.getValue().getSleep(); // Assume che "isStatus()" sia il metodo che restituisce il booleano dalla classe Rule
            return new SimpleStringProperty(sleep>0 ? "On" : "Off");
        });
        tableView.setRowFactory(row -> new TableRow<Rule>() {
            @Override
            protected void updateItem(Rule r, boolean empty) {
            super.updateItem(r, empty);
                if (r == null || empty) {
                setStyle(""); // Se l'elemento è vuoto o la riga è vuota, non impostare uno stile
            } else {
            // Imposta il colore della riga in base al tipo dell'elemento
            if (r.getAction().isFired()) {
                setStyle("-fx-background-color: lightgreen;");
            } else if (!r.getAction().isFired()) {
                setStyle("-fx-background-color: lightblue;");
            } else {
                setStyle(""); // Altri tipi possono avere uno stile diverso o nessuno
            }
        }
    }
});

        rules.addObserver(this);

    }

    @FXML
    private void removeRules(ActionEvent event) {
        if (tableView.getSelectionModel().getSelectedItems() == null) {
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Rimozione");
        alert.setHeaderText("");
        alert.setContentText("Sei sicuro di voler rimuovere?");
        Optional<ButtonType> response = alert.showAndWait();
        if (response.isPresent()) {
            ButtonType b = response.get();
            if (b == ButtonType.OK) {
                rules.removeAll(tableView.getSelectionModel().getSelectedItems());
                ruleList.removeAll(tableView.getSelectionModel().getSelectedItems());

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
            rootScene.setDisable(true);

            Stage stage = new Stage();
            stage.setTitle("RuleCreator");
            stage.setScene(new Scene(root));
            stage.showAndWait();
            rootScene.setDisable(false);

            if (rules.getLast().ruleIsValid()) {
                alertShow("Inserimento", "", "Regola correttamente inserita", Alert.AlertType.INFORMATION);
                ruleList.add(r);
            } else {
                alertShow("Errore!", "", "Regola non inserita", Alert.AlertType.ERROR);
                rules.removeLast();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void setOnRule(ActionEvent event) {
        Rule r = tableView.getSelectionModel().getSelectedItem();
        if (r != null) {
            r.active();
        } else {
        }

    }

    @FXML
    private void set(ActionEvent event) {
        Rule r = tableView.getSelectionModel().getSelectedItem();
        if (r != null) {
            r.deactive();
        } else {
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg == null)
            tableView.refresh();
        else {
            tableView.refresh();
            Rule r = (Rule)arg;
            ActionHandlerFactory.createActionHandler().fireAction(r.getAction());
        }
    }
}
