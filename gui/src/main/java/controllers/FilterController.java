package controllers;

import enums.FilterOperator;
import model.Entity;
import view.MainFrame;
import dialogs.FilterDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FilterController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        FilterDialog fd = new FilterDialog(MainFrame.getInstance(), "Filter data", true);
        if (fd.getMode() == 0){
            List<String[]> toSend = fd.getFields();
            List<Entity> result = new ArrayList<>();
            for (int i = 0; i < toSend.size(); i++) {
                if (toSend.get(i)[0].equals("")) continue;
                if (toSend.get(i)[0].equals("ID")){
                    if (toSend.get(i)[2].equals("")) continue;
                    if (toSend.get(i)[2].equals("EQUALS") && i == 0)
                        result.addAll(MainFrame.getInstance().getFilterSortService().filterById(FilterOperator.EQUALS, toSend.get(i)[3]));
                    if (toSend.get(i)[2].equals("EQUALS") && i > 0)
                        result = MainFrame.getInstance().getFilterSortService().filterById(result,FilterOperator.EQUALS, toSend.get(i)[3]);
                    if (toSend.get(i)[2].equals("CONTAINS") && i == 0)
                        result.addAll(MainFrame.getInstance().getFilterSortService().filterById(FilterOperator.CONTAINS, toSend.get(i)[3]));
                    if (toSend.get(i)[2].equals("CONTAINS") && i > 0)
                        result = MainFrame.getInstance().getFilterSortService().filterById(result,FilterOperator.CONTAINS, toSend.get(i)[3]);
                    if (toSend.get(i)[2].equals("STARTS_WITH") && i == 0)
                        result.addAll(MainFrame.getInstance().getFilterSortService().filterById(FilterOperator.STARTS_WITH, toSend.get(i)[3]));
                    if (toSend.get(i)[2].equals("STARTS_WITH") && i > 0)
                        result = MainFrame.getInstance().getFilterSortService().filterById(result,FilterOperator.STARTS_WITH, toSend.get(i)[3]);
                    if (toSend.get(i)[2].equals("ENDS_WITH") && i == 0)
                        result.addAll(MainFrame.getInstance().getFilterSortService().filterById(FilterOperator.ENDS_WITH, toSend.get(i)[3]));
                    if (toSend.get(i)[2].equals("ENDS_WITH") && i > 0)
                        result = MainFrame.getInstance().getFilterSortService().filterById(result,FilterOperator.ENDS_WITH, toSend.get(i)[3]);
                }else if(toSend.get(i)[0].equals("NAME")){
                    if (toSend.get(i)[2].equals("")) continue;
                    if (toSend.get(i)[2].equals("EQUALS") && i == 0)
                        result.addAll(MainFrame.getInstance().getFilterSortService().filterByTitle(FilterOperator.EQUALS, toSend.get(i)[3]));
                    if (toSend.get(i)[2].equals("EQUALS") && i > 0)
                        result = MainFrame.getInstance().getFilterSortService().filterByTitle(result,FilterOperator.EQUALS, toSend.get(i)[3]);
                    if (toSend.get(i)[2].equals("CONTAINS") && i == 0)
                        result.addAll(MainFrame.getInstance().getFilterSortService().filterByTitle(FilterOperator.CONTAINS, toSend.get(i)[3]));
                    if (toSend.get(i)[2].equals("CONTAINS") && i > 0)
                        result = MainFrame.getInstance().getFilterSortService().filterByTitle(result,FilterOperator.CONTAINS, toSend.get(i)[3]);
                    if (toSend.get(i)[2].equals("STARTS_WITH") && i == 0)
                        result.addAll(MainFrame.getInstance().getFilterSortService().filterByTitle(FilterOperator.STARTS_WITH, toSend.get(i)[3]));
                    if (toSend.get(i)[2].equals("STARTS_WITH") && i > 0)
                        result = MainFrame.getInstance().getFilterSortService().filterByTitle(result,FilterOperator.STARTS_WITH, toSend.get(i)[3]);
                    if (toSend.get(i)[2].equals("ENDS_WITH") && i == 0)
                        result.addAll(MainFrame.getInstance().getFilterSortService().filterByTitle(FilterOperator.ENDS_WITH, toSend.get(i)[3]));
                    if (toSend.get(i)[2].equals("ENDS_WITH") && i > 0)
                        result = MainFrame.getInstance().getFilterSortService().filterByTitle(result,FilterOperator.ENDS_WITH, toSend.get(i)[3]);
                }else if(toSend.get(i)[0].equals("KEY")){
                    if (toSend.get(i)[2].equals("")) continue;
                    if (toSend.get(i)[2].equals("EQUALS") && i == 0)
                        result.addAll(MainFrame.getInstance().getFilterSortService().filterByKey(FilterOperator.EQUALS, toSend.get(i)[3]));
                    if (toSend.get(i)[2].equals("EQUALS") && i > 0)
                        result = MainFrame.getInstance().getFilterSortService().filterByKey(result,FilterOperator.EQUALS, toSend.get(i)[3]);
                    if (toSend.get(i)[2].equals("CONTAINS") && i == 0)
                        result.addAll(MainFrame.getInstance().getFilterSortService().filterByKey(FilterOperator.CONTAINS, toSend.get(i)[3]));
                    if (toSend.get(i)[2].equals("CONTAINS") && i > 0)
                        result = MainFrame.getInstance().getFilterSortService().filterByKey(result,FilterOperator.CONTAINS, toSend.get(i)[3]);
                    if (toSend.get(i)[2].equals("STARTS_WITH") && i == 0)
                        result.addAll(MainFrame.getInstance().getFilterSortService().filterByKey(FilterOperator.STARTS_WITH, toSend.get(i)[3]));
                    if (toSend.get(i)[2].equals("STARTS_WITH") && i > 0)
                        result = MainFrame.getInstance().getFilterSortService().filterByKey(result,FilterOperator.STARTS_WITH, toSend.get(i)[3]);
                    if (toSend.get(i)[2].equals("ENDS_WITH") && i == 0)
                        result.addAll(MainFrame.getInstance().getFilterSortService().filterByKey(FilterOperator.ENDS_WITH, toSend.get(i)[3]));
                    if (toSend.get(i)[2].equals("ENDS_WITH") && i > 0)
                        result = MainFrame.getInstance().getFilterSortService().filterByKey(result,FilterOperator.ENDS_WITH, toSend.get(i)[3]);
                }else if(toSend.get(i)[0].equals("VALUE")){
                    if (toSend.get(i)[2].equals("")) continue;
                    if (toSend.get(i)[2].equals("EQUALS") && i == 0)
                        result.addAll(MainFrame.getInstance().getFilterSortService().filterByValue(FilterOperator.EQUALS, toSend.get(i)[1] ,toSend.get(i)[3]));
                    if (toSend.get(i)[2].equals("EQUALS") && i > 0)
                        result = MainFrame.getInstance().getFilterSortService().filterByValue(result,FilterOperator.EQUALS, toSend.get(i)[1] ,toSend.get(i)[3]);
                    if (toSend.get(i)[2].equals("CONTAINS") && i == 0)
                        result.addAll(MainFrame.getInstance().getFilterSortService().filterByValue(FilterOperator.CONTAINS, toSend.get(i)[1] ,toSend.get(i)[3]));
                    if (toSend.get(i)[2].equals("CONTAINS") && i > 0)
                        result = MainFrame.getInstance().getFilterSortService().filterByValue(result,FilterOperator.CONTAINS, toSend.get(i)[1] ,toSend.get(i)[3]);
                    if (toSend.get(i)[2].equals("STARTS_WITH") && i == 0)
                        result.addAll(MainFrame.getInstance().getFilterSortService().filterByValue(FilterOperator.STARTS_WITH, toSend.get(i)[1] ,toSend.get(i)[3]));
                    if (toSend.get(i)[2].equals("STARTS_WITH") && i > 0)
                        result = MainFrame.getInstance().getFilterSortService().filterByValue(result,FilterOperator.STARTS_WITH, toSend.get(i)[1] ,toSend.get(i)[3]);
                    if (toSend.get(i)[2].equals("ENDS_WITH") && i == 0)
                        result.addAll(MainFrame.getInstance().getFilterSortService().filterByValue(FilterOperator.ENDS_WITH, toSend.get(i)[1] ,toSend.get(i)[3]));
                    if (toSend.get(i)[2].equals("ENDS_WITH") && i > 0)
                        result = MainFrame.getInstance().getFilterSortService().filterByValue(result,FilterOperator.ENDS_WITH, toSend.get(i)[1] ,toSend.get(i)[3]);
                }
            }
            MainFrame.getInstance().getDataTableModel().updateTableModel(result);
        }
    }
}
