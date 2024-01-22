package com.ido.ble.protocol.model;

import java.util.List;
/* loaded from: classes11.dex */
public class V3AppExchangeDataHeartRate {
    public List<Integer> heart_rate_history;
    public int heart_rate_history_len;
    public int hour;
    public int interval_second;
    public int minute;
    public int second;

    public String toString() {
        return "V3AppExchangeDataHeartRate{hour=" + this.hour + ", minute=" + this.minute + ", second=" + this.second + ", heart_rate_history_len=" + this.heart_rate_history_len + ", interval_second=" + this.interval_second + ", heart_rate_history=" + this.heart_rate_history + '}';
    }
}
