package code.router.events.show_projects_tree_event;

import code.router.utils.event.Event;
import code.router.utils.event.EventType;

/**
 * Created by razvanolar on 29.12.2016
 */
public class ShowProjectsTreeEvent extends Event<ShowProjectsTreeEventHandler> {

  public static final EventType<ShowProjectsTreeEventHandler> TYPE = new EventType<>();

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(ShowProjectsTreeEventHandler handler) {
    handler.onShowProjectsTreeEvent(this);
  }
}
