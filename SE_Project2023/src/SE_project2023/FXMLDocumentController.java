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
        // inizializzazione Liste, attende che il thread di caricamento finisca per iniziare ad usare Rules.
        list = new ArrayList<>();
        load.setOnSucceeded(e -> {
            for (Rule r : rules) {
                list.add(r);
            }
            ruleList = FXCollections.observableArrayList(list); //uso una lista d'appoggio list per far si che la tableView abbia le stesse regole di RuleList.
            tableView.setItems(ruleList);
        });

        // setting selezione multipla
        tableView.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
        // setting View
        nameCln.setCellValueFactory(new PropertyValueFactory<>("Name"));
        actionCln.setCellValueFactory(new PropertyValueFactory<>("Action"));
        triggerCln.setCellValueFactory(new PropertyValueFactory<>("Trigger"));
        statusCln.setCellValueFactory(cellData -> {
            boolean status = cellData.getValue().getStatus(); // Assume che "isStatus()" sia il metodo che restituisce
            // il booleano dalla classe Rule
            return new SimpleStringProperty(status ? "On" : "Off");
        });
        sleepCln.setCellValueFactory(cellData -> {
            long sleep = cellData.getValue().getSleep(); // Assume che "isStatus()" sia il metodo che restituisce il
            // booleano dalla classe Rule
            return new SimpleStringProperty(sleep > 0 ? "On" : "Off");
        });

        this.colorRow();
        rules.addObserver(this); //Il controller diventa observer di RuleList in modo da aggiornare la tableView anche quando un elemento interno viene aggiornato.

    }

    @FXML //Elimina tutti gli elementi selezionati chiedendo esplicitamente conferma
    private void removeRules(ActionEvent event) {
        if (tableView.getSelectionModel().getSelectedItems() == null) {
            return;
        }
        Alert alert = AlertUtil.confirmationAlert("Rimozione", "Sei sicuro di voler rimuovere?");
        Optional<ButtonType> response = alert.showAndWait();
        if (response.isPresent()) {
            ButtonType b = response.get();
            if (b == ButtonType.OK) {
                rules.removeAll(tableView.getSelectionModel().getSelectedItems());
                ruleList.removeAll(tableView.getSelectionModel().getSelectedItems());

            }
        }

    }


    //Quando una regola non è stata mai eseguita la riga è di colore blu, altrimenti verde.
    //Serve come segnalazione per l'utente anzichè usare tanti allert.
    private void colorRow() {

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

    }

    @FXML
    private void addRule(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLRule.fxml")); //apre il nuovo controller per permettere di inserire la regola.

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
                Alert alert = AlertUtil.informationAlert("Inserimento","Regola correttamente inserita");
                alert.showAndWait();
                ruleList.add(r);
            } else {
                Alert alert = AlertUtil.errorAlert("Errore!", "Regola non inserita");
                alert.showAndWait();
                rules.removeLast();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    //Metodi per attivare e disattivare una regola dal menu.
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

     //Essendo Observer, ogni volta che la RuleList si aggiorna, viene aggiornata anche la TableView.
     //Quando invece viene passato anche arg, significa che rule ha eseguito un'azione, di conseguenza crea la catena
     //grazie alla Factory e la fa partire.
    @Override
    public void update(Observable o, Object arg) {
        if (arg == null) {
            tableView.refresh();
        } else {
            tableView.refresh();
            Rule r = (Rule) arg;
            ActionHandlerFactory.createActionHandler().fireAction(r.getAction());
        }
    }
}
