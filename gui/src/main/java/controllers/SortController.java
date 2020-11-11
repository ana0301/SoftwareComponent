package controllers;

import view.MainFrame;
import dialogs.SortDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SortController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        SortDialog sd = new SortDialog(MainFrame.getInstance(), "Sort data", true);
    }
}
