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
        UnivariateFunction function = new PolynomialFunction(new double[] {1, 2, 1});

        DescriptiveStatistics stats = new DescriptiveStatistics();
        for (int x = 0; x <= 100; x++) {
            stats.addValue(x);
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (double x : stats.getValues()) {
            double y = function.value(x);
            dataset.addValue(y, "Function", Double.toString(x));
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Function Plot", "x", "y", dataset,
                PlotOrientation.VERTICAL, true, true, false);

        File chartFile = new File("ApachePlottedGraph.png");
        ChartUtilities.saveChartAsPNG(chartFile, chart, 800, 600);

        File dataFile = new File("ApachePlottedData.csv");
        FileWriter writer = new FileWriter(dataFile);
        for (double x : stats.getValues()) {
            double y = function.value(x);
            writer.write(Double.toString(x) + "," + Double.toString(y) + "\n");
        }
        writer.close();
    }
}