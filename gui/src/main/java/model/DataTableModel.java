package model;


import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class DataTableModel extends DefaultTableModel {

    public DataTableModel(){

        super(new String[]{"Title", "ID", "Data"}, 0);
    }

    @Override
    public void addRow(Vector<?> rowData) {

        super.addRow(rowData);
    }

    @Override
    public void removeRow(int row) {

        super.removeRow(row);
    }
}
