import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVAnalyticsApp {
    public static void main(String[] args) {
        // Read CSV file into a 2-dimensional array or ArrayList
        List<List<Double>> data = readCSV("dataset3.csv");

        // Create an instance of CSVAnalyticsService
        CSVAnalyticsService analyticsService = new CSVAnalyticsService(data);

        // Perform analytics for each row
        analyticsService.computeRowAnalytics();

        // Perform analytics for each column
        analyticsService.computeColumnAnalytics();

        // Perform analytics for the entire dataset
        analyticsService.computeTotalAnalytics();

        // Re-print all analytics for column 1
        List<Double> column1Data = new ArrayList<>();
        for (List<Double> row : data) {
            column1Data.add(row.get(0));
        }
        analyticsService.computeSingleColumnAnalytics(column1Data, 1);

        // Transpose the 2-dimensional array
        System.out.println("2 DIMENSIONAL ARRAY...");
        List<List<Double>> transposedData = analyticsService.transposeData();

        // Recompute all analytics for the transposed array
        CSVAnalyticsService transposedAnalyticsService = new CSVAnalyticsService(transposedData);
        transposedAnalyticsService.computeRowAnalytics();
        transposedAnalyticsService.computeColumnAnalytics();
        transposedAnalyticsService.computeTotalAnalytics();
        transposedAnalyticsService.computeSingleColumnAnalytics(transposedData.get(0), 1);
    }


    // Method to read CSV file and store data into a 2-dimensional ArrayList
    private static List<List<Double>> readCSV(String filename) {
        List<List<Double>> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                List<Double> row = new ArrayList<>();
                for (String value : values) {
                    if (value.isEmpty()) {
                        row.add(null);
                    } else {
                        row.add(Double.parseDouble(value));
                    }
                }
                data.add(row);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
