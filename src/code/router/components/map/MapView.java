package code.router.components.map;

import code.router.EventBus;
import code.router.components.map.chart_content.ChartContentController;
import code.router.components.map.map_content.MapContentController;
import code.router.components.map_settings.MapSettingsController;
import code.router.events.map_direction_change_event.MapDirectionChangedEvent;
import code.router.events.map_direction_change_event.MapDirectionChangedEventHandler;
import code.router.events.show_hide_chart_content_event.HideChartContentEvent;
import code.router.events.show_hide_chart_content_event.HideChartContentEventHandler;
import code.router.events.show_hide_chart_content_event.ShowChartContentEvent;
import code.router.events.show_hide_chart_content_event.ShowChartContentEventHandler;
import code.router.events.show_hide_map_content_event.HideMapContentEvent;
import code.router.events.show_hide_map_content_event.HideMapContentEventHandler;
import code.router.events.show_hide_map_content_event.ShowMapContentEvent;
import code.router.events.show_hide_map_content_event.ShowMapContentEventHandler;
import code.router.events.show_hide_map_settings_content_event.HideMapSettingsEvent;
import code.router.events.show_hide_map_settings_content_event.HideMapSettingsEventHandler;
import code.router.events.show_hide_map_settings_content_event.ShowMapSettingsEvent;
import code.router.events.show_hide_map_settings_content_event.ShowMapSettingsEventHandler;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;

/**
 * Created by razvanolar on 29.12.2016
 */
public class MapView implements MapController.IMapView {

  private BorderPane mainContainer;
  private SplitPane mainSplitPane;
  private SplitPane mapAndChartSplitPane;

  private MapContentController.IMapContentView mapContentView;
  private ChartContentController.IChartContentView chartContentView;
  private MapSettingsController.IMapSettingsView mapSettingsView;

  private double mapAndChartSplitLastDividerPosition = .65;
  private double mainSplitLastDividerPosition = .7;

  public MapView(MapContentController.IMapContentView mapContentView,
                 ChartContentController.IChartContentView chartContentView,
                 MapSettingsController.IMapSettingsView mapSettingsView) {
    this.mapContentView = mapContentView;
    this.chartContentView = chartContentView;
    this.mapSettingsView = mapSettingsView;
    init();
    addListeners();
  }

  private void init() {
    mapAndChartSplitPane = new SplitPane(mapContentView.asNode(), chartContentView.asNode());
    mainSplitPane = new SplitPane(mapAndChartSplitPane);
    mainContainer = new BorderPane(mainSplitPane);

    mapAndChartSplitPane.setOrientation(Orientation.HORIZONTAL);
    mapAndChartSplitPane.setDividerPositions(mapAndChartSplitLastDividerPosition);
    mainSplitPane.setOrientation(Orientation.HORIZONTAL);

    SplitPane.setResizableWithParent(chartContentView.asNode(), false);
  }

  private void addListeners() {
    EventBus.addHandler(HideMapContentEvent.TYPE, (HideMapContentEventHandler) event -> {
      ObservableList<Node> items = mapAndChartSplitPane.getItems();
      if (items.contains(mapContentView.asNode())) {
        if (items.size() > 1)
          mapAndChartSplitLastDividerPosition = mapAndChartSplitPane.getDividerPositions()[0];
        items.remove(mapContentView.asNode());
      }
    });

    EventBus.addHandler(HideChartContentEvent.TYPE, (HideChartContentEventHandler) event -> {
      ObservableList<Node> items = mapAndChartSplitPane.getItems();
      if (items.contains(chartContentView.asNode())) {
        if (items.size() > 1)
          mapAndChartSplitLastDividerPosition = mapAndChartSplitPane.getDividerPositions()[0];
        items.remove(chartContentView.asNode());
      }
    });

    EventBus.addHandler(ShowMapContentEvent.TYPE, (ShowMapContentEventHandler) event -> {
      ObservableList<Node> items = mapAndChartSplitPane.getItems();
      items.add(0, mapContentView.asNode());
      if (items.size() > 1)
        mapAndChartSplitPane.setDividerPositions(mapAndChartSplitLastDividerPosition);
    });

    EventBus.addHandler(ShowChartContentEvent.TYPE, (ShowChartContentEventHandler) event -> {
      ObservableList<Node> items = mapAndChartSplitPane.getItems();
      items.add(items.isEmpty() ? 0 : 1, chartContentView.asNode());
      if (items.size() > 1)
        mapAndChartSplitPane.setDividerPositions(mapAndChartSplitLastDividerPosition);
    });

    EventBus.addHandler(ShowMapSettingsEvent.TYPE, (ShowMapSettingsEventHandler) event -> {
      if (!mainSplitPane.getItems().contains(mapSettingsView.asNode())) {
        mainSplitPane.getItems().add(1, mapSettingsView.asNode());
        mainSplitPane.setDividerPositions(mainSplitLastDividerPosition);
      }
    });

    EventBus.addHandler(HideMapSettingsEvent.TYPE, (HideMapSettingsEventHandler) event -> {
      if (mainSplitPane.getItems().contains(mapSettingsView.asNode())) {
        mainSplitLastDividerPosition = mainSplitPane.getDividerPositions()[0];
        mainSplitPane.getItems().remove(mapSettingsView.asNode());
      }
    });

    EventBus.addHandler(MapDirectionChangedEvent.TYPE, (MapDirectionChangedEventHandler) event -> {
      mapAndChartSplitPane.setOrientation(event.isHorizontal() ? Orientation.HORIZONTAL : Orientation.VERTICAL);
    });
  }

  public MapContentController.IMapContentView getMapContentView() {
    return mapContentView;
  }

  public ChartContentController.IChartContentView getChartContentView() {
    return chartContentView;
  }

  public MapSettingsController.IMapSettingsView getMapSettingsView() {
    return mapSettingsView;
  }

  @Override
  public Node asNode() {
    return mainContainer;
  }
}
