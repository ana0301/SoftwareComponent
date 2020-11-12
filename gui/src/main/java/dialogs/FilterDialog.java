package dialogs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FilterDialog extends JDialog implements ActionListener {
    public static String[] filterBy = {"","ID", "NAME", "KEY", "VALUE"};
    //public static String[] numberFilter = {"","EQUALS", "GREATER_THAN", "LESS_THAN"};
    public static String[] stringFilter = {"","EQUALS", "CONTAINS", "STARTS_WITH", "ENDS_WITH"};
    //public static String[] allFilter = {"","EQUALS", "GREATER_THAN", "LESS_THAN","CONTAINS", "STARTS_WITH", "ENDS_WITH"};

    public static final int CONFIRM = 0;
    public static final int CANCEL = 1;

    private int mode = 1;

    private List<JComboBox<String>> filterByCmb;
    private List<JComboBox<String>> filterOptionsCmb;
    private List<JTextField> leftTextField;
    private List<JTextField> rightTextField;

    private JButton confirmButton;
    private JButton cancelButton;

    public FilterDialog(JFrame owner, String title, boolean modal) {
        super(owner, title, modal);
        setMode(FilterDialog.CANCEL);
        this.setMinimumSize(new Dimension(600,300));
        setLocationRelativeTo(owner);

        initFields();

        setGrid();

        setButtons();

        setActions();

        setVisible(true);
    }

    private void setButtons() {
        confirmButton = new JButton("CONFIRM");
        cancelButton = new JButton("CANCEL");
        confirmButton.addActionListener(this);
        cancelButton.addActionListener(this);

        JPanel commandPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        commandPanel.add(confirmButton);
        commandPanel.add(cancelButton);

        this.add(commandPanel, BorderLayout.SOUTH);
    }

    private void setActions() {
        for (int i = 0; i < 5; i++){
            int finalI = i;
            filterByCmb.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedIndex = filterByCmb.get(finalI).getSelectedIndex();
                    filterOptionsCmb.get(finalI).removeAllItems();
                    if (selectedIndex == 1){
                        for (int j = 0; j < stringFilter.length; j++){
                            filterOptionsCmb.get(finalI).addItem(stringFilter[j]);
                        }
                        rightTextField.get(finalI).setEditable(true);
                    }else if(selectedIndex == 2 || selectedIndex == 3){
                        for (int j = 0; j < stringFilter.length; j++){
                            filterOptionsCmb.get(finalI).addItem(stringFilter[j]);
                        }
                        rightTextField.get(finalI).setEditable(true);
                    }else if(selectedIndex == 4){
                        for (int j = 0; j < stringFilter.length; j++){
                            filterOptionsCmb.get(finalI).addItem(stringFilter[j]);
                        }
                        leftTextField.get(finalI).setEditable(true);
                        rightTextField.get(finalI).setEditable(true);
                    }else{
                        //TODO return error
                    }
                }
            });
        }
    }

    private void setGrid() {
        JPanel panel = new JPanel(new GridLayout(5,4));

        for (int i = 0; i < 5; i++){
            panel.add(filterByCmb.get(i));
            panel.add(leftTextField.get(i));
            panel.add(filterOptionsCmb.get(i));
            panel.add(rightTextField.get(i));
        }

        this.add(panel, BorderLayout.CENTER);
    }

    private void initFields() {
        filterByCmb = new ArrayList<>();
        filterOptionsCmb = new ArrayList<>();
        leftTextField = new ArrayList<>();
        rightTextField = new ArrayList<>();

        for (int i = 0; i < 5; i++){
            JComboBox<String> fcmb = new JComboBox<>(filterBy);
            fcmb.setSelectedItem("");
            JComboBox<String> ocmb = new JComboBox<>();
            JTextField tfleft = new JTextField();
            tfleft.setEditable(false);
            JTextField tfright = new JTextField();
            tfright.setEditable(false);

            filterByCmb.add(fcmb);
            filterOptionsCmb.add(ocmb);
            leftTextField.add(tfleft);
            rightTextField.add(tfright);
        }
    }

    public ArrayList<String[]> getFields(){
        ArrayList<String[]> toReturn = new ArrayList<>();
        for (int i = 0; i < 5; i++){
            String[] strings = {filterByCmb.get(i).getItemAt(filterByCmb.get(i).getSelectedIndex()),
                leftTextField.get(i).getText(),
                    filterOptionsCmb.get(i).getItemAt(filterOptionsCmb.get(i).getSelectedIndex()),
                    rightTextField.get(i).getText()};
            toReturn.add(strings);
        }
        return toReturn;
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
            setMode(FilterDialog.CONFIRM);
        } else{
            setMode(FilterDialog.CANCEL);
        }
        setVisible(false);
    }
}
