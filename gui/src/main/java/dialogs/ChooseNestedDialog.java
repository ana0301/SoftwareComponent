package dialogs;

import view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ChooseNestedDialog extends JDialog implements ActionListener {

    private JComboBox<String> comboBox;
    private JButton confirmButton;
    private JButton cancelButton;

    public static final int CONFIRM = 0;
    public static final int CANCEL = 1;

    private int mode = 1;



    public ChooseNestedDialog(Set<String> entities){
        super(MainFrame.getInstance(),"Choose entity", true);
        comboBox = new JComboBox<>();
        for(String entity : entities){
            comboBox.addItem(entity);
        }
        setSize(200,200);

        confirmButton = new JButton("CONFIRM");
        cancelButton = new JButton("CANCEL");
        confirmButton.addActionListener(this);
        cancelButton.addActionListener(this);
        JPanel commandPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        commandPanel.add(confirmButton);
        commandPanel.add(cancelButton);

        JPanel panel = new JPanel();
        panel.add(comboBox);
        panel.add(commandPanel);

        add(panel);
        setVisible(true);

    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("CONFIRM")){
            setMode(AddUpdateDialog.CONFIRM);
        } else{
            setMode(AddUpdateDialog.CANCEL);
        }
        setVisible(false);
    }
    public String getSelectedItem(){
       return (String) comboBox.getSelectedItem();
    }

}
