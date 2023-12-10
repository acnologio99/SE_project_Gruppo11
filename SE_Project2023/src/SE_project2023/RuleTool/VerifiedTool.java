/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023.RuleTool;

import SE_project2023.Regole.Rule;
import java.io.Serializable;

/**
 * Questa interfaccia rappresenta i possibili stati che puo' avere una regola.
 * @author giova
 */
public interface VerifiedTool extends Serializable{
       public boolean verified(Rule r);
}
