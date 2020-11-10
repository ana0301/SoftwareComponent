package service.impl;

import model.CompareOperator;
import model.Entity;
import model.SortOrder;
import service.DatabaseQueries;

import java.util.ArrayList;
import java.util.List;

public class DatabaseQueriesImpl implements DatabaseQueries {
    @Override
    public List<Entity>  getAllData() {
         return (List<Entity>) Database.getInstance().getEntityMap().values();
    }

    @Override
    public List<Entity>  getById(String id) {
        Integer idi = Integer.parseInt(id);
        List<Entity> resultSet = new ArrayList<>();
        Entity e = Database.getInstance().getEntityMap().get(idi);
        resultSet.add(e);
        return resultSet;
    }

    @Override
    public List<Entity>  getByEntityName(List<Entity>  resultSet, String title) {
        if (resultSet == null){
            resultSet = (List<Entity> ) Database.getInstance().getEntityMap().values();
        }
        List<Entity> toReturn = new ArrayList<>();
        for (Entity e: resultSet) {
            if(e.getTitle().equalsIgnoreCase(title)) toReturn.add(e);
        }
        return toReturn;
    }

    @Override
    public List<Entity>  hasKey(List<Entity>  resultSet, String key) {
        if (resultSet == null){
            resultSet = (List<Entity> ) Database.getInstance().getEntityMap().values();
        }
        List<Entity> toReturn = new ArrayList<>();
        for (Entity e: resultSet) {
            if(e.getEntityData().containsKey(key)) toReturn.add(e);
        }
        return toReturn;
    }

    @Override
    public List<Entity>  compareValue(List<Entity>  resultSet, String key, CompareOperator operator, String value) {
        if (resultSet == null){
            resultSet = (List<Entity> ) Database.getInstance().getEntityMap().values();
        }
        List<Entity> toReturn = new ArrayList<>();
        for (Entity e: resultSet) {
            if (e.getEntityData().containsKey(key)){
                if (operator == CompareOperator.CONTAINS){
                    if(e.getEntityData().get(key).toString().toLowerCase().contains(value.toLowerCase())) toReturn.add(e);
                } else if(operator == CompareOperator.STARTS_WITH){
                    if(e.getEntityData().get(key).toString().toLowerCase().startsWith(value.toLowerCase())) toReturn.add(e);
                }else if (operator == CompareOperator.ENDS_WITH){
                    if(e.getEntityData().get(key).toString().toLowerCase().endsWith(value.toLowerCase())) toReturn.add(e);
                }else if (operator == CompareOperator.EQUAL_TO){
                    //todo for string
                    try {
                        if (Integer.parseInt(e.getEntityData().get(key).toString()) == Integer.parseInt(value)) toReturn.add(e);
                    }catch (Exception exception){
                        exception.printStackTrace();
                    }
                }else if(operator == CompareOperator.GREATER_THAN){
                    try {
                        if (Integer.parseInt(e.getEntityData().get(key).toString()) > Integer.parseInt(value)) toReturn.add(e);
                    }catch (Exception exception){
                        exception.printStackTrace();
                    }
                }else if (operator == CompareOperator.LESS_THAN){
                    try {
                        if (Integer.parseInt(e.getEntityData().get(key).toString()) < Integer.parseInt(value)) toReturn.add(e);
                    }catch (Exception exception){
                        exception.printStackTrace();
                    }
                }else break;
            }
        }
        return toReturn;
    }

    //TODO Sort Value implementation
    @Override
    public List<Entity>  sort(List<Entity>  resultSet, SortOrder sortOrder, String key) {
        return null;
    }
}
