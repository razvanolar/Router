package code.router.components.map;

import code.router.EventBus;
import code.router.RouterController;
import code.router.components.map.chart_content.ChartContentController;
import code.router.components.map.map_content.MapContentController;
import code.router.components.map_settings.MapSettingsController;
import code.router.events.clear_all_from_map_event.ClearAllFromMapEvent;
import code.router.events.clear_all_from_map_event.ClearAllFromMapEventHandler;
import code.router.events.clear_chart_event.ClearChartEvent;
import code.router.events.clear_chart_event.ClearChartEventHandler;
import code.router.events.load_resources_events.load_map_event.LoadMapEvent;
import code.router.events.load_resources_events.load_map_event.LoadMapEventHandler;
import code.router.events.markers_events.clear_markers_events.clear_all_markers_event.ClearAllMarkersEvent;
import code.router.events.markers_events.clear_markers_events.clear_all_markers_event.ClearAllMarkersEventHandler;
import code.router.events.markers_events.clear_markers_events.clear_elevation_markers_event.ClearElevationMarkersEvent;
import code.router.events.markers_events.clear_markers_events.clear_elevation_markers_event.ClearElevationMarkersEventHandler;
import code.router.events.markers_events.clear_markers_events.clear_end_marker_event.ClearEndMarkerEvent;
import code.router.events.markers_events.clear_markers_events.clear_end_marker_event.ClearEndMarkerEventHandler;
import code.router.events.markers_events.clear_markers_events.clear_intermediate_markers_event.ClearIntermediateMarkersEvent;
import code.router.events.markers_events.clear_markers_events.clear_intermediate_markers_event.ClearIntermediateMarkersEventHandler;
import code.router.events.markers_events.clear_markers_events.clear_start_marker_event.ClearStartMarkerEvent;
import code.router.events.markers_events.clear_markers_events.clear_start_marker_event.ClearStartMarkerEventHandler;
import code.router.events.markers_events.show_elevation_marker_event.ShowElevationMarkerEvent;
import code.router.events.markers_events.show_elevation_marker_event.ShowElevationMarkerEventHandler;
import code.router.events.markers_events.show_info_marker_event.ShowInfoMarkerEvent;
import code.router.events.markers_events.show_info_marker_event.ShowInfoMarkerEventHandler;
import code.router.events.routes_events.find_route_for_last_2_elevation_markers_event.FindRouteForLast2ElevationMarkersEvent;
import code.router.events.routes_events.find_route_for_last_2_elevation_markers_event.FindRouteForLast2ElevationMarkersEventHandler;
import code.router.events.routes_events.next_route_event.NextRouteEvent;
import code.router.events.routes_events.next_route_event.NextRouteEventHandler;
import code.router.events.routes_events.previous_route_event.PreviousRouteEvent;
import code.router.events.routes_events.previous_route_event.PreviousRouteEventHandler;
import code.router.events.routes_events.save_route_events.SaveCurrentRouteEvent;
import code.router.events.routes_events.save_route_events.SaveCurrentRouteEventHandler;
import code.router.events.routes_events.update_elevations_event.UpdateElevationsEvent;
import code.router.events.routes_events.update_elevations_event.UpdateElevationsEventHandler;
import code.router.events.show_save_route_dialog_event.ShowSaveRouteDialogEvent;
import code.router.model.routes.MapDetails;
import code.router.utils.Controller;
import code.router.utils.View;

/**
 * Created by razvanolar on 29.12.2016
 */
public class MapController implements Controller<MapController.IMapView> {

  public interface IMapView extends View {
    MapContentController.IMapContentView getMapContentView();
    ChartContentController.IChartContentView getChartContentView();
    MapSettingsController.IMapSettingsView getMapSettingsView();
  }

  private MapContentController mapContentController;
  private ChartContentController chartContentController;
  private MapSettingsController mapSettingsController;

  private String relativeProjectPath;

  public MapController(MapContentController mapContentController,
          ChartContentController chartContentController,
          MapSettingsController mapSettingsController) {
    this.mapContentController = mapContentController;
    this.chartContentController = chartContentController;
    this.mapSettingsController = mapSettingsController;
  }

