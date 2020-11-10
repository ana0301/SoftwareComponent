package appcore;

import dialogs.NewFileDialog;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class MainFrameWindowListener extends WindowAdapter {

    @Override
    public void windowClosing(WindowEvent e) {
        int o = JOptionPane.showConfirmDialog(MainFrame.getInstance(), "Do you want to save current database?",
                "Warning", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

        if(o == JOptionPane.YES_OPTION){



        }else if(o == JOptionPane.NO_OPTION) System.exit(0);
        else this.windowOpened(e);
    }



    @Override
    public void windowOpened(WindowEvent e) {
        int o = JOptionPane.showConfirmDialog(MainFrame.getInstance(), "Do you want to open existing database?",
                "Welcome", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        if(o == JOptionPane.YES_OPTION){
            int returnVal = chooser.showOpenDialog(MainFrame.getInstance());

            if(returnVal == JFileChooser.APPROVE_OPTION){
                //TODO Poziv import metode


            }

        }else if (o == JOptionPane.NO_OPTION){

        }else{
            System.exit(0);
        }

    }

}
