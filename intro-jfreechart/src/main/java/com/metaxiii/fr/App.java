package com.metaxiii.fr;

import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class App {

  private static final String SALES = "Sales";
  private static final String JANUARY = "January";
  private static final String FEBRUARY = "February";
  private static final String MARCH = "March";
  private static final String APRIL = "April";
  private static final String MAY = "May";
  private static final String MONTHLY_SALES = "Monthly Sales";
  private static final String MONTH = "Month";
  private static final String PROFIT = "Profit";

  public static void main(final String[] args) {
    final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    dataset.addValue(200, SALES, JANUARY);
    dataset.addValue(150, SALES, FEBRUARY);
    dataset.addValue(180, SALES, MARCH);
    dataset.addValue(260, SALES, APRIL);
    dataset.addValue(300, SALES, MAY);
    getBasicLineChart(dataset);
    getBarChart(dataset);
    getPieChart();
    getTimeSeriesChart();
    getCombinationChart();
  }

  private static void getBasicLineChart(final DefaultCategoryDataset dataset) {
    final JFreeChart chart = ChartFactory.createLineChart(MONTHLY_SALES, MONTH, SALES, dataset);
    renderPanel(chart);
  }

  private static void getBarChart(final DefaultCategoryDataset dataset) {
    final JFreeChart chart = ChartFactory.createBarChart(MONTHLY_SALES, MONTH, SALES, dataset);
    renderPanel(chart);
  }

  private static void getPieChart() {
    final var dataset = new DefaultPieDataset<String>();
    dataset.setValue(JANUARY, 200);
    dataset.setValue(FEBRUARY, 150);
    dataset.setValue(MARCH, 180);
    final JFreeChart chart = ChartFactory.createPieChart(MONTHLY_SALES, dataset, true, true, false);
    renderPanel(chart);
  }

  private static void getTimeSeriesChart() {
    final TimeSeries series = new TimeSeries(MONTHLY_SALES);
    series.add(new Month(1, 2024), 200);
    series.add(new Month(2, 2024), 150);
    series.add(new Month(3, 2024), 180);

    final TimeSeriesCollection dataset = new TimeSeriesCollection();
    dataset.addSeries(series);

    final JFreeChart chart = ChartFactory.createTimeSeriesChart(
      MONTHLY_SALES,
      "Date",
      SALES,
      dataset,
      true,
      false,
      false
    );
    renderPanel(chart);
  }

  private static void getCombinationChart() {
    final DefaultCategoryDataset lineDataset = new DefaultCategoryDataset();
    lineDataset.addValue(200, SALES, JANUARY);
    lineDataset.addValue(150, SALES, FEBRUARY);
    lineDataset.addValue(180, SALES, MARCH);

    final DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
    barDataset.addValue(400, PROFIT, JANUARY);
    barDataset.addValue(300, PROFIT, FEBRUARY);
    barDataset.addValue(250, PROFIT, MARCH);

    final CategoryPlot plot = new CategoryPlot();
    plot.setDataset(0, lineDataset);
    plot.setRenderer(0, new LineAndShapeRenderer());

    plot.setDataset(1, barDataset);
    plot.setRenderer(1, new BarRenderer());

    plot.setDomainAxis(new CategoryAxis(MONTH));
    plot.setRangeAxis(new NumberAxis("Value"));

    plot.setOrientation(PlotOrientation.VERTICAL);
    plot.setRangeGridlinesVisible(true);
    plot.setDomainGridlinesVisible(true);
    final JFreeChart chart = new JFreeChart("Monthly Sales and Profit", null, plot, true);
    renderPanel(chart);
  }

  private static void renderPanel(final JFreeChart chart) {
    final ChartPanel chartPanel = new ChartPanel(chart);
    final JFrame frame = new JFrame();
    frame.setSize(800, 600);
    frame.setContentPane(chartPanel);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}
