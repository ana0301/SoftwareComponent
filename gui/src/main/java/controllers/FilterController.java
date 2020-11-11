package controllers;

import view.MainFrame;
import dialogs.FilterDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FilterController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        FilterDialog fd = new FilterDialog(MainFrame.getInstance(), "Filter data", true);

    }
}
