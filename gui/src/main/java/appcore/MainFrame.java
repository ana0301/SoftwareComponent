package appcore;

import importExport.ImportExportManager;
import importExport.ImportExportService;
import model.DataTableModel;
import model.Database;
import service.CRUDService;
import service.FilterSortService;
import service.impl.CRUDServiceImpl;
import service.impl.FilterSortServiceImpl;
import view.DataView;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private DataTableModel dataTableModel;
    private CRUDService crudService;
    private FilterSortService filterSortService;

    private ImportExportService importExportService;
    private MainFrame() {
        try {
            Class.forName("ImportExportJson");
             importExportService = ImportExportManager.getImportExportService();
        }catch (Exception e){
            e.printStackTrace();
        }

        //HEEEEEJ
        dataTableModel = new DataTableModel();
        crudService = new CRUDServiceImpl();
        filterSortService = new FilterSortServiceImpl();

        this.setTitle("GUIapp2a");
        this.setSize(1200, 1200);
        this.setLayout(new BorderLayout());

        DataView dataView = new DataView();
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
}
