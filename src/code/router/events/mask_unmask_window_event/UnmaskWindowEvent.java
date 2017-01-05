package code.router.events.mask_unmask_window_event;

import code.router.utils.event.Event;
import code.router.utils.event.EventType;

/**
 * Created by razvanolar on 03.01.2017
 */
public class UnmaskWindowEvent extends Event<UnmaskWindowEventHandler> {

  public static final EventType<UnmaskWindowEventHandler> TYPE = new EventType<>();

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(UnmaskWindowEventHandler handler) {
    handler.onUnmaskEventHandler(this);
  }
}
