package service.impl;

import exceptions.IdNotUnique;
import model.Database;
import model.Entity;
import service.CRUDService;

import java.util.List;
import java.util.Map;

public class CRUDServiceImpl implements CRUDService {

    @Override
    public boolean addEntity(String title, String id, Map<String, Object> data) throws IdNotUnique {
        //if (Database.getInstance().getEntityMap().containsKey(Integer.parseInt(id))) throw new IdNotUnique();
        //if (Database.getInstance().getNestedEntityMap().containsKey(Integer.parseInt(id))) throw new IdNotUnique();
        //Entity e = new Entity(title, Integer.parseInt(id), data);
        //Database.getInstance().getEntityMap().put(Integer.parseInt(id), e);
        return true;
    }

    @Override
    public boolean addNestedEntity(String parentId, String title, String childId, Map<String, Object> data) throws IdNotUnique {
        //if (Database.getInstance().getEntityMap().containsKey(Integer.parseInt(childId))) throw new IdNotUnique();
        //if (Database.getInstance().getNestedEntityMap().containsKey(Integer.parseInt(childId))) throw new IdNotUnique();
        //Entity e = new Entity(title, Integer.parseInt(childId), data);
        //Database.getInstance().getEntityMap().get(Integer.parseInt(parentId)).getEntityData().put(title, e);
        //Database.getInstance().getNestedEntityMap().put(Integer.parseInt(childId), e);
        return true;
    }

    //TODO Fix update entity
    @Override
    public boolean updateEntity(String oldId, String title, String newId, Map<String, Object> data) throws IdNotUnique {
        //if (!oldId.equalsIgnoreCase(newId)){
            //if (Database.getInstance().getEntityMap().containsKey(Integer.parseInt(newId))) throw new IdNotUnique();
            //Database.getInstance().getEntityMap().remove(Integer.parseInt(oldId));
            //Entity e = new Entity(title, Integer.parseInt(newId), data);
            //Database.getInstance().getEntityMap().put(Integer.parseInt(newId), e);
        //}else{
            //Database.getInstance().getEntityMap().get(Integer.parseInt(oldId)).setTitle(title);
            //Database.getInstance().getEntityMap().get(Integer.parseInt(oldId)).setEntityData(data);
        //}
        return true;
    }

    @Override
    public boolean deleteEntity(List<Entity> resultSet) {
        for (Entity e: resultSet) {
            Database.getInstance().getEntityMap().remove(e.getId());
        }
        return true;
    }
}
