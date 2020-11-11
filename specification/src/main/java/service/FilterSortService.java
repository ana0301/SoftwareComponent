package service;

import enums.FilterOperator;
import enums.SortTarget;
import model.Entity;
import enums.SortOrder;

import java.util.List;

public interface FilterSortService {
    List<Entity> getAllData();
    List<Entity> filterById(FilterOperator filterOperator, String target);
    List<Entity> filterById(List<Entity> entities, FilterOperator filterOperator, String id);
    List<Entity> filterByTitle(FilterOperator filterOperator, String target);
    List<Entity> filterByTitle(List<Entity> entities, FilterOperator filterOperator, String target);
    List<Entity> filterByKey(FilterOperator filterOperator, String target);
    List<Entity> filterByKey(List<Entity> entities, FilterOperator filterOperator, String target);
    List<Entity> filterByValue(FilterOperator filterOperator, String key, String target);
    List<Entity> filterByValue(List<Entity> entities, FilterOperator filterOperator, String key, String target);
    List<Entity> sort(SortOrder sortOrder, SortTarget sortTarget);
    List<Entity> sort(List<Entity> entities, SortOrder sortOrder, SortTarget sortTarget);
}
