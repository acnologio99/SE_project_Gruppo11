/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package SE_project2023;

import SE_project2023.Action.Action;
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
    private Action a;

    public MessageHandlerTest() {
    }

    @Before
    public void setUp() {
        instance = new MessageHandler(null);

    }

    /**
     * Test of fireAction method, of class MessageHandler.
     */
    @Test(expected = ClassCastException.class)
    public void testFireAction() {
        System.out.println("fireAction");

        a = new MessageBoxAction("test");
        JFXPanel jfxPanel = new JFXPanel();
         Platform.runLater(() -> {
           instance.fireAction(a); //javafx test, allert with same "test" message.
        });
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        a= new AudioAction();
        instance.fireAction(a); //rule with a differenct Action but without "next" ClassCastException expected
    }

     @Test
    public void testFireActionChain() {
        System.out.println("fireActionChain");

        a = new MessageBoxAction("Test");
        ActionHandler handler = ActionHandlerFactory.createActionHandler();
        JFXPanel jfxPanel = new JFXPanel();
         Platform.runLater(() -> {
           handler.fireAction(a); //expected to show the allert
        });
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }



}
