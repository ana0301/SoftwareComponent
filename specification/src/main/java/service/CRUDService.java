package service;

import exceptions.IdNotUnique;
import model.Entity;

import java.util.List;

/**
 * Service for adding, updating and deleting entities
 */
public interface CRUDService {
    /**
     * Creates entity and adds it to the database
     * @param id Entity id
     * @param title Entity title
     * @param keys Array of entity data keys
     * @param values Array of entity data values
     * @return True if entity is successfully added
     * @throws IdNotUnique If id is not unique
     */
    boolean addEntity(String id, String title, String[] keys, String[] values) throws IdNotUnique;

    /**
     * Creates nested entity and adds it to its parent
     * @param parentId Id of parent entity
     * @param parentKey Key in parent entity for nested entity
     * @param title Nested entity title
     * @param childId Nested entity id
     * @param keys Array of nested entity data keys
     * @param values Array of nested entity data values
     * @return True if nested entity is successfully added to its parent
     * @throws IdNotUnique If id is not unique
     */
    boolean addNestedEntity(String parentId, String parentKey ,String title, String childId, String[] keys, String[] values) throws IdNotUnique;

    /**
     * Updates data of entity with given id
     * @param oldId Old id of entity that is being updated
     * @param title New title of entity that is being updated
     * @param newId New id of entity that is being updated
     * @param keys Array of new keys for entity that is being updated
     * @param values Array of new values for entity that is being updated
     * @return True if entity is successfully updated
     * @throws IdNotUnique If id is not unique
     */
    boolean updateEntity(String oldId, String title, String newId, String[] keys, String[] values) throws IdNotUnique;

    /**
     * Deletes all entities that have id from the given list of ids
     * @param ids Given list of ids
     * @return True if entites are successfully deleted
     */
    boolean deleteEntity(List<String>ids);

    /**
     * Updates nested entity data
     * @param oldEntity Nested entity to be updated
     * @param keyFor Key for nested entity in parent entity
     * @param idParent Id of parent entity
     * @param title Title of new nested entity
     * @param newId New id of nested entity
     * @param keys Array of new keys of nested entity
     * @param values Array of new values of nested entity
     * @return True if nested entity is successfully updated
     * @throws IdNotUnique If id is not unique
     */

    boolean updateNestedEntity(Entity oldEntity,String keyFor,String idParent, String title, String newId, String[] keys, String[] values) throws IdNotUnique;
}
