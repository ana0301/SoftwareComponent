package service;

import enums.CompareOperator;
import model.Entity;
import enums.SortOrder;

import java.util.List;

public interface DatabaseQueries {
    List<Entity>  getAllData();
    List<Entity>  getById(String id);
    List<Entity>  getByEntityName(List<Entity>  resultSet, String title);
    List<Entity>  hasKey(List<Entity>  resultSet, String key);
    List<Entity>  compareValue(List<Entity>  resultSet, String key, CompareOperator operator, String value);
    List<Entity>  sort(List<Entity>  resultSet, SortOrder sortOrder, String key); //or string id
}
