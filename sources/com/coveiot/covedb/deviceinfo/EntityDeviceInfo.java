package com.coveiot.covedb.deviceinfo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.jstyle.blesdk1860.constant.DeviceKey;
@Entity(tableName = "device_info")
/* loaded from: classes8.dex */
public class EntityDeviceInfo {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = DeviceKey.MacAddress)

    /* renamed from: a  reason: collision with root package name */
    public String f6946a;
    @ColumnInfo(name = "is_active")
    public boolean b;
    @ColumnInfo(name = "last_sync_timestamp")
    public long c;
    @ColumnInfo(name = "last_sync_date_walk")
    public String d;
    @ColumnInfo(name = "last_sync_date_spo2")
    public String e;
    @ColumnInfo(name = "last_sync_date_raw_ppg")
    public String f;
    @ColumnInfo(name = "last_sync_date_accelerometer")
    public String g;
    @ColumnInfo(name = "last_sync_date_sedentary")
    public String h;
    @ColumnInfo(name = "last_sync_date_sleep")
    public String i;
    @ColumnInfo(name = "last_sync_date_hr")
    public String j;
    @ColumnInfo(name = "last_sync_date_bp")
    public String k;
    @ColumnInfo(name = "last_sync_date_temperature")
    public String l;
    @ColumnInfo(name = "last_sync_date_sens_ai_summary")
    public String m;
    @ColumnInfo(name = "last_sync_date_distance_data")
    public String n;
    @ColumnInfo(name = "last_sync_date_calorie_data")
    public String o;
    @ColumnInfo(name = "last_sync_date_rr")
    public String p;
    @ColumnInfo(name = "last_server_sync_steps")
    public String q;
    @ColumnInfo(name = "last_server_sync_sleep")
    public String r;
    @ColumnInfo(name = "last_sync_date_periodic_spo2")
    public String s;
    @ColumnInfo(name = "last_server_sync_date_periodic_spo2")
    public String t;
    @ColumnInfo(name = "last_sync_date_ambient_sound")
    public String u;

    public String getLastServerSyncDatePeriodicSpo2() {
        return this.t;
    }

    public String getLastServerSyncSleep() {
        return this.r;
    }

    public String getLastServerSyncSteps() {
        return this.q;
    }

    public String getLastSyncDateAccelerometer() {
        return this.g;
    }

    public String getLastSyncDateAmbientSound() {
        return this.u;
    }

    public String getLastSyncDateBp() {
        return this.k;
    }

    public String getLastSyncDateCalorieData() {
        return this.o;
    }

    public String getLastSyncDateDistanceData() {
        return this.n;
    }

    public String getLastSyncDateHr() {
        return this.j;
    }

    public String getLastSyncDatePeriodicSpo2() {
        return this.s;
    }

    public String getLastSyncDateRawPPG() {
        return this.f;
    }

    public String getLastSyncDateRr() {
        return this.p;
    }

    public String getLastSyncDateSedentary() {
        return this.h;
    }

    public String getLastSyncDateSensAISummary() {
        return this.m;
    }

    public String getLastSyncDateSleep() {
        return this.i;
    }

    public String getLastSyncDateSpo2() {
        return this.e;
    }

    public String getLastSyncDateTemperature() {
        return this.l;
    }

    public String getLastSyncDateWalk() {
        return this.d;
    }

    public long getLasySyncTime() {
        return this.c;
    }

    @NonNull
    public String getMacAddress() {
        return this.f6946a;
    }

    public boolean isActive() {
        return this.b;
    }

    public void setActive(boolean z) {
        this.b = z;
    }

    public void setLastServerSyncDatePeriodicSpo2(String str) {
        this.t = str;
    }

    public void setLastServerSyncSleep(String str) {
        this.r = str;
    }

    public void setLastServerSyncSteps(String str) {
        this.q = str;
    }

    public void setLastSyncDateAccelerometer(String str) {
        this.g = str;
    }

    public void setLastSyncDateAmbientSound(String str) {
        this.u = str;
    }

    public void setLastSyncDateBp(String str) {
        this.k = str;
    }

    public void setLastSyncDateCalorieData(String str) {
        this.o = str;
    }

    public void setLastSyncDateDistanceData(String str) {
        this.n = str;
    }

    public void setLastSyncDateHr(String str) {
        this.j = str;
    }

    public void setLastSyncDatePeriodicSpo2(String str) {
        this.s = str;
    }

    public void setLastSyncDateRawPPG(String str) {
        this.f = str;
    }

    public void setLastSyncDateRr(String str) {
        this.p = str;
    }

    public void setLastSyncDateSedentary(String str) {
        this.h = str;
    }

    public void setLastSyncDateSensAISummary(String str) {
        this.m = str;
    }

    public void setLastSyncDateSleep(String str) {
        this.i = str;
    }

    public void setLastSyncDateSpo2(String str) {
        this.e = str;
    }

    public void setLastSyncDateTemperature(String str) {
        this.l = str;
    }

    public void setLastSyncDateWalk(String str) {
        this.d = str;
    }

    public void setLasySyncTime(long j) {
        this.c = j;
    }

    public void setMacAddress(@NonNull String str) {
        this.f6946a = str;
    }
}
