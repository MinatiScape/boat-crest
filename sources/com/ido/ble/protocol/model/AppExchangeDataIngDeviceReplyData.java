package com.ido.ble.protocol.model;

import java.io.Serializable;
import java.util.Arrays;
/* loaded from: classes11.dex */
public class AppExchangeDataIngDeviceReplyData implements Serializable {
    public static final int STATUS_FAILED_DEVICE_ALREADY_IN_SPORT_MODE = 2;
    public static final int STATUS_SUCCESS = 1;
    private static final long serialVersionUID = 1;
    public int calories;
    public int cur_hr_value;
    public int distance;
    public int[] hr_value;
    public int hr_value_serial;
    public int interval_second;
    public int status;
    public int step;

    public String toString() {
        return "AppExchangeDataIngDeviceReplyData{status=" + this.status + ", step=" + this.step + ", calories=" + this.calories + ", distance=" + this.distance + ", cur_hr_value=" + this.cur_hr_value + ", interval_second=" + this.interval_second + ", hr_value_serial=" + this.hr_value_serial + ", hr_value=" + Arrays.toString(this.hr_value) + '}';
    }
}
