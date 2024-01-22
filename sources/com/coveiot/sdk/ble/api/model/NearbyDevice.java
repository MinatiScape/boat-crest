package com.coveiot.sdk.ble.api.model;

import androidx.annotation.NonNull;
import com.coveiot.utils.utility.AppUtils;
import java.util.Date;
/* loaded from: classes9.dex */
public class NearbyDevice {
    private String deviceName;
    private int rssi;
    private long timestamp;
    private DeviceMatchType type;

    public String getDeviceName() {
        return this.deviceName;
    }

    public int getRssi() {
        return this.rssi;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public DeviceMatchType getType() {
        return this.type;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public void setRssi(int i) {
        this.rssi = i;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public void setType(DeviceMatchType deviceMatchType) {
        this.type = deviceMatchType;
    }

    @NonNull
    public String toString() {
        return "deviceName: " + this.deviceName + ", rssi:" + this.rssi + ", timestamp:" + this.timestamp + ", timestamp2:" + AppUtils.formatDate(new Date(this.timestamp), "yyyy-MM-dd HH:mm:ss") + ", type:" + this.type;
    }
}
