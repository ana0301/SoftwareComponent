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

    private String title;
    private Integer uqID;
    //TODO figure out uqid
    private Map<String, Entity> entityMap;
    private List<Entity> entityList;

    private Database(){
        this.title = null;
        this.uqID = 0;
       // this.entityMap = new HashMap<String, Entity>();
        this.entityList =  new ArrayList<>();
        try{

        }catch (Exception e){
            e.printStackTrace();
        }




    }

    public static Database getInstance() {
        if (instance == null){
            instance = new Database();
        }
        return instance;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Map<String, Entity> getEntityMap() {
        return entityMap;
    }

    public void setEntityTable(Map<String, Entity> entityTable) {
        this.entityMap = entityTable;
    }


    public void addEntity(Entity e){
        entityMap.put(e.getId(), e);
    }

    public void deleteEntity(Entity e){
        entityMap.remove(e.getId(), e);
    }

}
