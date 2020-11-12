package importExport;

import exceptions.UnsupportedImplementation;
import model.Database;
import model.Entity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for importing database from files and exporting database to chosen file format
 */
public abstract class ImportExportService {


    /**
     * Reads file and makes entities from it
     * @param file which need to be load
     * @return list of entities that has been loaded
     * @throws IOException if file is in wrong format
     * @throws UnsupportedImplementation if wrong file is chosen
     */
    public abstract List<Entity> loadDatabase(File file) throws IOException, UnsupportedImplementation;

    /**
     * Writes list of entities in certain format
     * @param file in which list will be written
     * @param database list of entities that need to be written in file
     * @return true if file has been written successfully else false
     * @throws IOException
     */
    public abstract boolean saveDatabase(File file, List<Entity> database) throws IOException;

    /**
     *Creates a file with correct extension
     * @param namePath name of file
     * @return File created file
     */
    public abstract File createDatabase(String namePath);

    /**
     *Loads one or more files calling loadDatabase(File file) for every file in list and set loaded list of entities to database and set that files as current files in database
     * @param files to load
     * @return true loading has been finished successfully else false
     */
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
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        Database.getInstance().setCurrentFiles(files);
        return true;
    }

    /**
     * Saving database in new files
     * @param path location where file will be saved
     * @param numberOfEntity that user want to save in one file
     * @param nameFile wanted name file
     * @return true if files have been saved successfully
     * @throws IOException
     */
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

    /**
     *Saving database in files from database was loaded and creates new files if it is necessary
     * @param numberOfEntity user want to save in one file
     * @return true if files have been saved successfully
     */
    public boolean saveDatabase(int numberOfEntity){
        int counter = 0;
        int counterNewFile = 1;
        int counterOldFile = 0;
        String path = Database.getInstance().getCurrentFiles().get(0).getAbsolutePath();
        String folderPath = path.substring(0,path.length()-Database.getInstance().getCurrentFiles().get(0).getName().length());
        boolean old = true;
        List<Entity> entityToWrite = new ArrayList<>();
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

    /**
     *Creates new file and add that file in list of files in database
     * @param path name of file
     * @return true if file has been created
     */
    public boolean createNewDatabase(String path){
            File file = createDatabase(path);
            if(file != null)
                Database.getInstance().getCurrentFiles().add(file);
            else return false;
            return true;
    }
}
