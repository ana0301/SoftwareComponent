package controllers;

import dialogs.AddUpdateDialog;
import view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

public class UpdateEntityController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        int[] selectedRows = MainFrame.getInstance().getDataTableView().getSelectedRows();
        if (selectedRows.length == 0) JOptionPane.showMessageDialog(MainFrame.getInstance(),"You need to select entity","Warning" ,JOptionPane.WARNING_MESSAGE);
        else if(selectedRows.length != 1) JOptionPane.showMessageDialog(MainFrame.getInstance(),"You need to select only one entity","Warning" ,JOptionPane.WARNING_MESSAGE);
        else{
            AddUpdateDialog addUpdateDialog  = new AddUpdateDialog(MainFrame.getInstance(),"Update",true);
            String id = (String) MainFrame.getInstance().getDataTableView().getValueAt(selectedRows[0],0);
            String title = (String)MainFrame.getInstance().getDataTableView().getValueAt(selectedRows[0],1);
            Map<String,Object> data = (Map<String, Object>) MainFrame.getInstance().getDataTableView().getValueAt(selectedRows[0],2);
            addUpdateDialog.setFields(id,title,data);
            addUpdateDialog.setVisible(true);
            if(addUpdateDialog.getMode() == 0){
                List<String[]> toSend = addUpdateDialog.getFields();

                try{
                    MainFrame.getInstance().getCrudService().updateEntity(id, toSend.get(1)[0],toSend.get(0)[0],
                            toSend.get(2), toSend.get(3));

                    MainFrame.getInstance().getDataTableModel().updateTableModel(
                            MainFrame.getInstance().getFilterSortService().getAllData());

                }catch (Exception exception){
                    JOptionPane.showMessageDialog(MainFrame.getInstance(),exception.getMessage(),"Warning" ,JOptionPane.WARNING_MESSAGE);
                }

            }

        }
    }
}
