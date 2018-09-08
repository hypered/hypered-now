package co.hypered.android.hypered.now;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;


class Channels extends ContextWrapper {
  private NotificationManager manager;
  public static final String PRIMARY_CHANNEL = "default";
  public static final String SECONDARY_CHANNEL = "second";

  public Channels(Context ctx) {
    super(ctx);

    manager = (NotificationManager) getSystemService(
      Context.NOTIFICATION_SERVICE);

    NotificationChannel chan1 = new NotificationChannel(
      PRIMARY_CHANNEL,
      getString(R.string.noti_channel_default),
      NotificationManager.IMPORTANCE_DEFAULT);
    chan1.setLightColor(Color.GREEN);
    chan1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
    manager.createNotificationChannel(chan1);

    NotificationChannel chan2 = new NotificationChannel(
      SECONDARY_CHANNEL,
      getString(R.string.noti_channel_second),
      NotificationManager.IMPORTANCE_HIGH);
    chan2.setLightColor(Color.BLUE);
    chan2.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
    manager.createNotificationChannel(chan2);
  }

  public Notification.Builder makeNotification(
    String channel, String title, String body) {
    return new Notification.Builder(getApplicationContext(), channel)
      .setContentTitle(title)
      .setContentText(body)
      .setSmallIcon(android.R.drawable.stat_notify_chat)
      .setAutoCancel(true);
  }

  public void notify(int id, Notification.Builder nb) {
    manager.notify(id, nb.build());
  }
}
