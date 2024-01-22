package com.ido.ble.protocol.model;

import com.blankj.utilcode.constant.TimeConstants;
import java.io.Serializable;
import java.util.TimeZone;
/* loaded from: classes11.dex */
public class SystemTime implements Serializable {
    private static final long serialVersionUID = 1;
    public int day;
    public int hour;
    public int minute;
    public int monuth;
    public int second;
    public int time_zone = TimeZone.getDefault().getOffset(System.currentTimeMillis()) / TimeConstants.HOUR;
    public int week;
    public int year;

    public String toString() {
        return "SystemTime{year=" + this.year + ", monuth=" + this.monuth + ", day=" + this.day + ", hour=" + this.hour + ", minute=" + this.minute + ", second=" + this.second + ", week=" + this.week + ", time_zone=" + this.time_zone + '}';
    }
}
