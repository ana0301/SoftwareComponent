package model;

import java.util.HashMap;
import java.util.Map;

public class Entity{
    /**
     * Type of entity
     */
    private String title;
    /**
     * Id of entity
     */
    private String id;
    /**
     * Data of entity
     */
    private Map<String, Object> entityData = new HashMap<String, Object>();

    /**
     * Empty constructor for entity
     */
    public Entity(){}

    /**
     * Constructor for entity
     * @param title
     * @param id
     */
    public Entity(String title, String id) {
        this.title = title;
        this.id = id;
    }

    /**
     * Constructor for entity
     * @param title - type of entity
     * @param id of entity
     * @param entityData  - data of entity
     */
    public Entity(String title, String id, Map<String, Object> entityData) {
        this.title = title;
        this.id = id;
        this.entityData = entityData;
    }


    /**
     *
     * @return type of entity
     */
    public String getTitle() {
        return title;
    }

    /**
     * setter for type of entity
     * @param title of entity
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return id of entity
     */
    public String getId() {
        return id;
    }

    /**
     * setter for id of entity
     * @param id of entity
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return data of entity
     */
    public Map<String, Object> getEntityData() {
        return entityData;
    }

    /**
     * setter for entity data
     * @param entityData data of entity
     */
    public void setEntityData(Map<String, Object> entityData) {
        this.entityData = entityData;
    }

    /**
     * Adds one key-value set in existing data of entity
     * @param key  for set
     * @param value for set
     */
    public void addEntityData(String key, Object value){
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
