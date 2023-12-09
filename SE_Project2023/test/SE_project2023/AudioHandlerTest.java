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
     */
    @Test(expected = ClassCastException.class)
    public void testFireAction() {
        System.out.println("fireAction");

        a = new AudioAction("./data/song01.wav");
        JFXPanel jfxPanel = new JFXPanel();
        Platform.runLater(() -> {
            a.fire();
            instance.fireAction(a); // javafx test, allert with sound
        });
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        a = new MessageBoxAction("Test");
        instance.fireAction(a); // rule with a differenct Action but without "next" nullPointer expected
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
            handler.fireAction(a); // expected to play the sound
        });
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
