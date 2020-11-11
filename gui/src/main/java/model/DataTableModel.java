package model;


import com.sun.rowset.internal.Row;
import view.MainFrame;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataTableModel extends DefaultTableModel {

    private List<Entity> entities;

    public DataTableModel(){
        super(new String[]{"ID", "Title", "Data"}, 0);
        this.entities = new ArrayList<>();
    }

    public void updateTableModel(List<Entity> entities){
        for(int i = 0; i < getRowCount(); i++){
            removeRow(i);
        }
        setEntities(entities);
        for (Entity e: entities) {
            Object[] rowData = {e.getId(), e.getTitle(), e.getEntityData()};
            addRow(rowData);
        }
    }

    public List<Entity> entitiesToDelete(int[] selectedRows) {
        List<Entity> entities = new ArrayList<>();
        for(int i = 0; i < selectedRows.length;i++){
            Entity entity = new Entity();
            for(int j = 0; j < getColumnCount();j++){
                Object value = getValueAt(i,j);
                if(getColumnName(j).equals("ID"))
                    entity.setId((String) value);
                else if(getColumnName(j).equals("Title")){
                    entity.setTitle((String) value);
                }else if(getColumnName(j).equals("Data")){
                    entity.setEntityData((Map<String, Object>) value);
                }
            }
            entities.add(entity);
        }
        return entities;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }
}
