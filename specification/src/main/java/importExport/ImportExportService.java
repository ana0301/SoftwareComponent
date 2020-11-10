package importExport;

import model.Entity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public abstract class ImportExportService {

    public abstract List<Entity> loadDatabase(File file) throws IOException;
    public abstract boolean saveDatabase(File file, List<Entity> database) throws IOException;
    public abstract File createDatabase(String namePath);

}
