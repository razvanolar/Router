package code.router.utils.callbacks;

/**
 * Created by razvanolar on 24.01.2017
 */
public interface DialogCallback<T extends CallbackEntity> {
  void callback(T entity);
}
