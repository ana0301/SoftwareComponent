package service.impl;

import enums.FilterOperator;
import enums.SortOrder;
import enums.SortTarget;
import exceptions.SortUnable;
import model.Database;
import model.Entity;
import service.FilterSortService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FilterSortServiceImpl implements FilterSortService {
    @Override
    public List<Entity> getAllData() {
        return Database.getInstance().getEntityList();
    }

    @Override
    public List<Entity> filterById(FilterOperator filterOperator, String target) {
        return filterById(Database.getInstance().getEntityList(), filterOperator, target);
    }

    @Override
    public List<Entity> filterById(List<Entity> entities, FilterOperator filterOperator, String target) {
        List<Entity> returnList = new ArrayList<>();
        for (Entity e : entities) {
            switch (filterOperator){
                case STARTS_WITH:
                    if(e.getId().startsWith(target)) returnList.add(e);
                    break;
                case ENDS_WITH:
                    if(e.getId().endsWith(target)) returnList.add(e);
                    break;
                case EQUALS:
                    if(e.getId().equals(target)) returnList.add(e);
                    break;
                case CONTAINS:
                    if(e.getId().contains(target)) returnList.add(e);
                    break;
                default: break;
            }
        }
        return returnList;
    }

    @Override
    public List<Entity> filterByTitle(FilterOperator filterOperator, String target) {
        return filterByTitle(Database.getInstance().getEntityList(), filterOperator, target);
    }

    @Override
    public List<Entity> filterByTitle(List<Entity> entities, FilterOperator filterOperator, String target) {
        List<Entity> returnList = new ArrayList<>();
        for (Entity e : entities) {
            switch (filterOperator){
                case STARTS_WITH:
                    if(e.getTitle().startsWith(target)) returnList.add(e);
                    break;
                case ENDS_WITH:
                    if(e.getTitle().endsWith(target)) returnList.add(e);
                    break;
                case EQUALS:
                    if(e.getTitle().equals(target)) returnList.add(e);
                    break;
                case CONTAINS:
                    if(e.getTitle().contains(target)) returnList.add(e);
                    break;
                default: break;
            }
        }
        return returnList;
    }

    @Override
    public List<Entity> filterByKey(FilterOperator filterOperator, String target) {
        return filterByKey(Database.getInstance().getEntityList(), filterOperator, target);
    }

    @Override
    public List<Entity> filterByKey(List<Entity> entities, FilterOperator filterOperator, String target) {
        List<Entity> returnList = new ArrayList<>();
        for (Entity e : entities) {
            String[] keys = e.getEntityData().keySet().toArray(new String[0]);
            for(int i = 0; i < keys.length; i++) {
                switch (filterOperator) {
                    case STARTS_WITH:
                        if (keys[i].startsWith(target)) returnList.add(e);
                        break;
                    case ENDS_WITH:
                        if (keys[i].endsWith(target)) returnList.add(e);
                        break;
                    case EQUALS:
                        if (keys[i].equals(target)) returnList.add(e);
                        break;
                    case CONTAINS:
                        if (keys[i].contains(target)) returnList.add(e);
                        break;
                    default:
                        break;
                }
            }
        }
        return returnList;
    }

    @Override
    public List<Entity> filterByValue(FilterOperator filterOperator, String key, String target) {
        return filterByValue(Database.getInstance().getEntityList(), filterOperator, key, target);
    }

    @Override
    public List<Entity> filterByValue(List<Entity> entities, FilterOperator filterOperator, String key, String target) {
        List<Entity> returnList = new ArrayList<>();
        for (Entity e : entities) {
            if(e.getEntityData().containsKey(key) && e.getEntityData().get(key) instanceof String) {
                String value = e.getEntityData().get(key).toString();
                switch (filterOperator) {
                    case STARTS_WITH:
                        if (value.startsWith(target)) returnList.add(e);
                        break;
                    case ENDS_WITH:
                        if (value.endsWith(target)) returnList.add(e);
                        break;
                    case EQUALS:
                        if (value.equals(target)) returnList.add(e);
                        break;
                    case CONTAINS:
                        if (value.contains(target)) returnList.add(e);
                        break;
                    default:
                        break;
                }
            }
        }
        return returnList;
    }

    @Override
    public List<Entity> sortByIdTitle(List<Entity> entities, SortOrder sortOrder, SortTarget sortTarget) {
        //List<Entity> toReturn = new ArrayList<>();
        System.out.println("Duzina poslatog niza // sortByIdTitle: "+entities.size());
        switch (sortTarget){
            case ID:
                if (sortOrder == SortOrder.ASC)
                    entities = entities.stream().sorted(Comparator.comparing(Entity::getId)).collect(Collectors.toList());
                else entities = entities.stream().sorted(Comparator.comparing(Entity::getId).reversed()).collect(Collectors.toList());
                break;
            case TITLE:
                if (sortOrder == SortOrder.ASC)
                    entities = entities.stream().sorted(Comparator.comparing(Entity::getTitle)).collect(Collectors.toList());
                else entities = entities.stream().sorted(Comparator.comparing(Entity::getTitle).reversed()).collect(Collectors.toList());
                break;
            default: break;
        }
        System.out.println("Duzina toReturn niza // sortByIdTitle: "+entities.size());
        return entities;
    }

    @Override
    public List<Entity> sortByKey(List<Entity> entities, SortOrder sortOrder, SortTarget sortTarget, String key) throws SortUnable {
        if(sortTarget == SortTarget.KEY && sortOrder == SortOrder.ASC){
            Collections.sort(entities, new Comparator<Entity>() {
                @Override
                public int compare(Entity o1, Entity o2) {
                    if(o1.getEntityData().containsKey(key) && o2.getEntityData().containsKey(key)){
                        return (o1.getEntityData().get(key).toString().compareTo(o2.getEntityData().get(key).toString()));
                    }else if(o1.getEntityData().containsKey(key)){
                        return -1;
                    }else if(o2.getEntityData().containsKey(key)){
                        return 1;
                    }
                    return 0;
                }
            });
        }
        return null;
    }
}
