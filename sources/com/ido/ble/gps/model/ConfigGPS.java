package com.ido.ble.gps.model;

import java.util.Calendar;
import java.util.TimeZone;
/* loaded from: classes11.dex */
public class ConfigGPS {
    public int cycleMS;
    public int gnsValue;
    public int operationMode;
    public int startMode;
    private int utcDay;
    private int utcHour;
    private int utcMinute;
    private int utcMonth;
    private int utcSecond;
    private int utcYear;

    public ConfigGPS() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        this.utcYear = calendar.get(1);
        this.utcMonth = calendar.get(2) + 1;
        this.utcDay = calendar.get(5);
        this.utcHour = calendar.get(11);
        this.utcMinute = calendar.get(12);
        this.utcSecond = calendar.get(13);
    }

    public String toString() {
        return "ConfigGPS{utcYear=" + this.utcYear + ", utcMonth=" + this.utcMonth + ", utcDay=" + this.utcDay + ", utcHour=" + this.utcHour + ", utcMinute=" + this.utcMinute + ", utcSecond=" + this.utcSecond + ", startMode=" + this.startMode + ", operationMode=" + this.operationMode + ", cycleMS=" + this.cycleMS + ", gnsValue=" + this.gnsValue + '}';
    }
}
