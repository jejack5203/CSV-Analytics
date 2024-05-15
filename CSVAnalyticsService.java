import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CSVAnalyticsService {
    private List<List<Double>> data;

    public CSVAnalyticsService(List<List<Double>> data) {
        this.data = data;
    }

    // Method to compute analytics for each row
    public void computeRowAnalytics() {
        for (List<Double> row : data) {
            int count = 0;
            double sum = 0;
            double min = Double.MAX_VALUE;
            double max = Double.MIN_VALUE;
            List<Double> nonNullValues = new ArrayList<>();

            // Loop through each value in the row
            for (Double value : row) {
                if (value != null) {
                    count++;
                    sum += value;
                    nonNullValues.add(value);
                    if (value < min) min = value;
                    if (value > max) max = value;
                }
            }

            if (count == 0) {
                System.out.println("Row is empty.");
                continue;
            }

            double average = sum / count;

            Collections.sort(nonNullValues);
            double median;
            if (count % 2 == 0) {
                median = (nonNullValues.get(count / 2) + nonNullValues.get(count / 2 - 1)) / 2;
            } else {
                median = nonNullValues.get(count / 2);
            }

            Map<Double, Integer> frequencyMap = new HashMap<>();
            for (Double value : nonNullValues) {
                frequencyMap.put(value, frequencyMap.getOrDefault(value, 0) + 1);
            }

            int maxFrequency = Collections.max(frequencyMap.values());
            List<Double> modes = new ArrayList<>();
            for (Map.Entry<Double, Integer> entry : frequencyMap.entrySet()) {
                if (entry.getValue() == maxFrequency) {
                    modes.add(entry.getKey());
                }
            }

            double range = max - min;

            double variance = 0;
            for (Double value : nonNullValues) {
                variance += Math.pow(value - average, 2);
            }
            double standardDeviation = Math.sqrt(variance / count);

            System.out.println("Row Analytics:");
            System.out.println("Count: " + count);
            System.out.println("Sum: " + sum);
            System.out.println("Average: " + average);
            System.out.println("Median: " + median);
            System.out.println("Modes: " + modes);
            System.out.println("Min: " + min);
            System.out.println("Max: " + max);
            System.out.println("Range: " + range);
            System.out.println("Standard Deviation: " + standardDeviation);
            System.out.println();
        }
    }

    // Method to compute analytics for each column
    public void computeColumnAnalytics() {
        int numColumns = data.get(0).size();
        for (int col = 0; col < numColumns; col++) {
            List<Double> columnData = new ArrayList<>();
            for (List<Double> row : data) {
                if (col < row.size() && row.get(col) != null) {
                    columnData.add(row.get(col));
                }
            }
            computeSingleColumnAnalytics(columnData, col + 1);
        }
    }

    // Method to compute analytics for the entire dataset
    public void computeTotalAnalytics() {
        List<Double> allValues = new ArrayList<>();
        for (List<Double> row : data) {
            for (Double value : row) {
                if (value != null) {
                    allValues.add(value);
                }
            }
        }
        computeSingleColumnAnalytics(allValues, 0);
    }

    // Method to transpose the 2 dimensional array
    public List<List<Double>> transposeData() {
        int numRows = data.size();
        int numCols = data.get(0).size();
        List<List<Double>> transposedData = new ArrayList<>();
        for (int col = 0; col < numCols; col++) {
            List<Double> transposedRow = new ArrayList<>();
            for (int row = 0; row < numRows; row++) {
                if (col < data.get(row).size()) {
                    transposedRow.add(data.get(row).get(col));
                } else {
                    transposedRow.add(null);
                }
            }
            transposedData.add(transposedRow);
        }
        return transposedData;
    }

    // Method to compute analytics for a single column
    public void computeSingleColumnAnalytics(List<Double> columnData, int columnNumber) {
        int count = columnData.size();
        double sum = 0;
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        List<Double> nonNullValues = new ArrayList<>();

        for (Double value : columnData) {
        if (value != null) { // Check for null values
            sum += value;
            nonNullValues.add(value);
            if (value < min) min = value;
            if (value > max) max = value;
        }
    }


        if (count == 0) {
            System.out.println("Column " + columnNumber + " is empty.");
            return;
        }

        double average = sum / count;

        Collections.sort(nonNullValues);
        double median;
        if (count % 2 == 0) {
            median = (nonNullValues.get(count / 2) + nonNullValues.get(count / 2 - 1)) / 2;
        } else {
            median = nonNullValues.get(count / 2);
        }

        Map<Double, Integer> frequencyMap = new HashMap<>();
        for (Double value : nonNullValues) {
            frequencyMap.put(value, frequencyMap.getOrDefault(value, 0) + 1);
        }

        int maxFrequency = Collections.max(frequencyMap.values());
        List<Double> modes = new ArrayList<>();
        for (Map.Entry<Double, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() == maxFrequency) {
                modes.add(entry.getKey());
            }
        }

        double range = max - min;

        double variance = 0;
        for (Double value : nonNullValues) {
            variance += Math.pow(value - average, 2);
        }
        double standardDeviation = Math.sqrt(variance / count);

        // Output column analytics
        System.out.println("Column " + columnNumber + " Analytics:");
        System.out.println("Count: " + count);
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + average);
        System.out.println("Median: " + median);
        System.out.println("Modes: " + modes);
        System.out.println("Min: " + min);
        System.out.println("Max: " + max);
        System.out.println("Range: " + range);
        System.out.println("Standard Deviation: " + standardDeviation);
        System.out.println();
    }
}
