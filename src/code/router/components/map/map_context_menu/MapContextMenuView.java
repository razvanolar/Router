package code.router.components.map.map_context_menu;

import javafx.scene.Node;
import javafx.scene.control.*;

/**
 * Created by razvanolar on 05.01.2017
 */
public class MapContextMenuView implements MapContextMenuController.IMapContextMenuView {

  private static MapContextMenuView INSTANCE;

  private ContextMenu contextMenu;
  private RadioMenuItem horizontalDirectionMenuItem;
  private RadioMenuItem verticalDirectionMenuItem;
  private MenuItem clearStartMarkerMenuItem;
  private MenuItem clearEndMarkerMenuItem;
  private MenuItem clearIntermediateMarkersMenuItem;
  private MenuItem clearElevationMarkersMenuItem;
  private MenuItem clearAllMarkersMenuItem;
  private MenuItem findRouteForLastTwoElevationMarkersMenuItem;
  private MenuItem findRouteForStartAndEndMarkersMenuItem;
  private MenuItem clearAllFromMapMenuItem;
  private MenuItem showInfoMarkerMenuItem;
  private CheckBox openInNewTabCheckBox;

  private MapContextMenuView() {
    init();
  }

  private void init() {
    openInNewTabCheckBox = new CheckBox("In New Tab");
    horizontalDirectionMenuItem = new RadioMenuItem("Horizontal");
    verticalDirectionMenuItem = new RadioMenuItem("Vertical");
    Menu directionMenu = new Menu("View Direction");
    clearStartMarkerMenuItem = new MenuItem("Start Marker");
    clearEndMarkerMenuItem = new MenuItem("End Marker");
    clearIntermediateMarkersMenuItem = new MenuItem("Intermediate Markers");
    clearElevationMarkersMenuItem = new MenuItem("Elevation Markers");
    clearAllMarkersMenuItem = new MenuItem("All Markers");
    findRouteForStartAndEndMarkersMenuItem = new MenuItem("Start-End Markers");
    findRouteForLastTwoElevationMarkersMenuItem = new MenuItem("Last 2 Elevation Markers");
    Menu findRouteMenu = new Menu("Find Route For");
    clearAllFromMapMenuItem = new MenuItem("Clear All");
    showInfoMarkerMenuItem = new MenuItem("Show Info Marker");
    CustomMenuItem inNewTabCheckMenuItem = new CustomMenuItem(openInNewTabCheckBox);
    Menu clearMarkersMenu = new Menu("Clear Markers");
    contextMenu = new ContextMenu(directionMenu, clearMarkersMenu, findRouteMenu, clearAllFromMapMenuItem,
            showInfoMarkerMenuItem, inNewTabCheckMenuItem);

    ToggleGroup directionGroup = new ToggleGroup();
    directionGroup.getToggles().addAll(horizontalDirectionMenuItem, verticalDirectionMenuItem);
    horizontalDirectionMenuItem.setSelected(true);

    directionMenu.getItems().addAll(horizontalDirectionMenuItem, verticalDirectionMenuItem);
    clearMarkersMenu.getItems().addAll(clearStartMarkerMenuItem,
            clearEndMarkerMenuItem,
            clearIntermediateMarkersMenuItem,
            clearElevationMarkersMenuItem,
            clearAllMarkersMenuItem);
    findRouteMenu.getItems().addAll(findRouteForStartAndEndMarkersMenuItem, findRouteForLastTwoElevationMarkersMenuItem);

    openInNewTabCheckBox.setSelected(true);
    inNewTabCheckMenuItem.setHideOnClick(false);
  }

  public MenuItem getHorizontalDirectionMenuItem() {
    return horizontalDirectionMenuItem;
  }

  public MenuItem getVerticalDirectionMenuItem() {
    return verticalDirectionMenuItem;
  }

  public MenuItem getClearStartMarkerMenuItem() {
    return clearStartMarkerMenuItem;
  }

  public MenuItem getClearEndMarkerMenuItem() {
    return clearEndMarkerMenuItem;
  }

  public MenuItem getClearIntermediateMarkersMenuItem() {
    return clearIntermediateMarkersMenuItem;
  }

  public MenuItem getClearElevationMarkersMenuItem() {
    return clearElevationMarkersMenuItem;
  }

  public MenuItem getClearAllMarkersMenuItem() {
    return clearAllMarkersMenuItem;
  }

  public MenuItem getFindRouteForLastTwoElevationMarkersMenuItem() {
    return findRouteForLastTwoElevationMarkersMenuItem;
  }

  public MenuItem getFindRouteForStartAndEndMarkersMenuItem() {
    return findRouteForStartAndEndMarkersMenuItem;
  }

  public MenuItem getClearAllFromMapMenuItem() {
    return clearAllFromMapMenuItem;
  }

  public MenuItem getShowInfoMarkerMenuItem() {
    return showInfoMarkerMenuItem;
  }

  public CheckBox getOpenInNewTabCheckBox() {
    return openInNewTabCheckBox;
  }

  public ContextMenu getContextMenu() {
    return contextMenu;
  }

  @Override
  public Node asNode() {
    return null;
  }

  public static MapContextMenuView getInstance() {
    if (INSTANCE == null)
      INSTANCE = new MapContextMenuView();
    return INSTANCE;
  }
}
