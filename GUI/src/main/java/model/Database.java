package model;


import java.util.HashMap;
import java.util.Map;

public class Database {
    private static Database instance = null;

    private String title;
    private Integer uqID;
    //TODO figure out uqid
    private Map<String, Entity> entityMap;

    private Database(){
        this.title = null;
        this.uqID = 0;
        this.entityMap = new HashMap<String, Entity>();
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
