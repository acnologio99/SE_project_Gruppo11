/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023.Tool;

import SE_project2023.Regole.Rule;

/**
 *
 * @author giova
 */
public class FireOnceVerified extends VerifiedTool{

    public FireOnceVerified() {
    }
    
    public boolean Verified(Rule r){
            return !r.getAction().isFired();
       }
}
