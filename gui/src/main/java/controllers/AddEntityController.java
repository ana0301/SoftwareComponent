package controllers;

import appcore.MainFrame;
import dialogs.AddUpdateDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEntityController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        AddUpdateDialog addUpdateDialog = new AddUpdateDialog(MainFrame.getInstance(), "Add Entity", true, null);
        if(addUpdateDialog.getMode() == 0){

        }else{

        }
    }
}
