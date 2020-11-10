import model.Entity;

import java.io.File;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        ImportExportYaml importExport = new ImportExportYaml();
        try {
            File file = new File("Yaml/src/proba.yaml");
            System.out.println(file.getAbsolutePath());
            List<Entity> entityList = importExport.loadDatabase(file);
            System.out.println("YAML");
            for(Entity entity : entityList){
                System.out.println(entity);
            }
            //importExport.createDatabase("dada.json");
            importExport.saveDatabase(new File("Yaml/src/upis.yaml"),entityList);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
