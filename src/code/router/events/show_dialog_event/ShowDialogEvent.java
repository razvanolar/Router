package code.router.events.show_dialog_event;

import code.router.utils.event.Event;
import code.router.utils.event.EventType;
import com.jfoenix.controls.JFXDialog;

/**
 * Created by razvanolar on 24.01.2017
 */
public class ShowDialogEvent extends Event<ShowDialogEventHandler> {

  public static final EventType<ShowDialogEventHandler> TYPE = new EventType<>();

  private JFXDialog dialog;

  public ShowDialogEvent(JFXDialog dialog) {
    this.dialog = dialog;
  }

  public JFXDialog getDialog() {
    return dialog;
  }

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(ShowDialogEventHandler handler) {
    handler.onShowDialogEvent(this);
  }
}
