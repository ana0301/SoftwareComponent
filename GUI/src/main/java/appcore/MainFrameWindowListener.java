package appcore;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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

        if(o == JOptionPane.YES_OPTION){
            JFileChooser chooser = new JFileChooser();
            int returnVal = chooser.showOpenDialog(MainFrame.getInstance());

            if(returnVal == JFileChooser.APPROVE_OPTION){
                //TODO Poziv import metode
                //
            }

        }else if (o == JOptionPane.NO_OPTION){

        }else{
            System.exit(0);
        }

    }

}
