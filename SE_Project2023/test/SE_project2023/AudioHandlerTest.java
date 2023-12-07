/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package SE_project2023;

import SE_project2023.Action.AudioAction;
import SE_project2023.Action.FileAction;
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
public class AudioHandlerTest {
    
    private AudioHandler instance;
    private Rule r;
    
    public AudioHandlerTest() {
    }
    
    @Before
    public void setUp() {
        instance = new AudioHandler(null);
        r = new Rule();
    }

    /**
     * Test of fireAction method, of class FileHandler.
     */
    @Test(expected=ClassCastException.class)
    public void testFireAction() {
        System.out.println("fireAction");
        
        r.setAction(new AudioAction("./data/song01.wav"));
        JFXPanel jfxPanel = new JFXPanel();
         Platform.runLater(() -> {
           instance.fireAction(r); //javafx test, allert with sound
        });
        try {
            Thread.sleep(5000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        r.setAction(new MessageBoxAction("Test"));
        instance.fireAction(r); //rule with a differenct Action but without "next" nullPointer expected
    }
    
    @Test
    public void testFireActionChain() {
        System.out.println("fireActionChain");
        
        r.setAction(new AudioAction("./data/song01.wav"));
        ActionHandler handler = ActionHandlerFactory.createActionHandler();
        JFXPanel jfxPanel = new JFXPanel();
         Platform.runLater(() -> {
           handler.fireAction(r); //expected to play the sound
        });
        try {
            Thread.sleep(5000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        
    }
    
}
