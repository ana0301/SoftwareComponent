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
        int i;
        lastUnique++;
        while (true){
            for (i = 0; i < allIds.size(); i++){
                if (allIds.get(i).equalsIgnoreCase(lastUnique.toString())){
                    lastUnique++;
                    break;
                }
            }
            if (i == allIds.size()) break;
        }
        return lastUnique.toString();
    }

    public void setEntityList(List<Entity> entityList) {
        this.entityList = entityList;
    }

    public boolean addEntity(Entity e){
        return this.entityList.add(e);
    }

    public boolean removeEntity(String id){
        for (Entity e: entityList) {
            if (e.getId().equalsIgnoreCase(id)) return entityList.remove(e);
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
