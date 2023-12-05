/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023.Tool;

import SE_project2023.Regole.Rule;
import java.io.Serializable;

/**
 *
 * @author giova
 */
public abstract class VerifiedTool implements Serializable{
       public boolean Verified(Rule r){
            return true;
       }
}
