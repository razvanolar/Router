package code.router.components.map.chart_content;

import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * Created by razvanolar on 29.12.2016
 */
public class ChartContentView implements ChartContentController.IChartContentView {

  private BorderPane mainContainer;
  private LineChart<Number, Number> lineChart;
  private NumberAxis yAxis;

  public ChartContentView() {
    init();
  }

  private void init() {
    NumberAxis xAxis = new NumberAxis();
    yAxis = new NumberAxis();
    lineChart = new LineChart<>(xAxis, yAxis);
    mainContainer = new BorderPane(lineChart);
  }

  public LineChart<Number, Number> getLineChart() {
    return lineChart;
  }

  public NumberAxis getyAxis() {
    return yAxis;
  }

  @Override
  public Node asNode() {
    return mainContainer;
  }
}
