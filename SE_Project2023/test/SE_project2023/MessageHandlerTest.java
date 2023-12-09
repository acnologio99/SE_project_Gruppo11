/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package SE_project2023;

import SE_project2023.Action.AudioAction;
import SE_project2023.Action.MessageBoxAction;
import SE_project2023.Regole.Rule;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author chris
 */
public class MessageHandlerTest {
    
    private MessageHandler instance;
    private Rule r;
    
    public MessageHandlerTest() {
    }
    
    @Before
    public void setUp() {
        instance = new MessageHandler(null);
        r = new Rule();
        
    }

    /**
     * Test of fireAction method, of class MessageHandler.
     */
    @Test(expected = ClassCastException.class)
    public void testFireAction() {
        System.out.println("fireAction");
        
        r.setAction(new MessageBoxAction("test"));
        JFXPanel jfxPanel = new JFXPanel();
         Platform.runLater(() -> {
           instance.fireAction(r); //javafx test, allert with same "test" message.
        });
        try {
            Thread.sleep(2000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        r.setAction(new AudioAction());
        instance.fireAction(r); //rule with a differenct Action but without "next" ClassCastException expected
        //Nella catena questo non può accadere dato che è presente un Handler Catch All.
    }
    
     @Test
    public void testFireActionChain() {
        System.out.println("fireActionChain");
        
        r.setAction(new MessageBoxAction("Test"));
        ActionHandler handler = ActionHandlerFactory.createActionHandler();
        JFXPanel jfxPanel = new JFXPanel();
         Platform.runLater(() -> {
           handler.fireAction(r); //expected to show the allert
        });
        try {
            Thread.sleep(2000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        
    }
    
   
    
}
