package dialogs;


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewFileDialog extends JDialog implements ActionListener {
    public static final int CONFIRM = 0;
    public static final int CANCEL = 1;

    private int mode = 1;
    private JLabel name;
    private JTextField enterName;
    private JPanel panel;
    private JButton confirmButton;
    private JButton cancelButton;

    public NewFileDialog(JFrame owner) {
        super(owner, "New file", true);

        setMode(NewFileDialog.CANCEL);

        setSize(350,150);
        setLocationRelativeTo(owner);


        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        enterName = new JTextField(10);
        enterName.setText("Enter a name file");
        name = new JLabel("Name: ");

        JPanel panCommands=new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnConfirm=new JButton("CONFIRM");
        btnConfirm.addActionListener(this);
        JButton btnCancel=new JButton("CANCEL");
        btnCancel.addActionListener(this);

        panCommands.add(btnConfirm);
        panCommands.add(btnCancel);

       panel.add(name);
       panel.add(enterName);
       panel.add(panCommands);
       this.add(panel);

        setVisible(true);

    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getActionCommand().equals("CONFIRM")){
            setMode(NewFileDialog.CONFIRM);
        } else{
            setMode(NewFileDialog.CANCEL);
        }
        setVisible(false);
    }
}
