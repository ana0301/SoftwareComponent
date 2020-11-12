package dialogs;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public AddUpdateDialog(JFrame owner, String title, boolean modal) {
        super(owner, title, modal);
        setMode(AddUpdateDialog.CANCEL);
        this.setMinimumSize(new Dimension(600,250));
        setLocationRelativeTo(owner);

        idLabel = new JLabel("ID: ");
        idLabel.setHorizontalAlignment(JLabel.CENTER);
        idLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel = new JLabel("NAME: ");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        dataLabel = new JLabel("DATA: ");
        dataLabel.setHorizontalAlignment(JLabel.CENTER);
        dataLabel.setVerticalAlignment(JLabel.CENTER);

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

    }

    public List<String[]> getFields(){
        List<String[]> toReturn = new ArrayList<>();
        String[] id = {idField.getText()};
        toReturn.add(id);
        String[] title = {titleField.getText()};
        toReturn.add(title);
        String[] tokens = dataTextArea.getText().split("\n");
        String keys[] = tokens.clone();
        String values[] = tokens.clone();
        for (int i = 0; i < tokens.length; i++) {
            String delim = null;
            if(tokens[i].contains(":")) delim = ":";
            if(tokens[i].contains("-")) delim = "-";
            if(tokens[i].contains("=")) delim = "=";
            String[] token = tokens[i].split(delim);
            for(int j  =0; j < token.length; j++){
                System.out.println(token[j] + Integer.toString(j));
            }
            System.out.println(token[0] + " --- > token 0");
            keys[i] = token[0].trim();
            System.out.println(keys[i] + " --- > key ");
            values[i] = token[1].trim();
            //System.out.println(keys[i]);
            //System.out.println(values[i]);
        }
        toReturn.add(keys);
        toReturn.add(values);
        return toReturn;
    }

    public void setFields(String id, String title, Map<String,Object> data){
        idField.setText(id);
        titleField.setText(title);
        String toSend = "";
        for(Map.Entry<String, Object> property : data.entrySet()){
            if(property.getValue() instanceof String){
                toSend = toSend.concat(property.getKey() + " - " + property.getValue() +"\n");
            }
        }
        dataTextArea.setText(toSend);
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
