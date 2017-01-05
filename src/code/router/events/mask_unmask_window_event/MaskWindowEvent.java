package code.router.events.mask_unmask_window_event;

import code.router.utils.event.Event;
import code.router.utils.event.EventType;

/**
 * Created by razvanolar on 03.01.2017
 */
public class MaskWindowEvent extends Event<MaskWindowEventHandler> {

  public static final EventType<MaskWindowEventHandler> TYPE = new EventType<>();
  private String message;

  public MaskWindowEvent() {
    this("Waiting...");
  }

  public MaskWindowEvent(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(MaskWindowEventHandler handler) {
    handler.onMaskWindowEvent(this);
  }
}
