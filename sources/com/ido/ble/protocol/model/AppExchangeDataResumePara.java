package com.ido.ble.protocol.model;

import java.io.Serializable;
/* loaded from: classes11.dex */
public class AppExchangeDataResumePara implements Serializable {
    private static final long serialVersionUID = 1;
    public int day;
    public int hour;
    public int minute;
    public int second;

    public String toString() {
        return "AppExchangeDataResumePara{day=" + this.day + ", hour=" + this.hour + ", minute=" + this.minute + ", second=" + this.second + '}';
    }
}