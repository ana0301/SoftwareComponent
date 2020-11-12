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

    public boolean loadDatabase(List<File> files){
        if(files.isEmpty()) return false;
        List<Entity> list = new ArrayList<Entity>();
        try {
            for (File file : files) {
                List<Entity> list1 = loadDatabase(file);
                list.addAll(list1);
            }
           if( Database.getInstance().loadIds(list))
                Database.getInstance().setEntityList(list);
           else return false;
            System.out.println( "Ucitano entiteta: "+Database.getInstance().getEntityList().size());
            System.out.println(Database.getInstance().getAllIds());
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        Database.getInstance().setCurrentFiles(files);
        return true;
    }
    public boolean saveDatabase(String path, int numberOfEntity, String nameFile) throws IOException {
        int counter = 0;
        int counterFile = 1;
        List<Entity> entityToWrite = new ArrayList<>();
        try {
            for (Entity entity : Database.getInstance().getEntityList()) {
                if (counter == numberOfEntity) {
                    File file = createDatabase(path+"\\"+nameFile + counterFile);
                    System.out.println(file.getAbsolutePath());
                    saveDatabase(file, entityToWrite);
                    counter = 0;
                    counterFile++;
                    entityToWrite.clear();
                }
                entityToWrite.add(entity);
                counter++;
            }
            File file = createDatabase(path+"\\"+nameFile + counterFile);
            System.out.println(file.getAbsolutePath());
            saveDatabase(file, entityToWrite);
            entityToWrite.clear();
            for(File filee: Database.getInstance().getCurrentFiles()){
                filee.delete();
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean saveDatabase(int numberOfEntity){
        int counter = 0;
        int counterNewFile = 1;
        int counterOldFile = 0;
        String path = Database.getInstance().getCurrentFiles().get(0).getAbsolutePath();
        String folderPath = path.substring(0,path.length()-Database.getInstance().getCurrentFiles().get(0).getName().length());
        boolean old = true;
        List<Entity> entityToWrite = new ArrayList<>();
        System.err.println(Database.getInstance().getEntityList().size() + " toliko entiteta");
        try {
            for (Entity entity : Database.getInstance().getEntityList()) {
               if(counter == numberOfEntity){
                    if(counterOldFile!= Database.getInstance().getCurrentFiles().size()) {
                        saveDatabase(Database.getInstance().getCurrentFiles().get(counterOldFile), entityToWrite);
                        counterOldFile++;
                    }else{
                        File file = createDatabase(folderPath+"new"+counterNewFile);
                        saveDatabase(file,entityToWrite);
                        counterNewFile++;
                        old = false;
                    }
                   counter = 0;
                   entityToWrite.clear();
                }
                entityToWrite.add(entity);
                counter++;
            }
            if(old && counterOldFile!= Database.getInstance().getCurrentFiles().size()) {
                saveDatabase(Database.getInstance().getCurrentFiles().get(counterOldFile), entityToWrite);
                counterOldFile++;
            }
            else {
                File file = createDatabase(folderPath+"new"+counterNewFile);
                saveDatabase(file,entityToWrite);
            }
            System.err.println(counterOldFile);
            if(counterOldFile < Database.getInstance().getCurrentFiles().size()){
                while(counterOldFile < Database.getInstance().getCurrentFiles().size()){
                   Database.getInstance().getCurrentFiles().get(counterOldFile).delete();
                    counterOldFile++;
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean createNewDatabase(String path){
            File file = createDatabase(path);
            if(file != null)
                Database.getInstance().getCurrentFiles().add(file);
            else return false;
            return true;
    }
}
