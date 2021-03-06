package com.example.powerfailure.events;

import com.example.powerfailure.models.BatteryStatus;

/**
 * Created by Albert on 9/18/2016.
 */
public class PowerStatusEvent {
    public static final String POWER_DISCONNECTED = "POWER_DISCONNECTED";
    public static final String POWER_CONNECTED = "POWER_CONNECTED";

    private String eventType;
    private BatteryStatus status;

    public PowerStatusEvent(String type, BatteryStatus status) {
        this.eventType = type;
        this.status = status;
    }

    public BatteryStatus getBatteryStatus() {
        return status;
    }
}
