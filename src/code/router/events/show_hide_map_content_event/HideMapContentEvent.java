package code.router.events.show_hide_map_content_event;

import code.router.utils.event.Event;
import code.router.utils.event.EventType;

/**
 * Created by razvanolar on 30.12.2016
 */
public class HideMapContentEvent extends Event<HideMapContentEventHandler> {

  public static final EventType<HideMapContentEventHandler> TYPE = new EventType<>();

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(HideMapContentEventHandler handler) {
    handler.onHideMapContentEvent(this);
  }
}
