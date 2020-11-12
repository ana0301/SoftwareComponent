package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class that contains all loaded entities
 */
public class Database {
    /**
     * Instance of current database
     */
    private static Database instance = null;
    /**
     * Saves the last available id
     */
    private Integer lastUnique;
    /**
     * List of loaded entities
     */
    private List<Entity> entityList;
    /**
     * List of ids for current database
     */
    private List<String> allIds;
    /**
     * List of files from database which was loaded
     */
    private List<File> currentFiles;

    /**
     * Empty constructor
     */
    private Database(){
        this.lastUnique = -1;
        this.entityList =  new ArrayList<>();
        this.allIds = new ArrayList<>();
        this.currentFiles = new ArrayList<>();
    }

    /**
     *
     * @return unique id
     */
    public String getUniqueId(){
        boolean unique;
        lastUnique++;
        while (true){
            unique = true;
            for (String id: allIds) {
                if (id.equalsIgnoreCase(lastUnique.toString())){
                    unique = false;
                    lastUnique++;
                    break;
                }
            }
            if(unique) break;
        }
        return lastUnique.toString();
    }

    /**
     * Checks if given id is unique
     * @param id of entity
     * @return true if given id is unique else false
     */
    public boolean isUniqueId(String id){
        for (String i: allIds) {
            if (i.equalsIgnoreCase(id)) return false;
        }
        return true;
    }

    /**
     * Getter for entity list
     * @return list of loaded entities
     */
    public List<Entity> getEntityList() {
        return entityList;
    }

    /**
     * Setter for entity list
     * @param entityList that is loaded
     */
    public void setEntityList(List<Entity> entityList) {
        this.entityList = entityList;
    }

    /**
     *
     * @param id of entity
     * @return entity from current entity list which has given id
     */
    public Entity getEntityById(String id){
        for (Entity e : entityList) {
            if (e.getId().equalsIgnoreCase(id)) return e;
        }
        return null;
    }

    /**
     * Adds entity to entity list if id is unique
     * @param e entity wanted to add in list
     * @return true if the entity has been added to the entity list else false
     */
    public boolean addEntity(Entity e){
        if (addId(e.getId())) return this.entityList.add(e);
        return false;
    }

    /**
     * Removes entity with given id from entity list
     * @param id of entity
     * @return true if entity has been removed correctly else false
     */
    public boolean removeEntity(String id){
        for (Entity e: entityList) {
            if (e.getId().equalsIgnoreCase(id)) {
                if (removeId(e.getId())) return entityList.remove(e);
            }
        }
        return false;
    }

    /**
     * Adds given id to the list of current ids
     * @param id of entity
     * @return true if given id has been added correctly else false
     */
    public boolean addId(String id){
        return this.allIds.add(id);
    }

    /**
     * Removes given id from the list of ids if id exists
     * @param id of entity
     * @return true if given id has been removed correctly else false
     */
    public boolean removeId(String id){
        for(String i: allIds){
            if (i.equalsIgnoreCase(id)) return allIds.remove(i);
        }
        return false;
    }

    /**
     *
     * @return the instance of database if it exists, else create a new database and returns that instance
     */
    public static Database getInstance() {
        if (instance == null){
            instance = new Database();
        }
        return instance;
    }


    /**
     * Loads ids of all entities from the given database
     * @param entityList whose ids need to be loaded
     * @return false if some id has already been added in list of ids
     */
    public boolean loadIds(List<Entity> entityList){
        for(Entity entity: entityList){
            if(allIds.contains(entity.getId())) {
                System.out.println("POGRESNA BAZA");
                return false;
            }
            allIds.add(entity.getId());
            for(Map.Entry<String,Object> nested : entity.getEntityData().entrySet()){
                if(nested.getValue() instanceof Entity){
                    Entity e = (Entity) nested.getValue();
                    if(allIds.contains(e.getId())) {
                        System.out.println("POGRESNA BAZA");
                        return false;
                    }
                    allIds.add(e.getId());
                }
            }
        }
        return true;
    }

    /**
     * Getter for current files
     * @return list of files
     */
    public List<File> getCurrentFiles() {
        return currentFiles;
    }

    /**
     * Setter for current files
     * @param currentFiles from which database has been loaded
     */
    public void setCurrentFiles(List<File> currentFiles) {
        this.currentFiles = currentFiles;
    }

    /**
     * Getter for all ids
     * @return list of ids
     */
    public List<String> getAllIds() {
        return allIds;
    }
}
