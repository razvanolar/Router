package code.router.components.map.chart_content;

import code.router.EventBus;
import code.router.events.markers_events.show_elevation_marker_event.ShowElevationMarkerEvent;
import code.router.events.routes_events.update_elevations_event.UpdateElevationsEvent;
import code.router.events.routes_events.update_elevations_event.UpdateElevationsEventHandler;
import code.router.model.Elevation;
import code.router.utils.Controller;
import code.router.utils.View;
import javafx.event.EventHandler;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by razvanolar on 29.12.2016
 */
public class ChartContentController implements Controller<ChartContentController.IChartContentView> {

  private EventHandler<MouseEvent> mouseEnteredEvent;
  private EventHandler<MouseEvent> mouseExitedEvent;
  private EventHandler<MouseEvent> mouseClickedEvent;

  public interface IChartContentView extends View {
    LineChart<Number, Number> getLineChart();
    NumberAxis getyAxis();
  }

  private IChartContentView view;

  @Override
  public void bind(IChartContentView view) {
    this.view = view;

    setupCustomTooltipBehavior(100, 10000, 200);

    mouseEnteredEvent = mouseEvent -> {
      if (mouseEvent.getSource() instanceof Shape) {
        Shape shape = (Shape) mouseEvent.getSource();
        shape.setFill(Color.YELLOWGREEN);
      }
    };

    mouseExitedEvent = mouseEvent -> {
      if (mouseEvent.getSource() instanceof Shape) {
        Shape shape = (Shape) mouseEvent.getSource();
        shape.setFill(Color.DARKBLUE);
      }
    };

    mouseClickedEvent = event -> {
      if (event.getSource() instanceof Shape) {
        Shape shape = (Shape) event.getSource();
        if (shape.getUserData() != null && shape.getUserData() instanceof Elevation) {
          Elevation elevation = (Elevation) shape.getUserData();
          EventBus.fireEvent(new ShowElevationMarkerEvent(elevation));
        }
      }
    };

    EventBus.addHandler(UpdateElevationsEvent.TYPE, (UpdateElevationsEventHandler) event -> {

    });
  }

  public void updateElevationChart(UpdateElevationsEvent event) {
    view.getLineChart().getData().clear();
    XYChart.Series<Number, Number> series = new XYChart.Series<>();
    series.setName("Elevation");
    List<Elevation> elevations = event.getElevations();
    double minElevation = Double.MAX_VALUE;
    double maxElevation = Double.MIN_VALUE;
    for (int i = 0; i < elevations.size(); i ++) {
      Elevation elevation = elevations.get(i);
      if (elevation.getElevation() < minElevation)
        minElevation = elevation.getElevation();
      if (maxElevation > elevation.getElevation())
        maxElevation = elevation.getElevation();
      XYChart.Data<Number, Number> data = new XYChart.Data<>(i + 1, elevation.getElevation());
      Circle circle = new Circle(3);
      circle.setFill(Color.DARKBLUE);
      circle.setOnMouseEntered(mouseEnteredEvent);
      circle.setOnMouseExited(mouseExitedEvent);
      circle.setOnMouseClicked(mouseClickedEvent);
      data.setNode(circle);
      series.getData().add(data);

      Tooltip tooltip = new Tooltip(elevation.getElevation() + " m\nLat: " + elevation.getLatitude() + "\nLng: " + elevation.getLongitude());
      Tooltip.install(circle, tooltip);
      circle.setUserData(elevation);
    }

    view.getLineChart().getData().add(series);
  }

  public void clearChartData() {
    this.view.getLineChart().getData().clear();
  }

  /**
   * <p>
   * Tooltip behavior is controlled by a private class javafx.scene.control.Tooltip$TooltipBehavior.
   * All Tooltips share the same TooltipBehavior instance via a static private member BEHAVIOR, which
   * has default values of 1sec for opening, 5secs visible, and 200 ms close delay (if mouse exits from node before 5secs).
   *
   * The hack below constructs a custom instance of TooltipBehavior and replaces private member BEHAVIOR with
   * this custom instance.
   * </p>
   *
   */
  @SuppressWarnings("unchecked")
  private void setupCustomTooltipBehavior(int openDelayInMillis, int visibleDurationInMillis, int closeDelayInMillis) {
    try {

      Class TTBehaviourClass = null;
      Class<?>[] declaredClasses = Tooltip.class.getDeclaredClasses();
      for (Class c:declaredClasses) {
        if (c.getCanonicalName().equals("javafx.scene.control.Tooltip.TooltipBehavior")) {
          TTBehaviourClass = c;
          break;
        }
      }
      if (TTBehaviourClass == null) {
        // abort
        return;
      }
      Constructor constructor = TTBehaviourClass.getDeclaredConstructor(
              Duration.class, Duration.class, Duration.class, boolean.class);
      if (constructor == null) {
        // abort
        return;
      }
      constructor.setAccessible(true);
      Object newTTBehaviour = constructor.newInstance(
              new Duration(openDelayInMillis), new Duration(visibleDurationInMillis),
              new Duration(closeDelayInMillis), false);
      Field ttbehaviourField = Tooltip.class.getDeclaredField("BEHAVIOR");
      if (ttbehaviourField == null) {
        // abort
        return;
      }
      ttbehaviourField.setAccessible(true);

      // Cache the default behavior if needed.
//      Object defaultTTBehavior = ttbehaviourField.get(Tooltip.class);
      ttbehaviourField.set(Tooltip.class, newTTBehaviour);

    } catch (Exception e) {
      System.out.println("Aborted setup due to error:" + e.getMessage());
    }
  }
}
