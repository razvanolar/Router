package code.router.components.right_toolbar;

import code.router.EventBus;
import code.router.events.show_hide_chart_content_event.HideChartContentEvent;
import code.router.events.show_hide_chart_content_event.ShowChartContentEvent;
import code.router.events.show_hide_map_content_event.HideMapContentEvent;
import code.router.events.show_hide_map_content_event.ShowMapContentEvent;
import code.router.events.show_hide_map_settings_content_event.HideMapSettingsEvent;
import code.router.events.show_hide_map_settings_content_event.ShowMapSettingsEvent;
import code.router.utils.Controller;
import code.router.utils.View;
import javafx.scene.control.ToggleButton;

/**
 * Created by razvanolar on 29.12.2016
 */
public class RightToolBarController implements Controller<RightToolBarController.IRightToolBarView> {

  public interface IRightToolBarView extends View {
    ToggleButton getMapButton();
    ToggleButton getChartButton();
    ToggleButton getMapSettingsButton();
  }

  @Override
  public void bind(IRightToolBarView view) {
    view.getMapButton().selectedProperty().addListener((observable, oldValue, newValue) -> {
      EventBus.fireEvent(newValue ? new ShowMapContentEvent() : new HideMapContentEvent());
    });

    view.getChartButton().selectedProperty().addListener((observable, oldValue, newValue) -> {
      EventBus.fireEvent(newValue ? new ShowChartContentEvent() : new HideChartContentEvent());
    });

    view.getMapSettingsButton().selectedProperty().addListener((observable, oldValue, newValue) -> {
      EventBus.fireEvent(newValue ? new ShowMapSettingsEvent() : new HideMapSettingsEvent());
    });
  }
}
