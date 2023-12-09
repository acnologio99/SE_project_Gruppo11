/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SE_project2023.Action.FileAction;

/**
 *
 * @author emanu
 */
public class StrategyFactory {
    public FileStrategy getStrategy(String action) {
        switch (action) {
            case "copy":
                return new CopyStrategy();
            case "move":
                return new MoveStrategy();
            case "remove":
                return new RemoveStrategy();
            default:
                throw new IllegalArgumentException("Unknown action: " + action);
        }
    }
}
