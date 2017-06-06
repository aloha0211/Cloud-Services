package com.example.powerfailure.models;

import android.content.Intent;
import android.os.BatteryManager;

/**
 * Created by Albert on 9/18/2016.
 */
public class BatteryStatus {
    private boolean isCharging;
    private int level;
    private int scale;

    public BatteryStatus(Intent intent) {
        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        isCharging = (status == BatteryManager.BATTERY_STATUS_CHARGING) ||
                     (status == BatteryManager.BATTERY_STATUS_FULL);

        level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
    }
}
