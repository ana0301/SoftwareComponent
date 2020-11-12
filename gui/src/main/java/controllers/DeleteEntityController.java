package controllers;


import view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DeleteEntityController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        int[] selectedRows = MainFrame.getInstance().getDataTableView().getSelectedRows();
        int selection = JOptionPane.showConfirmDialog(MainFrame.getInstance(),"Do you want to delete selected entities?","Warning" ,JOptionPane.YES_NO_OPTION ,JOptionPane.WARNING_MESSAGE);
        if (selection == JOptionPane.YES_OPTION){
            System.out.println("YES");
            if(selectedRows.length == 0) JOptionPane.showMessageDialog(MainFrame.getInstance(), "You didn't select anything");
            else {
                deleteRows(selectedRows);
            }


        }else{
            System.out.println("NO");
        }

    }
    public void deleteRows(int[] selectedRows) {
        List<String> entityList = MainFrame.getInstance().getDataTableModel().entitiesIds(selectedRows);
        MainFrame.getInstance().getCrudService().deleteEntity(entityList);
        MainFrame.getInstance().getDataTableModel().updateTableModel(MainFrame.getInstance().getFilterSortService().getAllData());
    }
}
