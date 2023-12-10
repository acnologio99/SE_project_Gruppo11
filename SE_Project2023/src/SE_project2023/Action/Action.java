/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package SE_project2023.Action;

import java.io.Serializable;

/**
 * Action è l'interfaccia delle azioni. E' serializzabile in quanto le azioni devono essere salvate su file.
 * I metodi add(), remove(), getChild() sono presenti per implementare un composite di Azioni in modo da poter
 * eseguire più azioni in sequenza utilizzando però la stessa interfaccia.
 * @author chris
 */
public interface Action extends Serializable {
    
    public boolean isFired(); //Controlla se un'azione è stata eseguita.
    public void fire(); //Esegue l'azione corrispondente.
    public void add();
    public void remove();
    public Action getChild();
    
}
