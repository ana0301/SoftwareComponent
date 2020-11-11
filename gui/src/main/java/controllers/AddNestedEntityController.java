package controllers;

import dialogs.AddUpdateDialog;
import view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddNestedEntityController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        int[] selectedRows = MainFrame.getInstance().getDataTableView().getSelectedRows();
        if (selectedRows.length == 0) JOptionPane.showMessageDialog(MainFrame.getInstance(),"You need to select entity","Warning" ,JOptionPane.WARNING_MESSAGE);
        else {
            String name = JOptionPane.showInputDialog(MainFrame.getInstance(), "Enter a name of entity");
            if(name != null){
                AddUpdateDialog addUpdateDialog = new AddUpdateDialog(MainFrame.getInstance(), "Add Entity", true);
                addUpdateDialog.setVisible(true);
                List<String[]> toSend = addUpdateDialog.getFields();
                if (addUpdateDialog.getMode() == 0) {
                    List<String> ids = MainFrame.getInstance().getDataTableModel().entitiesIds(selectedRows);
                    for(String id: ids){
                        try {
                            MainFrame.getInstance().getCrudService().addNestedEntity(id, name,  toSend.get(1)[0],toSend.get(0)[0],
                                    toSend.get(2), toSend.get(3));

                            MainFrame.getInstance().getDataTableModel().updateTableModel(
                                    MainFrame.getInstance().getFilterSortService().getAllData());
                        }catch (Exception exception){
                            JOptionPane.showMessageDialog(MainFrame.getInstance(),exception.getMessage(),"Warning" ,JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            }else JOptionPane.showMessageDialog(MainFrame.getInstance(),"You haven't entered a name, try again");
        }
    }
}
