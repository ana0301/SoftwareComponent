package controllers;

import view.MainFrame;
import model.Entity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ShowAllDataController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        List<Entity> allEntities = MainFrame.getInstance().getFilterSortService().getAllData();
        MainFrame.getInstance().getDataTableModel().updateTableModel(allEntities);
    }
}
