package code.router.events.notification_event;

import code.router.utils.event.EventHandler;

/**
 * Created by razvanolar on 24.01.2017
 */
public interface NotificationEventHandler extends EventHandler {
  void onNotificationEvent(NotificationEvent event);
}
