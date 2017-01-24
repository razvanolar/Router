package code.router.model.other;

import code.router.utils.callbacks.CallbackEntity;

/**
 * Created by razvanolar on 24.01.2017
 */
public class StringEntity implements CallbackEntity {

  private String value;

  public StringEntity(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
