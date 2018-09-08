package co.hypered.android.hypered.now;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity extends Activity {
  private static final int NOTIF_PRIMARY = 1100;
  private static final int NOTIF_SECONDARY1 = 1200;
  private MainUi ui;
  private Channels channels;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    channels = new Channels(this);
    ui = new MainUi(findViewById(R.id.activity_main));
  }

  class MainUi implements View.OnClickListener {
    private MainUi(View root) {
      ((Button) root.findViewById(R.id.main_primary_send)).setOnClickListener(this);
      ((ImageButton) root.findViewById(R.id.main_primary_config)).setOnClickListener(this);

      ((Button) root.findViewById(R.id.main_secondary_send)).setOnClickListener(this);
      ((ImageButton) root.findViewById(R.id.main_secondary_config)).setOnClickListener(this);

      ((Button) root.findViewById(R.id.btnA)).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      Notification.Builder nb = null;
      int id = view.getId();

      switch (id) {
        case R.id.main_primary_send:
          nb = channels.makeNotification(
            channels.PRIMARY_CHANNEL,
            getString(R.string.main_primary_title),
            getString(R.string.primary_body));
          channels.notify(id, nb);
          break;
        case R.id.main_primary_config:
          goToNotificationsSettings(Channels.PRIMARY_CHANNEL);
          break;

        case R.id.main_secondary_send:
          nb = channels.makeNotification(
            channels.SECONDARY_CHANNEL,
            getString(R.string.main_secondary_title),
            getString(R.string.secondary_body));
          channels.notify(id, nb);
          break;
        case R.id.main_secondary_config:
          goToNotificationsSettings(Channels.SECONDARY_CHANNEL);
          break;

        case R.id.btnA:
          goToNotificationsSettings();
          break;
        default:
          Log.e("hypered-now", "Unknown click event.");
          break;
      }
    }
  }

  public void goToNotificationsSettings() {
    Intent i = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
    i.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
    startActivity(i);
  }

  public void goToNotificationsSettings(String channel) {
    Intent i = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
    i.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
    i.putExtra(Settings.EXTRA_CHANNEL_ID, channel);
    startActivity(i);
  }
}
