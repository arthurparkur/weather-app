package weatherapp.arthurdanilov92.gmail.com.weatherapp;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.ViewGroup;

import weatherapp.arthurdanilov92.gmail.com.weatherapp.fragments.ContactsFragment;
import weatherapp.arthurdanilov92.gmail.com.weatherapp.fragments.HelpFragment;
import weatherapp.arthurdanilov92.gmail.com.weatherapp.fragments.TodayFragment;
import weatherapp.arthurdanilov92.gmail.com.weatherapp.fragments.WeekFragment;

public class NavDrawerListener implements NavigationView.OnNavigationItemSelectedListener {

  private final String curFragmentTag = "currentFragment";
  AppCompatActivity activity;

  public NavDrawerListener(AppCompatActivity activity) {
    this.activity = activity;
  }

  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    int      id = item.getItemId();
    Fragment fragment;

    if (id == R.id.weather_today) {
      fragment = new TodayFragment();
    } else if (id == R.id.weather_week) {
      fragment = new WeekFragment();
    } else if (id == R.id.contacts) {
      fragment = new ContactsFragment();
    } else if (id == R.id.help) {
      fragment = new HelpFragment();
    } else fragment = new TodayFragment();

    ViewGroup container = activity.findViewById(R.id.fragment_container);
    container.removeAllViews();
    activity.getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.fragment_container, fragment, curFragmentTag)
            .commit();

    DrawerLayout drawer = activity.findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  public void initFirstFragment() {
    activity.getSupportFragmentManager()
            .beginTransaction()
            .add(R.id.fragment_container, new TodayFragment(), curFragmentTag)
            .commit();
  }

  public void refreshFragments() {
    FragmentManager fm          = activity.getSupportFragmentManager();
    Fragment        curFragment = fm.findFragmentByTag(curFragmentTag);
    if (curFragment != null) {
      String curFragmentName = curFragment.getClass().getSimpleName();
      Fragment newFragment = curFragmentName.equals(TodayFragment.class.getSimpleName()) ?
              new TodayFragment() :
              new WeekFragment();
      fm.beginTransaction()
              .replace(R.id.fragment_container, newFragment, curFragmentTag)
              .commit();
      return;
    }
  }
}
