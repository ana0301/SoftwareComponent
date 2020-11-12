package view;

import model.DataTableModel;
import javax.swing.*;

public class DataTableView extends JTable {

    public DataTableView(DataTableModel dataTableModel){
        super(dataTableModel);
    }

}
