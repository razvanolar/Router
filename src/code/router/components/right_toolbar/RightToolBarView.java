package code.router.components.right_toolbar;

import code.router.utils.RouterConstants;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToolBar;

/**
 * Created by razvanolar on 29.12.2016
 */
public class RightToolBarView implements RightToolBarController.IRightToolBarView {

  private ToolBar toolBar;
  private ToggleButton mapButton;
  private ToggleButton chartButton;
  private ToggleButton mapSettingsButton;

  public RightToolBarView() {
    init();
  }

  private void init() {
    mapButton = new ToggleButton("Map");
    chartButton = new ToggleButton("Chart");
    mapSettingsButton = new ToggleButton("Map Settings");
    Group mapGroup = new Group(mapButton);
    Group chartGroup = new Group(chartButton);
    Group mapSettingsGroup = new Group(mapSettingsButton);

    mapButton.setRotate(90);
    chartButton.setRotate(90);
    mapSettingsButton.setRotate(90);

    toolBar = new ToolBar(mapGroup, chartGroup, mapSettingsGroup);
    toolBar.setOrientation(Orientation.VERTICAL);

    mapButton.setSelected(true);
    chartButton.setSelected(true);
    toolBar.getStyleClass().add(RouterConstants.TOOL_BAR_CLASS);
  }

  public ToggleButton getMapButton() {
    return mapButton;
  }

  public ToggleButton getChartButton() {
    return chartButton;
  }

  public ToggleButton getMapSettingsButton() {
    return mapSettingsButton;
  }

  @Override
  public Node asNode() {
    return toolBar;
  }
}
