package model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Entity extends Type {
    private String title;
    private String id;
    private Map<String, Type> entityData = new HashMap<String, Type>();

    public Entity(){}

    public Entity(String title, String id) {
        this.title = title;
        this.id = id;
    }

    public Entity(String title, String id, Map<String, Type> entityData) {
        this.title = title;
        this.id = id;
        this.entityData = entityData;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, Type> getEntityData() {
        return entityData;
    }

    public void setEntityData(Map<String, Type> entityData) {
        this.entityData = entityData;
    }

    public void addEntityData(String key, Type value){
        entityData.put(key,value);
    }

    @Override
    public String toString() {
        return "Entity{" +
                "title='" + title + '\'' +
                ", id=" + id +
                ", entityData=" + entityData +
                '}';
    }

}
