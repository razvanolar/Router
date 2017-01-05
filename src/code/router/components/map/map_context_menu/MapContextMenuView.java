package code.router.components.map.map_context_menu;

import javafx.scene.Node;
import javafx.scene.control.*;

/**
 * Created by razvanolar on 05.01.2017
 */
public class MapContextMenuView implements MapContextMenuController.IMapContextMenuView {

  private ContextMenu contextMenu;
  private RadioMenuItem horizontalDirectionMenuItem;
  private RadioMenuItem verticalDirectionMenuItem;
  private MenuItem clearStartMarkerMenuItem;
  private MenuItem clearEndMarkerMenuItem;
  private MenuItem clearIntermediateMarkersMenuItem;
  private MenuItem clearAllMarkersMenuItem;

  public MapContextMenuView() {
    init();
  }

  private void init() {
    horizontalDirectionMenuItem = new RadioMenuItem("Horizontal");
    verticalDirectionMenuItem = new RadioMenuItem("Vertical");
    Menu directionMenu = new Menu("View Direction");
    clearStartMarkerMenuItem = new MenuItem("Start Marker");
    clearEndMarkerMenuItem = new MenuItem("End Marker");
    clearIntermediateMarkersMenuItem = new MenuItem("Intermediate Markers");
    clearAllMarkersMenuItem = new MenuItem("All Markers");
    Menu clearMarkersMenu = new Menu("Clear Markers");
    contextMenu = new ContextMenu(directionMenu, clearMarkersMenu);

    ToggleGroup directionGroup = new ToggleGroup();
    directionGroup.getToggles().addAll(horizontalDirectionMenuItem, verticalDirectionMenuItem);
    horizontalDirectionMenuItem.setSelected(true);

    directionMenu.getItems().addAll(horizontalDirectionMenuItem, verticalDirectionMenuItem);
    clearMarkersMenu.getItems().addAll(clearStartMarkerMenuItem,
            clearEndMarkerMenuItem,
            clearIntermediateMarkersMenuItem,
            clearAllMarkersMenuItem);
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

  public MenuItem getClearAllMarkersMenuItem() {
    return clearAllMarkersMenuItem;
  }

  public ContextMenu getContextMenu() {
    return contextMenu;
  }

  @Override
  public Node asNode() {
    return null;
  }
}
