package model;


import importExport.ImportExportManager;
import importExport.ImportExportService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Database {
    private static Database instance = null;

    private Integer lastUnique;
    private List<Entity> entityList;
    private List<String> allIds;
    private List<File> currentFiles;

    private Database(){
        this.lastUnique = -1;
        this.entityList =  new ArrayList<>();
        this.allIds = new ArrayList<>();
        this.currentFiles = new ArrayList<>();
    }

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

    public boolean isUniqueId(String id){
        for (String i: allIds) {
            if (i.equalsIgnoreCase(id)) return false;
        }
        return true;
    }

    public List<Entity> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<Entity> entityList) {
        this.entityList = entityList;
    }

    public Entity getEntityById(String id){
        for (Entity e : entityList) {
            if (e.getId().equalsIgnoreCase(id)) return e;
        }
        return null;
    }

    public boolean addEntity(Entity e){
        if (addId(e.getId())) return this.entityList.add(e);
        return false;
    }

    public boolean removeEntity(String id){
        for (Entity e: entityList) {
            if (e.getId().equalsIgnoreCase(id)) {
                if (removeId(e.getId())) return entityList.remove(e);
            }
        }
        return false;
    }

    public boolean addId(String id){
        return this.allIds.add(id);
    }

    public boolean removeId(String id){
        for(String i: allIds){
            if (i.equalsIgnoreCase(id)) return allIds.remove(i);
        }
        return false;
    }

    public static Database getInstance() {
        if (instance == null){
            instance = new Database();
        }
        return instance;
    }



    public boolean loadIds(){
        for(Entity entity: entityList){
            if(allIds.contains(entity.getId())) {
                allIds.clear();
                System.out.println("POGRESNA BAZA");
                return false;
            }
            allIds.add(entity.getId());
            for(Map.Entry<String,Object> nested : entity.getEntityData().entrySet()){
                if(nested.getValue() instanceof Entity){
                    Entity e = (Entity) nested.getValue();
                    if(allIds.contains(e.getId())) {
                        allIds.clear();
                        System.out.println("POGRESNA BAZA");
                        return false;
                    }
                    allIds.add(entity.getId());
                }
            }
        }
        return true;
    }
    public List<File> getCurrentFiles() {
        return currentFiles;
    }

    public void setCurrentFiles(List<File> currentFiles) {
        this.currentFiles = currentFiles;
    }

    public List<String> getAllIds() {
        return allIds;
    }
}
