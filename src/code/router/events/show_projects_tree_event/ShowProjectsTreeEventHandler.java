package code.router.events.show_projects_tree_event;

import code.router.utils.event.EventHandler;

/**
 * Created by razvanolar on 29.12.2016
 */
public interface ShowProjectsTreeEventHandler extends EventHandler {
  void onShowProjectsTreeEvent(ShowProjectsTreeEvent event);
}
