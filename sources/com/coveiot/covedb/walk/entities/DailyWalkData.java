package com.coveiot.covedb.walk.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
@Entity(primaryKeys = {"serial_no", "mDate"}, tableName = "dailywalkdata")
/* loaded from: classes8.dex */
public class DailyWalkData {
    @ColumnInfo(name = "steps")

    /* renamed from: a  reason: collision with root package name */
    public int f6993a;
    @ColumnInfo(name = "distance")
    public int b;
    @ColumnInfo(name = "calories")
    public double c;
    @ColumnInfo(name = "pace")
    public double d;
    @ColumnInfo(name = "steps_target")
    public int e;
    @ColumnInfo(name = "active_time")
    public Integer f = null;
    public boolean is_sync_server;
    @NonNull
    public String mDate;
    @NonNull
    @ColumnInfo(name = "serial_no")
    public String mac_address;

    public Integer getActiveTime() {
        return this.f;
    }

    public double getCalories() {
        return this.c;
    }

    @NonNull
    public String getMacAddress() {
        return this.mac_address;
    }

    public int getMeters() {
        return this.b;
    }

    public double getPace() {
        return this.d;
    }

    public int getStepsTarget() {
        return this.e;
    }

    public int getValue() {
        return this.f6993a;
    }

    @NonNull
    public String getmDate() {
        return this.mDate;
    }

    public boolean isSyncedToSevrer() {
        return this.is_sync_server;
    }

    public void setActiveTime(Integer num) {
        this.f = num;
    }

    public void setCalories(double d) {
        this.c = d;
    }

    public void setMacAddress(@NonNull String str) {
        this.mac_address = str;
    }

    public void setMeters(int i) {
        this.b = i;
    }

    public void setPace(double d) {
        this.d = d;
    }

    public void setStepsTarget(int i) {
        this.e = i;
    }

    public void setSyncedToSevrer(boolean z) {
        this.is_sync_server = z;
    }

    public void setValue(int i) {
        this.f6993a = i;
    }

    public void setmDate(@NonNull String str) {
        this.mDate = str;
    }
}
