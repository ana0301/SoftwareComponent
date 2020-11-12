package model;


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
        while (getRowCount() != 0) removeRow(0);
        setEntities(entities);
        for (Entity e: entities) {
            Object[] rowData = {e.getId(), e.getTitle(), e.getEntityData()};
            addRow(rowData);
        }
    }

    public List<String> entitiesIds(int[] selectedRows) {
        List<String> entities = new ArrayList<>();
        for(int i = 0; i < selectedRows.length;i++){
            Object value = getValueAt(selectedRows[i],0);
            entities.add((String) value);
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
