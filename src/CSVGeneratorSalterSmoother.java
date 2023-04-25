import java.io.*;
import java.util.*;

public class CSVGeneratorSalterSmoother {
    public static void main(String[] args) {
        String originalFilename = "original.csv";
        String saltedFilename = "salted.csv";
        String smoothedFilename = "smoothed.csv";
        generateOriginalCSV(originalFilename);
        saltCSV(originalFilename, saltedFilename);
        smoothCSV(saltedFilename, smoothedFilename, 3);
    }

    public static void generateOriginalCSV(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            for (int i = 1; i <= 100; i++) {
                int x = i;
                int y = x * x + 2 * x + 1;
                writer.append(x + "," + y + "\n");
            }
            writer.flush();
            writer.close();
            System.out.println("Successfully generated data in " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while generating data in " + filename);
            e.printStackTrace();
        }
    }

    public static void saltCSV(String originalFilename, String saltedFilename) {
        Random rand = new Random();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(originalFilename));
            FileWriter writer = new FileWriter(saltedFilename);
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                int x = Integer.parseInt(values[0]);
                int y = Integer.parseInt(values[1]);
                int saltedY = y + rand.nextInt(101) - 50;
                writer.append(x + "," + saltedY + "\n");
            }
            reader.close();
            writer.flush();
            writer.close();
            System.out.println("Successfully salted data in " + saltedFilename);
        } catch (IOException e) {
            System.out.println("An error occurred while salting data in " + saltedFilename);
            e.printStackTrace();
        }
    }

    public static void smoothCSV(String saltedFilename, String smoothedFilename, int windowValue) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(saltedFilename));
            FileWriter writer = new FileWriter(smoothedFilename);
            String line = "";
            List<Integer> yValues = new ArrayList<Integer>();
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                int x = Integer.parseInt(values[0]);
                int y = Integer.parseInt(values[1]);
                yValues.add(y);
            }
            reader.close();
            for (int i = 0; i < yValues.size(); i++) {
                int sum = 0;
                int count = 0;
                for (int j = Math.max(0, i - windowValue); j <= Math.min(yValues.size() - 1, i + windowValue); j++) {
                    sum += yValues.get(j);
                    count++;
                }
                int smoothedY = sum / count;
                writer.append((i+1) + "," + smoothedY + "\n");
            }
            writer.flush();
            writer.close();
            System.out.println("Successfully smoothed data in " + smoothedFilename);
        } catch (IOException e) {
            System.out.println("An error occurred while smoothing data in " + smoothedFilename);
            e.printStackTrace();
        }
    }
}