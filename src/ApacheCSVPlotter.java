import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class ApacheCSVPlotter {

    public static void main(String[] args) throws IOException {
        // Define the function to plot
        UnivariateFunction function = new PolynomialFunction(new double[] {1, 2, 1});

        // Define the range of x values
        DescriptiveStatistics stats = new DescriptiveStatistics();
        for (int x = 0; x <= 100; x++) {
            stats.addValue(x);
        }

        // Create a dataset for the function values
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (double x : stats.getValues()) {
            double y = function.value(x);
            dataset.addValue(y, "Function", Double.toString(x));
        }

        // Create a chart from the dataset
        JFreeChart chart = ChartFactory.createLineChart(
                "Function Plot", "x", "y", dataset,
                PlotOrientation.VERTICAL, true, true, false);

        // Save the chart to an image file
        File chartFile = new File("function.png");
        ChartUtilities.saveChartAsPNG(chartFile, chart, 800, 600);

        // Write the function values to a CSV file
        File dataFile = new File("function.csv");
        FileWriter writer = new FileWriter(dataFile);
        for (double x : stats.getValues()) {
            double y = function.value(x);
            writer.write(Double.toString(x) + "," + Double.toString(y) + "\n");
        }
        writer.close();
    }
}