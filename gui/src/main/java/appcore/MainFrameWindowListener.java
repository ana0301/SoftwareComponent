package appcore;


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
        chooser.setMultiSelectionEnabled(true);
        if (o == JOptionPane.YES_OPTION) {
            boolean oldFiles = true;
            boolean written = false;
            int jOptionPane = JOptionPane.showConfirmDialog(MainFrame.getInstance(), "Do you want to change file?", "Choose", JOptionPane.YES_NO_OPTION);
            String num = JOptionPane.showInputDialog(MainFrame.getInstance(), "Enter a number of entity you want to save in file");
            boolean correct = false;
            int number = 0;
            while (!correct) {
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
                    if (chooser.getSelectedFile() != null) {
                        String path = chooser.getSelectedFile().getAbsolutePath();
                        String name = JOptionPane.showInputDialog(MainFrame.getInstance(), "Enter name of file");
                        try {
                            written = MainFrame.getInstance().getImportExportService().saveInNewFiles(path, number, name);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                    }
                }

            } else {
                written = MainFrame.getInstance().getImportExportService().saveInOldFiles(number);
            }
            if (written)
                JOptionPane.showMessageDialog(MainFrame.getInstance(), "Data has been written successfully!");
            else JOptionPane.showMessageDialog(MainFrame.getInstance(), "Mistake");
        } else if (o == JOptionPane.NO_OPTION) System.exit(0);
        else MainFrame.getInstance().setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    }


    @Override
    public void windowOpened(WindowEvent e) {
        int o = JOptionPane.showConfirmDialog(MainFrame.getInstance(), "Do you want to open existing database?",
                "Welcome", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setMultiSelectionEnabled(true);

        if (o == JOptionPane.YES_OPTION) {
            int returnVal = chooser.showOpenDialog(MainFrame.getInstance());

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                //TODO Poziv import metode
                if (chooser.getSelectedFile() != null) {
                    List<File> files = new ArrayList<>();
                    if (chooser.getSelectedFile().isFile()) {
                        files.add(chooser.getSelectedFile());
                    } else {
                        if (chooser.getSelectedFile().listFiles() != null) {
                            for (File file : chooser.getSelectedFile().listFiles()) {
                                if (file.isDirectory())
                                    JOptionPane.showMessageDialog(MainFrame.getInstance(), "You have chosen folder with another folder in it");
                            }
                            files = Arrays.asList(chooser.getSelectedFile().listFiles());
                        } else JOptionPane.showMessageDialog(MainFrame.getInstance(), "Need to choose some file");
                    }
                    boolean done = MainFrame.getInstance().getImportExportService().load(files);
                    if (done) JOptionPane.showMessageDialog(MainFrame.getInstance(), "Data load successfully!");
                    else JOptionPane.showMessageDialog(MainFrame.getInstance(), "Something gone wrong!");
                }


            } else MainFrame.getInstance().setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        } else if (o == JOptionPane.NO_OPTION) {
            String name = JOptionPane.showInputDialog(MainFrame.getInstance(), "Enter a name for new database", "Create", JOptionPane.INFORMATION_MESSAGE);
            if (name != null) {
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int choose = chooser.showSaveDialog(MainFrame.getInstance());
                if (choose == JFileChooser.APPROVE_OPTION) {
                    if (chooser.getSelectedFile() != null) {
                        String path = chooser.getSelectedFile().getAbsolutePath();
                        boolean written = MainFrame.getInstance().getImportExportService().createNewDatabase(path+"\\"+name);
                        if(written) JOptionPane.showMessageDialog(MainFrame.getInstance(),"File has been created");
                    }
                }
            } else {
                MainFrame.getInstance().setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
            }

        }

    }
}