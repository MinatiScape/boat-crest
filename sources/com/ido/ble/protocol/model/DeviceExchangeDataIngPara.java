package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class DeviceExchangeDataIngPara implements Serializable {
    private static final long serialVersionUID = 1;
    public int day;
    public int distance;
    public int hour;
    public int minute;
    public int second;

    public String toString() {
        return "DeviceExchangeDataIngPara{day=" + this.day + ", hour=" + this.hour + ", minute=" + this.minute + ", second=" + this.second + ", distance=" + this.distance + '}';
    }
}
