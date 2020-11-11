package controllers;

import view.MainFrame;
import dialogs.AddUpdateDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddEntityController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        AddUpdateDialog addUpdateDialog = new AddUpdateDialog(MainFrame.getInstance(), "Add Entity", true);
        addUpdateDialog.setVisible(true);
        if(addUpdateDialog.getMode() == 0){
            List<String[]> toSend = addUpdateDialog.getFields();

            try{
                MainFrame.getInstance().getCrudService().addEntity(toSend.get(0)[0], toSend.get(1)[0],
                        toSend.get(2), toSend.get(3));
                MainFrame.getInstance().getDataTableModel().updateTableModel(
                        MainFrame.getInstance().getFilterSortService().getAllData());

            }catch (Exception exception){
                JOptionPane.showMessageDialog(MainFrame.getInstance(),exception.getMessage(),"Warning" ,JOptionPane.WARNING_MESSAGE);
            }


        }
    }
}
