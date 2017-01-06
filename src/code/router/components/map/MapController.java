package code.router.components.map;

import code.router.EventBus;
import code.router.RouterController;
import code.router.components.map.chart_content.ChartContentController;
import code.router.components.map.map_content.MapContentController;
import code.router.components.map_settings.MapSettingsController;
import code.router.events.load_resources_events.load_map_event.LoadMapEvent;
import code.router.events.load_resources_events.load_map_event.LoadMapEventHandler;
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

    EventBus.addHandler(LoadMapEvent.TYPE, (LoadMapEventHandler) event -> {
      if (!equals(RouterController.SELECTED_MAP_CONTROLLER)) {
        return;
      }
      mapContentController.loadMap();
    });
  }
}
