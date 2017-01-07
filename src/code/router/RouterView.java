package code.router;

import code.router.components.masking_indicator.MaskPane;
import code.router.events.hide_tree_event.HideTreeEvent;
import code.router.events.hide_tree_event.HideTreeEventHandler;
import code.router.events.load_resources_events.load_map_event.LoadMapEvent;
import code.router.events.mask_unmask_window_event.MaskWindowEvent;
import code.router.events.mask_unmask_window_event.MaskWindowEventHandler;
import code.router.events.mask_unmask_window_event.UnmaskWindowEvent;
import code.router.events.mask_unmask_window_event.UnmaskWindowEventHandler;
import code.router.events.new_route_event.AddNewRouteViewEvent;
import code.router.events.new_route_event.NewRouteViewEventHandler;
import code.router.events.show_projects_tree_event.ShowProjectsTreeEvent;
import code.router.events.show_projects_tree_event.ShowProjectsTreeEventHandler;
import code.router.events.show_routes_tree_events.ShowRouteTreeEvent;
import code.router.events.show_routes_tree_events.ShowRouteTreeEventHandler;
import code.router.utils.Component;
import code.router.utils.View;
import code.router.utils.factories.ComponentFactory;
import code.router.utils.types.ComponentTypes;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.*;

import java.util.Map;

/**
 * Created by razvanolar on 29.12.2016
 */
public class RouterView implements RouterController.IRouterView {

  private StackPane mainContainer;
  private SplitPane centerSplitPane;
  private View routesTreeView;
  private View projectsTreeView;
  private TabPane tabPane;
  private MaskPane maskPane;

  private double lastFirstDividerPosition;
  private double lastSecondDividerPosition;

  public RouterView(Map<ComponentTypes, Component> componentsMap) throws Exception {
    init(componentsMap);
    addListeners();
  }

  private void init(Map<ComponentTypes, Component> componentsMap) throws Exception {
    View menuBarView = componentsMap.get(ComponentTypes.MENU_BAR).getView();
    View toolBarView = componentsMap.get(ComponentTypes.TOOL_BAR).getView();
    View leftToolBarView = componentsMap.get(ComponentTypes.LEFT_TOOL_BAR).getView();
    View rightToolBarView = componentsMap.get(ComponentTypes.RIGHT_TOOL_BAR).getView();
    routesTreeView = componentsMap.get(ComponentTypes.ROUTES_TREE).getView();
    projectsTreeView = componentsMap.get(ComponentTypes.PROJECTS_TREE).getView();
    View footerView = componentsMap.get(ComponentTypes.FOOTER).getView();

    VBox topContainer = new VBox(menuBarView.asNode(), toolBarView.asNode());
    tabPane = new TabPane();
    centerSplitPane = new SplitPane(tabPane);
    BorderPane borderContainer = new BorderPane(centerSplitPane);
    mainContainer = new StackPane(borderContainer);
    maskPane = new MaskPane();

    borderContainer.setTop(topContainer);
    borderContainer.setLeft(leftToolBarView.asNode());
    borderContainer.setRight(rightToolBarView.asNode());
    borderContainer.setBottom(footerView.asNode());

    lastFirstDividerPosition = 0.2;
    lastSecondDividerPosition = .8;
    centerSplitPane.setDividerPositions(lastFirstDividerPosition, lastSecondDividerPosition);
  }

  private void addListeners() {
    EventBus.addHandler(HideTreeEvent.TYPE, (HideTreeEventHandler) event -> clearLeftItem());

    EventBus.addHandler(ShowRouteTreeEvent.TYPE, (ShowRouteTreeEventHandler) event -> {
      clearLeftItem();
      centerSplitPane.getItems().add(0, routesTreeView.asNode());
      centerSplitPane.setDividerPosition(0, lastFirstDividerPosition);
      centerSplitPane.setDividerPosition(1, lastSecondDividerPosition);
    });

    EventBus.addHandler(ShowProjectsTreeEvent.TYPE, (ShowProjectsTreeEventHandler) event -> {
      clearLeftItem();
      centerSplitPane.getItems().add(0, projectsTreeView.asNode());
      centerSplitPane.setDividerPosition(0, lastFirstDividerPosition);
      centerSplitPane.setDividerPosition(1, lastSecondDividerPosition);
    });

    EventBus.addHandler(MaskWindowEvent.TYPE, (MaskWindowEventHandler) event -> {
      maskPane.setTextMessage(event.getMessage());
      mainContainer.getChildren().add(maskPane.asNode());
    });

    EventBus.addHandler(UnmaskWindowEvent.TYPE, (UnmaskWindowEventHandler) event -> {
      mainContainer.getChildren().remove(maskPane.asNode());
    });

    EventBus.addHandler(AddNewRouteViewEvent.TYPE, (NewRouteViewEventHandler) this::addMapTab);
  }

  private void clearLeftItem() {
    ObservableList<Node> items = centerSplitPane.getItems();
    if (items.contains(routesTreeView.asNode())) {
      lastFirstDividerPosition = centerSplitPane.getDividerPositions()[0];
      items.remove(routesTreeView.asNode());
    }
    if (items.contains(projectsTreeView.asNode())) {
      lastFirstDividerPosition = centerSplitPane.getDividerPositions()[0];
      items.remove(projectsTreeView.asNode());
    }
  }

  private void addMapTab(AddNewRouteViewEvent event) {
    Component component = ComponentFactory.createComponent(ComponentTypes.MAP);
    if (component == null)
      return;
    Tab tab = new Tab(event.getTitle(), component.getView().asNode());
    tabPane.getTabs().add(tab);
    tab.setUserData(component.getController());
    tabPane.getSelectionModel().select(tab);

    RouterController.SELECTED_MAP_CONTROLLER = component.getController();
    EventBus.fireEvent(new LoadMapEvent(event.getRouteToBeLoaded()));
  }

  public TabPane getTabPane() {
    return tabPane;
  }

  @Override
  public Node asNode() {
    return mainContainer;
  }
}
