/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package SE_project2023;

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
public class AudioHandlerTest {

    private AudioHandler instance;
    private Action a;

    public AudioHandlerTest() {
    }

    @Before
    public void setUp() {
        instance = new AudioHandler(null);
        a = new AudioAction();
    }

    /**
     * Test of fireAction method, of class FileHandler.
     * Viene testato che l'azione viene effettivamente visualizzata, inoltre ritorna true se l'azione è stata eseguita correttamente.
     * Il test è prettamente legato a JavaFx in quanto utlizza la libreria Platform.
     * Si testa che l'azione AudioHandler venga effettivamente visualizzata con un'allert quando si utilizza JavaFx.
     */
    @Test(expected = ClassCastException.class)
    public void testFireAction() {
        System.out.println("fireAction");

        a = new AudioAction("./data/song01.wav");
        JFXPanel jfxPanel = new JFXPanel();
        Platform.runLater(() -> {
            a.fire();
            boolean res=instance.fireAction(a); // javafx test, allert che accompagna il suono
            assertTrue(res); // se l'handler viene eseguito correttamente ritorna true.
        });
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        a = new MessageBoxAction("Test");
        instance.fireAction(a); // Azione diversa ma con next = null, ClassCastException expected.
    }

    /**
     * Test di una regola con una AudioAction all'interno della catena.
     */

    @Test
    public void testFireActionChain() {
        System.out.println("fireActionChain");

        a = new AudioAction("./data/song01.wav");
        ActionHandler handler = ActionHandlerFactory.createActionHandler();
        JFXPanel jfxPanel = new JFXPanel();
        Platform.runLater(() -> {
            a.fire();
            boolean res = handler.fireAction(a); // testo il funzionamento dell'azione nella catena, visivamente deve mostrare un allert.
            assertTrue(res);
        });
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
