package dialogs;

import appcore.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUpdateDialog extends JDialog implements ActionListener {
    private JLabel titleLabel;
    private JLabel idLabel;
    private JLabel dataLabel;

    private JTextField titleField;
    private JTextField idField;
    private JTextArea dataTextArea;

    private JButton confirmButton;
    private JButton cancelButton;

    public static final int CONFIRM = 0;
    public static final int CANCEL = 1;

    private int mode = 1;

    public AddUpdateDialog(JFrame owner, String title, boolean modal, String[] entityData) {
        super(owner, title, modal);
        setMode(AddUpdateDialog.CANCEL);
        this.setMinimumSize(new Dimension(500,500));
        setLocationRelativeTo(owner);

        idLabel = new JLabel("ID: ");
        titleLabel = new JLabel("NAME: ");
        dataLabel = new JLabel("DATA: ");

        idField = new JTextField();
        titleField = new JTextField();
        dataTextArea = new JTextArea();

        confirmButton = new JButton("CONFIRM");
        cancelButton = new JButton("CANCEL");
        confirmButton.addActionListener(this);
        cancelButton.addActionListener(this);

        JPanel mainPanel = new JPanel(new GridLayout(3,2));
        mainPanel.add(idLabel);
        mainPanel.add(idField);
        mainPanel.add(titleLabel);
        mainPanel.add(titleField);
        mainPanel.add(dataLabel);
        mainPanel.add(dataTextArea);

        JPanel commandPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        commandPanel.add(confirmButton);
        commandPanel.add(cancelButton);

        add(mainPanel, BorderLayout.CENTER);
        add(commandPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public String[] getFields(){
        return new String[]{idField.getText(), titleField.getText(), dataTextArea.getText()};
    }

    public void setFields(String[] args){
        idField.setText(args[0]);
        titleField.setText(args[1]);
        dataTextArea.setText(args[2]);
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
}
