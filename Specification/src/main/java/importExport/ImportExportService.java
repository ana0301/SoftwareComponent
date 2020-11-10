package importExport;

import model.Entity;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public interface ImportExportService {

    List<Entity> loadDatabase(File file) throws IOException;
    boolean saveDatabase(File file, List<Entity> database) throws IOException;
    File createDatabase(String namePath);

}
