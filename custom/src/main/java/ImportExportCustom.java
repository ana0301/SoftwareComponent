import exceptions.UnsupportedImplementation;
import importExport.ImportExportManager;
import importExport.ImportExportService;
import model.Entity;
import java.io.*;
import java.util.*;

public class ImportExportCustom extends ImportExportService {
    static {
        ImportExportManager.registerImportExportService(new ImportExportCustom());
    }
    public List<Entity> loadDatabase(File file) throws IOException, UnsupportedImplementation {
        if(!file.getAbsolutePath().endsWith(".txt")) throw new UnsupportedImplementation("TXT");
        List<Entity> entities = new ArrayList<Entity>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = "";
        try {
            line = reader.readLine();
            while(!line.equals(".")) {
                if (line.equals("<")) {
                    Entity entity = new Entity();
                    line = reader.readLine();
                    while (!line.equals(">")) {
                        List<String> keyValue = Arrays.asList(line.split("->"));
                        System.out.println("----------------------------"+keyValue.get(0) + " " + keyValue.get(1));
                        if (keyValue.get(0).equals("id")) {
                            System.err.println("regular " + keyValue.get(1));
                            entity.setId(keyValue.get(1));
                            System.out.println("usao u id");
                        } else if (keyValue.get(0).equals("title")) {
                            entity.setTitle(keyValue.get(1));
                            System.out.println("usao u title");
                        } else if (keyValue.get(0).equals("entityData")) {
                            System.out.println("usao u entity Data");
                            Map<String, Object> entityData = new HashMap<String, Object>();
                            line = reader.readLine();
                            while (!line.equals("#")) {//ne zavrsi ta hes mapa
                                if (!line.equals(">")) {
                                    List<String> kVV = Arrays.asList(line.split("->"));
                                    //ugnjezdeni entitet
                                    System.err.println(kVV.get(1));
                                    if (kVV.get(1).equals("<")) {
                                        Entity nestedEntity = new Entity();
                                        line = reader.readLine();
                                        while (!line.equals(">")) {
                                            if (!line.equals("#")) {
                                                List<String> kVnested = Arrays.asList(line.split("->"));
                                                if (kVnested.get(0).equals("id")) {
                                                    System.err.println(kVnested.get(1));
                                                    nestedEntity.setId(kVnested.get(1));
                                                    System.out.println(" usao u nested id");
                                                } else if (kVnested.get(0).equals("title")) {
                                                    nestedEntity.setTitle(kVnested.get(1));
                                                    System.out.println(" usao u nested title");
                                                } else if (kVnested.get(0).equals("entityData")) {
                                                    System.out.println(" usao u nested nested entity data");
                                                    //line = reader.readLine();
                                                    while (!line.equals("#")) {
                                                        System.out.println(" usao u nested datas");
                                                        List<String> kVnestedd = Arrays.asList(line.split("->"));
                                                        nestedEntity.addEntityData(kVnestedd.get(0), (String)kVnestedd.get(1));
                                                        line = reader.readLine();
                                                    }
                                                }
                                            }
                                            line = reader.readLine();
                                        }
                                       entityData.put(kVV.get(0),nestedEntity);
                                    } else {
                                        //property
                                        System.out.println(" usao u regular entitydata");
                                        entityData.put(kVV.get(0), (String) kVV.get(1));
                                    }
                                }
                                line = reader.readLine();
                            }
                            entity.setEntityData(entityData);
                        }
                        line = reader.readLine();
                    }
                    entities.add(entity);
                }
                else System.err.println("NEVALIDAN UNOS");
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }
        //return stringBuilder.toString();
        return entities;
    }

    public boolean saveDatabase(File file, List<Entity> database) throws IOException {
        boolean correct = false;
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        try{
            for (Entity entity: database) {
                writer.write("<\n");
                writer.write("id->" + entity.getId()+"\n");
                writer.write("title->" + entity.getTitle()+"\n");
                if(!entity.getEntityData().isEmpty()) {
                    writer.write("entityData->#\n");
                    for(Map.Entry<String,Object> property : entity.getEntityData().entrySet()){
                        if(property.getValue() instanceof String){
                            writer.write(property.getKey()+"->"+((String) property.getValue())+"\n");
                        }else{
                            Entity e = (Entity) property.getValue();
                            writer.write(property.getKey()+"-><\n");
                            writer.write("id->"+e.getId()+"\n");
                            writer.write("title->"+e.getTitle()+"\n");
                            if(!entity.getEntityData().isEmpty()) {
                                writer.write("entityData->#\n");
                                for (Map.Entry<String, Object> nestedProp : e.getEntityData().entrySet()) {
                                    writer.write(nestedProp.getKey()+"->"+((String) nestedProp.getValue())+"\n");
                                }
                                writer.write("#\n");
                            }
                            writer.write(">\n");
                        }
                    }
                    writer.write("#\n");
                }
                writer.write(">\n");
            }
            writer.write(".");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            writer.close();
        }
        return correct;
    }

    public File createDatabase(String namePath) {
        try {
            File f = new File(namePath+".txt");
            f.createNewFile();
            return f;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
