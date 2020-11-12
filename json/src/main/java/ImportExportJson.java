import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import exceptions.UnsupportedImplementation;
import importExport.ImportExportManager;
import importExport.ImportExportService;
import model.Entity;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ImportExportJson extends ImportExportService {
    //import
    static{
        ImportExportManager.registerImportExportService(new ImportExportJson());
    }

    @Override
    public List<Entity> loadDatabase(File file) throws IOException, UnsupportedImplementation {
        if(!file.getAbsolutePath().endsWith(".json")) throw new UnsupportedImplementation("JSON");
        List<Entity> entities = new ArrayList<Entity>();
        String json = fileToString(file);
        Gson gson = new Gson();
        List<Object> objects = gson.fromJson(json, List.class);
        for (Object o : objects) {
            Entity e = new Entity();
            Map<String, Object> map = (LinkedTreeMap<String, Object>) o;
            e.setId(map.get("id").toString());
            e.setTitle(map.get("title").toString());
            if (map.get("entityData") != null) {
                Map<String, Object> entityData = (Map<String, Object>) map.get("entityData");
                for (Map.Entry<String, Object> property : entityData.entrySet()) {
                    if (property.getValue() instanceof Map) {
                        Map<String, Object> nested = (Map<String, Object>) property.getValue();
                        Entity nestedEntity = new Entity();
                        nestedEntity.setId(nested.get("id").toString());
                        nestedEntity.setTitle((String) nested.get("title"));
                        if(nested.get("entityData") != null){
                            Map<String,Object> nestedData = (Map<String, Object>) nested.get("entityData");
                            for (Map.Entry<String,Object> data : nestedData.entrySet()){
                                if(data.getValue() instanceof String)
                                    nestedEntity.addEntityData(data.getKey(), (String)data.getValue().toString());
                                else System.out.println("GRESKA");
                            }
                        }
                        e.addEntityData(property.getKey(), nestedEntity);
                    } else if(property.getValue() instanceof String){
                        e.addEntityData(property.getKey(), (String) property.getValue());
                    }else {
                        System.out.println("Mora string ili entity");
                    }
                }
                entities.add(e);
            }
        }
        return entities;
    }

    @Override
    public boolean saveDatabase(File file, List<Entity> database) throws IOException {
        Boolean b = false;
        try {
            Gson gson = new Gson();
            GsonBuilder gsonBuilder  = new GsonBuilder();
            gsonBuilder.setPrettyPrinting();
            String json = gsonBuilder.create().toJson(database);
            stringToFile(file, json);
            b = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return b;
    }

    //new database
    @Override
    public File createDatabase(String namePath) {
        try {
            File f = new File(namePath+".json");
            f.createNewFile();
            return f;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String fileToString(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader (file));
        String         line = null;
        StringBuilder  stringBuilder = new StringBuilder();
        String         ls = System.getProperty("line.separator");

        try {
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }

            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
        return stringBuilder.toString();
    }

    public void stringToFile(File file, String data) {

        file.setWritable(true);

        try (FileOutputStream fos = new FileOutputStream(file);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            // convert string to byte array
            byte[] bytes = data.getBytes();
            // write byte array to file
            bos.write(bytes);
            bos.close();
            fos.close();
            System.out.print("Data written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}