import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import importExport.ImportExportManager;
import importExport.ImportExportService;
import model.Entity;
import model.SimpleType;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class ImportExportYaml extends ImportExportService {
    static {
        ImportExportManager.registerImportExportService(new ImportExportYaml());
    }
    @Override
    public List<Entity> loadDatabase(File file) throws IOException {
        Yaml yaml = new Yaml();
        try {
            InputStream in = new FileInputStream(file);
            //Object o = yaml.loadAs(in, classOf);
            Iterable<Object> objects = yaml.loadAll(in);
            // objects.forEach();
            //System.out.println(objects.toString());
            Iterator<Object> it = objects.iterator();
            List<Entity> entities = new ArrayList<Entity>();
            while(it.hasNext()){
                Map<String,Object> map = (HashMap<String, Object>) it.next();
                Entity entity = new Entity();
                entity.setId(map.get("id").toString());
                entity.setTitle((String) map.get("title"));
                if(map.get("entityData") != null){
                    Map<String,Object> entityData = (Map<String, Object>) map.get("entityData");
                    for(Map.Entry<String,Object> property : entityData.entrySet()){
                        if(property.getValue() instanceof Map){
                            Map<String,Object> nested = (Map<String, Object>) property.getValue();
                            Entity nestedEntity = new Entity();
                            nestedEntity.setId(nested.get("id").toString());
                            nestedEntity.setTitle((String) nested.get("title"));
                            //nestedEntity.setEntityData((Map<String, Object>) nested.get("entityData"));
                            if(nested.get("entityData") != null){
                                Map<String,Object> nestedData = (Map<String, Object>) nested.get("entityData");
                                for(Map.Entry<String, Object> data: nestedData.entrySet()){
                                    if(data.getValue() instanceof String)
                                        nestedEntity.addEntityData(data.getKey(),new SimpleType(data.getValue().toString()));
                                }
                                entity.addEntityData(property.getKey(),entity);
                            }
                            entity.addEntityData(property.getKey(), nestedEntity);
                        }else if(property.getValue()instanceof String){
                            entity.addEntityData(property.getKey(),new SimpleType(property.getValue().toString()));
                        }
                    }
                }
                /*
                for (Map.Entry<String, Object> attributes : map.entrySet()) {
                    if(attributes.getKey().equals("id")){
                       // System.out.println(attributes.getKey() + " "+  attributes.getValue());
                        entity.setId((Integer) attributes.getValue());
                    }
                    else if (attributes.getKey().equals("name"))
                        entity.setTitle((String) attributes.getValue());
                   *//* else if(attributes.getValue() instanceof Map){
                      Map<String, Object> map1 = (HashMap<String, Object>) attributes.getValue();
                      Map<String,Object> data = new HashMap<>();
                        for (Map.Entry<String, Object> a : map1.entrySet()){
                            if(a.getValue() instanceof Map) {
                                Map<String, Object> nest = (HashMap<String, Object>) a.getValue();
                                Entity e = new Entity();
                                for (Map.Entry<String, Object> nested : nest.entrySet()){
                                    if(nested.getKey().equals("id"))
                                        e.setId((Integer) nested.getValue());
                                    else if (nested.getKey().equals("name"))
                                        e.setTitle((String) nested.getValue());
                                    else
                                        e.addEntityData(nested.getKey(),nested.getValue());
                                }
                                data.put(a.getKey(),e);

                            }
                        }
                        entity.addEntityData(attributes.getKey(), data);
                    }*//*
                    else{
                        entity.setEntityData((Map<String, Object>) map.get("entityData"));
                    }
                }
                entities.add(entity);*/
                entities.add(entity);

            }
            return entities;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean saveDatabase(File file, List<Entity> database) throws IOException {
        boolean correct = false;
        try {
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));
            String yaml = mapper.writeValueAsString(database);
            stringToFile(file, yaml);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


        return correct;
    }

    @Override
    public File createDatabase(String namePath) {
        try {
            if(namePath.contains(".yaml")) {
                File f = new File(namePath);
                f.createNewFile();
                return f;
            }else {
                System.out.println("Pogresna ekstenzija");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public String fileToString(File file) {

        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(file.getPath()), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contentBuilder.toString();
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
