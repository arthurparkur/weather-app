package weatherapp.arthurdanilov92.gmail.com.weatherapp.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import weatherapp.arthurdanilov92.gmail.com.weatherapp.R;

public class HelpFragment extends Fragment {

  private Unbinder unbinder;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.help_frgt, container, false);
    unbinder = ButterKnife.bind(this, view);
    return view;
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  @OnClick(R.id.send_email_btn)
  public void sendEmail() {
    try {
      Intent intent = new Intent(Intent.ACTION_SEND);
      intent.setType("text/plain");
      intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.appMailAddress)});
      intent.putExtra(Intent.EXTRA_SUBJECT, R.string.help_text);
      Intent chooser = Intent.createChooser(intent, getString(R.string.send_mail_text));
      startActivity(chooser);
    } catch (Exception e) {
      Toast.makeText(getActivity().getApplicationContext(),
                     getString(R.string.err_send_mail),
                     Toast.LENGTH_LONG).show();
      Log.e(getString(R.string.err_send_mail), e.getMessage());
    }
  }

  @OnClick(R.id.make_call_btn)
  public void makeCall() {
    try {
      Intent callIntent = new Intent(Intent.ACTION_CALL);
      callIntent.setData(Uri.parse("tel:0800000000"));
      startActivity(callIntent);
    } catch (Exception e) {
      Toast.makeText(getActivity().getApplicationContext(),
                     getString(R.string.err_call),
                     Toast.LENGTH_LONG).show();
      Log.e(getString(R.string.err_call), e.getMessage());
    }
  }
}
