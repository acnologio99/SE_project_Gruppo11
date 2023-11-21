/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package SE_project2023;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 *
 * @author giova
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private Button saveButton;
    @FXML
    private Button loadButton;
    @FXML
    private Button addButton;
    @FXML
    private Button removeButton;
    @FXML
    private ListView<Rule> listView;
    @FXML
    private AnchorPane rulesWindow;
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
    private AnchorPane actionPane;
    @FXML
    private Button cancelBtnAction;
    @FXML
    private AnchorPane triggerPane;
    @FXML
    private Button timeTriggerBtn;
    @FXML
    private Button cancelBtnTrigger;
    @FXML
    private TitledPane rulesPane;
    @FXML
    private TextField ruleName;
    
    
    
    ObservableList<Rule> ruleList;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        HashSet<Rule> rules=new HashSet();
        ruleList=FXCollections.observableArrayList(rules);
        listView.getSelectionModel().setSelectionMode(javafx.scene.control.SelectionMode.MULTIPLE);
        Rule r=new Rule("giorgio");
        ruleList.add(r);
        listView.setItems(ruleList);
        
    }    

    @FXML
    private void saveRules(ActionEvent event) {
    }

    @FXML
    private void loadButton(ActionEvent event) {
    }

    @FXML
    private void addRule(ActionEvent event) {
        //Viene resa visibile la Window dell'aggiunta regole
        rulesWindow.setVisible(true);    
        
        
        
    }

    @FXML
    private void removeRules(ActionEvent event) {
        
        ruleList.removeAll(listView.getSelectionModel().getSelectedItems());
        
    }

    @FXML
    private void addAction(ActionEvent event) {
        actionPane.setVisible(true);
    }

    @FXML
    private void addTrigger(ActionEvent event) {
        triggerPane.setVisible(true);
    }

    @FXML
    private void confirmRule(ActionEvent event) {
        
        Rule regola= new Rule(ruleName.getText());
        ruleList.add(regola);
        
        rulesWindow.setVisible(false);
    }

    @FXML
    private void cancel(ActionEvent event) {
        rulesWindow.setVisible(false);
    }

    @FXML
    private void cancelAction(ActionEvent event) {
        actionPane.setVisible(false);
    }

    @FXML
    private void timeTrigger(ActionEvent event) {
    }

    @FXML
    private void CancelTrigger(ActionEvent event) {
        triggerPane.setVisible(false);
    }
    
}
