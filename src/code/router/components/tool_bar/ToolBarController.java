package code.router.components.tool_bar;

import code.router.EventBus;
import code.router.events.map_settings_change_event.MapSettingsChangeEvent;
import code.router.events.routes_events.find_route_event.FindRouteEvent;
import code.router.events.routes_events.next_route_event.NextRouteEvent;
import code.router.events.routes_events.open_route_event.OpenRouteEvent;
import code.router.events.routes_events.previous_route_event.PreviousRouteEvent;
import code.router.events.routes_events.save_route_events.GenericSaveRouteEvent;
import code.router.events.show_new_route_dialog_event.ShowNewRouteDialogEvent;
import code.router.model.routes.MapSettings;
import code.router.utils.Controller;
import code.router.utils.View;
import code.router.utils.types.MarkerTypes;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

/**
 * Created by razvanolar on 29.12.2016
 */
public class ToolBarController implements Controller<ToolBarController.IToolBarView> {

  public interface IToolBarView extends View {
    Button getNewRouteButton();
    Button getOpenButton();
    Button getSaveRouteButton();
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

    view.getNewRouteButton().setOnAction(event -> EventBus.fireEvent(new ShowNewRouteDialogEvent()));

    view.getOpenButton().setOnAction(event -> EventBus.fireEvent(new OpenRouteEvent()));

    view.getSaveRouteButton().setOnAction(event -> EventBus.fireEvent(new GenericSaveRouteEvent()));

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
}
