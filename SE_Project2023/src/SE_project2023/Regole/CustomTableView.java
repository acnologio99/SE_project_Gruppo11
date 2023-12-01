/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023.Regole;

import java.util.Observable;
import java.util.Observer;
import javafx.scene.control.TableView;

public class CustomTableView implements Observer {

    TableView<Rule> tv;
    
    public CustomTableView(TableView<Rule> tv) {
       
        this.tv=tv;
        
    }

    @Override
    public void update(Observable o, Object arg) {
        tv.refresh();
    }
    
    
}
