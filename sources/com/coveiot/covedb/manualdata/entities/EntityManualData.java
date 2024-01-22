package com.coveiot.covedb.manualdata.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import com.jstyle.blesdk1860.constant.DeviceKey;
@Entity(primaryKeys = {"timeStamp", "isLevelInterpretation"}, tableName = "manual_data")
/* loaded from: classes8.dex */
public class EntityManualData {
    @NonNull
    @ColumnInfo(name = "timeStamp")

    /* renamed from: a  reason: collision with root package name */
    public long f6959a;
    @NonNull
    @ColumnInfo(name = "source")
    public String b;
    @ColumnInfo(name = "serial_no")
    public String c;
    @ColumnInfo(name = "userDeviceId")
    public String d;
    @ColumnInfo(name = "hr")
    public int e;
    @ColumnInfo(name = "systolicBp")
    public int f;
    @ColumnInfo(name = "diastolicBp")
    public int g;
    @ColumnInfo(name = "spo2")
    public double h;
    @ColumnInfo(name = DeviceKey.TempData)
    public double i;
    @ColumnInfo(name = "isSyncedWithServer")
    public boolean j;
    @ColumnInfo(name = "isLevelInterpretation")
    public boolean k;
    @ColumnInfo(name = "spo2Level")
    public String l;
    @ColumnInfo(name = DeviceKey.HRV)
    public int m;
    @ColumnInfo(name = DeviceKey.Stress)
    public int n;
    @ColumnInfo(name = DeviceKey.VascularAging)
    public int o;

    public EntityManualData(long j, String str) {
        this.f6959a = j;
        this.b = str;
    }

    public int getDiastolicBp() {
        return this.g;
    }

    public int getHr() {
        return this.e;
    }

    public int getHrv() {
        return this.m;
    }

    public String getSerialNo() {
        return this.c;
    }

    public String getSource() {
        return this.b;
    }

    public double getSpo2() {
        return this.h;
    }

    public String getSpo2Level() {
        return this.l;
    }

    public int getStress() {
        return this.n;
    }

    public int getSystolicBp() {
        return this.f;
    }

    public double getTemperature() {
        return this.i;
    }

    public long getTimeStamp() {
        return this.f6959a;
    }

    public String getUserDeviceId() {
        return this.d;
    }

    public int getVascularAging() {
        return this.o;
    }

    public boolean isLevelInterpretation() {
        return this.k;
    }

    public boolean isSyncedWithServer() {
        return this.j;
    }

    public void setDiastolicBp(int i) {
        this.g = i;
    }

    public void setHr(int i) {
        this.e = i;
    }

    public void setHrv(int i) {
        this.m = i;
    }

    public void setLevelInterpretation(boolean z) {
        this.k = z;
    }

    public void setSerialNo(String str) {
        this.c = str;
    }

    public void setSpo2(double d) {
        this.h = d;
    }

    public void setSpo2Level(String str) {
        this.l = str;
    }

    public void setStress(int i) {
        this.n = i;
    }

    public void setSyncedWithServer(boolean z) {
        this.j = z;
    }

    public void setSystolicBp(int i) {
        this.f = i;
    }

    public void setTemperature(double d) {
        this.i = d;
    }

    public void setTimeStamp(long j) {
        this.f6959a = j;
    }

    public void setUserDeviceId(String str) {
        this.d = str;
    }

    public void setVascularAging(int i) {
        this.o = i;
    }
}
