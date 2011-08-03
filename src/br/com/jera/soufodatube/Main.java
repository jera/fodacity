package br.com.jera.soufodatube;

import android.os.Bundle;
import br.com.jeramobstats.JeraAgent;

import com.phonegap.DroidGap;

public class Main extends DroidGap {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.loadUrl("file:///android_asset/www/template.html");
		SplashDialog dialog = new SplashDialog(this);
		if (!dialog.isSplashed()) {
			dialog.show();
			dialog.setSplashed(true);
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		JeraAgent.onStartSession(this, "YI885C7NWJ5Y37T8ESMU");
	}

	@Override
	protected void onStop() {
		super.onStop();
		JeraAgent.onEndSession(this);
	}
}
