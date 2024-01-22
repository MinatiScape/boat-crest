package com.coveiot.covedb.temperature.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
@Entity(primaryKeys = {"serial_no", "date"}, tableName = "daily_temperature")
/* loaded from: classes8.dex */
public class DailyTemperature {
    public boolean is_sync_server;
    @NonNull
    @ColumnInfo(name = "date")
    public String mDate;
    @NonNull
    @ColumnInfo(name = "serial_no")
    public String mac_address;
    @ColumnInfo(name = "temperature_avg")
    public double temperature_avg;
    @ColumnInfo(name = "temperature_high")
    public double temperature_high;
    @ColumnInfo(name = "temperature_low")
    public double temperature_low;
}
