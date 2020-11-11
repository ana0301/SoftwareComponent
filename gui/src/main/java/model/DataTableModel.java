package model;


import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class DataTableModel extends DefaultTableModel {

    private List<Entity> entities;

    public DataTableModel(){
        super(new String[]{"ID", "Title", "Data"}, 0);
        this.entities = new ArrayList<>();
    }

    public void updateTableModel(List<Entity> entities){
        for (int i = 0; i < getRowCount(); i++) {
            removeRow(i);
        }
        for (Entity e: entities) {
            String[] rowData = {e.getId(), e.getTitle(), e.getEntityData().toString()};
            addRow(rowData);
        }
    }

}
