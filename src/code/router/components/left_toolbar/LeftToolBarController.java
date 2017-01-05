package code.router.components.left_toolbar;

import code.router.EventBus;
import code.router.events.hide_tree_event.HideTreeEvent;
import code.router.events.show_projects_tree_event.ShowProjectsTreeEvent;
import code.router.events.show_routes_tree_events.ShowRouteTreeEvent;
import code.router.utils.Controller;
import code.router.utils.View;
import javafx.scene.control.ToggleButton;

/**
 * Created by razvanolar on 29.12.2016
 */
public class LeftToolBarController implements Controller<LeftToolBarController.ILeftToolBarView> {

  public interface ILeftToolBarView extends View {
    ToggleButton getRoutesButton();
    ToggleButton getProjectsButton();
  }

  @Override
  public void bind(ILeftToolBarView view) {
    view.getRoutesButton().selectedProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue && !view.getProjectsButton().isSelected())
        EventBus.fireEvent(new HideTreeEvent());
    });

    view.getProjectsButton().selectedProperty().addListener((observable, oldValue, newValue) -> {
      if (!newValue && !view.getRoutesButton().isSelected())
        EventBus.fireEvent(new HideTreeEvent());
    });

    view.getRoutesButton().setOnAction(event -> {
      if (view.getRoutesButton().isSelected())
        EventBus.fireEvent(new ShowRouteTreeEvent());
    });

    view.getProjectsButton().setOnAction(event -> {
      if (view.getProjectsButton().isSelected())
        EventBus.fireEvent(new ShowProjectsTreeEvent());
    });
  }
}
