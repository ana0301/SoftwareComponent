import model.Entity;

import java.io.File;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        ImportExportJson importExport = new ImportExportJson();


        try {
            File file = new File("json/src/proba.json");
            System.out.println(file.getAbsolutePath());
            List<Entity> entityList = importExport.loadDatabase(file);
            System.out.println("JSON");
            for(Entity entity : entityList){
                System.out.println(entity);
            } File files =importExport.createDatabase("C:\\Users\\Reljic\\Desktop\\dadad.json");
            importExport.saveDatabase(new File("json/src/upis.json"),entityList);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
