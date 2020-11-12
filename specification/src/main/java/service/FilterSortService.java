package service;

import enums.FilterOperator;
import enums.SortTarget;
import exceptions.SortUnable;
import model.Entity;
import enums.SortOrder;

import java.util.List;

/**
 * Service for filtering and sorting data
 */

public interface FilterSortService {
    /**
     * Gets all data in database
     * @return list of all entities
     */
    List<Entity> getAllData();

    /**
     * Filters database by entity id
     * @param filterOperator Explains how entities are compared
     * @param target Parameter on which entities are compared
     * @return List of entities that match given parameters
     */
    List<Entity> filterById(FilterOperator filterOperator, String target);

    /**
     * Filters given list by entity id
     * @param entities Given entity list to be filtered
     * @param filterOperator Explains how entities are compared
     * @param id Parameter on which entities are compared
     * @return List of entities that match given parameters
     */
    List<Entity> filterById(List<Entity> entities, FilterOperator filterOperator, String id);
    /**
     * Filters database by entity title
     * @param filterOperator Explains how entities are compared
     * @param target Parameter on which entities are compared
     * @return List of entities that match given parameters
     */
    List<Entity> filterByTitle(FilterOperator filterOperator, String target);
    /**
     * Filters given list by entity title
     * @param entities Given entity list to be filtered
     * @param filterOperator Explains how entities are compared
     * @param target Parameter on which entities are compared
     * @return List of entities that match given parameters
     */
    List<Entity> filterByTitle(List<Entity> entities, FilterOperator filterOperator, String target);
    /**
     * Filters database by entity key
     * @param filterOperator Explains how entities are compared
     * @param target Parameter on which entities are compared
     * @return List of entities that match given parameters
     */
    List<Entity> filterByKey(FilterOperator filterOperator, String target);
    /**
     * Filters given list by entity key
     * @param entities Given entity list to be filtered
     * @param filterOperator Explains how entities are compared
     * @param target Parameter on which entities are compared
     * @return List of entities that match given parameters
     */
    List<Entity> filterByKey(List<Entity> entities, FilterOperator filterOperator, String target);

    /**
     * Filters database by entity value
     * @param filterOperator  Explains how entities are compared
     * @param key Key of the value on which the filter is performed
     * @param target Parameter on which entities are compared
     * @return List of entities that match given parameters
     */
    List<Entity> filterByValue(FilterOperator filterOperator, String key, String target);

    /**
     * Filters given list by entity value
     * @param entities Given entity list to be filtered
     * @param filterOperator Explains how entities are compared
     * @param key Key of the value on which the filter is performed
     * @param target Parameter on which entities are compared
     * @return List of entities that match given parameters
     */
    List<Entity> filterByValue(List<Entity> entities, FilterOperator filterOperator, String key, String target);

    /**
     * Sorts given list by title or id
     * @param entities Given entity list to be sorted
     * @param sortOrder Explains in which order entities are sorted
     * @param sortTarget Explains if entities are sorted by id, title or key
     * @return Sorted list of entities
     */
    List<Entity> sortByIdTitle(List<Entity> entities, SortOrder sortOrder, SortTarget sortTarget);

    /**
     * Sorts given list by key
     * @param entities Given entity list to be sorted
     * @param sortOrder Explains in which order entities are sorted
     * @param sortTarget Explains if entities are sorted by id, title or key
     * @param key Key of the value on which the sort is performed
     * @return Sorted list of entities
     * @throws SortUnable
     */
    List<Entity> sortByKey(List<Entity> entities, SortOrder sortOrder, SortTarget sortTarget, String key) throws SortUnable;
}
