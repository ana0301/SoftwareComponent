package appcore;

import exceptions.UnsupportedImplementation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainFrameWindowListener extends WindowAdapter {

    @Override
    public void windowClosing(WindowEvent e) {
        int o = JOptionPane.showConfirmDialog(MainFrame.getInstance(), "Do you want to save current database?",
                "Warning", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        if(o == JOptionPane.YES_OPTION){
            boolean oldFiles = true;
            boolean written = false;
            int jOptionPane = JOptionPane.showConfirmDialog(MainFrame.getInstance(),"Do you want to change file?","Choose",JOptionPane.YES_NO_OPTION);
            String num = JOptionPane.showInputDialog(MainFrame.getInstance(), "Enter a number of entity you want to save in file");
            boolean correct = false;
            int number = 0;
            while(!correct) {
                try {
                    number = Integer.parseInt(num);
                    correct = true;
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(MainFrame.getInstance(), "You must enter a number", "Error", JOptionPane.ERROR_MESSAGE);
                    num = JOptionPane.showInputDialog(MainFrame.getInstance(), "Enter a number of entity you want to save in file");
                }
            }
                if (jOptionPane == JOptionPane.YES_OPTION) {
                    //dodati da odabere putanju
                    int choose = chooser.showSaveDialog(MainFrame.getInstance());
                    if (choose == JFileChooser.APPROVE_OPTION) {
                        // if(chooser.getSelectedFile())
                    }
                    String name = JOptionPane.showInputDialog(MainFrame.getInstance(), "Enter name of file with correct extension");
                    try {
                        written = MainFrame.getInstance().getImportExportService().saveInNewFiles(number, name);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }else{
                     written = MainFrame.getInstance().getImportExportService().saveInOldFiles(number);
                }
                if (written)
                    JOptionPane.showMessageDialog(MainFrame.getInstance(), "Data has been written successfully!");
                else JOptionPane.showMessageDialog(MainFrame.getInstance(), "Mistake");
            }
        else if(o == JOptionPane.NO_OPTION) System.exit(0);
        else this.windowOpened(e);
    }



    @Override
    public void windowOpened(WindowEvent e) {
        int o = JOptionPane.showConfirmDialog(MainFrame.getInstance(), "Do you want to open existing database?",
                "Welcome", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setMultiSelectionEnabled(true);

        if(o == JOptionPane.YES_OPTION){
            int returnVal = chooser.showOpenDialog(MainFrame.getInstance());

            if(returnVal == JFileChooser.APPROVE_OPTION){
                //TODO Poziv import metode
               if(chooser.getSelectedFile()!= null) {
                   List<File> files = new ArrayList<File>();
                   if (chooser.getSelectedFile().isFile()) {
                        files.add(chooser.getSelectedFile());
                   }
                   else {
                       if(chooser.getSelectedFile().listFiles() != null) {
                           for (File file : chooser.getSelectedFile().listFiles()) {
                               if (file.isDirectory())
                                   JOptionPane.showMessageDialog(MainFrame.getInstance(), "You have chosen folder with another folder in it");
                           }
                           files = Arrays.asList(chooser.getSelectedFile().listFiles());
                       }else JOptionPane.showMessageDialog(MainFrame.getInstance(),"Need to choose some file");
                   }
                   boolean done = MainFrame.getInstance().getImportExportService().load(files);
                   if(done) JOptionPane.showMessageDialog(MainFrame.getInstance(),"Data load successfully!");
                   else JOptionPane.showMessageDialog(MainFrame.getInstance(),"Something gone wrong!");
               }


            }

        }else if (o == JOptionPane.NO_OPTION){
        }else{
            System.exit(0);
        }

    }

}
