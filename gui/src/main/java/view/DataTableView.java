package view;

import model.DataTableModel;
import model.Entity;

import javax.swing.*;

public class DataTableView extends JTable {

    public DataTableView(DataTableModel dataTableModel){
        super(dataTableModel);
    }

}
