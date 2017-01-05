package code.router.utils.factories;

import code.router.components.footer.FooterController;
import code.router.components.left_toolbar.LeftToolBarController;
import code.router.components.map.MapController;
import code.router.components.map.chart_content.ChartContentController;
import code.router.components.map.map_content.MapContentController;
import code.router.components.map.map_context_menu.MapContextMenuController;
import code.router.components.map_settings.MapSettingsController;
import code.router.components.menu_bar.MenuBarController;
import code.router.components.projects_tree.ProjectsTreeController;
import code.router.components.right_toolbar.RightToolBarController;
import code.router.components.routes_tree.RoutesTreeController;
import code.router.components.tool_bar.ToolBarController;
import code.router.utils.Controller;
import code.router.utils.types.ComponentTypes;

/**
 * Created by razvanolar on 29.12.2016
 */
public class ControllerFactory {

  public Controller createController(ComponentTypes type) {
    switch (type) {
      case MENU_BAR:
        return new MenuBarController();
      case TOOL_BAR:
        return new ToolBarController();
      case LEFT_TOOL_BAR:
        return new LeftToolBarController();
      case RIGHT_TOOL_BAR:
        return new RightToolBarController();
      case ROUTES_TREE:
        return new RoutesTreeController();
      case PROJECTS_TREE:
        return new ProjectsTreeController();
      case MAP:
        return new MapController(new MapContentController(), new ChartContentController(), new MapSettingsController());
      case FOOTER:
        return new FooterController();
      case MAP_CONTEXT_MENU:
        return new MapContextMenuController();
    }
    return null;
  }
}
