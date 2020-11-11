package controllers;

import view.MainFrame;
import dialogs.AddUpdateDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddEntityController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        AddUpdateDialog addUpdateDialog = new AddUpdateDialog(MainFrame.getInstance(), "Add Entity", true, null);
        if(addUpdateDialog.getMode() == 0){
            List<String[]> toSend = addUpdateDialog.getFields();

            for(int i = 0; i < toSend.get(2).length; i ++) System.out.println(toSend.get(1)[i] + "---> in controller");

            try{
                MainFrame.getInstance().getCrudService().addEntity(toSend.get(0)[0], toSend.get(1)[0],
                        toSend.get(2), toSend.get(3));

                MainFrame.getInstance().getDataTableModel().updateTableModel(
                        MainFrame.getInstance().getFilterSortService().getAllData()
                );

            }catch (Exception exception){
                //TODO exception
            }
        }else{

        }
    }
}
