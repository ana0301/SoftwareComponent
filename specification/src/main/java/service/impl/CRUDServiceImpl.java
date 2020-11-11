package service.impl;

import exceptions.IdNotUnique;
import model.Database;
import model.Entity;
import model.SimpleType;
import model.Type;
import service.CRUDService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CRUDServiceImpl implements CRUDService {

    private Map<String, Object> mapData(String[] keys, String[] values){
        //TODO exception kada duzina nizova keys i values nije ista
        Map<String, Object> data = new HashMap<>();
        for (int i = 0; i < keys.length; i++){
            if(keys[i].equalsIgnoreCase("")) continue;
            data.put(keys[i], values[i]);
            System.out.println(keys[i] + " ---> key in crud");
            System.out.println(values[i] + " ---> value in crud");
        }
        return data;
    }

    @Override
    public boolean addEntity(String id, String title, String[] keys, String[] values) throws IdNotUnique {
        if (id != null){
            if(!Database.getInstance().isUniqueId(id)) throw new IdNotUnique();
        }else{
            id = Database.getInstance().getUniqueId();
        }

        Entity e = new Entity(title, id, mapData(keys, values));

        return Database.getInstance().addEntity(e);
    }

    @Override
    public boolean addNestedEntity(String parentId, String parentKey, String title, String childId, String[] keys, String[] values) throws IdNotUnique {
        //TODO exception da parent entity nije izabran
        if (childId != null){
            if(!Database.getInstance().isUniqueId(childId)) throw new IdNotUnique();
        }else{
            childId = Database.getInstance().getUniqueId();
        }

        //Type e = new Entity(title, childId, mapData(keys,values));

        //Database.getInstance().getEntityById(parentId).getEntityData().put(parentKey, e);

        return true;
    }

    @Override
    public boolean updateEntity(String oldId, String title, String newId, String[] keys, String[] values) throws IdNotUnique {
        if(!oldId.equals(newId)){
            if (newId != null && !newId.equals("")){
                if(!Database.getInstance().isUniqueId(newId)) throw new IdNotUnique();
            }else{
                newId = Database.getInstance().getUniqueId();
            }
        }

        Database.getInstance().removeEntity(oldId);

        //Entity e = new Entity(title, newId, mapData(keys, values));

        //return Database.getInstance().addEntity(e);
        return false;
    }

    @Override
    public boolean deleteEntity(String[] ids) {
        for (int i = 0; i < ids.length; i++){
            return Database.getInstance().removeEntity(ids[i]);
        }
        return true;
    }
}
