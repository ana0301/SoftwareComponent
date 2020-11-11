package appcore;

import controllers.*;
import importExport.ImportExportManager;
import importExport.ImportExportService;
import model.DataTableModel;
import model.Database;
import service.CRUDService;
import service.FilterSortService;
import service.impl.CRUDServiceImpl;
import service.impl.FilterSortServiceImpl;
import view.DataTableView;
import view.DataView;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private DataTableModel dataTableModel;
    private CRUDService crudService;
    private FilterSortService filterSortService;

    private DataTableView dataTableView;
    private JPanel buttonPanel;

    private JButton addButton;
    private JButton addNestedButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton filterButton;
    private JButton sortButton;
    private JButton showAllDataButton;

    private ImportExportService importExportService;
    private MainFrame() {
        try {
            Class.forName("ImportExportJson");
             importExportService = ImportExportManager.getImportExportService();
        }catch (Exception e){
            e.printStackTrace();
        }

        dataTableModel = new DataTableModel();
        crudService = new CRUDServiceImpl();
        filterSortService = new FilterSortServiceImpl();

        this.setTitle("GUIapp2a");
        this.setSize(1200, 1200);
        this.setLayout(new BorderLayout());

        JPanel dataView = new JPanel();
        dataView.setSize(400,400);
        dataTableView = new DataTableView(dataTableModel);
        dataView.setLayout(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(dataTableView);
        dataView.add(scrollPane, BorderLayout.CENTER);

        initButtonPanel(dataView);

        this.add(dataView, BorderLayout.NORTH);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addWindowListener(new MainFrameWindowListener());
    }


    private static class InstanceHolder {
        private static MainFrame instance = new MainFrame();
    }

    public CRUDService getCrudService() {
        return crudService;
    }

    public FilterSortService getFilterSortService() {
        return filterSortService;
    }

    public DataTableModel getDataTableModel() {
        return dataTableModel;
    }

    public void setDataTableModel(DataTableModel dataTableModel) {
        this.dataTableModel = dataTableModel;
    }

    public static MainFrame getInstance() {

        return InstanceHolder.instance;
    }

    public ImportExportService getImportExportService() {
        return importExportService;
    }

    private void initButtonPanel(JPanel panel) {
        this.buttonPanel = new JPanel();
        this.buttonPanel.setLayout(new FlowLayout());

        this.addButton = new JButton("ADD");
        addButton.addActionListener(new AddEntityController());
        this.addNestedButton = new JButton("ADD NESTED");
        addNestedButton.addActionListener(new AddNestedEntityController());
        this.deleteButton = new JButton("DELETE");
        deleteButton.addActionListener(new DeleteEntityController());
        this.updateButton = new JButton("UPDATE");
        updateButton.addActionListener(new UpdateEntityController());
        this.filterButton = new JButton("FILTER");
        filterButton.addActionListener(new FilterController());
        this.sortButton = new JButton("SORT");
        sortButton.addActionListener(new SortController());
        this.showAllDataButton = new JButton("SHOW ALL");
        showAllDataButton.addActionListener(new ShowAllDataController());

        buttonPanel.add(addButton);
        buttonPanel.add(addNestedButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(filterButton);
        buttonPanel.add(sortButton);
        buttonPanel.add(showAllDataButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);
    }
}
