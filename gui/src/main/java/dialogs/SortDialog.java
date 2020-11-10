package dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SortDialog extends JDialog implements ActionListener {
    public static String[] sortBy = {"","ID", "NAME", "KEY"};
    public static String[] sortOrder = {"","ASCENDING", "DESCENDING"};

    private JComboBox<String> sortByCmb;
    private JTextField keyTextField;
    private JComboBox<String> sortOrderCmb;

    public static final int CONFIRM = 0;
    public static final int CANCEL = 1;

    private int mode = 1;

    private JButton confirmButton;
    private JButton cancelButton;

    public SortDialog(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        setMode(FilterDialog.CANCEL);
        this.setMinimumSize(new Dimension(500,500));
        setLocationRelativeTo(owner);

        sortByCmb = new JComboBox<>(sortBy);
        keyTextField = new JTextField();
        keyTextField.setEditable(false);
        sortOrderCmb = new JComboBox<>(sortOrder);

        sortByCmb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(sortByCmb.getSelectedIndex() == 3) keyTextField.setEditable(true);
                else keyTextField.setEditable(false);
            }
        });

        JPanel panel = new JPanel(new GridLayout(1,3));
        panel.add(sortByCmb);
        panel.add(keyTextField);
        panel.add(sortOrderCmb);

        this.add(panel, BorderLayout.CENTER);

        confirmButton = new JButton("CONFIRM");
        cancelButton = new JButton("CANCEL");
        confirmButton.addActionListener(this);
        cancelButton.addActionListener(this);

        JPanel commandPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        commandPanel.add(confirmButton);
        commandPanel.add(cancelButton);

        this.add(commandPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("CONFIRM")){
            setMode(FilterDialog.CONFIRM);
        } else{
            setMode(FilterDialog.CANCEL);
        }
        setVisible(false);
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getMode() {
        return mode;
    }
}
