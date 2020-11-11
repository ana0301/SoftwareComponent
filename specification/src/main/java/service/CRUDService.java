package service;

import exceptions.IdNotUnique;
import model.Entity;

import java.util.List;

public interface CRUDService {

    boolean addEntity(String id, String title, String[] keys, String[] values) throws IdNotUnique;
    boolean addNestedEntity(String parentId, String parentKey ,String title, String childId, String[] keys, String[] values) throws IdNotUnique;
    boolean updateEntity(String oldId, String title, String newId, String[] keys, String[] values) throws IdNotUnique;
    boolean deleteEntity(List<String>ids);
}
