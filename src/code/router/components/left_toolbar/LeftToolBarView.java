package code.router.components.left_toolbar;

import code.router.utils.RouterConstants;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;

/**
 * Created by razvanolar on 29.12.2016
 */
public class LeftToolBarView implements LeftToolBarController.ILeftToolBarView {

  private ToolBar toolBar;
  private ToggleButton routesButton;
  private ToggleButton projectsButton;

  public LeftToolBarView() {
    init();
  }

  private void init() {
    routesButton = new ToggleButton("Routes");
    projectsButton = new ToggleButton("Projects");
    Group routesGroup = new Group(routesButton);
    Group projectsGroup = new Group(projectsButton);

    routesButton.setRotate(-90);
    projectsButton.setRotate(-90);

    toolBar = new ToolBar(routesGroup, projectsGroup);
    toolBar.setOrientation(Orientation.VERTICAL);

    ToggleGroup group = new ToggleGroup();
    group.getToggles().addAll(routesButton, projectsButton);
    toolBar.getStyleClass().add(RouterConstants.TOOL_BAR_CLASS);
  }

  public ToggleButton getRoutesButton() {
    return routesButton;
  }

  public ToggleButton getProjectsButton() {
    return projectsButton;
  }

  @Override
  public Node asNode() {
    return toolBar;
  }
}
