package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class GenerateTimeSeriesData {
    private static final String CSV_FILE_PATH = "/labhome/ccandan/iot_data.csv";
    private static final int ROW_COUNT = 10000;

    private static final int min = 100000; // Minimum 6-digit value
    private static final int SENDOR_ID_COUNT = 12;
    private static final int max = 999999; // Maximum 6-digit value

    public static void generateAndWriteCsvData() {

        try (FileWriter writer = new FileWriter(CSV_FILE_PATH)) {
            //Create header row
            writer.write("Timestamp,SensorID, Value\n");

            //Generate and write data rows
            Random random = new Random();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            for (int i = 0; i < ROW_COUNT; i++) {
                int assetId = random.nextInt(SENDOR_ID_COUNT) + 100000; // Generate 6-digit asset ID
                LocalDateTime timestamp = LocalDateTime.now().minusSeconds(random.nextInt(60 * 60 * 24 * 365)); // Random date within the past year
                double value = random.nextDouble();
                String row = timestamp.format(formatter) + ","+assetId+ ","+ value +"\n";
                writer.write(row);
            }
            writer.close(); // Ensure the CSV file is properly closed
            System.out.println("CSV file created successfully.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}