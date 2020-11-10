package appcore;

import view.DataView;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private MainFrame(){
        this.setTitle("GUIapp2a");
        this.setSize(1200, 1200);
        this.setLayout(new BorderLayout());

        DataView dataView = new DataView();
        this.add(dataView, BorderLayout.NORTH);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static class InstanceHolder {
        private static MainFrame instance = new MainFrame();
    }

    public static MainFrame getInstance() {
        return InstanceHolder.instance;
    }

}
