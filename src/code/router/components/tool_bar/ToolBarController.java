package code.router.components.tool_bar;

import code.router.EventBus;
import code.router.events.map_settings_change_event.MapSettingsChangeEvent;
import code.router.events.mask_unmask_window_event.MaskWindowEvent;
import code.router.events.mask_unmask_window_event.UnmaskWindowEvent;
import code.router.events.routes_events.find_route_event.FindRouteEvent;
import code.router.events.routes_events.next_route_event.NextRouteEvent;
import code.router.events.routes_events.previous_route_event.PreviousRouteEvent;
import code.router.model.MapSettings;
import code.router.utils.Controller;
import code.router.utils.View;
import code.router.utils.types.MarkerTypes;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

/**
 * Created by razvanolar on 29.12.2016
 */
public class ToolBarController implements Controller<ToolBarController.IToolBarView> {

  public interface IToolBarView extends View {
    Button getNewRouteButton();
    ToggleButton getStartMarkerButton();
    ToggleButton getEndMarkerButton();
    ToggleButton getIntermediateMarkerButton();
    ToggleButton getElevationMarkerButton();
    ToggleGroup getToggleGroup();
    Button getFindRouteButton();
    Button getPrevRouteButton();
    Button getNextRouteButton();
  }

  private MapSettings mapSettingsModel;

  @Override
  public void bind(IToolBarView view) {
    mapSettingsModel = new MapSettings();
    MapSettingsChangeEvent mapSettingsChangeEvent = new MapSettingsChangeEvent(mapSettingsModel);

//    view.getNewRouteButton().setOnAction(event -> EventBus.fireEvent(new NewRouteViewEvent()));
    view.getNewRouteButton().setOnAction(event -> {
      EventBus.fireEvent(new MaskWindowEvent());
      runThread();
    });

    view.getToggleGroup().selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue == view.getStartMarkerButton())
        mapSettingsModel.setMarkerType(MarkerTypes.START);
      else if (newValue == view.getEndMarkerButton())
        mapSettingsModel.setMarkerType(MarkerTypes.END);
      else if (newValue == view.getIntermediateMarkerButton())
        mapSettingsModel.setMarkerType(MarkerTypes.INTERMEDIATE);
      else if (newValue == view.getElevationMarkerButton())
        mapSettingsModel.setMarkerType(MarkerTypes.ELEVATION);
      else
        mapSettingsModel.setMarkerType(MarkerTypes.NONE);

      EventBus.fireEvent(mapSettingsChangeEvent);
    });

    view.getFindRouteButton().setOnAction(event -> EventBus.fireEvent(new FindRouteEvent()));

    view.getPrevRouteButton().setOnAction(event -> EventBus.fireEvent(new PreviousRouteEvent()));

    view.getNextRouteButton().setOnAction(event -> EventBus.fireEvent(new NextRouteEvent()));
  }

  private void runThread() {
    Thread thread = new Thread(() -> {
      int n = 1000000;
      for (int i = 0; i < n; i ++) {
        double a = Math.sqrt(i) * Math.pow(i, 1.5) * Math.log(i);
        a = Math.sqrt(i) * Math.pow(i, 1.5) * Math.log(i) - Math.sin(i) + Math.cos(i) * Math.tan(i);
        a = Math.sqrt(i) * Math.pow(i, 1.5) * Math.log(i) - Math.sin(i) + Math.cos(i) * Math.tan(i);
        a = Math.sqrt(i) * Math.pow(i, 1.5) * Math.log(i) - Math.sin(i) + Math.cos(i) * Math.tan(i);
        a = Math.sqrt(i) * Math.pow(i, 1.5) * Math.log(i) - Math.sin(i) + Math.cos(i) * Math.tan(i);
      }
      Platform.runLater(() -> EventBus.fireEvent(new UnmaskWindowEvent()));
    });

    thread.start();
  }
}
