package code.router.events.notification_event;

import code.router.utils.event.Event;
import code.router.utils.event.EventType;
import code.router.utils.types.NotificationLevelTypes;

/**
 * Created by razvanolar on 24.01.2017
 */
public class NotificationEvent extends Event<NotificationEventHandler> {

  public static final EventType<NotificationEventHandler> TYPE = new EventType<>();

  private String message;
  private NotificationLevelTypes notificationLevel;

  public NotificationEvent(String message, NotificationLevelTypes notificationLevel) {
    this.message = message;
    this.notificationLevel = notificationLevel;
  }

  public String getMessage() {
    return message;
  }

  public NotificationLevelTypes getNotificationLevel() {
    return notificationLevel;
  }

  @Override
  public EventType getAssociatedType() {
    return TYPE;
  }

  @Override
  public void dispatch(NotificationEventHandler handler) {
    handler.onNotificationEvent(this);
  }
}
