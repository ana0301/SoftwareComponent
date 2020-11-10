import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import importExport.ImportExportService;
import model.Entity;
import model.SimpleType;
import model.Type;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ImportExportJson implements ImportExportService {
    //import
    @Override
    public List<Entity> loadDatabase(File file) throws IOException {
        List<Entity> entities = new ArrayList<Entity>();
        String json = fileToString(file);
        Gson gson = new Gson();
        List<Object> objects = gson.fromJson(json, List.class);
        for (Object o : objects) {
            Entity e = new Entity();
            LinkedTreeMap<String, Object> map = (LinkedTreeMap<String, Object>) o;
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
                        // nestedEntity.setEntityData((Map<String, Type>) nested.get("entityData"));
                        if(nested.get("entityData") != null){
                            Map<String,Object> nestedData = (Map<String, Object>) nested.get("entityData");
                            for (Map.Entry<String,Object> data : nestedData.entrySet()){
                                if(data.getValue() instanceof String)
                                    nestedEntity.addEntityData(data.getKey(), new SimpleType(data.getValue().toString()));
                                else System.out.println("GRESKA");
                            }
                        }
                        e.addEntityData(property.getKey(), nestedEntity);
                    } else if(property.getValue() instanceof String){
                        SimpleType simpleType = new SimpleType(property.getValue().toString());
                        e.addEntityData(property.getKey(), simpleType);
                    }else {
                        System.out.println("Mora string ili entity");
                    }
                }
                entities.add(e);
            }
        }
       /* JsonReader jsonReader = new JsonReader(new FileReader(file));
        jsonReader.beginArray();
        while(!jsonReader.peek().equals(JsonToken.END_ARRAY)) {
            Entity e = new Entity();
            jsonReader.beginObject();
            while (!jsonReader.peek().equals(JsonToken.END_OBJECT)) {
                String key = jsonReader.nextName();
                if (key.equals("id"))
                    e.setId(jsonReader.nextInt());
                else if (key.equals("name"))
                    e.setTitle(jsonReader.nextString());
                 else {
                    if (jsonReader.peek().equals(JsonToken.NUMBER)) {
                        Double d = jsonReader.nextDouble();
                        e.addEntityData(key, d);
                    } else if (jsonReader.peek().equals(JsonToken.BOOLEAN)) {
                        Boolean b = jsonReader.nextBoolean();
                        e.addEntityData(key, b);
                    } else if (jsonReader.peek().equals(JsonToken.STRING)) {
                        String s = jsonReader.nextString();
                        e.addEntityData(key, s);
                    } else if (jsonReader.peek().equals(JsonToken.BEGIN_OBJECT)) {
                        //ugnjezdeni entitet
                        Entity entity = new Entity();
                        jsonReader.beginObject();
                        while (!jsonReader.peek().equals(JsonToken.END_OBJECT)) {
                            String key1 = jsonReader.nextName();
                            if (key1.equals("id"))
                                entity.setId(jsonReader.nextInt());
                            else if (key1.equals("name"))
                                entity.setTitle(jsonReader.nextString());
                            else {
                                if (jsonReader.peek().equals(JsonToken.NUMBER)) {
                                    Double d = jsonReader.nextDouble();
                                    entity.addEntityData(key1, d);
                                } else if (jsonReader.peek().equals(JsonToken.BOOLEAN)) {
                                    Boolean b = jsonReader.nextBoolean();
                                    entity.addEntityData(key1, b);
                                } else if (jsonReader.peek().equals(JsonToken.STRING)) {
                                    String s = jsonReader.nextString();
                                    entity.addEntityData(key1, s);
                                } else if (jsonReader.peek().equals(JsonToken.BEGIN_ARRAY)) {
                                    //LISTA
                                    List<Object> list1 = new ArrayList();
                                    jsonReader.beginArray();
                                    while (!jsonReader.peek().equals(JsonToken.END_ARRAY)){
                                        switch (jsonReader.peek()){
                                            case NUMBER: {
                                                Double d = jsonReader.nextDouble();
                                                list1.add(d);
                                                break;
                                            }
                                            case STRING: {
                                                String s = jsonReader.nextString();
                                                list1.add(s);
                                                break;
                                            }
                                            case BOOLEAN:{
                                                Boolean b = jsonReader.nextBoolean();
                                                list1.add(b);
                                                break;
                                            }
                                        }
                                    }
                                    jsonReader.endArray();
                                    entity.addEntityData(key1, list1);
                                } else {
                                    System.err.println("GRESKA PRI UCITAVANJU!");
                                }
                            }
                        }
                        jsonReader.endObject();
                        e.addEntityData(key, entity);
                    } else if (jsonReader.peek().equals(JsonToken.BEGIN_ARRAY)) {
                        //LISTA
                        List<Object> list = new ArrayList<Object>();
                        jsonReader.beginArray();
                        while (!jsonReader.peek().equals(JsonToken.END_ARRAY)){
                            //System.out.println(jsonReader.nextString());
                            switch (jsonReader.peek()){
                                case NUMBER: {
                                    Double d = jsonReader.nextDouble();
                                    list.add(d);
                                    break;
                                }
                                case STRING: {
                                    String s = jsonReader.nextString();
                                    list.add(s);
                                    break;
                                }
                                case BOOLEAN:{
                                    Boolean b = jsonReader.nextBoolean();
                                    list.add(b);
                                    break;
                                }
                            }
                        }
                        jsonReader.endArray();
                        e.addEntityData(key, list);
                    } else {
                        System.err.println("GRESKA PRI UCITAVANJU!");
                    }
                }
            }
            jsonReader.endObject();
            entities.add(e);
        }
        jsonReader.endArray();*/
        return entities;
    }

    //export
    @Override
    public boolean saveDatabase(File file, List<Entity> database) throws IOException {
        Boolean b = false;
        try {
            String json = new Gson().toJson(database);
            stringToFile(file, json);
            b = true;
        } catch (Exception e) {
            e.printStackTrace();
            return b;
        }

        /*
        JsonWriter jsonWriter = new JsonWriter(new OutputStreamWriter(new FileOutputStream(file)));
        jsonWriter.beginArray();
        try {
            for (Entity e : database) {
                jsonWriter.beginObject();
                jsonWriter.name("id").value(e.getId());
                jsonWriter.name("name").value(e.getTitle());
                for (Map.Entry<String, Object> entry : e.getEntityData().entrySet()) {
                    System.out.println(entry.getKey()+ "  + " +entry.getValue() + " value type: " + entry.getValue().getClass());
                 if (entry.getValue() instanceof String)
                        jsonWriter.name(entry.getKey()).value(entry.getValue().toString());
                    else if (entry.getValue() instanceof Double)
                        jsonWriter.name(entry.getKey()).value((Double)entry.getValue());
                    else if (entry.getValue() instanceof Boolean)
                        jsonWriter.name(entry.getKey()).value((Boolean) entry.getValue());
                    else if (entry.getValue() instanceof List) {
                        jsonWriter.name(entry.getKey());
                        jsonWriter.beginArray();
                        ArrayList<Object> objects = (ArrayList<Object>) entry.getValue();
                        for(int i = 0 ; i < objects.size(); i++){
                                if (objects.get(i) instanceof String)
                                    jsonWriter.value((String) objects.get(i));
                                else if (objects.get(i) instanceof Double)
                                    jsonWriter.value((Double) objects.get(i));
                                else if (objects.get(i) instanceof Boolean)
                                    jsonWriter.value((Boolean) objects.get(i));
                       }
                        jsonWriter.endArray();
                    }
                    else if (entry.getValue() instanceof Entity) {
                        Entity entity = (Entity) entry.getValue();
                        jsonWriter.name(entry.getKey()).beginObject();
                        jsonWriter.name("id").value(entity.getId());
                        jsonWriter.name("name").value(entity.getTitle());
                        for (Map.Entry<String, Object> entry1 : entity.getEntityData().entrySet()) {
                            if (entry1.getValue() instanceof String)
                                jsonWriter.name(entry1.getKey()).value(entry1.getValue().toString());
                            else if (entry1.getValue() instanceof Double)
                                jsonWriter.name(entry1.getKey()).value((Double)entry1.getValue());
                            else if (entry.getValue() instanceof Boolean)
                                jsonWriter.name(entry1.getKey()).value((Boolean) entry.getValue());
                            else if (entry.getValue() instanceof List) {
                                jsonWriter.name(entry.getKey());
                                jsonWriter.beginArray();
                                ArrayList<Object> objects = (ArrayList<Object>) entry.getValue();
                                for(int i = 0 ; i < objects.size(); i++){
                                    if (objects.get(i) instanceof String)
                                        jsonWriter.value((String) objects.get(i));
                                    else if (objects.get(i) instanceof Double)
                                        jsonWriter.value((Double) objects.get(i));
                                    else if (objects.get(i) instanceof Boolean)
                                        jsonWriter.value((Boolean) objects.get(i));
                                }
                                jsonWriter.endArray();
                           }
                        }
                        jsonWriter.endObject();
                    }
                    //jsonWriter.endObject();
                }
                jsonWriter.endObject();
            }
            jsonWriter.endArray();
            b = true;
            jsonWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }*/
        return b;
    }

    //new database
    @Override
    public File createDatabase(String namePath) {
        try {
            if (namePath.contains(".json")) {
                File f = new File(namePath);
                f.createNewFile();
                return f;
            } else {
                System.out.println("Pogresna ekstenzija");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String fileToString(File file) throws IOException {

       /* StringBuilder contentBuilder = new StringBuilder();
        if(file == null) System.out.println("FILE JE NULL");
        try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contentBuilder.toString();*/
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