  @Override
  public void bind(IMapView view) {
    mapContentController.bind(view.getMapContentView());
    chartContentController.bind(view.getChartContentView());
    mapSettingsController.bind(view.getMapSettingsView());

    mapContentController.injectParentController(this);

    EventBus.addHandler(LoadMapEvent.TYPE, (LoadMapEventHandler) event -> {
      if (!isActive()) { return; }
      mapContentController.loadMap(event.getRoute());
    });

    EventBus.addHandler(UpdateElevationsEvent.TYPE, (UpdateElevationsEventHandler) event -> {
      if (!isActive()) { return; }
      chartContentController.updateElevationChart(event);
    });

    EventBus.addHandler(PreviousRouteEvent.TYPE, (PreviousRouteEventHandler) event -> {
      if (!isActive()) { return; }
      if (mapContentController.getUtils() != null)
        mapContentController.getUtils().getPreviousRoute();
    });

    EventBus.addHandler(NextRouteEvent.TYPE, (NextRouteEventHandler) event -> {
      if (!isActive()) { return; }
      if (mapContentController.getUtils() != null)
        mapContentController.getUtils().getNextRoute();
    });

    EventBus.addHandler(ClearStartMarkerEvent.TYPE, (ClearStartMarkerEventHandler) event -> {
      if (!isActive()) { return; }
      if (mapContentController.getUtils() != null)
        mapContentController.getUtils().clearStartMarker();
    });

    EventBus.addHandler(ClearEndMarkerEvent.TYPE, (ClearEndMarkerEventHandler) event -> {
      if (!isActive()) { return; }
      if (mapContentController.getUtils() != null)
        mapContentController.getUtils().clearEndMarker();
    });

    EventBus.addHandler(ClearIntermediateMarkersEvent.TYPE, (ClearIntermediateMarkersEventHandler) event -> {
      if (!isActive()) { return; }
      if (mapContentController.getUtils() != null)
        mapContentController.getUtils().clearIntermediateMarkers();
    });

    EventBus.addHandler(ClearElevationMarkersEvent.TYPE, (ClearElevationMarkersEventHandler) event -> {
      if (!isActive()) { return; }
      if (mapContentController.getUtils() != null)
        mapContentController.getUtils().clearElevationMarkers();
    });

    EventBus.addHandler(ClearAllMarkersEvent.TYPE, (ClearAllMarkersEventHandler) event -> {
      if (!isActive()) { return; }
      if (mapContentController.getUtils() != null)
        mapContentController.getUtils().clearAllMarkers();
    });

    EventBus.addHandler(ClearAllFromMapEvent.TYPE, (ClearAllFromMapEventHandler) event -> {
      if (!isActive()) { return; }
      if (mapContentController.getUtils() != null)
        mapContentController.getUtils().clearAllFromMap();
    });

    EventBus.addHandler(ClearChartEvent.TYPE, (ClearChartEventHandler) event -> {
      if (!isActive()) { return; }
      chartContentController.clearChartData();
    });

    EventBus.addHandler(ShowElevationMarkerEvent.TYPE, (ShowElevationMarkerEventHandler) event -> {
      if (!isActive()) { return; }
      if (mapContentController.getUtils() != null)
        mapContentController.getUtils().showElevationMarker(event);
    });

    EventBus.addHandler(FindRouteForLast2ElevationMarkersEvent.TYPE, (FindRouteForLast2ElevationMarkersEventHandler) event -> {
      if (!isActive()) { return; }
      if (mapContentController.getUtils() != null)
        mapContentController.getUtils().findRouteForLastTwoElevationMarkers(event);
    });

    EventBus.addHandler(ShowInfoMarkerEvent.TYPE, (ShowInfoMarkerEventHandler) event -> {
      if (!isActive()) { return; }
      if (mapContentController.getUtils() != null)
        mapContentController.getUtils().showInfoMarker();
    });

    EventBus.addHandler(SaveCurrentRouteEvent.TYPE, (SaveCurrentRouteEventHandler) event -> {
      if (!isActive()) { return; }
      if (mapContentController.getUtils() != null) {
        MapDetails mapDetails = mapContentController.getUtils().getMapDetails();
        if (mapDetails != null) {
          mapDetails.setName(event.getRouteName());
          mapDetails.setParentDirPath(relativeProjectPath);
          EventBus.fireEvent(new ShowSaveRouteDialogEvent(mapDetails));
        }
      }
    });
  }

  public boolean isActive() {
    return equals(RouterController.SELECTED_MAP_CONTROLLER);
  }
}
