package controllers;

import dialogs.AddUpdateDialog;
import dialogs.ChooseNestedDialog;
import model.Entity;
import view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UpdateNestedEntityController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        int[] selectedRows = MainFrame.getInstance().getDataTableView().getSelectedRows();
        if (selectedRows.length == 0)
            JOptionPane.showMessageDialog(MainFrame.getInstance(), "You need to select entity", "Warning", JOptionPane.WARNING_MESSAGE);
        else if (selectedRows.length != 1)
            JOptionPane.showMessageDialog(MainFrame.getInstance(), "You need to select only one entity", "Warning", JOptionPane.WARNING_MESSAGE);
        else {
            String idParent = (String) MainFrame.getInstance().getDataTableView().getValueAt(selectedRows[0],0);
            Map<String, Object> data = (Map<String, Object>) MainFrame.getInstance().getDataTableView().getValueAt(selectedRows[0], 2);
            Map<String, Entity> entites = hasNested(data);
            Entity entity = null;
            String chosen = null;
            if (entites.isEmpty())
                JOptionPane.showMessageDialog(MainFrame.getInstance(), "Selected entity does not have a nested entity");
            else {
                if (entites.size() == 1) {
                    entity = entites.entrySet().iterator().next().getValue();
                    chosen = entites.entrySet().iterator().next().getKey();
                }
                else {
                    ChooseNestedDialog chooseNestedDialog = new ChooseNestedDialog(entites.keySet());
                    if (chooseNestedDialog.getMode() == 0) {
                        chosen = chooseNestedDialog.getSelectedItem();
                        entity = entites.get(chosen);
                    }
                }
                if (entity != null) {
                    AddUpdateDialog addUpdateDialog = new AddUpdateDialog(MainFrame.getInstance(), "Update nested entity", true);
                    String id = entity.getId();
                    String title = entity.getTitle();
                    Map<String, Object> entityData = entity.getEntityData();
                    addUpdateDialog.setFields(id, title, entityData);
                    addUpdateDialog.setVisible(true);
                    if (addUpdateDialog.getMode() == 0) {
                        List<String[]> toSend = addUpdateDialog.getFields();
                        try {
                            MainFrame.getInstance().getCrudService().updateNestedEntity(entity,chosen, idParent, toSend.get(1)[0], toSend.get(0)[0],
                                    toSend.get(2), toSend.get(3));

                            MainFrame.getInstance().getDataTableModel().updateTableModel(
                                    MainFrame.getInstance().getFilterSortService().getAllData());

                        } catch (Exception exception) {
                            JOptionPane.showMessageDialog(MainFrame.getInstance(), exception.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
                        }

                    }

                }
            }
        }
    }
    private Map<String,Entity> hasNested(Map<String, Object> data) {
        Map<String,Entity> entities = new HashMap<>();
        for(Map.Entry<String,Object> property : data.entrySet()){
            if(property.getValue() instanceof Entity)
                entities.put(property.getKey(), (Entity) property.getValue());
        }
        return entities;
    }
}
