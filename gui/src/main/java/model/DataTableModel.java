package model;


import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DataTableModel extends DefaultTableModel {

    private List<Entity> entities;

    public DataTableModel(){
        super(new String[]{"Title", "ID", "Data"}, 0);
        entities = new ArrayList<>();
    }
    /*
    @Override
    public void addRow(Vector<?> rowData) {

        super.addRow(rowData);
    }

    @Override
    public void removeRow(int row) {

        super.removeRow(row);
    }*/
}
