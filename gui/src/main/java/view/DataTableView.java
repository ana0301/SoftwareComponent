package view;

import model.DataTableModel;

import javax.swing.*;

public class DataTableView extends JTable {

    public DataTableView(){
        super(new DataTableModel());
    }
}
