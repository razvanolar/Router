package code.router.utils.factories;

import code.router.utils.Component;
import code.router.utils.Controller;
import code.router.utils.View;
import code.router.utils.types.ComponentTypes;

/**
 * Created by razvanolar on 29.12.2016
 */
public class ComponentFactory {

  private static ControllerFactory controllerFactory = new ControllerFactory();
  private static ViewFactory viewFactory = new ViewFactory();

  public static Component createComponent(ComponentTypes type) {
    Controller controller = controllerFactory.createController(type);
    View view = viewFactory.createView(type);
    return controller != null && view != null ? new Component(controller, view) : null;
  }
}
