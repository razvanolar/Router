package code.router.components.map.chart_content;

import code.router.EventBus;
import code.router.events.routes_events.update_elevations_event.UpdateElevationsEvent;
import code.router.events.routes_events.update_elevations_event.UpdateElevationsEventHandler;
import code.router.utils.Controller;
import code.router.utils.View;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.shape.Rectangle;

/**
 * Created by razvanolar on 29.12.2016
 */
public class ChartContentController implements Controller<ChartContentController.IChartContentView> {

  public interface IChartContentView extends View {
    LineChart<Number, Number> getLineChart();
    NumberAxis getyAxis();
  }

  @Override
  public void bind(IChartContentView view) {
    EventBus.addHandler(UpdateElevationsEvent.TYPE, (UpdateElevationsEventHandler) event -> {
      view.getLineChart().getData().clear();
      XYChart.Series<Number, Number> series = new XYChart.Series<>();
      series.setName("Elevation");
      double[] elevations = event.getElevations();
      double minElevation = Double.MAX_VALUE;
      for (int i = 0; i < elevations.length; i ++) {
        if (elevations[i] < minElevation)
          minElevation = elevations[i];
        XYChart.Data<Number, Number> data = new XYChart.Data<>(i + 1, elevations[i]);
        Rectangle rectangle = new Rectangle(3, 3);
//        rectangle.setOnMouseEntered(event -> {
//          System.out.println("Mouse entered");
//          rectangle.setFill(Color.AZURE);
//        });
//        rectangle.setOnMouseExited(event -> {
//          rectangle.setFill(Color.BLACK);
//        });
        data.setNode(rectangle);
        series.getData().add(data);
      }
      view.getyAxis().setLowerBound(minElevation);
      view.getLineChart().getData().add(series);
    });
  }
}
