package importExport;

public class ImportExportManager {
    private static ImportExportService importExportService;

    public static void registerImportExportService(ImportExportService importExport) {
        importExportService = importExport;
    }

    public static ImportExportService getImportExportService() {
        return importExportService;
    }
}
    /*public static ImportExportService getImportExportService(String fileName){
        Impo
    }*/

