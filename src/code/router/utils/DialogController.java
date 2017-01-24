package code.router.utils;

import code.router.utils.callbacks.CallbackEntity;
import code.router.utils.callbacks.DialogCallback;
import javafx.scene.control.Button;

/**
 * Created by razvanolar on 24.01.2017
 */
public abstract class DialogController<V extends View, T extends CallbackEntity> {

  protected Button actionButton;
  protected DialogCallback<T> callback;

  protected DialogController(Button actionButton, DialogCallback<T> callback) {
    this.actionButton = actionButton;
    this.callback = callback;
    init();
  }

  private void init() {
    this.actionButton.setOnAction(event -> callback.callback(collect()));
  }

  public abstract void bind(V view);

  public abstract View getView();

  public abstract T collect();
}
