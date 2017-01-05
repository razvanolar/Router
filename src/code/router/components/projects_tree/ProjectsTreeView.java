package code.router.components.projects_tree;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * Created by razvanolar on 29.12.2016
 */
public class ProjectsTreeView implements ProjectsTreeController.IProjectsTreeView {

  private BorderPane mainContainer;

  public ProjectsTreeView() {
    init();
  }

  private void init() {
    mainContainer = new BorderPane(new Label("Projects Tree View"));
  }

  @Override
  public Node asNode() {
    return mainContainer;
  }
}
