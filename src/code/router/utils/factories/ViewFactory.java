package code.router.utils.factories;

import code.router.components.footer.FooterView;
import code.router.components.left_toolbar.LeftToolBarView;
import code.router.components.map.MapView;
import code.router.components.map.chart_content.ChartContentView;
import code.router.components.map.map_content.MapContentView;
import code.router.components.map.map_context_menu.MapContextMenuView;
import code.router.components.map_settings.MapSettingsView;
import code.router.components.menu_bar.MenuBarView;
import code.router.components.projects_tree.ProjectsTreeView;
import code.router.components.right_toolbar.RightToolBarView;
import code.router.components.routes_tree.RoutesTreeView;
import code.router.components.tool_bar.ToolBarView;
import code.router.utils.View;
import code.router.utils.types.ComponentTypes;

/**
 * Created by razvanolar on 29.12.2016
 */
public class ViewFactory {

  public View createView(ComponentTypes type) {
    switch (type) {
      case MENU_BAR:
        return new MenuBarView();
      case TOOL_BAR:
        return new ToolBarView();
      case LEFT_TOOL_BAR:
        return new LeftToolBarView();
      case RIGHT_TOOL_BAR:
        return new RightToolBarView();
      case ROUTES_TREE:
        return new RoutesTreeView();
      case PROJECTS_TREE:
        return new ProjectsTreeView();
      case MAP:
        return new MapView(new MapContentView(), new ChartContentView(), new MapSettingsView());
      case FOOTER:
        return new FooterView();
      case MAP_CONTEXT_MENU:
        return new MapContextMenuView();
    }
    return null;
  }
}
