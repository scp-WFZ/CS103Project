import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONType;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CSV2JSON {
    public static Map<Integer, String> categoryMap;

    /**
     *
     * @param path: absolute csv map table path
     * @throws IOException: FileIOException
     * @apiNote unmapped: 31 (blue: turn round);
     */
    public static void initCategoryMap(String path) throws IOException {
        categoryMap = new HashMap<>();
        Files.lines((Paths.get(path)))
                .skip(1)
                .map(line -> line.split(","))
                .forEach(strings -> {
                    categoryMap.put(Integer.valueOf(strings[0]), strings[1]);
                });
    }

    public static Map<String, Figure> readCSV(String filename) throws IOException {
        Map<String, Figure> res = new LinkedHashMap<>();
        Files.lines(Paths.get(filename))
                .skip(1)
                .forEach(line -> {
                    String[] strs = line.split(",");
                    List<object> objects = new ArrayList<>();
                    BBOX bbox = new BBOX(Double.parseDouble(strs[3]), Double.parseDouble(strs[4]),
                            Double.parseDouble(strs[5]), Double.parseDouble(strs[6]));
                    String category = categoryMap.get(Integer.parseInt(strs[7]));
                    objects.add(new object(category, bbox));
                    String id = strs[0].replaceAll(".png", "");
                    Figure figure = new Figure("images/" + id + ".png", objects, id);
                    res.put(id, figure);
                });
        return res;

    }
    public static void main(String[] args) throws IOException {
        String csvPath = "src/main/resources/annotations.csv";
        String tablePath = "src/main/resources/categoryMapTable.csv";
        String resultPath = "src/main/resources/result.json";
        // generate category map
        initCategoryMap(tablePath);
        // generate JSONOject
        Map<String, Figure> map = readCSV(csvPath);
        // write to result.json
        try {
            FileWriter fw = new FileWriter(resultPath);
            PrintWriter out = new PrintWriter(fw);
            out.write(JSON.toJSONString(map, true));
            out.println();
            fw.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

@JSONType(orders = {"path","objects","id"})
class Figure {
    private String path;
    private List<object> objects;
    private String id;

    public String getPath() {
        return path;
    }

    public List<object> getObjects() {
        return objects;
    }

    public String getId() {
        return id;
    }

    public Figure(String path, List<object> objects, String id) {
        this.path = path;
        this.objects = objects;
        this.id = id;
    }
}

@JSONType(orders = {"category","bbox"})
class object{
    private String category;
    private BBOX bbox;

    public String getCategory() {
        return category;
    }

    public BBOX getBbox() {
        return bbox;
    }

    public object(String category, BBOX bbox) {
        this.category = category;
        this.bbox = bbox;
    }
}

@JSONType(orders = {"xmin","ymin","xmax","ymax"})
class BBOX{
    private double xmin;
    private double ymin;
    private double xmax;
    private double ymax;

    public double getXmin() {
        return xmin;
    }

    public double getYmin() {
        return ymin;
    }

    public double getXmax() {
        return xmax;
    }

    public double getYmax() {
        return ymax;
    }

    public BBOX(double xmin, double ymin, double xmax, double ymax) {
        this.xmin = xmin;
        this.ymin = ymin;
        this.xmax = xmax;
        this.ymax = ymax;
    }
}