package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class DeviceUnreadReminder implements Serializable {
    public static final int STATUS_OFF = 85;
    public static final int STATUS_ON = 170;
    public int on_off;

    public String toString() {
        return "DeviceUnreadReminder{on_off=" + this.on_off + '}';
    }
}
