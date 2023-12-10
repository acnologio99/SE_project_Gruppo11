/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package SE_project2023;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Alert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author emanu
 */
public class AlertUtilTest {

    Alert alert;

    public AlertUtilTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of informationAlert method, of class AlertUtil.
     */
    @Test
    public void testInformationAlert() {
        System.out.println("informationAlert");
        String title = "Information";
        String content = "info";

        JFXPanel jfxPanel = new JFXPanel();
        Platform.runLater(() -> {

            alert = AlertUtil.informationAlert(title, content);

            assertNotNull(alert);
            assertEquals(Alert.AlertType.INFORMATION, alert.getAlertType());
            assertEquals(title, alert.getTitle());
            assertEquals(content, alert.getContentText());
        });
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Test of errorAlert method, of class AlertUtil.
     */
    @Test
    public void testErrorAlert() {
        System.out.println("errorAlert");
        String title = "Error";
        String content = "error";

        JFXPanel jfxPanel = new JFXPanel();
        Platform.runLater(() -> {
            alert = AlertUtil.errorAlert(title, content);

            assertNotNull(alert);
            assertEquals(Alert.AlertType.ERROR, alert.getAlertType());
            assertEquals(title, alert.getTitle());
            assertEquals(content, alert.getContentText());
        });
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Test of confirmationAlert method, of class AlertUtil.
     */
    @Test
    public void testConfirmationAlert() {
        System.out.println("confirmationAlert");
        String title = "Confirmation";
        String content = "confirm";

        JFXPanel jfxPanel = new JFXPanel();
        Platform.runLater(() -> {
            alert = AlertUtil.confirmationAlert(title, content);

            assertNotNull(alert);
            assertEquals(Alert.AlertType.CONFIRMATION, alert.getAlertType());
            assertEquals(title, alert.getTitle());
            assertEquals(content, alert.getContentText());
        });
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
