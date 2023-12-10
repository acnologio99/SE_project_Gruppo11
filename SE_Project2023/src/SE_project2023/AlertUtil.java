/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023;

import javafx.scene.control.Alert;


/**
 * La classe AlertUtil fornisce metodi statici per creare diversi tipi di alert
 * riducendo la duplicazione di codice e aumentando la manutenibilita'.
 * 
 * I metodi statici consentono di creare alert information, di error e di confermation senza 
 * istanziare la classe. Ogni metodo prepara e mostra un alert specifico basato sui parametri forniti.
 * 
 * @author emanu
 */
public class AlertUtil {

    public AlertUtil() {
    }

    public static Alert informationAlert(String title, String content) {
        return createAlert(Alert.AlertType.INFORMATION, title, content);
    }

    public static Alert errorAlert(String title, String content) {
        return createAlert(Alert.AlertType.ERROR, title, content);
    }

    public static Alert confirmationAlert(String title, String content) {
        return createAlert(Alert.AlertType.CONFIRMATION, title, content);
    }

    private static Alert createAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        return alert;
    }
}
