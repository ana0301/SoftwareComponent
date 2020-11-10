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

    private Database(){
        this.lastUnique = -1;
        this.entityList =  new ArrayList<>();
        this.allIds = new ArrayList<>();
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

    public void setEntityList(List<Entity> entityList) {
        this.entityList = entityList;
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


}
