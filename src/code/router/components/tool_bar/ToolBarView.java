package code.router.components.tool_bar;

import code.router.utils.RouterConstants;
import javafx.scene.Node;
import javafx.scene.control.*;

/**
 * Created by razvanolar on 29.12.2016
 */
public class ToolBarView implements ToolBarController.IToolBarView {

  private ToolBar toolBar;
  private Button newRouteButton;
  private Button openButton;
  private Button saveRouteButton;
  private ToggleButton startMarkerButton;
  private ToggleButton endMarkerButton;
  private ToggleButton intermediateMarkerButton;
  private ToggleButton elevationMarkerButton;
  private ToggleGroup toggleGroup;
  private Button findRouteButton;
  private Button prevRouteButton;
  private Button nextRouteButton;


  public ToolBarView() {
    init();
  }

  private void init() {
    newRouteButton = new Button("New");
    openButton = new Button("Open");
    saveRouteButton = new Button("Save");
    startMarkerButton = new ToggleButton("Start");
    endMarkerButton = new ToggleButton("End");
    intermediateMarkerButton = new ToggleButton("Intermediate");
    elevationMarkerButton = new ToggleButton("Elevation");
    ToggleButton noneMarkerButton = new ToggleButton("None");
    findRouteButton = new Button("Find Route");
    prevRouteButton = new Button("Prev Route");
    nextRouteButton = new Button("Next");
    toggleGroup = new ToggleGroup();
    toolBar = new ToolBar(
            newRouteButton,
            openButton,
            saveRouteButton,
            new Separator(),
            new Label("Markers: "),
            startMarkerButton,
            endMarkerButton,
            intermediateMarkerButton,
            elevationMarkerButton,
            noneMarkerButton,
            new Separator(),
            findRouteButton,
            prevRouteButton,
            nextRouteButton
    );

    toggleGroup.getToggles().addAll(startMarkerButton, endMarkerButton, intermediateMarkerButton, elevationMarkerButton, noneMarkerButton);
    noneMarkerButton.setSelected(true);
    toolBar.getStyleClass().add(RouterConstants.TOOL_BAR_CLASS);
  }

  public Button getNewRouteButton() {
    return newRouteButton;
  }

  public Button getOpenButton() {
    return openButton;
  }

  public Button getSaveRouteButton() {
    return saveRouteButton;
  }

  public ToggleButton getStartMarkerButton() {
    return startMarkerButton;
  }

  public ToggleButton getEndMarkerButton() {
    return endMarkerButton;
  }

  public ToggleButton getIntermediateMarkerButton() {
    return intermediateMarkerButton;
  }

  public ToggleButton getElevationMarkerButton() {
    return elevationMarkerButton;
  }

  public ToggleGroup getToggleGroup() {
    return toggleGroup;
  }

  public Button getFindRouteButton() {
    return findRouteButton;
  }

  public Button getPrevRouteButton() {
    return prevRouteButton;
  }

  public Button getNextRouteButton() {
    return nextRouteButton;
  }

  @Override
  public Node asNode() {
    return toolBar;
  }
}
