package SE_project2023.Action.FileAction;

import java.io.File;

/**
 * Interfaccia implementata da tutte le strategie usate da FileAction,
 * per l'applicazione dello Strategy pattern.
 * 
 * @author emanu
 */
public interface FileStrategy {

    public void execute(File file, String sourcePath, String destinationPath);
}
