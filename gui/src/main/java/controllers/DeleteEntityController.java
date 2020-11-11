package controllers;

import view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteEntityController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        int selection = JOptionPane.showConfirmDialog(MainFrame.getInstance(),"Do you want to delete selected entities?","Warning" ,JOptionPane.YES_NO_OPTION ,JOptionPane.WARNING_MESSAGE);
        if (selection == JOptionPane.YES_OPTION){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }

    }
}
