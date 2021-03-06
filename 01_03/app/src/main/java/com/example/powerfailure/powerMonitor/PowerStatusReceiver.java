package com.example.powerfailure.powerMonitor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.example.powerfailure.PowerFailureApp;
import com.example.powerfailure.events.PowerStatusEvent;
import com.example.powerfailure.models.BatteryStatus;

/**
 * Created by Albert on 9/18/2016.
 */
public class PowerStatusReceiver {
    private BroadcastReceiver powerDisconnectedReceiver = null;
    private BroadcastReceiver powerConnectedReceiver = null;
    private Context context;


    public PowerStatusReceiver() {
        super();
        this.context = PowerFailureApp.getInstance();
        registerPowerDisconnectedReceiver(context);
        registerPowerConnectedReceiver(context);
    }

    public void unregister() {
        unregister(powerConnectedReceiver);
        powerConnectedReceiver = null;
        unregister(powerDisconnectedReceiver);
        powerDisconnectedReceiver = null;
    }
    private void unregister(BroadcastReceiver receiver) {
        if (receiver != null) {
            context.unregisterReceiver(receiver);
        }
    }

    private void registerPowerConnectedReceiver(Context context) {
        if (context == null || powerConnectedReceiver != null) return;

        powerConnectedReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                PowerFailureApp.getInstance()
                               .post(new PowerStatusEvent(PowerStatusEvent.POWER_CONNECTED,
                                                          new BatteryStatus(intent)));
            }
        };
        IntentFilter intentPowerFilter = new IntentFilter ();
        intentPowerFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        context.registerReceiver(powerDisconnectedReceiver, intentPowerFilter);
    }

    private void registerPowerDisconnectedReceiver(Context context) {
        if (context == null || powerDisconnectedReceiver != null) return;

        powerDisconnectedReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // send out notification of power failure
                PowerFailureApp.getInstance()
                               .post(new PowerStatusEvent(PowerStatusEvent.POWER_DISCONNECTED,
                                                          new BatteryStatus(intent)));
            }
        };

        IntentFilter intentPowerFilter = new IntentFilter ();
        intentPowerFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        context.registerReceiver(powerDisconnectedReceiver, intentPowerFilter);
    }
}
