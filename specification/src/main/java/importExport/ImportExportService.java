package importExport;

import exceptions.UnsupportedImplementation;
import model.Database;
import model.Entity;

import javax.xml.crypto.Data;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class ImportExportService {

    public abstract List<Entity> loadDatabase(File file) throws IOException, UnsupportedImplementation;
    public abstract boolean saveDatabase(File file, List<Entity> database) throws IOException;
    public abstract File createDatabase(String namePath);

    public boolean load(List<File> files){
        if(files.isEmpty()) return false;
        List<Entity> list = new ArrayList<Entity>();
        try {
            for (File file : files) {
                List<Entity> list1 = loadDatabase(file);
                System.out.println(list1 + "loadDatabase");
                list.addAll(list1);
            }
            Database.getInstance().setEntityList(list);
            System.out.println(Database.getInstance().getEntityList()+ " listaaaaa");
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        Database.getInstance().setCurrentFiles(files);
        return true;
    }
    public boolean saveInNewFiles(int numberOfEntity,String nameFile) throws IOException {
        int counter = 0;
        int counterFile = 1;
        List<Entity> entityToWrite = new ArrayList<>();
        try {
            for (Entity entity : Database.getInstance().getEntityList()) {
                if (counter != numberOfEntity) {
                    entityToWrite.add(entity);
                } else {
                    File file = createDatabase(nameFile + counterFile);
                    saveDatabase(file, entityToWrite);
                    counter = 0;
                    counterFile++;
                    entityToWrite.clear();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean saveInOldFiles(int numberOfEntity){
        int counter = 0;
        int counterNewFile = 1;
        int counterOldFile = 0;
        String path = Database.getInstance().getCurrentFiles().get(0).getAbsolutePath();
        String folderPath = path.substring(-path.length());
        System.out.println(folderPath);
        List<Entity> entityToWrite = new ArrayList<>();
        try {
            for (Entity entity : Database.getInstance().getEntityList()) {
                if (counter != numberOfEntity) {
                    entityToWrite.add(entity);
                    counter++;
                } else {
                    if(counterOldFile!= Database.getInstance().getCurrentFiles().size()) {
                        saveDatabase(Database.getInstance().getCurrentFiles().get(counterOldFile), entityToWrite);
                        counterOldFile++;
                    }else{
                        File file = createDatabase("new"+counterNewFile);
                        saveDatabase(file,entityToWrite);
                        counterNewFile++;
                    }
                    counter = 1;
                    entityToWrite.clear();
                    entityToWrite.add(entity);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
