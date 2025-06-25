
package maps;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MapLoader {

    public static int[][] loadMap(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int rows = 0;
            int cols = 0;

            String line;
            while ((line = reader.readLine()) != null) {
                rows++;
                cols = Math.max(cols, line.length());
            }

            int[][] map = new int[rows][cols];
            reader.close();

            try (BufferedReader readerAgain = new BufferedReader(new FileReader(filePath))) {
                int row = 0;
                while ((line = readerAgain.readLine()) != null) {
                    for (int col = 0; col < line.length(); col++) {
                        char symbol = line.charAt(col);
                        if (symbol == '#') {
                            map[row][col] = 1;
                        } else if (symbol == '.') {
                            map[row][col] = 0;
                        } else {
                            throw new IllegalArgumentException("Caractere inválido no mapa: " + symbol);
                        }
                    }
                    row++;
                }
            }

            return map;
        }
    }
}
