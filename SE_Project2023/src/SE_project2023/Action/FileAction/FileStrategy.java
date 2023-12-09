package SE_project2023.Action.FileAction;

import java.io.File;
import java.io.Serializable;

/**
 *
 * @author emanu
 */
public interface FileStrategy {

    public void execute(File file, String sourcePath, String destinationPath);
}
