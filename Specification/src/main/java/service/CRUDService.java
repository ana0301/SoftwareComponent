package service;

import exceptions.IdNotUnique;
import model.Entity;

import java.util.List;
import java.util.Map;

public interface CRUDService {

    boolean addEntity(String title, String id, Map<String, Object> data) throws IdNotUnique;
    boolean addNestedEntity(String parentId, String title, String childId, Map<String, Object> data) throws IdNotUnique;
    boolean updateEntity(String oldId, String title, String newId, Map<String, Object> data) throws IdNotUnique;
    boolean deleteEntity(List<Entity> resultSet);
}
