package controllers;

import dialogs.AddUpdateDialog;
import view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddNestedEntityController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        int[] selectedRows = MainFrame.getInstance().getDataTableView().getSelectedRows();
        if (selectedRows.length == 0) JOptionPane.showConfirmDialog(MainFrame.getInstance(),"You need to select entity","Warning" ,JOptionPane.OK_OPTION ,JOptionPane.WARNING_MESSAGE);
        AddUpdateDialog addUpdateDialog = new AddUpdateDialog(MainFrame.getInstance(), "Add Entity", true);
        if (addUpdateDialog.getMode() == 0){

        }else{

        }
    }
}
