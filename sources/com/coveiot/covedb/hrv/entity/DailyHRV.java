package com.coveiot.covedb.hrv.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
@Entity(primaryKeys = {"serial_no", "date"}, tableName = "daily_hrv")
/* loaded from: classes8.dex */
public class DailyHRV {
    @ColumnInfo(name = "baseline")
    public double baselinne;
    @ColumnInfo(name = "hrv_avg")
    public double hrv_avg;
    @ColumnInfo(name = "hrv_high")
    public double hrv_high;
    @ColumnInfo(name = "hrv_low")
    public double hrv_low;
    public boolean is_sync_server;
    @NonNull
    @ColumnInfo(name = "date")
    public String mDate;
    @NonNull
    @ColumnInfo(name = "serial_no")
    public String mac_address;
    @ColumnInfo(name = "readiness")
    public double readiness;
}
