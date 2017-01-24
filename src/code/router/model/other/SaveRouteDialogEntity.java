package code.router.model.other;

import code.router.utils.callbacks.CallbackEntity;

/**
 * Created by razvanolar on 24.01.2017
 */
public class SaveRouteDialogEntity implements CallbackEntity {

  private String routeName;
  private String routePath;

  public SaveRouteDialogEntity(String routeName, String routePath) {
    this.routeName = routeName;
    this.routePath = routePath;
  }

  public String getRouteName() {
    return routeName;
  }

  public String getRoutePath() {
    return routePath;
  }
}
