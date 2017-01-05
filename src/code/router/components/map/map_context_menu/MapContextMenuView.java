package code.router.components.map.map_context_menu;

import javafx.scene.Node;
import javafx.scene.control.*;

/**
 * Created by razvanolar on 05.01.2017
 */
public class MapContextMenuView implements MapContextMenuController.IMapContextMenuView {

  private ContextMenu contextMenu;
  private Menu directionMenu;
  private RadioMenuItem horizontalDirectionMenuItem;
  private RadioMenuItem verticalDirectionMenuItem;

  public MapContextMenuView() {
    init();
  }

  private void init() {
    horizontalDirectionMenuItem = new RadioMenuItem("Horizontal");
    verticalDirectionMenuItem = new RadioMenuItem("Vertical");
    directionMenu = new Menu("Direction");
    contextMenu = new ContextMenu(directionMenu);

    ToggleGroup directionGroup = new ToggleGroup();
    directionGroup.getToggles().addAll(horizontalDirectionMenuItem, verticalDirectionMenuItem);
    horizontalDirectionMenuItem.setSelected(true);

    directionMenu.getItems().addAll(horizontalDirectionMenuItem, verticalDirectionMenuItem);
  }

  public MenuItem getHorizontalDirectionMenuItem() {
    return horizontalDirectionMenuItem;
  }

  public MenuItem getVerticalDirectionMenuItem() {
    return verticalDirectionMenuItem;
  }

  public ContextMenu getContextMenu() {
    return contextMenu;
  }

  @Override
  public Node asNode() {
    return null;
  }
}
