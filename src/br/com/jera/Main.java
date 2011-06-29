package br.com.jera;

import android.content.Context;
import android.os.Bundle;
import br.com.jeramobstats.JeraAgent;

import com.phonegap.DroidGap;
import com.xtify.android.sdk.PersistentLocationManager;

public class Main extends DroidGap {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.loadUrl("file:///android_asset/www/template.html");

        // xtify specific-code
        Context context = this;
        final PersistentLocationManager persistentLocationManager = new PersistentLocationManager(context);
        Thread xtifyThread = new Thread(new Runnable() {
            @Override
            public void run()
            {
                persistentLocationManager.setNotificationIcon(R.drawable.notification);
                persistentLocationManager.setNotificationDetailsIcon(R.drawable.icon);
                boolean trackLocation = persistentLocationManager.isTrackingLocation();
                boolean deliverNotifications = persistentLocationManager.isDeliveringNotifications();
                if (trackLocation || deliverNotifications)
                {
                    persistentLocationManager.startService();
                }
            }
        });
        xtifyThread.start(); // to avoid Android's application-not-responding dialog box,
                             // do non-essential work in another thread
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        JeraAgent.onStartSession(this, "YI885C7NWJ5Y37T8ESMU");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        JeraAgent.onEndSession(this);
    }
}
