package code.router.components.menu_bar;

import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

/**
 * Created by razvanolar on 29.12.2016
 */
public class MenuBarView implements MenuBarController.IMenuBarView {

  private MenuBar menuBar;
  private Menu fileMenu;
  private Menu editMenu;
  private Menu helpMenu;

  public MenuBarView() {
    init();
  }

  private void init() {
    fileMenu = new Menu("File");
    editMenu = new Menu("Edit");
    helpMenu = new Menu("Help");

    menuBar = new MenuBar(fileMenu, editMenu, helpMenu);
  }

  @Override
  public Node asNode() {
    return menuBar;
  }
}
