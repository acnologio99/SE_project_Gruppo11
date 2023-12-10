/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package SE_project2023.Handlers;

import SE_project2023.Handlers.ActionHandler;
import SE_project2023.Handlers.ActionHandlerFactory;
import SE_project2023.Handlers.MessageHandler;
import SE_project2023.Action.Action;
import SE_project2023.Action.AudioAction;
import SE_project2023.Action.MessageBoxAction;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

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
     * Viene testato che l'azione viene effettivamente visualizzata, inoltre ritorna true se l'azione è stata eseguita correttamente.
     * Il test è prettamente legato a JavaFx in quanto utlizza la libreria Platform.
     * Si testa che l'azione MessageBox venga effettivamente visualizzata con un'allert quando si utilizza JavaFx.
     */
    @Test(expected = ClassCastException.class)
    public void testFireAction() {
        System.out.println("fireAction");

        a = new MessageBoxAction("test");
        JFXPanel jfxPanel = new JFXPanel();
        Platform.runLater(() -> {
            boolean res = instance.fireAction(a); // javafx test, allert con "test" message
            assertTrue(res); //se l'handler viene eseguito correttamente ritorna true.
        });
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       

        a = new AudioAction();
        instance.fireAction(a); // rule con un'azione diversa che ha next=null, ClassCastException expected
    }

    @Test
    public void testFireActionChain() {
        System.out.println("fireActionChain");

        a = new MessageBoxAction("Test");
        ActionHandler handler = ActionHandlerFactory.createActionHandler();
        JFXPanel jfxPanel = new JFXPanel();
        Platform.runLater(() -> {
            boolean res = handler.fireAction(a); //Viene testato il comportamento nell chainOfResponsability
            assertTrue(res);
        });
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
