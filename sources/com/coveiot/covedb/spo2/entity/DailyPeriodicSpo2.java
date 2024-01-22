package com.coveiot.covedb.spo2.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
@Entity(primaryKeys = {"serial_no", "date"}, tableName = "daily_spo2")
/* loaded from: classes8.dex */
public class DailyPeriodicSpo2 {
    @ColumnInfo(name = "is_sync_server")
    public boolean is_sync_server;
    @NonNull
    @ColumnInfo(name = "date")
    public String mDate;
    @NonNull
    @ColumnInfo(name = "serial_no")
    public String mac_address;
    @ColumnInfo(name = "spo2_avg")
    public double spo2_avg;
    @ColumnInfo(name = "spo2_high")
    public int spo2_high;
    @ColumnInfo(name = "spo2_low")
    public int spo2_low;

    @NonNull
    public String getMac_address() {
        return this.mac_address;
    }

    public double getSpo2_avg() {
        return this.spo2_avg;
    }

    public int getSpo2_high() {
        return this.spo2_high;
    }

    public int getSpo2_low() {
        return this.spo2_low;
    }

    @NonNull
    public String getmDate() {
        return this.mDate;
    }

    public boolean isIs_sync_server() {
        return this.is_sync_server;
    }

    public void setIs_sync_server(boolean z) {
        this.is_sync_server = z;
    }

    public void setMac_address(@NonNull String str) {
        this.mac_address = str;
    }

    public void setSpo2_avg(double d) {
        this.spo2_avg = d;
    }

    public void setSpo2_high(int i) {
        this.spo2_high = i;
    }

    public void setSpo2_low(int i) {
        this.spo2_low = i;
    }

    public void setmDate(@NonNull String str) {
        this.mDate = str;
    }
}
