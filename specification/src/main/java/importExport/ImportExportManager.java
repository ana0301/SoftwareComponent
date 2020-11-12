package importExport;

/**
 *Manages used ImportExportService
 */
public class ImportExportManager {
    /**
     * ImportExportService which is used
     */
    private static ImportExportService importExportService;

    /**
     * Sets ImportExportService
     * @param importExport as ImportExportService
     */
    public static void registerImportExportService(ImportExportService importExport) {
        importExportService = importExport;
    }

    /**
     *Getter for ImportExportService
     * @return ImportExportService
     */
    public static ImportExportService getImportExportService() {
        return importExportService;
    }
}

