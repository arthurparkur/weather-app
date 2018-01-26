package weatherapp.arthurdanilov92.gmail.com.weatherapp;

import android.app.Activity;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class NavDrawerListener implements NavigationView.OnNavigationItemSelectedListener {

  Activity activity;

  public NavDrawerListener(Activity activity) {
    this.activity = activity;
  }

  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    int id = item.getItemId();

    int targetId = R.layout.content_main;

    if (id == R.id.weather_today) {
      targetId = R.layout.weather_today_frgt;
    } else if (id == R.id.weather_week) {
      targetId = R.layout.weather_week_frgt;
    } else if (id == R.id.contacts) {
      targetId = R.layout.contacts_frgt;
    } else if (id == R.id.help) {
      targetId = R.layout.help_frgt;
    }

    ViewGroup container = activity.findViewById(R.id.fragment_container);
    container.removeAllViews();
    View newContent = activity.getLayoutInflater().inflate(targetId, container, false);
    container.addView(newContent);

    DrawerLayout drawer = activity.findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }
}
