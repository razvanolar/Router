package code.router.components.map.map_context_menu;

import code.router.EventBus;
import code.router.events.clear_all_from_map_event.ClearAllFromMapEvent;
import code.router.events.markers_events.clear_markers_events.clear_all_markers_event.ClearAllMarkersEvent;
import code.router.events.markers_events.clear_markers_events.clear_elevation_markers_event.ClearElevationMarkersEvent;
import code.router.events.markers_events.clear_markers_events.clear_end_marker_event.ClearEndMarkerEvent;
import code.router.events.markers_events.clear_markers_events.clear_intermediate_markers_event.ClearIntermediateMarkersEvent;
import code.router.events.markers_events.clear_markers_events.clear_start_marker_event.ClearStartMarkerEvent;
import code.router.events.map_direction_change_event.MapDirectionChangedEvent;
import code.router.events.markers_events.show_info_marker_event.ShowInfoMarkerEvent;
import code.router.events.routes_events.find_route_event.FindRouteEvent;
import code.router.events.routes_events.find_route_for_last_2_elevation_markers_event.FindRouteForLast2ElevationMarkersEvent;
import code.router.events.routes_events.find_route_in_new_window_event.FindRouteInNewWindowEvent;
import code.router.utils.Controller;
import code.router.utils.View;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

/**
 * Created by razvanolar on 05.01.2017
 */
public class MapContextMenuController implements Controller<MapContextMenuController.IMapContextMenuView> {

  public interface IMapContextMenuView extends View {
    ContextMenu getContextMenu();
    MenuItem getHorizontalDirectionMenuItem();
    MenuItem getVerticalDirectionMenuItem();
    MenuItem getClearStartMarkerMenuItem();
    MenuItem getClearEndMarkerMenuItem();
    MenuItem getClearIntermediateMarkersMenuItem();
    MenuItem getClearElevationMarkersMenuItem();
    MenuItem getClearAllMarkersMenuItem();
    MenuItem getFindRouteForLastTwoElevationMarkersMenuItem();
    MenuItem getFindRouteForStartAndEndMarkersMenuItem();
    MenuItem getClearAllFromMapMenuItem();
    MenuItem getShowInfoMarkerMenuItem();
    CheckBox getOpenInNewTabCheckBox();
  }

  @Override
  public void bind(IMapContextMenuView view) {
    view.getHorizontalDirectionMenuItem().setOnAction(event -> EventBus.fireEvent(new MapDirectionChangedEvent(true)));

    view.getVerticalDirectionMenuItem().setOnAction(event -> EventBus.fireEvent(new MapDirectionChangedEvent(false)));

    view.getClearStartMarkerMenuItem().setOnAction(event -> EventBus.fireEvent(new ClearStartMarkerEvent()));

    view.getClearEndMarkerMenuItem().setOnAction(event -> EventBus.fireEvent(new ClearEndMarkerEvent()));

    view.getClearIntermediateMarkersMenuItem().setOnAction(event -> EventBus.fireEvent(new ClearIntermediateMarkersEvent()));

    view.getClearElevationMarkersMenuItem().setOnAction(event -> EventBus.fireEvent(new ClearElevationMarkersEvent()));

    view.getClearAllMarkersMenuItem().setOnAction(event -> EventBus.fireEvent(new ClearAllMarkersEvent()));

    view.getFindRouteForStartAndEndMarkersMenuItem().setOnAction(event -> {
      if (view.getOpenInNewTabCheckBox().isSelected()) {
        EventBus.fireEvent(new FindRouteInNewWindowEvent());
      } else {
        EventBus.fireEvent(new FindRouteEvent());
      }
    });

    view.getFindRouteForLastTwoElevationMarkersMenuItem().setOnAction(event -> {
      if (view.getOpenInNewTabCheckBox().isSelected()) {
        EventBus.fireEvent(new FindRouteForLast2ElevationMarkersEvent(true));
      } else {
        EventBus.fireEvent(new FindRouteForLast2ElevationMarkersEvent());
      }
    });

    view.getClearAllFromMapMenuItem().setOnAction(event -> EventBus.fireEvent(new ClearAllFromMapEvent()));

    view.getShowInfoMarkerMenuItem().setOnAction(event -> EventBus.fireEvent(new ShowInfoMarkerEvent()));
  }
}
