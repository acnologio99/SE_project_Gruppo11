/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package SE_project2023.Handlers;

import SE_project2023.Handlers.ActionHandler;
import SE_project2023.Handlers.ProgramHandler;
import SE_project2023.Handlers.ActionHandlerFactory;
import SE_project2023.Action.Action;
import SE_project2023.Action.AudioAction;
import SE_project2023.Action.ProgramAction;
import java.io.File;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cauro
 */
public class ProgramHandlerTest {
    ArrayList<String> commands;
    File file = new File("nuovo_file.txt");
    private ProgramHandler instance;
    private Action a;

    public ProgramHandlerTest() {
    }

    @Before
    public void setUp() {
        instance = new ProgramHandler(null);

    }

    /**
     * Test of fireAction method, of class MessageHandler.
     * Viene testato che l'azione viene effettivamente visualizzata, inoltre ritorna true se l'azione è stata eseguita correttamente.
     * Il test è prettamente legato a JavaFx in quanto utlizza la libreria Platform.
     * Si testa che l'azione Program venga effettivamente visualizzata con un'allert quando si utilizza JavaFx.
     */
    @Test(expected = ClassCastException.class)
    public void testFireAction() {
        System.out.println("fireAction");

        a = new ProgramAction(file.getPath(),commands);
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
        instance.fireAction(a); 
// rule con un'azione diversa che ha next=null, ClassCastException expected
    }

    @Test
    public void testFireActionChain() {
        System.out.println("fireActionChain");

        a = new ProgramAction(file.getPath(),commands);
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
