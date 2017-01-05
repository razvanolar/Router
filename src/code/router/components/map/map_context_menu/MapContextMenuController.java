package code.router.components.map.map_context_menu;

import code.router.EventBus;
import code.router.events.map_direction_change_event.MapDirectionChangedEvent;
import code.router.utils.Controller;
import code.router.utils.View;
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
  }

  @Override
  public void bind(IMapContextMenuView view) {
    view.getHorizontalDirectionMenuItem().setOnAction(event -> {
      EventBus.fireEvent(new MapDirectionChangedEvent(true));
    });

    view.getVerticalDirectionMenuItem().setOnAction(event -> {
      EventBus.fireEvent(new MapDirectionChangedEvent(false));
    });
  }
}
