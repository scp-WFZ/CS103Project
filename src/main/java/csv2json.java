import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class csv2json {
    class Figure {
        String path;
        int width;
        int height;
        int x1;
        int y1;
        int x2;
        int y2;
        int category;

        public Figure(String path, int width, int height, int x1, int y1, int x2, int y2, int category) {
            this.path = path;
            this.width = width;
            this.height = height;
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.category = category;
        }
    }

    public static Stream<String> readCities(String filename) throws IOException
    {
        return Files.lines(Paths.get(filename));
//                .map(l -> l.split(", "))
//                .map(a -> new City(a[0], a[1], Integer.parseInt(a[2])));
    }
    public static void main(String[] args) {
        JSONObject jsonObject = new JSONObject();
    }
}
