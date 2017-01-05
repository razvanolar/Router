package code.router.events.hide_tree_event;

import code.router.utils.event.Event;
import code.router.utils.event.EventType;

/**
 * Created by razvanolar on 29.12.2016
 */
public class HideTreeEvent extends Event<HideTreeEventHandler> {

  public static final EventType<HideTreeEventHandler> TYPE = new EventType<>();

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(HideTreeEventHandler handler) {
    handler.onHideRouteTreeEvent(this);
  }
}
