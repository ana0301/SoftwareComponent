import model.Entity;

import java.io.File;
import java.util.List;

public class TestCustom {
    public static void main(String[] args) {
        ImportExportCustom importExport = new ImportExportCustom();
        try {
            File file = new File("Custom/src/proba.txt");
            List<Entity> entityList = importExport.loadDatabase(file);
            System.out.println("CUSTOM");
            for(Entity entity : entityList){
                System.out.println(entity);
            }
            //importExport.createDatabase("dada.json");
            importExport.saveDatabase(new File("Custom/src/upis.txt"),entityList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
