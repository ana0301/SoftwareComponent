package controllers;

import enums.SortOrder;
import enums.SortTarget;
import exceptions.SortUnable;
import model.Entity;
import view.MainFrame;
import dialogs.SortDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SortController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        SortDialog sd = new SortDialog(MainFrame.getInstance(), "Sort data", true);
        if (sd.getMode() == 0){
            String[] toSend = sd.getFields();
            List<Entity> result = new ArrayList<>();
            List<Entity> listToSend = MainFrame.getInstance().getDataTableModel().getEntities();
            System.out.println("Duzina niza listToSend: "+listToSend.size());
            if(toSend[0].equals("ID")){
                if (toSend[2].equals("ASCENDING")) result = MainFrame.getInstance().getFilterSortService().sortByIdTitle(
                        MainFrame.getInstance().getDataTableModel().getEntities(), SortOrder.ASC, SortTarget.ID
                );
                if (toSend[2].equals("DESCENDING"))result = MainFrame.getInstance().getFilterSortService().sortByIdTitle(
                        MainFrame.getInstance().getDataTableModel().getEntities(), SortOrder.DESC, SortTarget.ID
                );
            }
            if (toSend[0].equals("NAME")){
                if (toSend[2].equals("ASCENDING"))result.addAll(MainFrame.getInstance().getFilterSortService().sortByIdTitle(
                        MainFrame.getInstance().getDataTableModel().getEntities(), SortOrder.ASC, SortTarget.TITLE
                ));
                if (toSend[2].equals("DESCENDING"))result.addAll(MainFrame.getInstance().getFilterSortService().sortByIdTitle(
                        MainFrame.getInstance().getDataTableModel().getEntities(), SortOrder.DESC, SortTarget.TITLE
                ));
            }
            if (toSend[0].equals("KEY")){
                if(toSend[2].equals("ASCENDING")) {
                    try {
                        result = MainFrame.getInstance().getFilterSortService().sortByKey(
                                MainFrame.getInstance().getDataTableModel().getEntities(),SortOrder.ASC,SortTarget.KEY, toSend[1]);
                    } catch (SortUnable sortUnable) {
                        sortUnable.printStackTrace();
                    }
                }
                if(toSend[2].equals("DESCENDING")) {
                    try {
                        result = MainFrame.getInstance().getFilterSortService().sortByKey(
                                MainFrame.getInstance().getDataTableModel().getEntities(),SortOrder.DESC,SortTarget.KEY, toSend[1]);
                    } catch (SortUnable sortUnable) {
                        sortUnable.printStackTrace();
                    }
                }
            }
            MainFrame.getInstance().getDataTableModel().updateTableModel(result);
        }
    }
}
